package miniprojects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class ATM1{
	String name,address,query;
	long balance=0,contact;
	int newpin;
	Scanner sc = new Scanner(System.in);
	 static Connection dbconnect() throws ClassNotFoundException, SQLException{
		String url,user,pass;
		url = "jdbc:mysql://localhost:3306/pooja";	
		user = "root";
		pass = "Vs_vishnu@";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,user,pass);
		return conn;}
	void openAccount() throws ClassNotFoundException, SQLException {
		System.out.println("Enter your name,address,balance,contact:");
		name= sc.next();
		address = sc.next();
		balance = sc.nextLong();
		contact = sc.nextLong();
		System.out.println("Enter your pin:");
		newpin =  sc.nextInt();	
		query = "insert into AccountDetails(name,pin,balance,address,contact) values(?,?,?,?,?)"; 
		Connection conn = ATM1.dbconnect();
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, newpin);
		ps.setLong(3, balance);
		ps.setString(4, address);
		ps.setLong(5, contact);
		int i = ps.executeUpdate();
		if(i>0){
	   System.out.println("Account opened Successfully");}else{System.out.println("Account opening failed");}}
void balance() throws ClassNotFoundException, SQLException{
    int id;
    query = " select * from accountdetails where id=?";
    System.out.println("Enter id Check Balance :");
    id = sc.nextInt();
    Connection conn = ATM1.dbconnect();
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    while (rs.next()){
    	System.out.println("Balance Amount");
        System.out.println(rs.getLong("balance"));}}
void withdraw() throws ClassNotFoundException, SQLException {
	    int id;
	    query = "SELECT * FROM accountdetails WHERE id=?";
	    System.out.println("Enter your id");
	    id = sc.nextInt();
	    Connection conn = ATM1.dbconnect();
	    PreparedStatement st = conn.prepareStatement(query);
	    st.setInt(1, id);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
	    System.out.println("Balance Amount");
	    balance = rs.getLong("balance");
	    System.out.println(balance);}
	    System.out.println("Enter the amount to withdraw:");
	    long amount = sc.nextLong();
	    if (amount > 0 && amount <= balance){
	    balance -= amount;
	    String updateQuery = "UPDATE accountdetails SET balance = ? WHERE id = ?";
	    PreparedStatement ps = conn.prepareStatement(updateQuery) ;   
	    ps.setLong(1, balance);
	    ps.setInt(2, id);
	    int rowsUpdated = ps.executeUpdate(); 
	    if (rowsUpdated > 0) {
	    System.out.println("Withdrawal successful. Updated balance: " + balance);
	    } else {System.out.  println("Withdrawal failed.");}}
	   else { System.out.println("Invalid withdrawal amount or insufficient balance.");}}
void deposit() throws ClassNotFoundException, SQLException{
    int id;
    query = "SELECT * FROM accountdetails WHERE id=?";
    System.out.println("Enter your id");
    id = sc.nextInt();
    Connection conn = ATM1.dbconnect();
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
    System.out.println("Balance Amount");
    balance = rs.getLong("balance");
    System.out.println(balance);} 
    System.out.println("Enter the amount to Deposit:");
    int deposit = sc.nextInt();
    String updateQuery = "UPDATE accountdetails SET balance = ? WHERE id = ?";
    PreparedStatement ps1 = conn.prepareStatement(updateQuery);
    balance = balance + deposit;  
    ps1.setLong(1, balance);
    ps1.setInt(2, id);
    ps1.executeUpdate();
    System.out.println("Your Money has been successfully deposited");  
    System.out.println("");}  
void changepin() throws ClassNotFoundException, SQLException{
	int id;  
    query = "SELECT * FROM accountdetails WHERE id=?";
    System.out.println("Enter your id");
    id = sc.nextInt();
    Connection conn = ATM1.dbconnect();
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id); 
    ResultSet rs = ps.executeQuery();
    while (rs.next()){System.out.println("pin");}
    String updateQuery = "UPDATE accountdetails SET pin = ? WHERE id = ?";
    System.out.println("Enter new pin:");
    newpin = sc.nextInt();
    PreparedStatement ps1 = conn.prepareStatement(updateQuery);
    ps1.setInt(1, newpin);
    ps1.setInt(2, id);
    ps1.executeUpdate();
    System.out.println("Pin change successfully...");}
void delete() throws ClassNotFoundException, SQLException {
	int id;
	query = "delete from accountdetails where id =?";
	System.out.println("Enter yor id");
	id = sc.nextInt();
	Connection conn = ATM1.dbconnect();
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setInt(1,id);
	int i = ps.executeUpdate();
	System.out.println(i+" row deleted");}}
    public class Atm_machine_optimized_code {
	public static void main(String[] args)  throws ClassNotFoundException, SQLException {
    int choice;
    ATM1 a =  new ATM1();
    Scanner sc = new Scanner(System.in);
	do{
	System.out.println("**********Welcome to HDFC BANK ATM**************\n1.Open Account\n2.Check Balance\n3.Withdraw Money\n4.Deposit\n5.Change Pin\n6.delete\n7.Exit\nEnter your choice:");
	choice = sc.nextInt();
	switch(choice){
	    case 1:a.openAccount();break;
		case 2:a.balance();break;	
		case 3:a.withdraw();break;
		case 4:a.deposit();break;
		case 5:a.changepin();break;
		case 6:a.delete();break;
		case 7:System.out.println("Thank you Exit....."); break;
		default:System.out.println("Invalid input...");break;}}while(choice<7);}}
