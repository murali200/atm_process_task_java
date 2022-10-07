package Atm;
import java.sql.*;
import java.util.Scanner;
public class LoadCashToAtm 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int alpha ;
		System.out.println("LoadCashtoATM");
		System.out.println("ShowCustomerDetails");
		System.out.println("ShowATMOperations");
		alpha = sc.nextInt();  
        Class.forName("com.mysql.jdbc.Driver");
        Connection ca =DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","root");
		switch (alpha)
        {
						    case 1:
							PreparedStatement cc = ca.prepareStatement("insert into loadcashtoatm values(?,?,?)");
							int Denomination=sc.nextInt();
							int Number=sc.nextInt();
							int value=Denomination*Number;
							cc.setInt(1, Denomination);
						    cc.setInt(2, Number);
							cc.setInt(3, value);
							int cm = cc.executeUpdate();
							break;
							
							case 2:
							PreparedStatement mu=ca.prepareStatement("select * from bankcustomerdetail");
							ResultSet rm= mu.executeQuery();
							while(rm.next())
							{
							System.out.println(rm.getInt(1)+" "+rm.getString(2)+" "+rm.getInt(3)+" "+rm.getInt(4));
							}
							break;
							case 3:
								int alpha1;
								System.out.println("Check Balance");
								System.out.println("Withdraw Money");
								System.out.println("Transfer Money");
								System.out.println("Check ATM Balance");
								alpha1 =sc.nextInt();  
								switch(alpha1)
								{
											case 1:
											System.out.print("Enter AccountNo: ");
											int acc=sc.nextInt();
											PreparedStatement ra=ca.prepareStatement("select accbal from bankcustomerdetail where accno=(?)");
											ra.setInt(1, acc);
											ResultSet nm= ra.executeQuery();
											while(nm.next())
											{
											System.out.println("account balance:"+ nm.getInt(1));
											}
									        break;
									        
											case 2:
												System.out.println("enter the acc number:");
												int account=sc.nextInt();
												System.out.println("enter your pin nnumber:");
												int pin=sc.nextInt();
												System.out.println("enter the amount to withdrawal:");
												int withdraw=sc.nextInt();
												Class.forName("com.mysql.jdbc.Driver");
										        Connection uu =DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","root");
												Statement yy=uu.createStatement();
												uu.setAutoCommit (false);
												ResultSet rs=yy.executeQuery("select pin from bankcustomerdetail where accno="+account);
												rs.next();
												int pinno=rs.getInt(1);
													if(pinno==pin);
													{
														ResultSet ry=yy.executeQuery("select accbal from bankcustomerdetail where accno="+account);
														ry.next();
														int accobal=ry.getInt(1);
														if(accobal>withdraw)
														{
															int we=yy.executeUpdate("update bankcustomerdetail set accbal=accbal-"+withdraw+" where accno="+account);
															int wr=yy.executeUpdate("update loadcashtoatm set value=sum(value)-"+withdraw+" where accno="+);
														}
											         }						
						                          break;
											
											case 3:
												System.out.println("enter your account.no:");
												int your_accno=sc.nextInt();
												System.out.println("enter your beneficiary acc.no");
												int benef_accno=sc.nextInt();
												System.out.println("enter the amount to transfer:");
												int trans_amt=sc.nextInt();
												Class.forName("com.mysql.jdbc.Driver");
										        Connection mm =DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","root");
												Statement ss=mm.createStatement();
												mm.setAutoCommit (false);
												ResultSet rr=ss.executeQuery("select accbal from bankcustomerdetail where accno="+your_accno);
												rr.next();
													int accubal=rr.getInt(1);
													if(accubal>trans_amt);
														{
															
															int sm=ss.executeUpdate("update bankcustomerdetail set accbal=accbal-"+trans_amt+" where accno="+your_accno);
															int ms=ss.executeUpdate("update bankcustomerdetail set accbal=accbal+"+trans_amt+" where accno="+benef_accno);
															
															if(sm==1&&ms==1)
															{
																mm.commit();
																System.out.println("amount transferred");
															}
															else
															{
																mm.rollback();
																System.out.println("rollback");
															}
														}
														break;
											case 4:
												PreparedStatement sd=ca.prepareStatement("select * from loadcashtoatm");
												ResultSet sk= sd.executeQuery();
												while(sk.next())
												{
													System.out.println(sk.getInt(1)+" "+sk.getInt(2)+" "+sk.getInt(3));
												}
											break;
							            }
								break;
	     }
	}
}
