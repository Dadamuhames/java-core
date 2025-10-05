package lessons.lesson03.onlinestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        this.products = new HashMap<>();
    }

    public void save(final Product product) {
        String code = product.getCode();

        products.put(code, product);
    }

    public void updateCount(final String code, final int count) {
        products.get(code).setCount(count);
    }

    public Optional<Product> findProductByCode(final String code) {
        return Optional.ofNullable(products.get(code));
    }

    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    public boolean existsByCode(final String code) {
        return products.containsKey(code);
    }
}
