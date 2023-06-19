module com.example.sae_juba_antoine_said {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    //requires org.junit.jupiter.api;


    opens com.sae.sae_juba_antoine_said to javafx.fxml;
    exports com.sae.sae_juba_antoine_said;
    opens com.sae.sae_juba_antoine_said.Controlleur to  javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Controlleur;
    opens com.sae.sae_juba_antoine_said.Vue to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Vue;
    exports com.sae.sae_juba_antoine_said.Modele.Acteurs;
    opens com.sae.sae_juba_antoine_said.Modele.Acteurs to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Modele.Tours;
    opens com.sae.sae_juba_antoine_said.Modele.Tours to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Modele.Environnement;
    opens com.sae.sae_juba_antoine_said.Modele.Environnement to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Modele.BFS;
    opens com.sae.sae_juba_antoine_said.Modele.BFS to javafx.fxml;
    //exports java.TestJUnit;
    //opens java.TestJUnit to javafx.fxml;

}