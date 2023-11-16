package com.sae.sae_juba_antoine_said.modele.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
