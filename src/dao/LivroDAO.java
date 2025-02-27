package src.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import src.model.Livro;

public class LivroDAO extends DAO {
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
        String sql = "CREATE TABLE IF NOT EXISTS Livros (" +
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
        String sql = "SELECT * FROM Livros";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), formatter.parse(rs.getString("dataPublicacao")), rs.getInt("qtdPaginas"), rs.getString("idioma"), rs.getString("editora"));
                livros.add(livro);
            }
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }

        return livros;
    }

    public Livro select(int id) {
        Livro livro = null;
        String sql = "SELECT * FROM Livros WHERE id = " + id;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (Statement stmt = conexao.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);	
	        
            if(rs.next()){            
	        	livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), formatter.parse(rs.getString("dataPublicacao")), rs.getInt("qtdPaginas"), rs.getString("idioma"), rs.getString("editora"));
            }
        } catch (SQLException | ParseException e) {
            System.err.println(e.getMessage());
        }

        return livro;
    }

    public boolean insert(Livro livro) {
        boolean status = false;
        String sql = "INSERT INTO Livros (titulo, autor, dataPublicacao, qtdPaginas, idioma, editora) " +
                     "VALUES (?, ?, ?, ?, ?, ?);";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, formatter.format(livro.getDataPublicacao()));
            stmt.setInt(4, livro.getQtdPaginas());
            stmt.setString(5, livro.getIdioma());
            stmt.setString(6, livro.getEditora());

            int colunasAlteradas = stmt.executeUpdate();	
            if(colunasAlteradas > 0) {
                ResultSet idCriado = stmt.getGeneratedKeys();

                if(idCriado.next()) {
                    livro.setId(idCriado.getInt(1));
                    status = true;
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }
 
    public boolean update(Livro livro) {
        boolean status = false;
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, dataPublicacao = ?, qtdPaginas = ?, idioma = ?," +
                     "editora = ? WHERE id = ?;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, formatter.format(livro.getDataPublicacao()));
            stmt.setInt(4, livro.getQtdPaginas());
            stmt.setString(5, livro.getIdioma());
            stmt.setString(6, livro.getEditora());
            stmt.setInt(7, livro.getId());

            int colunasAlteradas = stmt.executeUpdate();	
            if(colunasAlteradas > 0) {
                status = true;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }

    public boolean delete(int id) {
        boolean status = false;
        String sql = "DELETE FROM Livros WHERE id = " + id;

        try (Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate(sql);	
	        status = true;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        return status;
    }
}
