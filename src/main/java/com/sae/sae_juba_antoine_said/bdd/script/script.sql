DROP SCHEMA IF EXISTS sae_dev cascade;

CREATE SCHEMA sae_dev;
SET search_path TO sae_dev;

CREATE TABLE sae_dev.Utilisateur (
    id_utilisateur SERIAL PRIMARY KEY,
    pseudo VARCHAR(64) NOT NULL,
    mot_de_passe VARCHAR(256) NOT NULL
);

CREATE TABLE sae_dev.Feedback (
    id_feedback SERIAL PRIMARY KEY,
    message TEXT NOT NULL
);

CREATE TABLE sae_dev.Partie (
    id_partie SERIAL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sae_dev.Envoyer_jouer (
    id_utilisateur INT NOT NULL,
    id_partie INT NOT NULL,
    id_feedback INT NOT NULL,
    score INT NOT NULL,
    nombre_vague INT NOT NULL,
    ennemis_tuer INT NOT NULL,
    PRIMARY KEY (id_utilisateur, id_partie, id_feedback),
    FOREIGN KEY (id_utilisateur) REFERENCES sae_dev.Utilisateur(id_utilisateur),
    FOREIGN KEY (id_partie) REFERENCES sae_dev.Partie(id_partie),
    FOREIGN KEY (id_feedback) REFERENCES sae_dev.Feedback(id_feedback)
);
