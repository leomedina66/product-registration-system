package registration;

import registration.domain.Product;
import registration.service.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductRegistrationApp {
    public static void main(String[] args) {
        ProductService service = new ProductService();

        Scanner scanner = new Scanner(System.in);

        int option = 0;


        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            option = readOption(scanner);

            if (option == 1) {
                registerProduct(scanner, service);

            } else if (option == 2) {
                listProducts(scanner, service);

            } else if (option == 3) {
                searchProduct(scanner, service);

            } else if (option == 4) {
                editProduct(scanner, service);
            } else if (option == 5) {
                deleteProduct(scanner, service);
            } else if (option == 6) {
                isRunning = exitMenu(scanner);
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("=========== MENU ===========");
        System.out.println("1 - Register product");
        System.out.println("2 - List products");
        System.out.println("3 - Search products");
        System.out.println("4 - Edit product");
        System.out.println("5 - Delete product");
        System.out.println("6 - Exit:");
    }

    private static int readOption(Scanner scanner) {
        int option = scanner.nextInt();
        scanner.nextLine();

        while (option > 6 || option < 1) {
            System.out.println("Invalid option, type a number between 1 and 6:");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        return option;
    }

    private static void registerProduct(Scanner scanner, ProductService service) {
        System.out.println("=========== Register Product ===========");
        System.out.println("--------------------------------");


        System.out.println("Type the product name:");
        String name = readValidName(scanner);

        if (service.findByName(name) != null) {
            System.out.println("Product already exists!");
            pause(scanner);
            return;
        }

        System.out.println("Type the product price:");
        double price = scanner.nextDouble();

        while (price < 0) {
            System.out.println("Invalid price, type a number equal or greater than 0:");
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
        service.addProduct(name, price, quantity);

        System.out.println("Product successfully registered!");

        pause(scanner);
    }

    private static void listProducts(Scanner scanner, ProductService service) {
        System.out.println("================ PRODUCT LIST ================");
        var products = service.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products registered");
            pause(scanner);
            return;
        }

        System.out.printf("%-20s %-10s %-10s\n", "Name", "Price", "Quantity");
        System.out.println("---------------------------------------------");

        for (Product product : products) {
            System.out.println(product);
        }
        pause(scanner);
    }

    private static void searchProduct(Scanner scanner, ProductService service) {
        System.out.println("================ PRODUCT SEARCH ================");

        if (service.isEmpty()) {
            System.out.println("No products to search.");
            pause(scanner);
            return;
        }

        System.out.println("Type the product name:");
        String name = readValidName(scanner);

        Product product = service.findByName(name);

        if (product != null) {
            System.out.println("Product found:");
            System.out.println(product);
        } else {
            System.out.println("Product not found!");
        }
        pause(scanner);
    }

    private static void editProduct(Scanner scanner, ProductService service) {
        System.out.println("================ EDIT PRODUCT ===============");
        System.out.println("Type the product name: ");
        String name = readValidName(scanner);

        Product product = service.findByName(name);

        if (product == null) {
            System.out.println("Product not found!");
            pause(scanner);
            return;
        }

        System.out.println("Current product data:");
        System.out.println(product);

        System.out.println("Type the new product price: ");
        double price = scanner.nextDouble();

        while (price < 0) {
            System.out.println("Invalid price, type a number equal or greater than 0:");
            price = scanner.nextDouble();
        }

        scanner.nextLine();


        System.out.println("Type the new product quantity: ");
        int quantity = scanner.nextInt();

        while (quantity < 0) {
            System.out.println("Invalid quantity, type a number equal or greater than 0:");
            quantity = scanner.nextInt();
        }

        scanner.nextLine();

        boolean updated = service.updateProduct(name, price, quantity);

        if (updated) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }

        pause(scanner);
    }

    private static void deleteProduct(Scanner scanner, ProductService service) {
        System.out.println("================ DELETE PRODUCT ================");

        System.out.println("Type the product name:");
        String name = readValidName(scanner);

        Product product = service.findByName(name);

        if (product == null) {
            System.out.println("Product not found!");
            pause(scanner);
            return;
        }

        System.out.println("Product found:");
        System.out.println(product);

        System.out.println("Do you want to delete this product? (Y/N)");
        char choice = Character.toLowerCase(scanner.next().charAt(0));
        scanner.nextLine();

        while (choice != 'y' && choice != 'n') {
            System.out.println("Invalid option, reply with Y/N:");
            choice = Character.toLowerCase(scanner.next().charAt(0));
            scanner.nextLine();
        }

        if (choice == 'y') {
            boolean deleted = service.removeProduct(name);

            if (deleted) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found!");
            }
        } else {
            System.out.println("Deletion canceled!");
        }

        System.out.println("Returning to main menu!");
        pause(scanner);
    }

    private static boolean exitMenu(Scanner scanner) {
        System.out.println("================ EXIT MENU ================");
        System.out.println("Are you sure ? Y/N");

        char confirm = Character.toLowerCase(scanner.next().charAt(0));

        while (confirm != 'y' && confirm != 'n') {
            System.out.println("Invalid option, reply with Y/N:");
            confirm = Character.toLowerCase(scanner.next().charAt(0));
        }

        scanner.nextLine();

        if (confirm == 'y') {
            System.out.println("Thank you for using our registration app!");
            return false;
        }

        System.out.println("Back to main menu \n");
        return true;

    }


    private static void pause(Scanner scanner) {
        System.out.println("Press ENTER to continue:");
        scanner.nextLine();
        System.out.println("\n\n\n\n\n");
    }

    private static String readValidName(Scanner scanner) {
        String name = scanner.nextLine();
        while (name.isBlank()) {
            System.out.println("Invalid name, type again:");
            name = scanner.nextLine();
        }
        return name;
    }

}