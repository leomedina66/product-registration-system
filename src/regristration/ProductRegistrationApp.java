package regristration;

import java.util.Scanner;

public class CadastroSimples {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        String[] nomeProduto = new String[10];
        double[] valorProduto = new double[10];
        int[] quantidadeProduto = new int[10];
        int contadorProdutos = 0;
        boolean encerrar = false;

        while (!encerrar) {
            System.out.println("=========== MENU ===========");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produtos");
            System.out.println("4 - Sair:");
            option = scanner.nextInt();
            scanner.nextLine();

            while (option > 4 || option < 1) {
                System.out.println("Opção inválida, digite um numero correspondente com o menu:");
                option = scanner.nextInt();
                scanner.nextLine();
            }

            if (option == 1) {
                System.out.println("=========== CADASTRO ===========");
                System.out.println("--------------------------------");

                if (contadorProdutos < nomeProduto.length) {

                    System.out.println("Digite o nome do produto:");
                    String nome = scanner.nextLine();

                    while (nome.trim().isEmpty()) {
                        System.out.println("Nome inválido. Digite novamente:");
                        nome = scanner.nextLine();
                    }

                    nomeProduto[contadorProdutos] = nome;

                    boolean existeProduto = false;

                    for (int i = 0; i < contadorProdutos; i++) {
                        if (nome.equalsIgnoreCase(nomeProduto[i])) {
                            existeProduto = true;
                            break;
                        }
                    }

                    if (existeProduto) {
                        System.out.println("Produto já cadastrado!");
                    } else {

                        System.out.println("Digite o valor do produto:");
                        valorProduto[contadorProdutos] = scanner.nextDouble();

                        while (valorProduto[contadorProdutos] <= 0) {
                            System.out.println("Valor inválido, digite um valor maior que zero:");
                            valorProduto[contadorProdutos] = scanner.nextDouble();
                        }

                        System.out.println("Digite quantidade do produto:");
                        quantidadeProduto[contadorProdutos] = scanner.nextInt();
                        scanner.nextLine();

                        while (quantidadeProduto[contadorProdutos] < 0) {
                            System.out.println("Quantidade inválida, digite um numero maior que zero:");
                            quantidadeProduto[contadorProdutos] = scanner.nextInt();
                            scanner.nextLine();
                        }

                        contadorProdutos++;
                        System.out.println("Produto cadastrado com sucesso!");
                    }

                } else {
                    System.out.println("Você já estourou o limite de produtos para cadastro!");
                }

                System.out.println(" ");

                System.out.println("Pressione ENTER para continuar:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 2) {
                System.out.println("================ LISTA DE PRODUTOS ================");
                System.out.printf("%-20s %-10s %-10s\n", "Nome", "Valor", "Quantidade");
                System.out.println("---------------------------------------------");

                if (contadorProdutos == 0) {
                    System.out.println("Nenhum produto cadastrado ainda!");
                } else {
                    for (int i = 0; i < contadorProdutos; i++) {
                        System.out.printf("%-20s %-10.2f %-10d\n",
                                nomeProduto[i],
                                valorProduto[i],
                                quantidadeProduto[i]);
                    }
                }

                System.out.println("Pressione ENTER para continuar:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 3) {
                System.out.println("================ BUSCA DE PRODUTOS ================");

                if (contadorProdutos > 0) {

                    System.out.println("Digite o nome do produto:");
                    String buscaNome = scanner.nextLine();

                    boolean produtoEncontrado = false;

                    for (int i = 0; i < contadorProdutos; i++) {

                        if (buscaNome.equalsIgnoreCase(nomeProduto[i])) {

                            System.out.printf("%-20s %-10s %-10s\n", "Nome", "Valor", "Quantidade");
                            System.out.println("---------------------------------------------");

                            System.out.printf("%-20s %-10.2f %-10d\n",
                                    nomeProduto[i],
                                    valorProduto[i],
                                    quantidadeProduto[i]);

                            produtoEncontrado = true;
                            break;
                        }
                    }

                    if (!produtoEncontrado) {
                        System.out.println("Produto não encontrado!");
                    }

                } else {
                    System.out.println("Ainda não há produtos cadastrados para procurar");
                }

                System.out.println("Pressione ENTER para continuar:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 4) {

                System.out.println("================ MENU SAÍDA ================");
                System.out.println("Você tem certeza que deseja sair do sistema? S/N");

                char confirm = Character.toLowerCase(scanner.next().charAt(0));

                while (confirm != 's' && confirm != 'n') {
                    System.out.println("Opção inválida, responda apenas com S/N:");
                    confirm = Character.toLowerCase(scanner.next().charAt(0));
                }

                if (confirm == 's') {
                    System.out.println("Muito obrigado por usar o Sistema!");
                    encerrar = true;
                } else {
                    System.out.println("De volta ao menu principal \n");

                    System.out.println("Pressione ENTER para continuar:");
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("\n\n\n\n\n");
                }
            }
        }

        scanner.close();
    }
}