package fr.m2i.javaapirest.annuaire;

import java.util.ArrayList;
import java.util.List;

class Annuaire {

    private List<Personne> personnes;
    private Long nextId;

    public Annuaire() {
        this.personnes = new ArrayList();
        this.nextId = 1L;
    }

    public Personne create(Personne personne) {
        personne.setId(nextId);
        personnes.add(personne);

        nextId++;

        return personne;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
}
