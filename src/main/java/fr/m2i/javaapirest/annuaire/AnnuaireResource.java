package fr.m2i.javaapirest.annuaire;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/annuaire")
public class AnnuaireResource {

//    //URI :
//    @GET
//    public Response getAnnuaire(@Context HttpServletRequest request) {
//        request.getSession().getAttribute("annuaire");
//
//        return Response.status(Response.Status.OK).entity(annuaire.getPersonne()).build();
//    }
    @POST
    @Path("/create")
    public void create(Personne personne, @Context HttpServletRequest request) {
        request.getSession().getAttribute("annuaire");
        if (request.getSession().getAttribute("annuaire") == null) {
            Annuaire personnes = new Annuaire();
            personnes.create(personne);
            request.getSession().setAttribute("annuaire", personnes);
        }

    }
}
