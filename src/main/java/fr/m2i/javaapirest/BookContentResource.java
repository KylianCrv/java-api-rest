package fr.m2i.javaapirest;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/content-books")
public class BookContentResource {

    //URI : /content-books/xml
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Book getContentBookXML() {
        System.out.println("Endpoint : getContentBookXML");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setIsbn("1-1111111-1");

        return book;

    }

    @PUT
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public void updateContentBookXML(Book book) {
        System.out.println("Endpoint : updateContentBookXML");
        System.out.println("Les modifications apportées :  name - " + book.getName() + ", isbn - " + book.getIsbn());

    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getContentBookJSON() {
        System.out.println("Endpoint : getContentBookJSON");
        Book book = new Book();
        book.setName("JSON Potter");
        book.setIsbn("1-999999-1");

        return book;
    }

    @PUT
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateContentBookJSON(Book book) {
        System.out.println("Endpoint : updateContentBookJSON");
        System.out.println("Les modifications apportées :  name - " + book.getName() + ", isbn - " + book.getIsbn());

    }

    @GET
    @Path("/jsonxml")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getContentBookJSONAndXML() {
        System.out.println("Endpoint : getContentBookJSONAndXML");
        Book book = new Book();
        book.setName("JSON or XML Potter");
        book.setIsbn("1-6666666-1");

        return book;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllContentBookJSON() {
        System.out.println("Endpoint : getAllContentBookJSON");

        Book book = new Book();
        book.setName("JSON Potter");
        book.setIsbn("1-6666666-1");

        Book book2 = new Book();
        book2.setName("Harry Potter");
        book2.setIsbn("1-1111111-1");

        Book book3 = new Book();
        book3.setName("GOT");
        book3.setIsbn("1-77777777-1");

        return Arrays.asList(book, book2, book3);
    }

}
