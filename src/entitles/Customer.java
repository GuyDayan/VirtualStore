package entitles;

import market.ShoppingCart;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Customer extends User implements Introducer{
    private boolean isClubMember;
    private ShoppingCart cart;
    private int countAllPurchases;
    private float sumAllPurchases;
    private Date lastPurchaseDate;

    public Customer(String firstName, String lastName, String userName, String password,int userType,boolean isClubMember) {
        super(firstName, lastName, userName, password, userType);
        this.isClubMember = isClubMember;
        this.cart  = new ShoppingCart();
        this.countAllPurchases = 0;
        this.sumAllPurchases =0;
    }

    public void finishPurchase(){
        this.lastPurchaseDate = new Date();
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

    public Date getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        StringBuilder message = new StringBuilder();
        message.append(this.getFirstName()).append(" ").append(this.getLastName());
        if (this.isClubMember) {
            message.append(" -- Club member");
        }else {
            message.append(" -- Not club member");
        }
                message.append(" ,Total purchases: ").append(this.sumAllPurchases).append(" ,Number of purchases:").append(this.countAllPurchases);
        if (lastPurchaseDate != null) {
            message.append(" ,Last purchase : " + formatter.format(this.lastPurchaseDate));
        }else {
            message.append(" ,Last purchase : No purchase");
        }

        return message.toString();
    }
}

