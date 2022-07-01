package br.com.bookmanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Class responsible for <i>book</i>
 * @author reinaldo_neves@hotmail.com
 * */
@Entity(name = "Book")
@Data
public class Book implements Serializable {

    /**
     * The book identifier
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The title of the book
     **/
    private String title;
    /**
     * The author of the book
     **/
    private String author;

    /**
     * The status indicating if the book is aviable
     * */
    private boolean isAvailable;

}
