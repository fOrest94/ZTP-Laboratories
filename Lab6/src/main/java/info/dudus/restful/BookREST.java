package info.dudus.restful;

import info.dudus.BooksRemote;
import info.dudus.model.Book;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@Path("books")
@LocalBean
@Stateless
public class Books implements BooksRemote, Serializable {

    @PersistenceContext(unitName = "ZTP_06")
    private EntityManager entityManager;

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public Book add(@FormParam("title")    String title,
                    @FormParam("year")     String year,
                    @FormParam("edition")  String edition,
                    @FormParam("authorid") String authorkId) {
        try {
            System.out.println(" => " + authorkId);
            Book book = new Book(title, Integer.parseInt(authorkId));

            if (year != null)    book.setReleaseYear(year);
            if (edition != null) book.setEditionNumber(edition);

            entityManager.persist(book);
            return book;
        } catch (Error e) {
            return null;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Book find(@PathParam("id") int id) {
        return entityManager.find(Book.class, id);
    }

    public List findAll() {
        return null;
    }

   /* @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }*/

    public Book update(int id, String title, Integer year, Integer edition) {
        return null;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Book update(@PathParam("id")      int id,
                       @FormParam("title")   String title,
                       @FormParam("year")    String year,
                       @FormParam("edition") String edition) {

        Book book = find(id);
        if (title   != null) book.setTitle(title);
        if (year    != null) book.setReleaseYear(year);
        if (edition != null) book.setEditionNumber(edition);

        entityManager.merge(book);
        return book;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Book remove(@PathParam("id") int id) {
        Book book = find(id);
        entityManager.remove(book);
        return book;
    }

    @POST
    @Path("/{id}/amount")
    @Produces(MediaType.APPLICATION_XML)
    public Book addAmount(@PathParam("id") int id) {
        Book book = find(id);
        book.increaseAmount(1);
        entityManager.merge(book);
        return book;
    }

    @DELETE
    @Path("/{id}/amount")
    @Produces(MediaType.APPLICATION_XML)
    public Book removeAmount(@PathParam("id") int id) {
        Book book = find(id);
        book.decreaseAmount(1);
        entityManager.merge(book);
        return book;
    }
}