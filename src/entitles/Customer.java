package entitles;

import market.ShoppingCart;


public class Customer extends User implements Introducer{
    private boolean isClubMember;
    private ShoppingCart cart;
    private int countAllPurchases;
    private float sumAllPurchases;

    public Customer(String firstName, String lastName, String userName, String password,int userType,boolean isClubMember) {
        super(firstName, lastName, userName, password, userType);
        this.isClubMember = isClubMember;
        this.cart  = new ShoppingCart();
        this.countAllPurchases = 0;
        this.sumAllPurchases =0;
    }

    public void finishPurchase(){
        this.countAllPurchases++;
        this.sumAllPurchases += this.cart.getTotal();
        this.cart.getCartList().clear();
        this.cart.setTotal(0);
    }

    public boolean isClubMember() {
        return isClubMember;
    }

    public void setClubMember(boolean clubMember) {
        isClubMember = clubMember;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String welcomeMessage(){
        StringBuilder message = new StringBuilder();
        message.append("Hello ").append(this.getFirstName()).append(" ").append(this.getLastName());
        if (isClubMember)
            message.append(" VIP!");
        else
            message.append(" !");
        return message.toString();
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
