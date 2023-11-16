module com.example.sae_juba_antoine_said {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    //requires org.junit.jupiter.api;

    opens com.sae.sae_juba_antoine_said to javafx.fxml;
    exports com.sae.sae_juba_antoine_said;
    opens com.sae.sae_juba_antoine_said.controlleur to  javafx.fxml;
    exports com.sae.sae_juba_antoine_said.controlleur;
    opens com.sae.sae_juba_antoine_said.vue to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.vue;
    exports com.sae.sae_juba_antoine_said.modele.acteurs;
    opens com.sae.sae_juba_antoine_said.modele.acteurs to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.modele.tours;
    opens com.sae.sae_juba_antoine_said.modele.tours to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.modele.environnement;
    opens com.sae.sae_juba_antoine_said.modele.environnement to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.modele.bfs;
    opens com.sae.sae_juba_antoine_said.modele.bfs to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.modele.vague;
    opens com.sae.sae_juba_antoine_said.modele.vague to javafx.fxml;
    //exports java.TestJUnit;
    //opens java.TestJUnit to javafx.fxml;

}