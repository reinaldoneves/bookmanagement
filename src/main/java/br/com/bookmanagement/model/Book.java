package br.com.bookmanagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Long id;

    /**
     * The international standard book number unique identifier
     */
    @Indexed(unique=true)
    private String ISBN;

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
