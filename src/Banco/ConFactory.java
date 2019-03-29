package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {
    private final String url;
    private final String usuario;
    private final String senha;

    public ConFactory(){
        url = "jdbc:postgresql://localhost:5432/RedeFarmaciaPOO";
        usuario = "postgres";
        senha = "96715570da";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        System.out.println("deu crt");
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url,usuario,senha);
    }
}
