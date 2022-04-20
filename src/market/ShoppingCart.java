package market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart{

    private float total;
    private int countAllPurchases;
    private float sumAllPurchases;
    private Map<Product,Integer> cartList;  // product,amount

    public ShoppingCart(){
        this.total=0;
        this.countAllPurchases = 0;
        this.sumAllPurchases =0;
        this.cartList = new HashMap<>();
    }


    public void addProductToCart(List<Product> productList, int option , int amount){
        for (Product product : productList){
            if (product.getProductId() == option){
                if (this.cartList.containsKey(product)){
                    this.cartList.put(product, this.cartList.get(product) + amount);
                }else
                    this.cartList.put(product,amount);
                break;
            }
        }
        calculateTotal();
    }
    public void printCart(){
        System.out.println("Your shopping list for this moment : ");
        System.out.println("========================================");
        for (Product product: this.cartList.keySet()){
            System.out.println("Product Name: " + "'"+product.getName()+"'" + " , " + "Amount: " + this.cartList.get(product));
            //this.total += (product.getPrice()*this.cartList.get(product));
        }
        System.out.println("Your balance is : " + this.total);
        System.out.println("========================================");
    }

    public void resetCart(){
        this.countAllPurchases++;
        this.sumAllPurchases += total;
        this.cartList.clear();
        this.total = 0;
    }
    public void calculateTotal(){
        this.total = 0;
        for (Product product : this.cartList.keySet()){
            this.total += ((this.cartList.get(product)) * (product.getPrice()));
        }
    }
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Map<Product, Integer> getCartList() {
        return cartList;
    }

    public void setCartList(Map<Product, Integer> cartList) {
        this.cartList = cartList;
    }

    public int getCountAllPurchases() {
        return countAllPurchases;
    }

    public void setCountAllPurchases(int countAllPurchases) {
        this.countAllPurchases = countAllPurchases;
    }

    public float getSumAllPurchases() {
        return sumAllPurchases;
    }

    public void setSumAllPurchases(float sumAllPurchases) {
        this.sumAllPurchases = sumAllPurchases;
    }

}
