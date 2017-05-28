package info.dudus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by f0rest94 on 2017-05-23.
 */

@Entity
@XmlRootElement
public class Book implements Serializable {
    private String title;
    private String releaseYear;
    private String editionNumber;

    public Book() { }

    public Book(String title, int authorId) {
        this.title  = title;
        this.releaseYear = 0;
        this.editionNumber = authorId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "release_year")
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name = "edition_number")
    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }
}
