package lessons.lesson03.onlinestore;

public class Product {
    private final String code;
    private final String name;
    private final Double price;
    private int count;

    public Product(String code, String name, Double price, int count) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return String.format("Code: %s\nName: %s\nPrice: %s\nCount: %d", code, name, price, count);
    }
}
