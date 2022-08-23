package fr.m2i.javaapirest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/books")
public class BookResource {

    //URI : /books/
    @GET
    public String getBooks() {
        System.out.println("Endpoint : getBooks");
        return "JAVA 8 docs / Angular pour les nuls / Les voyages les plus fous";
    }

    //URI : /books/borrowed
    @GET
    @Path("/borrowed")
    public String getBorrowedBooks() {
        System.out.println("Endpoint : getBooks");
        return "Angular pour les nuls / Les voyages les plus fous";
    }

    //URI : /books/1
    @GET
    @Path("{id}")
    public String getBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getBookById");
        return "Livre avec id : " + id + " - Java 8 docs ";
    }

    //URI : /books/name-angular-editor-google
    @GET
    @Path("/name-{name}-editor-{editor}")
    public String getBookByNameAndEditor(@PathParam("name") String name, @PathParam("editor") String editor) {
        System.out.println("Endpoint : getBookByNameAndEditor");
        return "Livre avec le nom : " + name + ", l'éditeur : " + editor + " - Angular pour les nuls";
    }

    // URI : /books/queryparameters?name=harry&isbn=1-111111-11&isExtended=true
    @GET
    @Path("/query-parameters")
    public String getQueryParametersBook(@DefaultValue("default") @QueryParam("name") String name,
            @DefaultValue("?-??????-??") @QueryParam("isbn") String isbn,
            @DefaultValue("false") @QueryParam("isExtended") boolean isExtended) {

        return "Livre recherché - nom : " + name + ", isbn : " + isbn + ", version étendue : " + isExtended;
    }

    @POST
    @Path("/create-from-form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createFromForm(@FormParam("name") String name) {
        System.out.println("Endpoint : create from form");
        return "Le nom du livre créé : " + name;
    }

    // URI : /books/header-parameters
    @GET
    @Path("/header-parameters")
    public String getHeaderParametersBook(@DefaultValue("default") @HeaderParam("name") String name,
            @DefaultValue("?-??????-??") @HeaderParam("isbn") String isbn,
            @DefaultValue("false") @HeaderParam("isExtended") boolean isExtended) {

        System.out.println("Endpoint : getHeaderParametersBook");
        return "Livre recherché via headers - nom : " + name + ", isbn : " + isbn + ", version étendue : " + isExtended;
    }

    // URI : /context-uri-info
    @GET
    @Path("/context-uri-info/{name}")
    public String geContextUriInfo(@Context UriInfo uriInfo, @PathParam("name") String name,
            @QueryParam("queryParam") String queryParam) {
        System.out.println("Endpoint : geContextUriInfo");

        StringBuilder result = new StringBuilder();
        result.append("getPath(): ")
                .append(uriInfo.getPath())
                .append("\n")
                .append("getPathSegments(): ");

        for (PathSegment p : uriInfo.getPathSegments()) {
            result.append(p.getPath()).append(" ");
        }

        result.append("\n")
                .append("getPathParameters(): ");

        for (String p : uriInfo.getPathParameters().keySet()) {
            result.append(p).append(" ");
        }

        result.append("\n")
                .append("getQueryParameters(): ");

        for (String p : uriInfo.getQueryParameters().keySet()) {
            result.append(p).append(" ");
        }

        result.append("\n")
                .append("getAbsolutePath(): ").append(uriInfo.getAbsolutePath()).append("\n")
                .append("getBaseURI(): ").append(uriInfo.getBaseUri()).append("\n")
                .append("getRequestURI(): ").append(uriInfo.getRequestUri()).append("\n");

        return result.toString();
    }

    // URI : /context-headers
    @GET
    @Path("/context-headers")
    public String geContextHeaders(@Context HttpHeaders headers) {
        System.out.println("Endpoint : geContextHeaders");

        StringBuilder result = new StringBuilder();
        result.append("Cookies: ");

        for (String key : headers.getCookies().keySet()) {
            result.append(key).append("\n");
        }

        result.append("RequestHeaders: ");

        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();

        for (String key : requestHeaders.keySet()) {
            result.append(key).append(" : ").append(requestHeaders.get(key)).append("\n");
        }

        return result.toString();
    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDetailsTextBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetaisById");

        return "le détail du livre numéro : " + id + " - il s'agit d'une autobiographie";

    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_XML)
    public String getDetailsXMLBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsXMLById");

        return "<?xml version=\"1.0\"?>"
                + "<details>"
                + "le détail du livre numéro : " + id + " - il s'agit d'une autobiographie";

    }

    @GET
    @Path("/details/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String getDetailsHTMLBookById(@PathParam("id") int id) {
        System.out.println("Endpoint : getDetailsHTMLById");

        return "<html>\n"
                + "    <head>"
                + "        <title>Le détail de mon livre</title>"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                + "    </head>"
                + "    <body>"
                + "        <h1>le détail du livre numéro :" + id + " - il s'agit d'une autobiographie</h1>"
                + "    </body>"
                + "</html>";

    }

}
