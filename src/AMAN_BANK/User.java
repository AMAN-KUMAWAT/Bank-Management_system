package AMAN_BANK;
import java.sql.Connection;
import java.util.Scanner;
import java.util.Random;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.sql.DriverManager;
import static java.lang.Class.forName;
public class User {
     private final Scanner sc = new Scanner(System.in);
    public void registration() {
        String url = "jdbc:mysql://localhost:3306/banking_system";
        String username = "root";
        String password = "Aman@123";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);

            System.out.print("ENTER NAME: ");
            String name = sc.nextLine();
            System.out.print("ENTER FATHER's NAME: ");
            String f_name = sc.nextLine();
            System.out.print("PERMANENT ADDRESS: ");
            String address = sc.nextLine();
            System.out.print("PHONE NUMBER: ");
            String ph = sc.nextLine();
            System.out.print("ENTER AMOUNT: ");
            double amount = sc.nextDouble();
            System.out.print("SET SECURITY PIN: ");
            int pin = sc.nextInt();

            String account ="10000"+generate();
//            System.out.println(account);
            String query = "insert into user(Name,F_Name,address,PIN,Phone_no,Account_no)values(?,?,?,?,?,?)";
            String query2 = "insert into accounts(Account_no,Amount)values(?,?)";
            PreparedStatement prep = con.prepareStatement(query);
            PreparedStatement prep2 = con.prepareStatement(query2);
            String account2 = account;
            prep.setString(1,name);
            prep.setString(2,f_name);
            prep.setString(3,address);
            prep.setInt(4,pin);
            prep.setString(5,ph);
            prep.setString(6,account);
            prep2.setString(1,account2);
            prep2.setDouble(2,amount);
            prep.executeUpdate();
            prep2.executeUpdate();
            System.out.println("SUCCESSFULLY CREATED:.....");
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void login(){
        String url = "jdbc:mysql://localhost:3306/banking_system";
        String username = "root";
        String password = "Aman@123";
        final AccountManager obj = new AccountManager();
        try{

            forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            System.out.println("ENTER ACCOUNT_NO: ");
            String acc = sc.nextLine();
            System.out.println("ENTER PIN: ");
            String pin = sc.nextLine();
            String Query = ("select Account_no from user where PIN="+pin+" ");

            ResultSet rs = stmt.executeQuery(Query);
            int ch;
            if (rs.next()) {
                System.out.println("Account number found.");
                do{
                    System.out.println("1.Withdraw");
                    System.out.println("2.Add");
                    System.out.println("3.Transfer Money");
                    System.out.println("4.Mini Statement");
                    System.out.println("5.Exit");
                    ch = sc.nextByte();
                    switch (ch){
                        case 1:
                               obj.withdrawMoney(acc);
                               break;
                        case 2:obj.AddMoney(acc);
                            break;
                            case 3:obj.transferMoney(acc);
                        break;
                        case 4:obj.mini_Statement(acc);
                            break;
                        case 5:System.exit(0);
                            break;
                        default:System.exit(0);
                    }
                }while(ch!=4);
            } else {
                System.out.println("Account number not found.");
            }

            rs.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public long generate(){
        Random rand = new Random();
        return rand.nextInt(99999);
    }
}