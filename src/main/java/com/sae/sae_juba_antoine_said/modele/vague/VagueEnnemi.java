package com.sae.sae_juba_antoine_said.modele.vague;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static java.lang.Thread.sleep;

public class VagueEnnemi {

    Environnement env;
    private IntegerProperty nbVague;
    private IntegerProperty nbVagueBinding;// cette variable pour bindé avec nb de vague dans controlleur
    private TypeVague typeVague;
    int temp;

    private IntegerProperty nbMort;

    public VagueEnnemi(Environnement env,TypeVague typeVague) {
        this.env = env;
        this.nbVague = new SimpleIntegerProperty(0);
        this.nbVagueBinding = new SimpleIntegerProperty(0);
        this.nbMort= new SimpleIntegerProperty(0);
        this.typeVague = typeVague;
        temp = 0;

    }

    public void creeVague() {
        try {

            typeVague.ajouteEnnemis(env);

        }catch (Exception ex) {
            System.out.println("Erreur 404");
        }

    }

    public IntegerProperty nbVagueProperty() {
        return nbVague;
    }

    public int getNbVague() {
        return nbVague.get();
    }

    public void setNbVague(int value){
        nbVague.set(value);
    }

    public IntegerProperty nbVagueBindingProperty() {
        return nbVagueBinding;
    }

    public int getNbVagueBinding() {
        return nbVagueBinding.get();
    }

    public void setNbVagueBinding(int value){
        nbVagueBinding.set(getNbVague() + value);
    }
}




