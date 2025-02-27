package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import src.model.Livro;

public class LivroDAO extends DAO {
    private final String TABELA = "Livros";

    public LivroDAO() {
        super();
        init();
    }

    private void init() {
        conectar();
        create();
    }

    @Override
    public boolean finalizar() {
        return super.finalizar();
    }

    public boolean create() {
        boolean status = false;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + "(" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                     "titulo VARCHAR(70) NOT NULL," +
                     "autor VARCHAR(70) NOT NULL," +
                     "dataPublicacao VARCHAR(20) NOT NULL," +
                     "qtdPaginas INTEGER," +
                     "idioma VARCHAR(50)," +
                     "editora VARCHAR(50)" +
                     ");";

        try {
            Statement stmt = conexao.createStatement();
            stmt.execute(sql);	
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }

    public List<Livro> selectAll() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA;
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);	
	        
            while(rs.next()){            
	        	Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), format.parse(rs.getString("dataPublicacao")) );
                livros.add(livro);
            }

            stmt.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        } catch (ParseException pe) {
            System.err.println(pe.getMessage());
        }

        return livros;
    }
    public boolean insert(Livro livro) {
        boolean status = false;

        return status;
    }
    public boolean select(int id) {
        boolean status = false;

        return status;
    }
    public boolean update(Livro livro) {
        boolean status = false;

        return status;
    }
    public boolean delete(int id) {
        boolean status = false;

        return status;
    }
}
