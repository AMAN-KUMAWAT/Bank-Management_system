package AMAN_BANK;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class BankingApp{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Gen-Z Bank");
            int ch;
            do{
                System.out.println("1.Register");
                System.out.println("2.Login");
                System.out.println("3.Exit");
                ch = sc.nextByte();
                switch(ch){
                    case 1:User user = new User();
                        user.registration();
                    break;
                    case 2: User user1 = new User();
                          user1.login();
                    break;
                    case 3:System.exit(0);
                    default:
                        System.exit(0);
                }
            }while(ch!=3);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}