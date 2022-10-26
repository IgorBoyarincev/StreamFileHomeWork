import java.io.*;
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
            Basket.loadFromTxtFile(textFile);
        }
    }

    public static void loadFromBinFile(File file) {
        try (
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        ) {
            Basket basket = (Basket) in.readObject();
            basket.printCart();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
