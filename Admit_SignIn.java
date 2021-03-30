package CollegeComplaintManagement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/*Admint_SignIn 
  This class gets admin's emailID, password and approves sigin 
*/
final class Admit_SignIn implements ActionListener
{
    JFrame Login = new JFrame("Log In");
    JLabel Login_label = new JLabel("Log In");
    JLabel email_label = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Register = new JLabel();
    JTextField email_text = new JTextField();
    JPasswordField password_text = new JPasswordField();
    JLabel image = new JLabel();
    JButton submit = new JButton();
    
    
    //Admit_SignIn constructor call submit_frame() methode
    
    Admit_SignIn()
    {
        submit_frame();
    }
    //submit_frame create Frame and display button 
    public void submit_frame()
    {
        Container con = Login.getContentPane();
        con.setLayout(null);
        
        Login_label.setFont(new java.awt.Font("Tahoma", 1, 36));
        Login_label.setText("Sign In");
        Login_label.setBounds(465, 85, 131, 44);
        con.add(Login_label);
        
        email_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_label.setText("Email Id");
        email_label.setBounds(465,179,310,25);
        con.add(email_label);
        
        email_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_text.setBounds(465 ,209 ,310 ,25);
        con.add(email_text);
        
        password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_label.setText("Password");
        password_label.setBounds(465 , 259, 310, 25);
        con.add(password_label);
        
        password_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_text.setBounds(465 , 289, 310, 25);
        con.add(password_text);
        
        submit.setFont(new java.awt.Font("Tahoma" , 0, 18));
        submit.setText("Submit");
        submit.setBounds(465, 349, 130, 43);
        con.add(submit);
        
        submit.addActionListener(this); 
        
        ImageIcon image1 = new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\signin.jpeg");
        image.setIcon(image1);
        image.setBounds(60, 110, 295, 350);
        con.add(image);
        
        Register.setFont(new java.awt.Font("Tahoma", 0, 14));
        Register.setText("Create an account");
        Register.setBounds(120, 500, 310, 30);
        Register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Register.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent evt) {
                Register_page(evt);
            }
        });
        con.add(Register);
        
        Login.getContentPane().setBackground(Color.white);
        Login.setSize(870, 630);
        Login.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Login.setVisible(true);
    }    
    public void Register_page(MouseEvent evt)
    {
        Admit_Signup Frame = new Admit_Signup();              //Creating for SignUp class
        this.Login.dispose();                                //Closing Login frame
    }
    
    @Override
    public void actionPerformed(ActionEvent a)
    {
        Valid_Checker();                          //checks whether the inputs are valid
    }
    /* Valid_Checker methode 
    * checks whether the input of email_text and password_text is not NULL,
    * IF NUll , Display error Message
    * ELSE , Call Database_Check methode
    */
    public void Valid_Checker()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
        
        if("".equals(email)){
            JOptionPane.showMessageDialog(Login, "Enter The Email");
        }
        else if("".equals(Password)){
            JOptionPane.showMessageDialog(Login, "Enter The Password");   
        }
        else{
            Database_Check();    
        }
    }
    /* Database_Check Methode
    *   Checks whether the entered email and password matches with database
    *   If Matches , Calls Admin_main_frame
    *   ELSE , Displays Error Message
    */
    public void Database_Check()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/bootcamp",
                    "project","livends@2020");
            System.out.println("Connection has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from admin_database where email='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next())                              
            {
                if(Password.equals(rs.getString("password"))){
                    new Admin_main_frame();                       //invoke constructor of Admin_main_frame
                    this.Login.dispose();                         //closes Login Frame
                }
                else{
                    JOptionPane.showMessageDialog(password_text, "Password Invalid");
                }
            }
            else{
                JOptionPane.showMessageDialog(password_text,"Email not found");
            }
            conn.close();
            System.out.println("Connection Closed"); 
        }catch (ClassNotFoundException | SQLException | HeadlessException e){
            System.out.println(e);
        }                            
    }
}