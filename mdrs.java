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

    public static boolean login() throws SQLException{
        System.out.println("\n\n\n\n\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\tLOGIN TO MOVIE TICKET BOOKING");
        System.out.println("\t\t\t\t-----------------------------\n\n");
        java.io.Console console=System.console();
        System.out.print("\t\t\t\t");
        String username =console.readLine("Username: ");
        System.out.print("\t\t\t\t");
        String password =new String(console.readPassword("Password: "));
        System.out.println("\n\n\n");
        if(username.equals("anamika") && password.equals("password")){
            return true;
        }
        else{
            return false;
        }
    }

    public static void BookTicket() throws SQLException{
      try{
          int num=ShowMovieList();
          int Id;
          System.out.println("\n\n\n\n");
          if(num==0){
              System.out.println("\t\t\t\t--------------------------------------");
              System.out.println("\t\t\t\tSorry...!!!");
              System.out.println("\n\t\t\t\tThere is no show going on.....!!!!!!");
              System.out.println("\t\t\t\t--------------------------------------");
              return;
          }
          while(true){
              System.out.println("\t\t\t\tEnter movie Id(0 to Exit):");
              System.out.print("\t\t\t\t");
              Id=sc.nextInt();
              if(Id==0){
                  return;
              }
              PreparedStatement pst=con.prepareStatement("SELECT * FROM movie WHERE columnid=?");
              pst.setInt(1,Id);
              ResultSet rs=pst.executeQuery();
              if(rs.next()){
                  System.out.println("\t\t\t\tEnter Customer Name: ");
                  sc.nextLine();
                  System.out.print("\t\t\t\t");
                  String Cname=sc.nextLine();
                  System.out.println("\t\t\t\tEnter seats: ");
                  System.out.print("\t\t\t\t");
                  int seat=sc.nextInt();
                  int RemainingSeats;
                  java.util.Date d;
                  java.util.Date t;
                  double price;
                  String Mname,format;
                  Mname=rs.getString(2);
                  format=rs.getString(3);
                  d=rs.getDate(4);
                  java.sql.Date date=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
                  t=rs.getTime(5);
                  java.sql.Time time=new Time(t.getHours(),t.getMinutes(),0);
                  price=rs.getDouble(6);
                  RemainingSeats=rs.getInt(7)-seat;
                  if(RemainingSeats<0){
                      System.out.println("\t\t\t\t---------------------------");
                      System.out.println("\t\t\t\tInsufficient seats....!!!");
                      System.out.println("\t\t\t\tBooking is cancelled...!!!!");
                      System.out.println("\t\t\t\t---------------------------");
                      return;
                  }
                  PreparedStatement pst1=con.prepareStatement("INSERT INTO customer (columnid,Mname,format,date,time,price,seat,Cid,user_id) SELECT ?,?,?,?,?,?,?,?,user_id FROM user WHERE name = ?",Statement.RETURN_GENERATED_KEYS);
                  pst1.setInt(1,Id);
                  pst1.setString(2,Mname);
                  pst1.setString(3,format);
                  pst1.setDate(4,date);
                  pst1.setTime(5,time);
                  pst1.setDouble(6,price*seat);
                  pst1.setInt(7,seat);
                  pst1.setInt(8,Cid);
                  pst1.setString(9,Cname);
                  if(pst1.executeUpdate()>0){
                      System.out.println("\t\t\t\t-------------------");
                      System.out.println("\t\t\t\tTicket Booked...!!!");
                      System.out.println("\t\t\t\t-------------------");
                      
                  }
                  else{
                      System.out.println("\t\t\t\t------------------------------");
                      System.out.println("\t\t\t\tSomething got Wrong.....!!!!!!");
                      System.out.println("\t\t\t\t------------------------------");
                      return;
                  }

 
                  pst1=con.prepareStatement("update movie set seat=? where columnId=?");
                  pst1.setInt(1,RemainingSeats);
                  pst1.setInt(2,Id);
                  if(pst1.executeUpdate()<=0){
                      System.out.println("\t\t\t\t-------------------------------");
                      System.out.println("\t\t\t\tSomething went Wrong.....!!!!!!");
                      System.out.println("\t\t\t\t-------------------------------");
                      return;
                  }
                  System.out.print("\t\t\t\tPress any key.....");
                  sc.next();
                  pst1=con.prepareStatement("select * from customer where name=? ");
                  pst1.setString(1,Cname);
                  ResultSet rs1=pst1.executeQuery();
                  if(rs1.next()){
                  clearscreen();
                  showMyTicket(rs1.getInt(10));
                  }
                  else{
                      System.out.println("\t\t\t\t-------------------------------");
                      System.out.println("\t\t\t\tSomething went Wrong.....!!!!!!");
                      System.out.println("\t\t\t\t-------------------------------");
                  }
                  return;
              }
              else{
                  System.out.println("\t\t\t\t---------------------------");
                  System.out.println("\t\t\t\tPlease Input a valid Id....");
                  System.out.println("\t\t\t\t---------------------------");
              }

          }
      }
      catch(Exception e){
          System.out.println("\t\t\t\tException occured");
      }
  }
public static void showMyTicket(int num) throws SQLException{
    try{
        PreparedStatement pst=con.prepareStatement("select * from customer where Cid=?");
        pst.setInt(1,num);
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            System.out.println("\n\n\n\t\t\t\t\t---MOVIE TICKET--");
            String name=rs.getString(8);
                System.out.println("\t\t\t---------------------------------------------------------");
                //System.out.println("\t\t\t\tName-\t\t\t"+rs.getString(1));
               // System.out.println("\t\t\t\tPhone No.-\t\t"+rs.getString(2));
                System.out.println("\t\t\t---------------------------------------------------------");
                System.out.println("\t\t\t\tMovie Name-\t\t"+rs.getString(2));
                System.out.println("\t\t\t\tType-\t\t\t"+rs.getString(3));
                System.out.println("\t\t\t\tdate-\t\t\t"+rs.getDate(4));
                System.out.println("\t\t\t\tTime-\t\t\t"+rs.getString(5));
                System.out.println("\t\t\t\tSeat(s)-\t\t"+rs.getInt(7));
                System.out.println("\t\t\t\tPrice-\t\t\t"+rs.getDouble(6));
                System.out.println("\t\t\t\tUnique Id-\t\t"+rs.getInt(8));
                System.out.println("\t\t\t---------------------------------------------------------");
                System.out.println("\n\n");
                String filename=name+".txt";
            File file = new File (filename);
            FileWriter writer = new FileWriter(file,true);
            writer.write("\n\n\n\t\t\t\t\t---MOVIE TICKET--\n");
            writer.write("\t\t\t---------------------------------------------------------\n");
            writer.write("\t\t\t\tMovie Name-\t\t"+rs.getString(2) + "\n");
            writer.write("\t\t\t\tType-\t\t\t"+rs.getString(3) + "\n");
            writer.write("\t\t\t\tdate-\t\t\t"+rs.getDate(4) + "\n");
            writer.write("\t\t\t\tTime-\t\t\t"+rs.getString(5) + "\n");
            writer.write("\t\t\t\tSeat(s)-\t\t"+rs.getInt(7) + "\n");
            writer.write("\t\t\t\tPrice-\t\t\t"+rs.getDouble(6) + "\n");
            writer.write("\t\t\t\tUnique Id-\t\t"+rs.getInt(8) + "\n");
            writer.write("\t\t\t---------------------------------------------------------\n");
            writer.write("\n\n");
            writer.close();
        }
        else{
            System.out.println("\t\t\t\t-------------------------------");
            System.out.println("\t\t\t\tNo Booking available.....!!!!!!");
        }
        
    }
    catch(Exception e){
        System.out.println("\t\t\t\tException occurred in showing ticket");
    }
}

public static void CancleTicket() throws SQLException{
    try{
        while(true){
            System.out.print("\n\n\n\n");
            System.out.print("\t\t\t\tEnter Unique Id(0 for exit): ");
            int num=sc.nextInt();
            if(num==0){
                return;
            }
            int seat,Mid;
            PreparedStatement pst=con.prepareStatement("select * from customer c join user u on c.user_id = u.user_id where c.Cid=?");
            pst.setInt(1,num);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                seat=rs.getInt("seat");
                Mid=rs.getInt("columnid");
            }
            else{
                System.out.println("\t\t\t\t-------------------------------");
                System.out.println("\t\t\t\tThe Unique Id doesn\'t Exist...!!");
                System.out.println("\t\t\t\tPlease enter correct Unique Id...!!\n");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next();
                clearscreen();
                continue;
            }
            pst=con.prepareStatement("select * from movie where columnid=?");
            pst.setInt(1,Mid);
            rs=pst.executeQuery();
            if(rs.next()){
                showMyTicket(num);
                System.out.println("\n\n\t\t\t\tPress \'c\' to cancle ticket...");
                System.out.print("\t\t\t\t");
                char t=sc.next().charAt(0);
                if(t!='c'&& t!='C'){
                    System.out.println("\n\t\t\t\tTicket is not cancelled....");
                    System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                    sc.next();
                    return;
                }
                PreparedStatement pst1=con.prepareStatement("update movie set seat=? where columnid=?");
                pst1.setInt(1,seat+rs.getInt("seat"));
                pst1.setInt(2,Mid);
                if(pst1.executeUpdate()<=0){
                    System.out.println("\t\t\t\tException occured.........");
                }
            }
            pst=con.prepareStatement("delete from customer where Cid=?");
            pst.setInt(1,num);
            if(pst.executeUpdate()>0){
                System.out.println("\t\t\t\t-------------------------------");
                System.out.println("\t\t\t\tThe ticket is cancelled.......!!");
                return;
            }
            else{
                System.out.println("\t\t\t\t-------------------------------");
                System.out.println("\t\t\t\tThe Unique Id doesn\'t Exist...!!");
                System.out.println("\t\t\t\tPlese enter correct Unique Id...!!\n");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next();
                clearscreen();
            }
        }
    }
    catch(Exception e){
        System.out.println("\t\t\t\t----------------------------");
        System.out.println("\t\t\t\tSome Error occured.....!!!!!!\n\n");
    }
}


public static void CheckSeat() throws SQLException{
    try{
        PreparedStatement pst=con.prepareStatement("Select * from movie");
        ResultSet rs=pst.executeQuery();
        System.out.print("\n\n\n\n");
        System.out.println("\t\t\t\t\t--CHECK SEATS--");
        System.out.println("\t\t\t\t-------------------------");
        System.out.println("\t\t\t\t| MOVIE NAME\t| SEATS\t|");
        System.out.println("\t\t\t\t-------------------------");
        while(rs.next()){
            System.out.println("\t\t\t\t| "+rs.getString(2)+"\t| "+rs.getInt(7)+"\t|");
        } 
        System.out.println("\t\t\t\t-------------------------\n\n");
    }
    catch(Exception e){
        System.out.println("\t\t\t\t----------------------------");
        System.out.println("\t\t\t\tSome Error occured.....!!!!!!\n\n");
    }
}


public static void AddNewMovie() throws SQLException{
    try{
        System.out.print("\n\n\n\n");
        System.out.println("\t\t\t\tEnter Movie name: ");
        sc.nextLine();
        System.out.print("\t\t\t\t");
        String name=sc.nextLine();
        System.out.println("\t\t\t\tFormat: ");
        System.out.print("\t\t\t\t");
        String format=sc.nextLine();
        System.out.println("\t\t\t\tDate(YYYY/MM/DD): ");
        System.out.print("\t\t\t\t");
        String date=sc.nextLine();
        System.out.println("\t\t\t\tTime(HH:MM:SS): ");
        System.out.print("\t\t\t\t");
        String time=sc.nextLine();
        System.out.println("\t\t\t\tPrice: ");
        System.out.print("\t\t\t\t");
        Double price=sc.nextDouble();
        System.out.println("\t\t\t\tSeats: ");
        System.out.print("\t\t\t\t");
        int seat=sc.nextInt();
        DateFormat df=new SimpleDateFormat("HH:MM:SS");
        java.util.Date t=df.parse(time);
        java.util.Date d=new java.util.Date(date);
        java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
        java.sql.Time t1=new java.sql.Time(t.getHours(),t.getMinutes(),t.getSeconds());
        PreparedStatement pst=con.prepareStatement("insert into movie (name,format,showdate,showtime,price,seat) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        pst.setString(1,name);
        pst.setString(2,format);
        pst.setDate(3,d1);
        pst.setTime(4,t1);
        pst.setDouble(5,price);
        pst.setInt(6,seat);
        if(pst.executeUpdate()>0){
            System.out.println("\t\t\t\t---------------------------------");
            System.out.println("\t\t\t\tMovie Added Successfully........");
        }
        else{
            System.out.println("\t\t\t\t-----------------------------");
            System.out.println("\t\t\t\tMovie is NOT added........!!!");
        }
    }
    catch(Exception e){
        System.out.println("\t\t\t\tException occured.....\n\n");
    }
}

public static void DeleteMovie(){
        try{
            System.out.println("\n\n\n\n");
            System.out.println("\t\t\t\tEnter Movie ID(0 to exit): ");
            System.out.print("\t\t\t\t");
            int num=sc.nextInt();
            if(num==0){
                return;
            }
            PreparedStatement pst=con.prepareStatement("select * from movie where columnid=?");
            pst.setInt(1,num);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
                System.out.println("  \t|ID\t|\tNAME\t|\tFORMAT\t| SHOW DATE\t| SHOW TIME\t| PRICE\t| AVL.SEAT(s)\t|");
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
                System.out.println("  \t| "+rs.getInt(1)+"\t| "+rs.getString(2)+"\t| "+rs.getString(3)+"\t| "+rs.getDate(4)+"\t| "+rs.getTime(5)+"\t| "+rs.getDouble(6)+"\t|\t"+rs.getInt(7)+"\t|");
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
            }
            else{
                System.out.println("\t\t\t\tInvalid Movie ID.....!!!");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next();
                return;
            }
            System.out.println("\n\n\t\t\t\tPress \'d\' to delete");
            System.out.print("\t\t\t\t");
            char c=sc.next().charAt(0);
            if(c=='d'||c=='D'){
                pst=con.prepareStatement("delete from movie where columnid=?");
                pst.setInt(1,num);
                if(pst.executeUpdate()>0){
                    System.out.println("\t\t\t\tMovie deleted.....");
                }
                else{
                    System.out.println("\t\t\t\tMovie is NOT deleted.....!!!");
                    System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                    sc.next();
                }
            }
            else{
                System.out.println("\t\t\t\tMovie is NOT deleted.....!!!");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next(); 
                return;
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured.....\n\n");
        }
    }

    public static int ShowMovieList() throws SQLException{
        int num=0;
        try{
            PreparedStatement pst=con.prepareStatement("select * from movie");
            ResultSet rs=pst.executeQuery();
            System.out.println("\n\n\n");
            System.out.println("  \t\t\t\t\t\tDETAILS OF ALL MOVIE");
            System.out.println("  \t---------------------------------------------------------------------------------------------------------");
            System.out.println("  \t| SNO.\t|ID\t|\tNAME\t|\tFORMAT\t| SHOW DATE\t| SHOW TIME\t| PRICE\t| AVL.SEAT(s)\t|");
            System.out.println("  \t---------------------------------------------------------------------------------------------------------");
            int i=1;
            while(rs.next()){
                System.out.println("  \t| "+i+"\t| "+rs.getInt(1)+"\t| "+rs.getString(2)+"\t| "+rs.getString(3)+"\t| "+rs.getDate(4)+"\t| "+rs.getTime(5)+"\t| "+rs.getDouble(6)+"\t|\t"+rs.getInt(7)+"\t|");
                System.out.println("  \t---------------------------------------------------------------------------------------------------------");
                num++;
                i++;
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured "+e.getMessage());
        }
        return num;
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