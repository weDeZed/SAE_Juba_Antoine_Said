package com.sae.sae_juba_antoine_said.Vue;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.sae.sae_juba_antoine_said.Modele.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Vue {

    private Environnement env;
    private TilePane Tilep;
    private Image imgTilep;
    private ImageView imgV;


    public Vue(Environnement env, TilePane TileP) throws FileNotFoundException{
        this.env = env;
        this.Tilep = TileP;
        iniTerrain();
    }

    private void iniTerrain() {
        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream("C:\\Users\\jubac\\OneDrive\\Bureau\\SAE_Juba_Antoine_Said\\SAE_Juba_Antoine_Said\\SAE_Juba_Antoine_Said\\src\\main\\java\\com\\sae\\sae_juba_antoine_said\\Ressources\\tuileblock.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet);
        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                imgV = new ImageView(this.imgTilep);
                if(this.env.getMap()[i][j] == 0){
                    this.env.getMap()[i][j] = 688;
                }
                afficherMap(imgV,this.env.getMap()[i][j]);

            }
        }
    }

    public void afficherMap(ImageView img, int id) {
        int x;
        int y;

        x = id/((int)imgTilep.getWidth()/16);

        y = id%((int)imgTilep.getWidth()/16);

        x = (x*16);
        y = (y*16);

        img.setViewport(new Rectangle2D(y,x, 16,16));
        this.Tilep.getChildren().add(img);
    }

    public void refreshMap(ImageView img,int id) {
        int x;
        int y;
        x = id%((int)imgTilep.getWidth()/16);
        y = id/((int)imgTilep.getHeight()/16);
        x = (x*16)-16;
        y = (y*16);

        img.setViewport(new Rectangle2D(x,y, 16,16));
    }

    public TilePane getTilep() {

        return Tilep;
    }

    public ImageView getImage (int x , int y){
        return (ImageView)Tilep.getChildren().get(y*40+x);
    }

    public ImageView getImgV()
    {
        return imgV;
    }

    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }
}
