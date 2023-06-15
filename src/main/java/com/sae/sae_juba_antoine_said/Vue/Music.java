package com.sae.sae_juba_antoine_said.Vue;

import com.sae.sae_juba_antoine_said.Lanceur;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {
    private static Clip clipFond;      // Variable pour la musique de fond
    private static Clip clipVictoire;  // Variable pour la musique de victoire
    private static Clip clipDefaite;   // Variable pour la musique de défaite

    public void playMusicFond() {
        URL urlImageVaiL = Lanceur.class.getResource("/com/sae/sae_juba_antoine_said/sounds/digital_love.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String location = urlImageVaiL.getPath();
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipFond = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipFond.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de fond
        clipFond.start();
    }


    public boolean verifSon(){
        // Vérification si le clip de musique de fond est en cours de lecture
        if (!clipFond.isRunning()) {
            return false;
        }
        return true;
    }

    public void stopMusicFond() {
        // Arrêt et fermeture du clip de musique de fond s'il est en cours de lecture
        if (clipFond != null && clipFond.isRunning()) {
            System.out.println("arreter la music de fond");
            clipFond.stop();
            clipFond.close();
        }
    }

    public void PlayMusicVictoire() {
        System.out.println("music de victoir");
        URL urlImageVaiL = Lanceur.class.getResource("/com/sae/sae_juba_antoine_said/sounds/musicVictoir.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String location = urlImageVaiL.getPath();
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipVictoire = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipVictoire.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de victoire
        clipVictoire.start();
    }

    public void stopMusicVictoire() {
        // Arrêt et fermeture du clip de musique de victoire s'il est en cours de lecture
        if (clipVictoire != null && clipVictoire.isRunning()) {
            System.out.println("arreter la music de victoir");
            clipVictoire.stop();
            clipVictoire.close();
        }
    }

    public void PlayMusicDefaite() {
        URL urlImageVaiL = Lanceur.class.getResource("/com/sae/sae_juba_antoine_said/sounds/launch_sound.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String location = urlImageVaiL.getPath();
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de défaite
        clipDefaite.start();
    }

    public void stopMusicDefaite() {
        // Arrêt et fermeture du clip de musique de défaite s'il est en cours de lecture
        if (clipDefaite != null && clipDefaite.isRunning()) {
            System.out.println("arrter la music défait ");
            clipDefaite.stop();
            clipDefaite.close();
        }
    }

    public void lancerFlecheSounding() {

        URL urlImageVaiL = Lanceur.class.getResource("/com/sae/sae_juba_antoine_said/sounds/fire_laser.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String location = urlImageVaiL.getPath();
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de défaite
        clipDefaite.start();
    }
}


