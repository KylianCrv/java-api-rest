package fr.m2i.javaapirest.tpuser.resource;

import fr.m2i.javaapirest.tpuser.dao.UserDAO;
import fr.m2i.javaapirest.tpuser.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserResource {

    UserDAO userDAO = new UserDAO();

//URI : /users
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@Context HttpServletRequest request) {
        System.out.println("getUsers");

        return Response.status(Response.Status.OK).entity(userDAO.findAllUsers()).build();
    }

//URI : /users/id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id, @Context HttpServletRequest request) {
        System.out.println("getUserById");

        User founded = userDAO.findUserById(id);

        if (founded == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error has occured").build();
        }

        return Response.status(Response.Status.OK).entity(founded).build();
    }

//URI : /users/id
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUserByEmailOrLastname(@QueryParam("q") String q, @QueryParam("count") int count, @Context HttpServletRequest request) {
        System.out.println("searchUserByEmailOrLastname");

        List<User> searched = userDAO.findUserByEmailOrLastname(q, count);

        if (searched == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error occured").build();

        }
        return Response.status(Response.Status.OK).entity(searched).build();

    }

//URI : /users
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user, @Context HttpServletRequest request) {
        System.out.println("createUser");

        boolean success = userDAO.create(user);

        if (!success) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error as occured").build();

        }

        return Response.status(Response.Status.CREATED).entity("User succesfully created").build();
    }

//URI : /users/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User userData, @PathParam("id") int id, @Context HttpServletRequest request) {
        System.out.println("updateUser");

//        if (userData.getId() != id) {
//            return Response.status(Response.Status.NOT_FOUND).entity("User was not found").build();
//        }
        boolean success = userDAO.update(id, userData);

        if (!success) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error as occured").build();
        }

        return Response.status(Response.Status.OK).entity("User succesfully modified").build();

    }

//URI : /users/{id}
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id, @Context HttpServletRequest request) {
        System.out.println("deleteUser");

        boolean success = userDAO.delete(id);

        if (!success) {
            return Response.status(Response.Status.BAD_REQUEST).entity("An error as occured").build();
        }

        return Response.status(Response.Status.OK).entity("User succesfully deleted").build();

    }

}
