
package Ali_proget;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AACC {
    
    
 private static final String user ="root";
 private static final String pass ="";
 private static final String url="jdbc:mysql://localhost/Superma";
 static  ResultSet re;
 public static Scanner s=new Scanner(System.in);
 static PreparedStatement st=null;
 public static String username  ;
  
 
 public static Connection  f() throws SQLException
 {
     String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
   
         Connection con =(Connection) DriverManager.getConnection(url+unicode, user, pass);
        
         return con ;
 }    
    
public static void op(String n)
{
    new JOptionPane().showMessageDialog(null,n);
    
}    
    public static int getid () throws SQLException
    {
        String ss="select max(id) from Superma.snaf";
        
        st =  f().prepareStatement(ss);
       re= st.executeQuery(ss);
       if(re.next())
       {
           
           return re.getInt(1);
       }
        return 1;
    }
    public static int getidfatora () throws SQLException
    {
        String ss="select max(fid) from Superma.myfatora";
        
        PreparedStatement st =  f().prepareStatement(ss);
       ResultSet r= st.executeQuery(ss);
       if(r.next())
       {
           
           return r.getInt(1)+1;
       }
        return 1;
    }
    
    public static ResultSet info(String g )
    {
        try{
      String ss2 = "select * from snaf where type = '"+g+"'";
      
       st =  f().prepareStatement(ss2);
       re= st.executeQuery(ss2);
         
           return re; 
        
            }catch(Exception e)
            {
                AACC.op(e.getMessage());
                return null;
            }
     
    }
    public static void fillcompo(JComboBox com)
    {
        try{
            String sss ="select type from snaf";
            Connection c=AACC.f();
             Statement st = null;
             st= c.prepareStatement(sss);
             re=st.executeQuery(sss);
             
             while(re.next())
             {
                 String name = re.getString(1);
                 com.addItem(name);
             }
            
        }catch(Exception e)
        {
            new JOptionPane().showMessageDialog(null,e.getMessage());
        }
        
    }
    
    public static void addmem(String n,String p)
    {
        try{
           if ("".equals(n)||"".equals(p)) op("ادخل الاسم والباسوورد");
           else{
        AACC.st=AACC.f().prepareStatement("INSERT INTO `member` (`user`, `password`) VALUES ('"+n+"', '"+p+"')");
        AACC.st.execute();
        op("تم اضافة مستخدم جديد ");
           }
        }
       
        catch(Exception e)
        {
            op(e.getMessage());
            System.out.println("---"+e.getMessage());
        }
    }
    
    public static void deletmem(String g )
    {
        try{
        if ("".equals(g)) op("ادخل  الباسوورد");
           else{
        String sss2 = "DELETE FROM `member` WHERE user ='"+g+"'";
         st =  f().prepareStatement(sss2);
         st.execute(sss2);
          new  JOptionPane().showMessageDialog(null,"تم الحذف بنجاح ");
        }
        }catch(Exception e)
                {
                   op(e.getMessage()) ;
                }
    }
    
    public static boolean log(String name,String pass)
    {
        try{
         String ss2 = "select password from member where user='"+name+"'";
      
       st =  f().prepareStatement(ss2);
       re= st.executeQuery(ss2);
       if(re.next())
       {
           if (re.getString(1).equals(pass.toString()))
           {
               username=name;
               return true;
           }
           else return false;
       }
       else 
           return false;
        }
        catch(Exception e)
        {
            op(e.getMessage());
            return false;
        }
        
    }
    public static void deletftora()
    {
        try{
        Statement s;
        String sss2 = "DELETE FROM `myfatora`";
         s = f().createStatement();
         s.executeUpdate(sss2);
          new  JOptionPane().showMessageDialog(null,"تم الحذف بنجاح ");
        }catch(Exception e)
                {
                   op(e.getMessage()) ;
                }
    }
     public static void deletsanf()
    {
        try{
        Statement s;
        String sss2 = "delete from snaf";
         s = f().createStatement();
         s.executeUpdate(sss2);
          new  JOptionPane().showMessageDialog(null,"تم الحذف بنجاح ");
        }catch(Exception e)
                {
                   op(e.getMessage()) ;
                }
    }
    
}


 