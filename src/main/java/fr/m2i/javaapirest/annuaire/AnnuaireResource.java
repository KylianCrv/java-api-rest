package fr.m2i.javaapirest.annuaire;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personnes")
public class AnnuaireResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Personne createPersonne(Personne personne, @Context HttpServletRequest request) {
        System.out.println("createPersonne");

        //Récuperer l'annuaire stocké dans la session
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");

        //Dans le cas où mon annuaire est null, je l'instancie
        if (annuaire == null) {
            annuaire = new Annuaire();
        }

        //Ajout de la personne créée
        Personne created = annuaire.create(personne);

        //Créer, met a jour mon annuaire en session
        request.getSession().setAttribute("annuaire", annuaire);

        return created;
    }

    //URI :
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getPersonnes(@Context HttpServletRequest request) {
        System.out.println("getPersonnes");

        //Récuperer l'annuaire stocké dans la session
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");

        if (annuaire == null) {
            return new ArrayList();
        }

        return annuaire.getPersonnes();
    }

    @PUT
    @Path("/update-{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Personne updatePersonne(Personne personne, @PathParam("id") Long id, @Context HttpServletRequest request) {
        System.out.println("updatePersonne");

        //Récuperer l'annuaire stocké dans la session
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");

        //Dans le cas où mon annuaire est null, je l'instancie
        if (annuaire == null) {
            annuaire = new Annuaire();
        }

        //Modification de la personne
        Personne updated = annuaire.update(id, personne);

        //Créer, met a jour mon annuaire en session
        request.getSession().setAttribute("annuaire", annuaire);

        return updated;
    }

    @DELETE
    @Path("/delete-{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Personne deletePersonne(Personne personne, @PathParam("id") Long id, @Context HttpServletRequest request) {
        System.out.println("deletePersonne");

        //Récuperer l'annuaire stocké dans la session
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");

        //Dans le cas où mon annuaire est null, je l'instancie
        if (annuaire == null) {
            annuaire = new Annuaire();
        }

        //Modification de la personne
        Personne deleted = annuaire.delete(id, personne);

        //Créer, met a jour mon annuaire en session
        request.getSession().setAttribute("annuaire", annuaire);

        return deleted;
    }
}
