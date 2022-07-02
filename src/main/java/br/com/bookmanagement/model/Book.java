package br.com.bookmanagement.model;

import lombok.Builder;
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
@Builder
public class Book implements Serializable {

    /**
     * The book identifier
     * We'll let the id and the ISBN in case of books with multiple editions or
     * other operation that may be necessary an extra identifier
     **/
    @Id
    private String id;

    /**
     * The international standard book number unique identifier
     */
    @Indexed(unique=true)
    private String isbn;

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
