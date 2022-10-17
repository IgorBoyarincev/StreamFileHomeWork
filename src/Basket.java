import java.io.*;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] counts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        counts = new int[3];
    }

    public Basket(String[] products, int[] prices, int[] counts) {
        this.products = products;
        this.prices = prices;
        this.counts = counts;
    }

    public void addToCart(int productNum, int amount) {
        counts[productNum] += amount;
        System.out.println(products[productNum] + " " + counts[productNum] + " шт по " + prices[productNum] + " р/шт");
    }

    public void printCart() {
        int productPrice;
        int sum = 0;
        System.out.println("ваша корзина: ");
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) {
                continue;
            }
            productPrice = prices[i] * counts[i];
            System.out.println("\t" + products[i] + " " + counts[i] + " шт по " + prices[i] + " р/шт " + productPrice + " руб в сумме");
            sum += productPrice;
        }
        System.out.println("итого: " + sum + " руб");
    }

    public void saveText(File textFile) {
        try (PrintWriter out = new PrintWriter(textFile);) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();
            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
            for (int count : counts) {
                out.print(count + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveBin(File file) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        ) {
            out.writeObject(new Basket(products, prices, counts));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getCounts() {
        return counts;
    }
}
