package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            } catch (Exception e) {
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

        do{
            System.out.println("Aplicação - CRUD de Livros");
            System.out.println("0 - Sair");
            System.out.println("1 - Listar Livros");
            System.out.println("2 - Buscar por ID");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Atualizar Livro");
            System.out.println("5 - Deletar Livro");
            System.out.print("Digite uma opção (0 a 5): ");
            
            opcao = Integer.parseInt(br.readLine());
            invalida = (opcao < 0) || (opcao > 5);

            if(invalida) System.out.println("Opção inválida! Tente novamente.\n");
        } while(invalida);

       return opcao;
    }

    public static void listar(LivroDAO database) {
        List<Livro> livros = database.selectAll();
               
        if(livros.isEmpty()){
            System.out.println("Nenhum livro foi registrado ainda.\n");
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            System.out.print("\nTitulo: ");
            livro.setTitulo(br.readLine());

            System.out.print("Autor: ");
            livro.setAutor(br.readLine());

            System.out.print("Data de publicação: ");
            livro.setDataPublicacao(formatter.parse(br.readLine()));
            
            System.out.print("Qtd de págs.: ");
            livro.setQtdPaginas(Integer.parseInt(br.readLine()));
    
            System.out.print("Idioma: ");
            livro.setIdioma(br.readLine());

            System.out.print("Editora: ");
            livro.setEditora(br.readLine());

            if(database.insert(livro))
                System.out.println("\nLivro registrado com sucesso:\n" + livro + "\n");
            else
                System.out.println("Erro ao registrar livro.\n");

        } catch (IOException ioe) {
            System.err.println("Erro ao ler ID do livro.\n");
        } catch (ParseException pe) {
            System.err.println("Erro ao fazer parse da data.\n");
        }            
    }

    public static void atualizar(LivroDAO database) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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

            try {
                System.out.println("Novo livro:");

                System.out.print("Titulo: ");
                livro.setTitulo(br.readLine());

                System.out.print("Autor: ");
                livro.setAutor(br.readLine());

                System.out.print("Data de publicação: ");
                livro.setDataPublicacao(formatter.parse(br.readLine()));
                
                System.out.print("Qtd de págs.: ");
                livro.setQtdPaginas(Integer.parseInt(br.readLine()));
        
                System.out.print("Idioma: ");
                livro.setIdioma(br.readLine());

                System.out.print("Editora: ");
                livro.setEditora(br.readLine());

                if(database.update(livro))
                    System.out.println("\nLivro atualizado com sucesso:\n" + livro + "\n");
                else
                    System.out.println("Erro ao atualizar livro.\n");

            } catch (IOException ioe) {
                System.err.println("Erro ao ler ID do livro.\n");
            } catch (ParseException pe) {
                System.err.println("Erro ao fazer parse da data.\n");
            }     
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