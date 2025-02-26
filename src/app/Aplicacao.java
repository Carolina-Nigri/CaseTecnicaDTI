package src.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplicacao {
    public static void main(String[] args) {
        int opcao = -1;
        
        do{
            try {
                opcao = menu();
            } catch (Exception e) {
                System.err.println("Erro ao realizar leitura da opção.\n");
            }
        
            switch (opcao) {
                case 0 -> System.out.println("Fechando...");
                case 1 -> listar();
                case 2 -> buscar();
                case 3 -> cadastrar();
                case 4 -> atualizar();
                case 5 -> deletar();
                default -> System.out.println("Opção inválida"); 
            }
        } while (opcao != 0);
    }

    public static int menu() throws IOException {
        int opcao;
        Boolean invalida;
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

    public static void listar() {

    }

    public static void buscar() {

    }

    public static void cadastrar() {

    }

    public static void atualizar() {

    }

    public static void deletar() {

    }
}