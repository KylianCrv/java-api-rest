package fr.m2i.javaapirest.tpuser.filter;

import fr.m2i.javaapirest.tpuser.model.User;
import fr.m2i.javaapirest.tpuser.util.BasicAuth;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //On récupère les identifiants /mdp depuis le header
        String auth = requestContext.getHeaderString("Authorization");

        if (auth == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build());
        }

        //lap = login / password
        String[] lap = BasicAuth.decode(auth);

        if (lap == null || lap.length != 2) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build());
        }

        //Vérifie les id envoyés
        User authentified = checkUser(lap[0], lap[1]);

        //null check
        if (authentified == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected")
                            .build());
        }
    }

    public User checkUser(String email, String password) {
        User admin = new User("Super", "admin", "SUPER_ADMIN", "super@admin.com", "admin");

        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
