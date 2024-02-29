package app;

import dao.CryptoDao;
import model.Crypto;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final CryptoDao cryptoDao = new CryptoDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenu();
        int opcao = scanner.nextInt();

        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    listarCrypto();
                    break;
                case 2:
                    inserirCrypto();
                    break;
                case 3:
                    excluirCrypto();
                    break;
                case 4:
                    atualizarCrypto();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            exibirMenu();
            opcao = scanner.nextInt();
        }

        System.out.println("Encerrando aplicação...");
        scanner.close();
        cryptoDao.dispose();
    }

    private static void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Listar Crypto");
        System.out.println("2. Inserir Crypto");
        System.out.println("3. Excluir Crypto");
        System.out.println("4. Atualizar Crypto");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void listarCrypto() {
        List<Crypto> cryptoList = cryptoDao.getAllCrypto();
        System.out.println("\n==== LISTAR CRYPTOS ====");
        for (Crypto crypto : cryptoList) {
            System.out.println(crypto);
        }
    }

    private static void inserirCrypto() {
        System.out.println("\n==== INSERIR CRYPTO ====");
        try {
            Scanner scanner = new Scanner(System.in);

            // Solicita ao usuário que insira os dados da Crypto
            System.out.print("Digite o ID da Crypto: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            System.out.print("Digite a rede da Crypto: ");
            String rede = scanner.nextLine();
            System.out.print("Digite o símbolo da Crypto: ");
            String symbol = scanner.nextLine();
            System.out.print("Digite o preço da Crypto: ");
            double price = scanner.nextDouble();

            // Cria um novo objeto Crypto com os dados inseridos pelo usuário
            Crypto crypto = new Crypto(id, rede, symbol, price);

            // Chama o método insert da CryptoDao para inserir a Crypto no banco de dados
            CryptoDao cryptoDao = new CryptoDao();
            boolean inserido = cryptoDao.insert(crypto);

            // Verifica se a inserção foi bem-sucedida
            if (inserido) {
                System.out.println("Crypto inserida com sucesso.");
            } else {
                System.out.println("Erro ao inserir a Crypto.");
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir a Crypto: " + e.getMessage());
        }
    }

    private static void excluirCrypto() {
        System.out.println("\n==== EXCLUIR CRYPTO ====");
        System.out.print("Informe o ID da Crypto que deseja excluir: ");
        int id = scanner.nextInt();
        if (cryptoDao.delete(id)) {
            System.out.println("Crypto excluída com sucesso!");
        } else {
            System.out.println("Falha ao excluir a Crypto.");
        }
    }

    private static void atualizarCrypto() {
        System.out.println("\n==== ATUALIZAR CRYPTO ====");
        try {
            Scanner scanner = new Scanner(System.in);

            // Solicita ao usuário que insira o ID da Crypto que deseja atualizar
            System.out.print("Digite o ID da Crypto que deseja atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Solicita ao usuário que insira os novos dados da Crypto
            System.out.print("Digite a nova rede da Crypto: ");
            String rede = scanner.nextLine();
            System.out.print("Digite o novo símbolo da Crypto: ");
            String symbol = scanner.nextLine();
            System.out.print("Digite o novo preço da Crypto: ");
            double price = scanner.nextDouble();

            // Cria um novo objeto Crypto com os dados inseridos pelo usuário
            Crypto crypto = new Crypto(id, rede, symbol, price);

            // Chama o método update da CryptoDao para atualizar a Crypto no banco de dados
            CryptoDao cryptoDao = new CryptoDao();
            boolean atualizado = cryptoDao.update(crypto);

            // Verifica se a atualização foi bem-sucedida
            if (atualizado) {
                System.out.println("Crypto atualizada com sucesso.");
            } else {
                System.out.println("Erro ao atualizar a Crypto.");
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a Crypto: " + e.getMessage());
        }
    }
}
