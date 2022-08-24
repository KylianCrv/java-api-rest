package fr.m2i.javaapirest.annuaire;

import java.util.List;

class Annuaire {

    private List<Personne> personnes;

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public void create(Personne personne) {
        personnes.add(personne);
    }

}
