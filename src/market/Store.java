package market;

import java.util.ArrayList;
import java.util.List;


public class Store {

    private List<Product> products;

    public Store() {
        this.products = new ArrayList<>();
    }


    public void showAllProducts() {
        System.out.println("List of our products (you can choose a number)");
        for (Product product : this.products) {
            System.out.print(product.getProductId() + " - " + product.getName() + " -- " +product.getPrice() + " NIS");
            if (product.getQuantity() == 0) {
                System.out.print(" - (NOT IN STOCK)");
            }
            System.out.println();
        }
    }

        public void updateInventory (int option, int amount){
            for (Product currentProduct : this.products) {
                if (option == currentProduct.getProductId()) {
                    currentProduct.setQuantity(currentProduct.getQuantity() - amount);
                    break;
                }
            }
        }

        public List<Product> getProducts () {
            return products;
        }

        public void setProducts (List < Product > products) {
            this.products = products;
        }



}
