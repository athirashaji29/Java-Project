import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;       
import java.util.*;

import javax.swing.CellRendererPane;
import java.io.FileWriter;
import java.io.IOException;
class MTBS{

    private static final int Cid = 0;
    static Scanner sc=new Scanner(System.in);
    static Connection con;

    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","123456");
        }
        catch(Exception e){
            System.out.println("\n\n\n\n\t\t\t\t"+e.getMessage());
        }
        
    }
    
    
    
    
    // athira added first
    public static void main(String[] arg) throws SQLException{
        int ch;
        int num=0;
       do{ 
        
 System.out.println("\t\t\t\t1. Login");
System.out.println("\t\t\t\t2. Signup");
System.out.println("\t\t\t\t------------------------------------------");
System.out.println("\t\t\t\tEnter your Choice...:");
System.out.print("\t\t\t\t");
ch=sc.nextInt();

// Login option
if (ch == 1) {
  System.out.println("\t\t\t\tEnter your username:");
  String username = sc.next();
  System.out.println("\t\t\t\tEnter your password:");
  String password = sc.next();

  // Verify user credentials
  String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
  PreparedStatement pstmt = con.prepareStatement(sql);
  pstmt.setString(1, username);
  pstmt.setString(2, password);
  ResultSet rs = pstmt.executeQuery();
  if (rs.next()) {
    // Successful login
    int user_id = rs.getInt("user_id");
    System.out.println("\t\t\t\tLogin successful!");
    process();
    // Update the customer panel
    // ...
  } else {
    // Failed login
    System.out.println("\t\t\t\tInvalid username or password. Please try again.");
    num=0;
  }
}

// Signup option
if (ch == 2) {
  System.out.println("\t\t\t\tEnter a new username:");
  String username = sc.next();
  System.out.println("\t\t\t\tEnter a new password:");
  String password = sc.next();

  // Insert new user into login table
  String sql = "INSERT INTO user (name, password) VALUES (?, ?)";
  PreparedStatement pstmt = con.prepareStatement(sql);
  pstmt.setString(1, username);
  pstmt.setString(2, password);
  int rowsAffected = pstmt.executeUpdate();
  if (rowsAffected > 0) {
    // Successful signup
    System.out.println("\t\t\t\tSignup successful! Please login to continue.");
    process();
  } else {
    // Failed signup
    System.out.println("\t\t\t\tSignup failed. Please try again.");
    num=0;
  }
}}while(num==0);
       {
        System.out.println("Invalid");
       }
}    }