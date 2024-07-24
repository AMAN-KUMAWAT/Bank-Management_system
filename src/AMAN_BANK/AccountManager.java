package AMAN_BANK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AccountManager {
    String url = "jdbc:mysql://localhost:3306/banking_system";
    String username = "root";
    String password = "Aman@123";
    Scanner sc;
    public void withdrawMoney(String acc){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.print("Enter amount to Withdraw: ");
            sc = new Scanner(System.in);
            double amount = sc.nextDouble();
            Statement stmt = con.createStatement();
            String query = "select Amount from accounts where Account_no="+acc+" ";
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                double balance = res.getDouble(1);  // Retrieve balance as double
                if (balance > amount) {
                    double newBalance = balance - amount;
                    String updateQuery = "UPDATE accounts SET Amount = " + newBalance + " WHERE Account_no = '" + acc + "'";
                    int rowsUpdated = stmt.executeUpdate(updateQuery);  // Use executeUpdate for updates
                    if (rowsUpdated > 0) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Error updating balance.");
                    }
                } else {
                    System.out.println("Insufficient Balance.");
                }
            } else {
                System.out.println("Account number not found.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void AddMoney(String acc){
        try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(url,username,password);
      Statement stmt = con.createStatement();
            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

      String query = "select Amount from accounts where Account_no="+acc+"";
      ResultSet res = stmt.executeQuery(query);
      if(res.next()){
          double balance = res.getDouble(1);
          double newbal = balance+amount;
          int rowUpdated = stmt.executeUpdate("update accounts set Amount="+newbal+"where Account_no="+acc+"");
          if(rowUpdated>0){
              System.out.println("Money Added successfully......");
          }else{
              System.out.println("Error updating");
          }
      }else{
          System.out.println("Account number not Found: ");
      }
      con.close();
      stmt.close();
                  }catch(Exception e){
            System.out.println(e);
        }
    }
    public void transferMoney(String acc){
        try{
            sc = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.print("Enter Recipient account Number: ");
            String newAcc_no = sc.nextLine();
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            Statement stmt = con.createStatement();
            String query = "select Amount from accounts where Account_no="+acc+"";
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                double balance = res.getDouble(1);
                if(amount<=balance){
                    String query2 = "select Amount from accounts where Account_no="+newAcc_no+"";
                    ResultSet res1 = stmt.executeQuery(query2);
                    if(res1.next()){
                        double new_balance = res1.getDouble(1);
                        double tot = amount+new_balance;
//                        int rowUpdated = stmt.executeUpdate("update accounts set Amount="+tot+"where Account_no="+acc+"");
                        String query3 = "update accounts set Amount="+tot+"where Account_no="+newAcc_no+"";
                        int df = stmt.executeUpdate(query3);
                        if(df>0){
                            System.out.println("Transfer success...");
                        }else{
                            System.out.println("un-success-full!!");
                        }
                    }else{
                        System.out.println("No such account found!!");
                    }res1.close();
                }else{
                    System.out.println("No sufficient balance....");
                }
            }else{
                System.out.println("Account not found!!");
            }
            con.close();
            res.close();
            stmt.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void mini_Statement(String acc){
try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(url,username,password);
    Statement stmt = con.createStatement();
    String query = "select Name from user where Account_no="+acc+"";

    ResultSet res = stmt.executeQuery(query);
    if(res.next()){
        String n = res.getString(1);

        String query2 = "select Amount from accounts where Account_no="+acc+"";
        ResultSet df = stmt.executeQuery(query2);
        if(df.next()){
            double amount = df.getDouble(1);
            System.out.println("Account Number: "+acc+"\n"+"Name: "+n+"\n"+"Available Balance: "+amount);
        }else{
            System.out.println("Error!!");
        }
    }else{
        System.out.println("No such Account Exists!!");
    }
}catch(Exception e){
    System.out.println(e);
}
    }
}