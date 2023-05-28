package com.sae.sae_juba_antoine_said.Modele;

public class TourAProjectile extends Tour{

    private Projectile projectile;

    public TourAProjectile(int x, int y, int degats, int range,Projectile projectile ) {
        super(x, y, degats, range);
        this.projectile = projectile;


    }

    @Override
    public void attaqueEnnemi() {


    }

    public void lancerProjectile(Acteur a){
        this.projectile.deplacerVers(a);

    }





}
