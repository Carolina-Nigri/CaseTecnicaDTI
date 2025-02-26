package app;

import java.util.Date;
import java.util.List;

public class Livro {
    // Campos obrigatórios
    private String nome;
    private String autor;
    private Date dataPublicacao;
    // Campos opcionais
    private int numeroPaginas;
    private String idioma;
    private List<String> generos;
    
    public Livro(String nome, String autor, Date dataPublicacao) {
        this.nome = nome;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }
    public Livro(String nome, String autor, Date dataPublicacao, int numeroPaginas, String idioma, List<String> generos) {
        this.nome = nome;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.generos = generos;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public List<String> getGeneros() {
        return generos;
    }
    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
}
