package lessons.lesson03.onlinestore;

import java.util.List;
import java.util.Scanner;

public class OnlineStore {
    private final ProductRepository productRepository;
    private final ProductAddService productAddService;
    private final Scanner scanner;

    public OnlineStore(ProductRepository productRepository, ProductAddService productAddService, Scanner scanner) {
        this.productRepository = productRepository;
        this.productAddService = productAddService;
        this.scanner = scanner;
    }

    public void getProducts() {
        List<Product> products = productRepository.findAll();

        System.out.println("Products list");

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    private Product getProduct(final String code) throws Exception {
        return productRepository.findProductByCode(code).orElseThrow(() -> new Exception("Product not found"));
    }

    public void getProductInfo() {
        System.out.print("Enter the product code: ");
        String code = scanner.nextLine();

        try {
            Product product = getProduct(code);

            System.out.printf("Product #%s info:\n", code);

            System.out.println(product.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProduct() {
        System.out.println("Adding new product");

        String code = productAddService.promptProductCode();
        String name = productAddService.promptProductName();
        Double price = productAddService.promptProductPrice();
        int count = productAddService.promptProductCount();

        Product product = new Product(code, name, price, count);

        productRepository.save(product);

        System.out.println("Product created");

        System.out.println(product);
    }

    public void buyProduct() {
        System.out.println("Buying product");
        System.out.print("Enter the product code:");
        String code = scanner.nextLine();

        System.out.print("Enter the quantity you want to buy:");
        int quantityToBuy = scanner.nextInt();

        scanner.nextLine();


        try {
            buyProduct(code, quantityToBuy);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    private void buyProduct(final String code, final int quantityToBuy) throws Exception {
        Product product = getProduct(code);

        if (quantityToBuy > product.getCount()) {
            throw new Exception("demand is exceeding supply");
        }

        int newCount = product.getCount() - quantityToBuy;

        productRepository.updateCount(code, newCount);

        System.out.println("Product bought");
    }
}
