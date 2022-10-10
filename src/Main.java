import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File textFile = new File("basket.txt");
        Scanner scan = new Scanner(System.in);
        String[] products = {"молоко", "хлеб", "гречка"};
        int[] prices = {50, 14, 80};
        Basket basket = new Basket(products, prices);
        System.out.println("список товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + products[i] + " по " + prices[i] + " р/шт");
        }
        String inputStr;
        while (true) {
            System.out.print("Введите номер товара и его кол-во через пробел или end:  ");
            inputStr = scan.nextLine();
            if (inputStr.equals("end")) {
                break;
            }
            int indexProduct;
            int productCount;
            String[] parts = inputStr.split(" ");
            indexProduct = Integer.parseInt(parts[0]) - 1;
            productCount = Integer.parseInt(parts[1]);
            basket.addToCart(indexProduct, productCount);
        }
        if (!textFile.exists()) {
            basket.printCart();
            basket.saveText(textFile);

        } else {
            basket.saveText(textFile);
            loadFtomTxtFile(textFile);
        }
    }

    public static Basket loadFtomTxtFile(File textFile) {
        String[] products;
        int[] prices;
        int[] counts;
        Basket basket = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile));) {
            products = reader.readLine().split(" ");
            String[] pricesStr = reader.readLine().trim().split(" ");
            prices = new int[pricesStr.length];
            for (int i = 0; i < pricesStr.length; i++) {
                prices[i] = Integer.parseInt(pricesStr[i]);
            }
            String[] countsStr = reader.readLine().trim().split(" ");
            counts = new int[countsStr.length];
            for (int i = 0; i < counts.length; i++) {
                counts[i] = Integer.parseInt(countsStr[i]);
            }
            basket = new Basket(products, prices, counts);
            basket.printCart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}
