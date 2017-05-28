package info.dudus.restful;

import info.dudus.model.Author;
import info.dudus.model.Book;
import info.dudus.remote.AuthorsRemote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by f0rest94 on 2017-05-24.
 */
@Path("authors")
@LocalBean
@Stateless
public class Authors implements AuthorsRemote, Serializable {

    @PersistenceContext(unitName = "ZTP_06")
    private EntityManager entityManager;

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Author add(@FormParam("firstname") String firstname,
                      @FormParam("lastname")  String lastname) {

        Author author = new Author(firstname, lastname);
        entityManager.persist(author);
        return author;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Author find(@PathParam("id") int id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findAll() {
        return null;
    }

    public List<Book> findBooks(int id) {
        return null;
    }

    /*@GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @GET
    @Path("/{id}/books")
    @Produces(MediaType.APPLICATION_XML)
    public List<Book> findBooks(@PathParam("id") int id) {

        return entityManager.createQuery("SELECT b FROM Book b WHERE b.authorId =" + id, Book.class).getResultList();
    }*/

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Author update(@PathParam("id")        int id,
                         @FormParam("firstname") String firstname,
                         @FormParam("lastname")  String lastname) {

        Author author = find(id);
        if (firstname != null) author.setFirstname(firstname);
        if (lastname  != null) author.setLastname(lastname);

        entityManager.merge(author);

        return author;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Author remove(@PathParam("id") int id) {
        Author author = find(id);
        entityManager.remove(author);
        return author;
    }
}