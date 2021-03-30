package CollegeComplaintManagement;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;


/* SignUp 
    GET's admin register detail and invokes Admin_Otpframe
*/
final class Admit_Signup extends Admin_otpframe implements ActionListener
{
    JFrame main_frame = new JFrame("Sign Up");
    JButton jbutton_signup_register = new JButton();
    JLabel signup = new JLabel();
    JLabel label_signup_name = new JLabel();
    JLabel email_label = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Jpassword_signup_retypepassword_label = new JLabel();
    JLabel label_signup_image = new JLabel();
    JTextField jtextdfeild_signup_name = new JTextField();
    JTextField jtextfield_signup_email = new JTextField();
    JPasswordField Jpassword_signup_password = new JPasswordField();
    JPasswordField Jpassword_signup_retypepassword = new JPasswordField();
    JLabel Go_Login_Frame = new JLabel();
    String str_signup;                      
    String email;
    String password,rollno; 
  
    public Admit_Signup()                             //Constructor
    {
        
        Container con = main_frame.getContentPane();
        con.setLayout(null);
        
        signup.setFont(new java.awt.Font("Tahoma", 1, 36));
        signup.setText("Sign UP");
        signup.setBounds(110,70,142,52);
        con.add(signup);
        
        label_signup_name.setFont(new java.awt.Font("Tahoma", 0, 18));
        label_signup_name.setText("Your Name");
        label_signup_name.setBounds(110,147,310,25);
        con.add(label_signup_name);
        
        jtextdfeild_signup_name.setFont(new java.awt.Font("Tahoma", 0, 18));
        jtextdfeild_signup_name.setBounds(110,177,310,30);
        con.add(jtextdfeild_signup_name);
        
        email_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_label.setText("Your Email");
        email_label.setBounds(110,232,310,25);
        con.add(email_label);
        
        jtextfield_signup_email.setFont(new java.awt.Font("Tahoma", 0, 18));
        jtextfield_signup_email.setBounds(110,262,310,30);
        con.add(jtextfield_signup_email);
        
        password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_label.setText("Your Password");
        password_label.setBounds(110,317,310,25);
        con.add(password_label);
        
        Jpassword_signup_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_password.setBounds(110,347,310,30);
        con.add(Jpassword_signup_password);
        
        Jpassword_signup_retypepassword_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_retypepassword_label.setText("Repeat Your Password");
        Jpassword_signup_retypepassword_label.setBounds(110,402,310,25);
        con.add(Jpassword_signup_retypepassword_label);
        
        Jpassword_signup_retypepassword.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_retypepassword.setBounds(110,432,310,30);
        con.add(Jpassword_signup_retypepassword);
        
        jbutton_signup_register.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbutton_signup_register.setText("Register");
        jbutton_signup_register.setBounds(110,502,130,43);
        con.add(jbutton_signup_register);
        
        Jpassword_signup_retypepassword.setFont(new java.awt.Font("Tahoma", 0, 18));
        Jpassword_signup_retypepassword.setBounds(110,432,310,30);
        con.add(Jpassword_signup_retypepassword);
        
        Go_Login_Frame.setFont(new java.awt.Font("Tahoma", 0, 14));
        Go_Login_Frame.setText("Click Here To Login");
        Go_Login_Frame.setBounds(580,500,310,30);
        Go_Login_Frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Go_Login_Frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Login_page(evt);
            }
        });
        con.add(Go_Login_Frame);
        
        ImageIcon label_signup_image1 = new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\signup.jpeg");
        label_signup_image.setIcon(label_signup_image1);
        label_signup_image.setBounds(515, 147, 288, 335);
        con.add(label_signup_image);
        
        jbutton_signup_register.addActionListener(this);
        
        main_frame.getContentPane().setBackground(Color.white);
        main_frame.setSize(870,630);
        main_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }
    /* Valid_Checker Methode
    * Check whether user input's are vaild and not Null
    * IF not Valid or equal's NUll , Then Displays Error Message
    * ELSE , Then call Otp_frame Methode
    */
    public void Valid_checker()
    {
        str_signup = jtextdfeild_signup_name.getText();
        email = jtextfield_signup_email.getText();
        password = Jpassword_signup_password.getText();
        email = email.toLowerCase();
        String repeat_password = Jpassword_signup_retypepassword.getText();
        String skct_email_id = (email.contains("@")) ?
                email.substring(email.indexOf("@")+1).toLowerCase() : "";
        if("".equals(str_signup))                                 //checks UserName whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The UserName");
          else if("".equals(email))                               //checks Useremail whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Email ID");
        else if(!email.contains("@"))                            //validates email for @sign
            JOptionPane.showMessageDialog(main_frame,"Email Id Is Wrong");
        else if(!(skct_email_id.equals("skct.edu.in")            //validates email for Domain
                    || skct_email_id.equals("skct.edu.in.test-google-a.com") 
                    || skct_email_id.equals("skct.edu.in.test-google-a.com"))){
                JOptionPane.showMessageDialog(main_frame,"Doesn't Match Skct Email Id");
        }
        else if(Already_Exists_Email()){                         //checks whether user already exists
            JOptionPane.showMessageDialog(main_frame,"Already Exists Email");
        }
        else if("".equals(password)){                            //Checks password whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Password");
        }
        else if("".equals(repeat_password)){                     //Checks repeat_password whether NULL
            JOptionPane.showMessageDialog(main_frame,"Enter The Repeat password");
        }
        else if(!password.equals(repeat_password)){             //Checks whether password and repeat_Password or Same                        
            JOptionPane.showMessageDialog(main_frame,"Password Doesn't Match");
        }
        else if( password.length() <= 8){                        //Validates password lenght
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n "
                    + "- Almost 8 Lettter\n - "
                    + "Atleast One Special Character\n - Atleast One Lower and "
                    + "Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[0-9]).*")){            //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n -"
                    + " Almost 8 Lettter\n -"
                    + " Atleast One Special Character\n - Atleast One Lower and"
                    + " Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[a-z]).*")){           //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n -"
                    + " Almost 8 Lettter\n -"
                    + " Atleast One Special Character\n - "
                    + "Atleast One Lower and "
                    + "Upper Case\n - Atleast One Number");
        }
        else if(!password.matches("(?=.*[A-Z]).*")){           //Validate Password Constraint
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - "
                    + "Almost 8 Lettter\n - Atleast One Special Character\n - "
                    + "Atleast One Lower and Upper Case\n - Atleast One Number");
        }
        else{
            Admin_otpframe Otp = new Admin_otpframe();
            Otp.Otp_Frame(str_signup, email, password);
            this.main_frame.dispose();
        }
    }
    /*Already_Exist_Email
    * Check whether the entered email is already present in the database
    * @return False    ,  IF present
    * @return True     ,  ElSE
    */
    public boolean Already_Exists_Email()
    {
       
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn ;
            conn= DriverManager.getConnection("jdbc:mysql://localhost/bootcamp"
                    ,"project","livends@2020");
            System.out.println("Connection to has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from admin_database where mail='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                return true;
            }
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        } 
        return false;
    }    
    @Override
    public void actionPerformed(ActionEvent a) {   
        if(a.getSource().equals(jbutton_signup_register)){            
            Valid_checker();
        }
    }
    /*Login_page
    * invokes constructor of Admit_SignIn 
    */
    public void Login_page(MouseEvent evt){
        Admit_SignIn admit_SignIn = new Admit_SignIn();
        this.main_frame.dispose();
    }
}