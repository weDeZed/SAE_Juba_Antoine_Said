package com.sae.sae_juba_antoine_said.modele.bdd;

import java.sql.*;

public class Connect {
    private static Connect getInstance = null;
    private Connection connection;
    public Connect() {
        try {
            connection = DriverManager.getConnection(
                    Params.dsn,
                    Params.login,
                    Params.password
            );
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Connect getConnectionInstance() {
        if(getInstance == null) {
            getInstance = new Connect();
            return getInstance;
        }
        return getInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void creerUtilisateur(String nom, String mdp) {
        if(verifUtilisateur(nom,mdp)) {
            try {
                String sql = "INSERT INTO sae_dev.utilisateur (pseudo,mot_de_passe) VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nom);
                statement.setString(2, mdp);
                statement.executeUpdate();
                System.out.println("Utilisateur créer avec succès.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : " + e.getMessage());

            }
        }
    }

    public boolean verifUtilisateur(String nom,String mdp) {
        String sql = "SELECT * FROM sae_dev.utilisateur WHERE pseudo = ? AND mot_de_passe = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom);
            statement.setString(2, mdp);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
            return false;
        }
    }

}
