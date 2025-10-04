package lessons.lesson03.onlinestore;

import java.util.Scanner;

public class OnlineStoreApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ProductRepository productRepository = new ProductRepository();
            ProductAddService productAddService = new ProductAddService(productRepository, scanner);

            OnlineStore onlineStore = new OnlineStore(productRepository, productAddService, scanner);


            printOptions();
            String option = scanner.nextLine();

            while (true) {
                switch (option) {
                    case "L" -> onlineStore.getProducts();

                    case "I" -> onlineStore.getProductInfo();

                    case "A" -> onlineStore.addProduct();

                    case "B" -> onlineStore.buyProduct();

                    case "Q" -> {
                        clearTerminal();
                        System.exit(0);
                    }
                }

                printOptions();
                option = scanner.nextLine();
                clearTerminal();
            }
        }
    }


    public static void printOptions() {
        System.out.println("Choose section:\n[L] - Product list\n[I] - get product info\n[A] - Add product\n[B] - Buy product\n[Q] - Quit");
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
