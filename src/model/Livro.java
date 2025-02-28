package src.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro {
    // Campos obrigatórios
    private int id;
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
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
    public Livro(int id, String titulo, String autor, LocalDate dataPublicacao, int qtdPaginas, String idioma, String editora) {
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
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDate dataPublicacao) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data = this.dataPublicacao.format(formatter);

        return this.id + " | " + this.titulo + " | " + this.autor + " | " + data + " | " + (this.qtdPaginas == 0 ? "Número de páginas desconhecido" : this.qtdPaginas) + " | " + ((this.idioma == null || this.idioma.isBlank()) ? "Idioma desconhecido" : this.idioma) + " | " + ((this.editora == null || this.editora.isBlank()) ? "Editora desconhecida" : this.editora);
    }
}
