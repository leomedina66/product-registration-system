package registration;

import registration.domain.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductRegistrationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option = 0;

        ArrayList<Product> products = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            option = readOption(scanner);

            if (option == 1) {
                registerProduct(scanner, products);

            } else if (option == 2) {
                listProducts(scanner, products);

            } else if (option == 3) {
                searchProduct(scanner, products);

            } else if (option == 4) {
                editProduct(scanner, products);
            } else if (option == 5) {
                deleteProduct(scanner, products);
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

    private static void registerProduct(Scanner scanner, ArrayList<Product> products) {
        System.out.println("=========== Register Product ===========");
        System.out.println("--------------------------------");


        System.out.println("Type the product name:");
        String name = scanner.nextLine();

        while (name.isBlank()) {
            System.out.println("Invalid name. Type again:");
            name = scanner.nextLine();
        }

        if (findProductByName(products, name) != null) {
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
        products.add(new Product(name, price, quantity));

        System.out.println("Product registered successfully!");


        System.out.println(" ");

        pause(scanner);
    }

    private static void listProducts(Scanner scanner, ArrayList<Product> products) {
        System.out.println("================ PRODUCT LIST ================");
        if (products.isEmpty()) {
            System.out.println("No products registered");
            pause(scanner);
            return;
        }

        System.out.printf("%-20s %-10s %-10s\n", "Name", "Price", "Quantity");

        for (Product product : products) {
            System.out.println(product);
        }
        pause(scanner);
    }

    private static void searchProduct(Scanner scanner, ArrayList<Product> products) {
        System.out.println("================ PRODUCT SEARCH ================");

        if (products.isEmpty()) {
            System.out.println("No products to search.");
            pause(scanner);
            return;
        }

        System.out.println("Type the product name:");
        String searchName = scanner.nextLine();

        while (searchName.isBlank()) {
            System.out.println("Invalid name, type again:");
            searchName = scanner.nextLine();
        }


        Product product = findProductByName(products, searchName);

        if (product != null) {
            System.out.println("Product found:");
            System.out.println(product);
        } else {
            System.out.println("Product not found!");
        }
        pause(scanner);
    }

    private static void editProduct(Scanner scanner, ArrayList<Product> products) {
        System.out.println("================ EDIT PRODUCT ===============");
        System.out.println("Type the product name: ");
        String name = scanner.nextLine();

        while (name.isBlank()) {
            System.out.println("Invalid name, type again:");
            name = scanner.nextLine();
        }

        Product product = findProductByName(products, name);

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
        scanner.nextLine();

        while (quantity < 0) {
            System.out.println("Invalid quantity, type a number equal or greater than 0:");
            quantity = scanner.nextInt();
        }

        scanner.nextLine();

        product.setPrice(price);
        product.setQuantity(quantity);

        System.out.println("Product updated successfully!");

        pause(scanner);
    }

    private static void deleteProduct(Scanner scanner, ArrayList<Product> products) {
        System.out.println("================ DELETE PRODUCT ================");

        System.out.println("Type the product name:");
        String name = scanner.nextLine();

        while (name.isBlank()) {
            System.out.println("Invalid name, type again:");
            name = scanner.nextLine();
        }

        Product product = findProductByName(products, name);

        if (product == null) {
            System.out.println("Product not found!");
            pause(scanner);
            return;
        }

        System.out.println("Product found:");
        System.out.println(product);
        System.out.println();

        System.out.println("Do you want to delete this product? (Y/N)");
        char choice = Character.toLowerCase(scanner.next().charAt(0));
        scanner.nextLine();

        while (choice != 'y' && choice != 'n') {
            System.out.println("Invalid option, reply with Y/N:");
            choice = Character.toLowerCase(scanner.next().charAt(0));
            scanner.nextLine();
        }

        if (choice == 'y') {
            products.remove(product);
            System.out.println("Product deleted successfully!");
        }else {
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

    private static Product findProductByName(ArrayList<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private static void pause(Scanner scanner) {
        System.out.println("Press ENTER to continue:");
        scanner.nextLine();
        System.out.println("\n\n\n\n\n");
    }

}