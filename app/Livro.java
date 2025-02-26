package app;

import java.util.Date;

public class Livro {
    // Campos obrigatórios
    private String titulo;
    private String autor;
    private Date dataPublicacao;
    // Campos opcionais
    private int qtdPaginas;
    private String idioma;
    private String editora;
    
    public Livro(String titulo, String autor, Date dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }
    public Livro(String titulo, String autor, Date dataPublicacao, int qtdPaginas, String idioma, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.qtdPaginas = qtdPaginas;
        this.idioma = idioma;
        this.editora = editora;
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
}
