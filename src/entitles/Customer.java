package entitles;

import market.ShoppingCart;


public class Customer extends User implements Introducer{
    private boolean isClubMember;
    private ShoppingCart cart;

    public Customer(String firstName, String lastName, String userName, String password,int userType,boolean isClubMember) {
        super(firstName, lastName, userName, password, userType);
        this.isClubMember = isClubMember;
        this.cart  = new ShoppingCart();
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



    }
