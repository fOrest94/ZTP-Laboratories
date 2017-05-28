package info.dudus;

import info.dudus.model.Book;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BooksRemote {

    public Book add(String title, String year, String edition, String authorkId);

    public Book find(int id);

    public List findAll();

    public Book update(int id, String title, Integer year, Integer edition);

    public Book remove(int id);

    public Book addAmount(int id);

    public Book removeAmount(int id);
}