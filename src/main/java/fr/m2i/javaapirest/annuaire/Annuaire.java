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

    public Personne update(Long id, Personne personne) {
        for (int i = 0; i < personnes.size(); i++) {
            Personne personneToUpdate = personnes.get(i);

            if (personneToUpdate.getId() == id) {
                personnes.remove(personneToUpdate);
                personne.setId(id);
                personnes.add(personne);

                return personne;
            }
        }
        return null;
    }

    public Personne delete(Long id, Personne personne) {
        for (int i = 0; i < personnes.size(); i++) {
            Personne personneToDelete = personnes.get(i);

            if (personneToDelete.getId() == id) {
                personnes.remove(personneToDelete);

                return personne;
            }
        }
        return null;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
}
