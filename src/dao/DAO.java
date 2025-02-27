package src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private final String DB_URL = "jdbc:sqlite:src/database/livraria.db";
    protected Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        boolean status = false;

        try {
            conexao = DriverManager.getConnection(DB_URL);
            status = (conexao == null);
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }

    public boolean finalizar() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }
}
