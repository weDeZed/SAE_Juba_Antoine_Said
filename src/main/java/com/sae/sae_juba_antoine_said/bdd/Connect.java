package com.sae.sae_juba_antoine_said.bdd;

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

    public boolean verifUtilisateur(String nom) {
        String sql = "SELECT * FROM sae_dev.utilisateur WHERE pseudo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : " + e.getMessage());
            return false;
        }
    }

    public boolean verifConnexion(String nom,String mdp) {
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

    public int getIdUtilisateur() {
        String sql = "SELECT id FROM sae_dev.utilisateur WHERE pseudo = ?";
        if(SessionUtilisateur.estConnecte()) {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, SessionUtilisateur.getIdentifiantUtilisateur());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id_utilisateur");
                } else {
                    return -1;
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : " + e.getMessage());
                return -1;
            }
        }
        return -1;
    }

    public void insertPartie() {
        String sql = "INSERT INTO sae_dev.partie DEFAULT VALUES";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    public void insertFeedback() {
        String sql = "INSERT INTO sae_dev.feedback(message) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,null);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }

    public int countFeedback() {
        String countSql = "SELECT COUNT(*) FROM sae_dev.feedback";
        try {
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ResultSet resultSet = countStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du comptage : " + e.getMessage());
        }
        return -1;
    }

    public int countPartie() {
        String countSql = "SELECT COUNT(*) FROM sae_dev.partie";
        try {
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ResultSet resultSet = countStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du comptage : " + e.getMessage());
        }
        return -1;
    }

    public void insertEnvoyer_jouer(int score,int nombreVague,int nombreTuer) {
        String sql = "INSERT INTO sae_dev.Envoyer_jouer VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,getIdUtilisateur());
            statement.setInt(2,countPartie());
            statement.setInt(3,countFeedback());
            statement.setInt(4,score);
            statement.setInt(5,nombreVague);
            statement.setInt(6,nombreTuer);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
        }
    }
}