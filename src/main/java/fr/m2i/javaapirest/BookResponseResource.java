package fr.m2i.javaapirest;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/response-books")
public class BookResponseResource {

    //URI : /ok/without-response
    @GET
    @Path("/ok/without-response")
    public String getBookWithoutResponse() {
        System.out.println("Endpoint : getBookWithoutResponse");

        return "Java 8 doc";

    }

//URI : /ok
    @GET
    @Path("/ok")
    public Response getBook() {
        System.out.println("Endpoint : getBook");

        return Response.status(Response.Status.OK).entity("Java 8 doc").build();

    }

//URI : /ok/headers
    @GET
    @Path("/ok/headers")
    public Response getBookWithHeaders() {
        System.out.println("Endpoint : getBookWithHeaders");

        return Response.status(Response.Status.OK).entity("Java 8 doc").header("test", "test").build();

    }

//URI : /ok/json-produces
    @GET
    @Path("/ok/json-produces")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookJSONProduces() {
        System.out.println("Endpoint : getBookJSONProduces");

        Book book = new Book();
        book.setName("GOT");
        book.setIsbn("1-77777777-1");

        return book;
    }

//URI : /ok/json
    @GET
    @Path("/ok/json")
    public Response getBookJSON() {
        System.out.println("Endpoint : getBookJSON");

        Book book = new Book();
        book.setName("GOT");
        book.setIsbn("1-77777777-1");

        return Response.status(Response.Status.OK).entity(book).type(MediaType.APPLICATION_JSON).build();
    }

//URI :/error/webapplicationexception
    @GET
    @Path("/error/webapplicationexception")
    public String getBookWithWebApplicationException(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookWithWebApplicationException");

        if (id == null) {
            throw new BadRequestException();
        }
        return "Java for life " + id;
    }

//URI :/error
    @GET
    @Path("/error")
    public Response getBookWithError(@QueryParam("id") Integer id) {
        System.out.println("Endpoint : getBookWithError");
        if (null == id) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity("Java for life " + id).build();
    }

}
