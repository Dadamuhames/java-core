package lessons.lesson03.onlinestore;

import java.util.Scanner;

public class ProductAddService {
    private final ProductRepository productRepository;
    private final Scanner scanner;


    public ProductAddService(ProductRepository productRepository, Scanner scanner) {
        this.productRepository = productRepository;
        this.scanner = scanner;
    }

    public String promptProductCode() {
        System.out.print("Enter unique product code: ");

        String code = scanner.nextLine();

        if (productRepository.existsByCode(code)) {
            return promptProductCode();
        }

        return code;
    }

    public String promptProductName() {
        System.out.print("Enter product name: ");

        return scanner.nextLine();
    }

    public Double promptProductPrice() {
        System.out.print("Enter product price: ");

        Double price = scanner.nextDouble();

        scanner.nextLine();

        return price;
    }


    public int promptProductCount() {
        System.out.print("Enter product count: ");

        int count = scanner.nextInt();

        scanner.nextLine();

        return count;
    }
}
