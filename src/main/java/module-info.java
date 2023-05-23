module com.example.sae_juba_antoine_said {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;


    opens com.sae.sae_juba_antoine_said to javafx.fxml;
    opens com.sae.sae_juba_antoine_said.Modele to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Modele;
    opens com.sae.sae_juba_antoine_said.Controlleur to  javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Controlleur;
    opens com.sae.sae_juba_antoine_said.Vue to javafx.fxml;
    exports com.sae.sae_juba_antoine_said.Vue;
}