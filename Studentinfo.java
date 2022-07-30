package studentmgmtsys;

import java.sql.*; 
import java.util.*;

public class Studentinfo {
	
   public static String generateid()
   {
	   // generating 6digit alphanumeric id;
       String random="abcdeghijklmnopqrstuvwxyz";
       String random1="0123456789";
		 String sid="";
		 Random rand = new Random();
		 int length = 6;
		
		 char text[] =new char[length];
		
		 
		 for(int i=0;i<length;i++)
		{
			 if(i == 1 || i==2)
			 {	 
		 	 text[i]=random.charAt(rand.nextInt(random.length()));
		    }
			 else
				 text[i]=random1.charAt(rand.nextInt(random1.length()));
				 
		 }
		 for(int i=0;i < text.length ;i++)
		{
			sid += text[i];
		 }
		 return sid;
   }
	

	public static void main(String args[])throws Exception
	{
		//connection to database in myssql server
		//type your database name below in place of studentmgmtsystem
		String url="jdbc:mysql://localhost:3306/studentmgmtsystem";
		String uname = "root";
		//type your password below in  place of tarun
		String password = "tarun";
		String query = "";
		
	    Class.forName("com.mysql.cj.jdbc.Driver");
	  //getConnection used for connecting to database
	    Connection con = DriverManager.getConnection(url,uname,password);
	    Statement st = con.createStatement();
	    
	    query="create table if not exists student_details(ID VARCHAR(6),NAME CHAR(30),AGE INTEGER,CITY CHAR(20), PRIMARY KEY(ID))";
	    int create = st.executeUpdate(query);
	
	  
	  int p=1;
	  
	  do
	  {
		  System.out.println("Student Management System");
		  System.out.println("1.Add 2.Update 3.Delete 4.View 5.Save and Quit");
		  
		 
		  int n;
		  Scanner sc =new Scanner(System.in);
		  n=sc.nextInt();
		  
		  switch(n)
		  {            //adding new student details
		     case 1: System.out.println("Add new student details [name,age,city] separated by comma;");
		             String names = sc.next();
		             String slist[]= names.split(",");
		             String name = slist[0];
		             int age = Integer.valueOf(slist[1]);
		             String city=slist[2];
		             
		             String id = generateid();
		            
		     		 //query for adding student into table
		     		 query="INSERT INTO student_details VALUES("+'"'+id+'"'+','+'"'+name+'"'+','+age+','+'"'+city+'"'+")";
		     		 int addrow = st.executeUpdate(query);
		     		 System.out.println("Student details successfully added with id="+id);
		     		  break;
		    	    
		    	     // for updating the values
		     case 2:  System.out.println("To update details input in format id,name,age,city");
		              String names1 = sc.next();
                      String slist1[]= names1.split(",");
                      int age1 = Integer.valueOf(slist1[2]);
                      
                      query="UPDATE student_details set NAME='"+slist1[1]+"',AGE="+age1+",CITY='"+slist1[3]+"'where ID='"+slist1[0]+"'";
                      
                      int update = st.executeUpdate(query);
                      
                      System.out.println("Successfully updated...");
		    	       break;
                      //deleting the records
		     case 3:  System.out.println("Enter id to delete");
		              String del_id=sc.next();
		              
		              query="DELETE FROM student_details where id="+"'"+del_id+"'";
		              int delrow = st.executeUpdate(query);
		              System.out.println("1 row deleted");
		              break;
		    	     
		    	     //viewing options
		     case 4: System.out.println("Enter your choice 1.entire data 2.with condition ");
		             int choice;
		             choice = sc.nextInt();
		             switch(choice)
		             {                             //view all data
		                 case 1:                   query = "select * from student_details";
	    	                                       ResultSet rs = st.executeQuery(query);
	    	     
	    	                                       while(rs.next())
	    	                                       {
	    	    	                                 String sdet = "Id: "+ rs.getString(1)+" "+"Name :"+rs.getString(2)+" "+"Age :"+rs.getInt(3)+" "+"City :"+rs.getString(4);
	    	    	 
	    	    	                                 System.out.println(sdet);
	    	                                       }
	    	                                          break;
	    	                                       
	    	                                      // for string type enter ex: city = "banglore"    
		                 case 2:                   System.out.println("Enter condition");  
		                                           String condition =sc.next();
		                                           
		                                           query="select * from student_details where "+condition;
		                                           ResultSet rs1 = st.executeQuery(query);
		                          	    	     
	    	                                       while(rs1.next())
	    	                                       {
	    	    	                                 String sdet1 = "Id: "+ rs1.getString(1)+" "+"Name :"+rs1.getString(2)+" "+"Age :"+rs1.getInt(3)+" "+"City :"+rs1.getString(4);
	    	    	 
	    	    	                                 System.out.println(sdet1);
	    	                                       }
	    	                                          break;
	    	                                          
		                                     
		             } break;                              
		                                           
		    	       //save data and close application
		     case 5:   System.out.println("Data saved and quitting...");
		               System.out.println("Application closed");
		               System.out.println("------THANK YOU------");
		               p=5;
		               break;
		    	
		     default : System.out.println("choose valid option");
		               break;
		  }
		  
	  }while(p < 5);
	  
	}

}
