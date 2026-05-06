package registration;

import registration.domain.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductRegistrationApp {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = 0;

        ArrayList<Product> products = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            option = readOption(scanner);

            if (option == 1) {
                System.out.println("=========== Register Product ===========");
                System.out.println("--------------------------------");


                System.out.println("Type the product name:");
                String name = scanner.nextLine();

                while (name.trim().isEmpty()) {
                    System.out.println("Invalid name. Type again:");
                    name = scanner.nextLine();
                }

                boolean productExists = false;

                for (Product product : products) {
                    if (product.getName().equalsIgnoreCase(name)) {
                        productExists = true;
                        break;
                    }
                }
                if (productExists) {
                    System.out.println("Product already exists.");
                } else {
                    System.out.println("Type the product price:");
                    double price = scanner.nextDouble();

                    while (price < 0) {
                        System.out.println("Invalid price, type a number equal/greater than 0:");
                        price = scanner.nextDouble();
                    }

                    System.out.println("Type the product quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    while (quantity < 0) {
                        System.out.println("Invalid quantity, type a number equal or greater than 0:");
                        quantity = scanner.nextInt();
                        scanner.nextLine();
                    }
                    products.add(new Product(name, price, quantity));

                    System.out.println("Product registered successfully!");
                }


                System.out.println(" ");

                System.out.println("Press ENTER to continue:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 2) {
                System.out.println("================ PRODUCT LIST ================");
                if (products.isEmpty()) {
                    System.out.println("No products registered");
                } else {
                    System.out.printf("%-20s %-10s %-10s\n", "Name", "Price", "Quantity");

                    for (Product product : products) {
                        System.out.println(product);
                    }
                }
                System.out.println("Press ENTER to continue:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 3) {
                System.out.println("================ PRODUCT SEARCH ================");

                if (!products.isEmpty()) {

                    System.out.println("Type the product name:");
                    String searchName = scanner.nextLine();

                    boolean producFound = false;

                    for (Product product : products) {

                        if (searchName.equalsIgnoreCase(product.getName())) {

                            System.out.println(product);

                            producFound = true;
                            break;
                        }
                    }

                    if (!producFound) {
                        System.out.println("Product not found!");
                    }

                } else {
                    System.out.println("Don't have products to search!");
                }

                System.out.println("Press ENTER to continue:");
                scanner.nextLine();
                System.out.println("\n\n\n\n\n");

            } else if (option == 4) {

                System.out.println("================ EXIT MENU ================");
                System.out.println("Are you sure ? Y/N");

                char confirm = Character.toLowerCase(scanner.next().charAt(0));

                while (confirm != 'y' && confirm != 'n') {
                    System.out.println("Invalid option, reply with Y/N:");
                    confirm = Character.toLowerCase(scanner.next().charAt(0));
                }

                if (confirm == 'y') {
                    System.out.println("Thank you for using our registration app!");
                    isRunning = false;
                } else {
                    System.out.println("Back to main menu \n");

                    System.out.println("Press ENTER to continue:");
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("\n\n\n\n\n");
                }
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("=========== MENU ===========");
        System.out.println("1 - Register product");
        System.out.println("2 - List products");
        System.out.println("3 - Search products");
        System.out.println("4 - Exit:");
    }

    private static int readOption(Scanner scanner) {
        int option = scanner.nextInt();
        scanner.nextLine();

        while (option > 4 || option < 1) {
            System.out.println("Invalid option, type a number between 1 and 4:");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }
}