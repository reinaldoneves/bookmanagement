package br.com.bookmanagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Class responsible for <i>book</i>
 * @author reinaldo_neves@hotmail.com
 * */

@Data
@Document
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
     * The book details and description
     **/
    private String details;

    /**
     * The status indicating if the book is aviable
     * */
    private boolean isAvailable;

}
