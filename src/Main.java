
import entitles.User;
import market.HomePage;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomePage homePage = new HomePage();
        int choose=0,userType =0;
        do {
                while ((choose>4)||(choose<=0)) {
                    try {
                        System.out.println("1 --> Create an account");
                        System.out.println("2 --> Login existing account");
                        System.out.println("3 --> Logout");
                        choose = scanner.nextInt();

                    }catch (Exception e){
                        System.out.println("Wrong input");
                        scanner.nextLine();
                    }
                }

                while ((userType<=0 || userType>=3) && (choose!=3)) {
                    try {
                        System.out.println("Which type of user you are ?"
                                + "\n" + "1 --> Customer" + "\n" +
                                "2 --> Employee");
                        userType = scanner.nextInt();
                    }catch (Exception e){
                        System.out.println("Wrong input");
                        scanner.nextLine();
                    }
                }
                switch (choose) {
                    case 1:
                        homePage.register(userType);
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.println("Enter your username");
                        String userName = scanner.nextLine();
                        System.out.println("Enter your password");
                        String password = scanner.nextLine();
                        User currentUser = homePage.checkUserExist(userName, password);
                        if (currentUser == null) {
                            System.out.println("No existing user");
                        } else {
                            homePage.enterIntoStore(currentUser);
                        }
                        break;
                    case 3:
                        System.out.println("Good Bye");
                        break;
                }
                if (choose !=3){
                    choose=0;
                    userType =0;
                }

        }while (choose != 3);
    }
}
