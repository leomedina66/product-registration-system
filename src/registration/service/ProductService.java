package registration.service;

import registration.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public boolean addProduct(String name, double price, int quantity) {
        if (findByName(name) != null) {
            return false;
        }
        products.add(new Product(name, price, quantity));
        return true;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product findByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean updateProduct(String name, double price, int quantity) {
        Product product = findByName(name);

        if (product == null) {
            return false;
        }
        product.setPrice(price);
        product.setQuantity(quantity);
        return true;
    }

    public boolean removeProduct(String name) {
        Product product = findByName(name);

        if (product == null) {
            return false;
        }

        products.remove(product);
        return true;
    }

}
