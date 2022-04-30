package market;

import entitles.Customer;
import entitles.Employee;
import entitles.User;
import utilities.Definitions;


import java.util.*;

public class HomePage {

    protected Store store;
    protected List<User> users;


    public HomePage() {
        this.users = new ArrayList<>();
        this.store = new Store();
        this.users.add(new Customer("Guy","Dayan","bobi" , "123456",1,true));
        this.users.add(new Employee("Yoni" ,"Hada" ,"momi","123456",2,true,2));

    }



    public void register(int userType) {
        Scanner scanner = new Scanner(System.in);
        String firstName, lastName, userName, password;
        boolean fnameSuccess = false;
        boolean lnameSuccess = false;
        boolean unameSuccess = false;
        boolean passSuccess = false;
        do {
            System.out.println("Enter your firstname (non-contain digits)");
            firstName = scanner.nextLine();
            fnameSuccess = isContainDigit(firstName);
        } while (fnameSuccess);
        do {
            System.out.println("Enter your lastname (non-contain digits)");
            lastName = scanner.nextLine();
            lnameSuccess = isContainDigit(lastName);
        } while (lnameSuccess);
        do {
            System.out.println("Enter username");
            userName = scanner.nextLine();
                unameSuccess = isAvailableUserName(userName);
        } while (unameSuccess);
        do {
            System.out.println("Enter password");
            password = scanner.nextLine();
            passSuccess = isStrongPassword(password);
        } while (!passSuccess);
        System.out.println("Are you a club member?" + "\n" + "1 - Yes" + "\n" + "2 - No");
        int clubMember = scanner.nextInt();
        boolean flagIsMember = (clubMember == 1); // check if clubMember
        Integer rank = null;
        if (userType == Definitions.EMPLOYEE_USER) {
            do {
                System.out.println("What is your rank (between 1-3):");
                int i = 1;
                for (EmployeeType type : EmployeeType.values()) {
                    System.out.println(i + ". " + type);
                    i++;
                }
                rank = scanner.nextInt();
            } while (rank <= 0 || rank >= 4);
        }
        if (rank == null) {
            this.users.add(new Customer(firstName, lastName, userName, password,userType,flagIsMember));
        }else {
            this.users.add(new Employee(firstName,lastName,userName,password,userType,flagIsMember,rank));
        }
        System.out.println("You are register successfully");
    }
    public void enterIntoStore(User currentUser) {
            if (currentUser.getUserType() == Definitions.CUSTOMER_USER) {
                Customer currentCustomer = (Customer) currentUser;
                customerMenu(currentCustomer);
            }
            if (currentUser.getUserType() == Definitions.EMPLOYEE_USER) {
                Employee currentEmployee = (Employee) currentUser;
                employeeMenu(currentEmployee);
            }
        }
    public User checkUserExist (String userName, String password){
        for (User user : this.users) {
            if ((user.getUserName().equals(userName)) && (user.getPassword().equals(password))) {
                return user;
            }
        }
        return null;
    }


        private void customerMenu(Customer currentCustomer) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println(currentCustomer.welcomeMessage());
            Integer option = null;
            if (this.store.getProducts().size() > 0) {
                do {
                    int amount = 0;
                    this.store.showAllProducts();
                    System.out.println("Select your option from below list (to end your purchase type ' -1 ')");
                    option = scanner.nextInt();
                    boolean validateProduct = checkValidateProduct(option);
                    boolean successPurchase = true;
                    if (validateProduct) {
                        do {
                            System.out.println("How much items from this product you want to add to your cart? (amount should be larger than 0)");
                            amount = scanner.nextInt();
                            boolean availableAmount = checkAvailabilityOfAmount(option, amount);
                            if (!availableAmount) {
                                System.out.println("Your entered amount bigger than quantity in store! - Choose again !");
                                successPurchase = false;
                            }
                        } while (amount <= 0);
                        if (successPurchase) {
                            currentCustomer.getCart().addProductToCart(this.store.getProducts(), option, amount);
                            currentCustomer.getCart().printCart();
                            this.store.updateInventory(option, amount);
                        }
                    }
                } while (option != Definitions.FINISH_PURCHASE);

                if (currentCustomer.getUserType() == Definitions.EMPLOYEE_USER) {
                    Employee employee = (Employee) currentCustomer;
                    float currentTotal = employee.getCart().getTotal();
                    switch (employee.getEmployeeType()) {
                        case REGULAR:
                            employee.getCart().setTotal(calculateDiscount(currentTotal, Definitions.REGULAR_DISCOUNT));
                            break;
                        case MANAGER:
                            employee.getCart().setTotal(calculateDiscount(currentTotal, Definitions.MANAGER_DISCOUNT));
                            break;
                        case STAFF_MEMBER:
                            employee.getCart().setTotal(calculateDiscount(currentTotal, Definitions.STAFF_MEMBER_DISCOUNT));
                            break;
                    }
                    System.out.println("You choose to finish your shopping, The total price after discount is: " + employee.getCart().getTotal());
                    employee.finishPurchase();
                } else {
                    System.out.println("You choose to finish your shopping, The total price is: " + currentCustomer.getCart().getTotal());
                    currentCustomer.finishPurchase();   // reset the cart of the customer;
                }
            }else {
                System.out.println("--- For this moment the store is empty ---");
            }
        }catch (Exception e){
            System.out.println("Wrong input");
        }
        }
        private void employeeMenu(Employee currentEmployee){
            Scanner scanner = new Scanner(System.in);
            System.out.println(currentEmployee.welcomeMessage());
            Integer option = null;
            do {
                System.out.println("1 - Print customers list");
                System.out.println("2 - Print club members only");
                System.out.println("3 - Print customers with at least 'one' purchase");
                System.out.println("4 - Print customer with Highest total purchases");
                System.out.println("5 - Add new product to store");
                System.out.println("6 - Change status inventory for product");
                System.out.println("7 - Make purchase");
                System.out.println("8 - Logout -> to first menu");
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        printAllCustomers();    // I don't know if you mean only customers or customers and employees
                        break;
                    case 2:
                        printClubMembers();
                        break;
                    case 3:
                        printCustomersMadePurchases();
                        break;
                    case 4:
                        printCustomerHighestPurchases();
                        break;
                    case 5:
                        addProductToStore();
                        break;
                    case 6:
                        changeProductStatus();
                        break;
                    case 7:
                        customerMenu(currentEmployee);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Wrong number choice , choose again");
                        scanner.nextLine();
                }

            }while (option != 8);

        }
        private void changeProductStatus(){
        Scanner scanner = new Scanner(System.in);
        this.store.showAllProducts();
            System.out.println("Choose product to change his status");
            int option = scanner.nextInt();
            System.out.println("Which status you want to put? "+
                    "\n" + "1 - In Stock" +
                    "\n" + "2 - Not In Stock");
            int status = scanner.nextInt();
            if (status == 1){
                System.out.println("Enter quantity of this product");
                int quantity = scanner.nextInt();
                this.store.getProducts().get(option).setQuantity(quantity);
            }if (status == 2){
                this.store.getProducts().get(option).setQuantity(0);
            }
        }
        private void addProductToStore(){
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter product name");
            String name = scanner.nextLine();
            System.out.println("Enter product price");
            float price = scanner.nextFloat();
            System.out.println("Enter product quantity");
            int amount  = scanner.nextInt();
            System.out.println("Enter discount for product (for club members)");
            int discount = scanner.nextInt();
            this.store.getProducts().add(new Product(this.store.getProducts().size(),name,price,amount,discount));

        }
        private void printCustomerHighestPurchases(){
            System.out.println("Customer with highest total purchases");
            float max= 0;
            Customer highestCurrentUser = null;
            Customer currentUser = null;
            for (User user : this.users){
                currentUser = (Customer)user;
                float maxCurrentUser  = currentUser.getSumAllPurchases();
                if ((currentUser!=null) && (maxCurrentUser > max)){
                    max = maxCurrentUser;
                    highestCurrentUser = currentUser;
                }
            }
            if (highestCurrentUser!=null)
            System.out.println(highestCurrentUser + " spent --> " + highestCurrentUser.getSumAllPurchases());
        }
        private void printCustomersMadePurchases(){
            System.out.println("List of customer made at least one purchase:");
            Customer currentUser = null;
            for (User user : this.users) {
                currentUser = (Customer) user;
                if ((currentUser!=null) && (currentUser.getCountAllPurchases() >= 1)){
                    System.out.println(currentUser);
                }
            }
        }
        private void printClubMembers(){
        System.out.println("List of all club members:");
        Customer currentUser = null;
        for (User user : this.users){
            currentUser = (Customer) user;
            if ((currentUser!=null) && (currentUser.isClubMember())) {
                    System.out.println(currentUser + "  **Club Member**");
                }
            }
            System.out.println("---------------------------------------");
        }
        public void printAllCustomers(){
            for (User user : this.users) {
                Customer customer = (Customer) user;
                System.out.println(customer.toString());
               // System.out.println(user.toString());
            }
        }
        private boolean checkAvailabilityOfAmount(int option , int amount){
             return this.store.getProducts().get(option).getQuantity() >= amount;
        }
        private boolean checkValidateProduct(int option) {
            boolean existProduct = false;
            for (Product product : this.store.getProducts()) {
                if (product.getProductId() == option) {
                    existProduct = true;
                    break;
                }
            }
            return existProduct;
        }
        private boolean isContainDigit (String str){
            return str.matches(".*\\d.*");    // * -> any string before and any string after
        }
        private boolean isAvailableUserName (String userName){
            return (this.users.contains(userName));
        }
        private boolean isStrongPassword (String password){
            return password.length() >= 6;
        }
        private float calculateDiscount(float total , int discount){
            float calculateTotal = total/100;
            calculateTotal *= discount;
            return (total-calculateTotal);
        }
    }



