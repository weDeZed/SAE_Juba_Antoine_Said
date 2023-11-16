package com.sae.sae_juba_antoine_said.modele.vague;

import com.sae.sae_juba_antoine_said.modele.environnement.Environnement;

import java.util.ArrayList;

public class CompositeVague implements TypeVague{
    private  ArrayList<TypeVague> lesVagues;
    private static int compteurVague = 0;

    public CompositeVague() {
        this.lesVagues = new ArrayList<>();
        initVague();
    }
    public void initVague(){
        lesVagues.add(new TypeVagueBandit());
        lesVagues.add(new TypeVagueAleatoire());
        lesVagues.add(new TypeVagueDragon());
    }

    @Override
    public void ajouteEnnemis(Environnement environment) {
        if (compteurVague%3==0){
           lesVagues.get(0).ajouteEnnemis(environment);
        }else if(compteurVague%3==1){
            lesVagues.get(1).ajouteEnnemis(environment);
        }else {
            lesVagues.get(2).ajouteEnnemis(environment);
        }
        compteurVague++;

    }
}
