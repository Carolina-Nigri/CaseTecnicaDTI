package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import src.dao.LivroDAO;
import src.model.Livro;

public class Aplicacao {
    public static void main(String[] args) {
        LivroDAO database = new LivroDAO();

        int opcao = -1;
        
        do{
            try {
                opcao = menu();
            } catch (IOException ioe) {
                System.err.println("Erro ao realizar leitura da opção.\n");
            }
        
            switch (opcao) {
                case 0 -> System.out.println("\nFechando...");
                case 1 -> listar(database);
                case 2 -> buscar(database);
                case 3 -> cadastrar(database);
                case 4 -> atualizar(database);
                case 5 -> deletar(database);
                default -> System.out.println("Opção inválida"); 
            }
        } while (opcao != 0);
    }

    public static int menu() throws IOException {
        int opcao;
        boolean invalida;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Aplicação - CRUD de Livros");
        System.out.println("0 - Sair");
        System.out.println("1 - Listar Livros");
        System.out.println("2 - Buscar por ID");
        System.out.println("3 - Cadastrar Livro");
        System.out.println("4 - Atualizar Livro");
        System.out.println("5 - Deletar Livro");

        do{
            System.out.print("\nDigite uma opção (0 a 5): ");
            
            opcao = Integer.parseInt(br.readLine());
            invalida = (opcao < 0) || (opcao > 5);

            if(invalida) System.out.print("Opção inválida! Tente novamente.");
        } while(invalida);

       return opcao;
    }

    public static void lerLivro (Livro livro) throws IOException {
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = null;
        int qtdPaginas = 0;
        
        System.out.print("\nTítulo: ");
        do {
            input = br.readLine();
            if(input.isBlank()) System.out.print("Título é obrigatório, não pode ser vazio: ");
        } while(input.isBlank());
        livro.setTitulo(input);

        System.out.print("Autor: ");
        do {
            input = br.readLine();
            if(input.isBlank()) System.out.print("Autor é obrigatório, não pode ser vazio: ");
        } while(input.isBlank());
        livro.setAutor(input);

        System.out.print("Data de publicação [YYYY-MM-DD]: ");
        do {
            input = br.readLine();
            if(input.isBlank()) System.out.print("Data de publicação é obrigatória, não pode ser vazia: ");

            try {
                data = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.print("Formato inadequado da data, tente novamente: ");
            }
        } while(input.isBlank() || data == null);
        livro.setDataPublicacao(data);
        
        System.out.print("Qtd de págs [0 a 40000] (opcional): ");
        do {
            input = br.readLine();
            if(!input.isBlank()) qtdPaginas = Integer.parseInt(input);
            if(qtdPaginas < 0 || qtdPaginas > 40000) System.out.print("Quantidade de páginas inválida (deve estar entre 0 e 40000): ");
        } while(qtdPaginas < 0 || qtdPaginas > 40000);
        livro.setQtdPaginas(qtdPaginas);

        System.out.print("Idioma (opcional): ");
        input = br.readLine();
        livro.setIdioma(input);

        System.out.print("Editora (opcional): ");
        input = br.readLine();
        livro.setEditora(input);
    }

    public static void listar(LivroDAO database) {
        List<Livro> livros = database.selectAll();
               
        if(livros.isEmpty()){
            System.out.println("\nNenhum livro foi registrado ainda.\n");
        } else {
            System.out.println("");
            livros.forEach(livro -> System.out.println(livro));
            System.out.println("");
        }
    }

    public static void buscar(LivroDAO database) {
        System.out.print("\nID do livro: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = -1;
        
        try {
            id = Integer.parseInt(br.readLine());
        } catch (IOException ioe) {
            System.err.println("Erro ao ler ID do livro\n");
        }

        Livro livro = database.select(id);
        System.out.println( (livro == null ? "Livro não encontrado" : livro) + "\n");
    }

    public static void cadastrar(LivroDAO database) {
        Livro livro = new Livro();

        try {
            lerLivro(livro);
        } catch(IOException ioe) {
            System.err.println("Erro ao ler dados do livro.");
        }

        if(database.insert(livro))
            System.out.println("\nLivro registrado com sucesso:\n" + livro + "\n");
        else
            System.out.println("Erro ao registrar livro.\n");
    }

    public static void atualizar(LivroDAO database) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = -1;
        
        System.out.print("\nID do livro a atualizar: ");
        try {
            id = Integer.parseInt(br.readLine());
        } catch (IOException ioe) {
            System.err.println("Erro ao ler ID do livro\n");
        }

        Livro livro = database.select(id);

        if(livro == null){
            System.out.println("Livro não encontrado\n");
        } else {
            System.out.println(livro + "\n");
            System.out.println("Novo livro:");

            try {
                lerLivro(livro);
            } catch(IOException ioe) {
                System.err.println("Erro ao ler dados do livro.");
            }

            if(database.update(livro))
                System.out.println("\nLivro atualizado com sucesso:\n" + livro + "\n");
            else
                System.out.println("Erro ao atualizar livro.\n");
        }       
    }

    public static void deletar(LivroDAO database) {
        System.out.print("\nID do livro a excluir: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = -1;
        
        try {
            id = Integer.parseInt(br.readLine());
        } catch (IOException ioe) {
            System.err.println("Erro ao ler ID do livro\n");
        }

        if(database.delete(id))
            System.out.println("Livro de ID " + id + " excluído com sucesso.\n");
        else
            System.out.println("Erro ao excluir livro de ID " + id + ".\n");

    }
}