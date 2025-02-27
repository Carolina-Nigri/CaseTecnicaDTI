package src.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Livro {
    // Campos obrigatórios
    private int id;
    private String titulo;
    private String autor;
    private Date dataPublicacao;
    // Campos opcionais
    private int qtdPaginas;
    private String idioma;
    private String editora;
    
    public Livro() {
        this.id = -1;
        this.titulo = "";
        this.autor = "";
        this.dataPublicacao = null;
        this.qtdPaginas = 0;
        this.idioma = "";
        this.editora = "";
    }
    public Livro(int id, String titulo, String autor, Date dataPublicacao, int qtdPaginas, String idioma, String editora) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.qtdPaginas = qtdPaginas;
        this.idioma = idioma;
        this.editora = editora;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public Date getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public int getQtdPaginas() {
        return qtdPaginas;
    }
    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String data = formatter.format(this.dataPublicacao);

        return this.id + " | " + this.titulo + " | " + this.autor + " | " + data + " | " + this.qtdPaginas + " págs. | " + (this.idioma == null ? "Idioma desconhecido" : this.idioma) + " | " + (this.editora == null ? "Editora desconhecida" : this.editora);
    }
}
