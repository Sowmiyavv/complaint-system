package CollegeComplaintManagement;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
/*Student
* 
*/
class Student_Signup implements ActionListener
{
    //Sign Up From
    JFrame main_frame = new JFrame("Student");
    JButton Register = new JButton();
    JLabel signup = new JLabel();
    JLabel Roll_No = new JLabel();
    JLabel Name_label = new JLabel();
    JLabel email_label = new JLabel();
    JLabel Gender = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Repeat_password_label = new JLabel();
    JLabel image = new JLabel();
    JTextField Roll_No_Text = new JTextField();
    JTextField Your_name = new JTextField();
    JTextField Your_email = new JTextField();
    JPasswordField Your_password = new JPasswordField();
    JPasswordField Repeat_password = new JPasswordField();
    JLabel Go_Login_Frame = new JLabel();
    JRadioButton Male = new JRadioButton("Male");    
    JRadioButton Female = new JRadioButton("Female");   
    String username,email,password,rollno,gender; 
    //constructor
    Student_Signup()
    {
       Register_Frame();
    }
    public void Register_Frame()
    {
        
        Container con = main_frame.getContentPane();
        con.setLayout(null);
        
        signup.setFont(new java.awt.Font("Tahoma", 1, 36));
        signup.setText("Sign UP");
        signup.setBounds(40,0,142,52);
        con.add(signup);
        
        Roll_No.setFont(new java.awt.Font("Tahoma", 0, 18));//147
        Roll_No.setText("Roll No");
        Roll_No.setBounds(110,62,310,25);
        con.add(Roll_No);
        
        Roll_No_Text.setFont(new java.awt.Font("Tahoma", 0, 18));
        Roll_No_Text.setBounds(110,92,310,30);
        con.add(Roll_No_Text);
        
        Name_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        Name_label.setText("Your Name");
        Name_label.setBounds(110,147,310,25);
        con.add(Name_label);
        
        Your_name.setFont(new java.awt.Font("Tahoma", 0, 18));
        Your_name.setBounds(110,177,310,30);
        con.add(Your_name);
        
        email_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_label.setText("Your Email");
        email_label.setBounds(110,232,310,25);
        con.add(email_label);
        
        Your_email.setFont(new java.awt.Font("Tahoma", 0, 18));
        Your_email.setBounds(110,262,310,30);
        con.add(Your_email);
        
        Gender.setFont(new java.awt.Font("Tahoma", 0, 18));
        Gender.setText("Your Email");
        Gender.setBounds(110,317,310,25);
        con.add(Gender);
        
        Male.setFont(new java.awt.Font("Tahoma", 0, 18));
        Male.setBounds(110,347,155,30);
        con.add(Male);
        
        Female.setFont(new java.awt.Font("Tahoma", 0, 18));
        Female.setBounds(265,347,155,30);
        con.add(Female);
        
        password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_label.setText("Your Password");
        password_label.setBounds(110,402,310,25);
        con.add(password_label);
        
        Your_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Your_password.setBounds(110,432,310,30);
        con.add(Your_password);
        
        Repeat_password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        Repeat_password_label.setText("Repeat Your Password");
        Repeat_password_label.setBounds(110,487,310,25);
        con.add(Repeat_password_label);
        
        Repeat_password.setFont(new java.awt.Font("Tahoma", 0, 18));
        Repeat_password.setBounds(110,517,310,30);
        con.add(Repeat_password);
        
        Register.setFont(new java.awt.Font("Tahoma", 0, 18));
        Register.setText("Register");
        Register.setBounds(110,575,130,43);
        con.add(Register);
        
        Go_Login_Frame.setFont(new java.awt.Font("Tahoma", 0, 14));
        Go_Login_Frame.setText("Click Here To Login");
        Go_Login_Frame.setBounds(580,550,310,30);
        Go_Login_Frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //onclicks call login_page methode
        Go_Login_Frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Login_page(evt);
            }
        });
        con.add(Go_Login_Frame);
        
        ImageIcon image1 = new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\signup.jpeg");
        image.setIcon(image1);
        image.setBounds(515, 147, 288, 405);
        con.add(image);
        
        Register.addActionListener((ActionListener) this);
        
        main_frame.getContentPane().setBackground(Color.white);
        main_frame.setSize(830,730);
        main_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }
    public void Valid_checker()
    {
        rollno = Roll_No_Text.getText();
        username = Your_name.getText();
        email = Your_email.getText();
        password = Your_password.getText();
        email = email.toLowerCase();
        String repeat_password = Repeat_password.getText();
        
        String skct_email_id = (email.contains("@")) ?
                email.substring(email.indexOf("@")+1).toLowerCase() : "";
        if(Already_Exists_Rollno()){
            JOptionPane.showMessageDialog(main_frame,"Already Exists Rollno");
        }
        //check whether rollno is NULL
        else if("".equals(rollno)){
            JOptionPane.showMessageDialog(main_frame,"Enter The RollNo");
        }
        //check whether username is NULL
        else if("".equals(username)){
            JOptionPane.showMessageDialog(main_frame,"Enter The UserName");
        }
        //check whether email is NULL
        else if("".equals(email)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Email ID");
        }
        //validate email
        else if(!email.contains("@")){
            JOptionPane.showMessageDialog(main_frame,"Email Id Is Wrong");
        }
        //validate domain of mail id
        else if(!(skct_email_id.equals("skct.edu.in")
                || skct_email_id.equals("skct.edu.in.test-google-a.com")
                || skct_email_id.equals("skct.edu.in.test-google-a.com"))){
            JOptionPane.showMessageDialog(main_frame,"Doesn't Match Skct Email Id");
        }
        else if(Already_Exists_Email(email)){
            JOptionPane.showMessageDialog(main_frame,"Already Exists Email");
        }
        //check whether any one of gender is selected
        else if(!(Male.isSelected() || Female.isSelected())){
            JOptionPane.showMessageDialog(main_frame,"Enter The Gender");
        }
        //checks whether password is not NULL
        else if("".equals(password)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Password");
        }
        //checks whether repeated password is not NULL
        else if("".equals(repeat_password)){
            JOptionPane.showMessageDialog(main_frame,"Enter The Repeat password");
        }
        //checks whether password and repeated password matches
        else if(!password.equals(repeat_password)){
            JOptionPane.showMessageDialog(main_frame,"Password Doesn't Match");
        }
        //checks password length
        else if( password.length() < 8)
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength "
                    + "Low\n - Almost 8 Lettter\n - Atleast One Special"
                    + " Character\n - Atleast One Lower and Upper Case\n "
                    + "- Atleast One Number");
        //checks password constraints 
        else if(!password.matches("(?=.*[0-9]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n"
                    + " - Almost 8 Lettter\n - Atleast One Special Character\n "
                    + "- Atleast One Lower and Upper Case\n - Atleast One Number");
        else if(!password.matches("(?=.*[a-z]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - "
                    + "Almost 8 Lettter\n - Atleast One Special Character\n -"
                    + " Atleast One Lower and Upper Case\n - Atleast One Number");
        else if(!password.matches("(?=.*[A-Z]).*"))
            JOptionPane.showMessageDialog(main_frame,"PassWord Stength Low\n - Almost "
                    + "8 Lettter\n - Atleast One Special Character\n - Atleast One"
                    + " Lower and Upper Case\n - Atleast One Number");
        else{
            gender = (Male.isSelected()) ? "male" : "female";
            Student_Otp Otp = new Student_Otp();
            Otp.Otp_Frame(rollno,username, email, gender, password);
            this.main_frame.dispose();
        }
    }
    //checks whether the user has already registered with rollno
    public boolean Already_Exists_Rollno()
    {
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp","project","livends@2020");
            //Creating Statements          
            Statement smt=conn.createStatement();
            String q = "Select * from student_database where Student_Rollno='"+rollno+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                return true;
            }
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        } 
        return false;
    }
     //checks whether the user has already registered with email
    public boolean Already_Exists_Email(String email)
    {
        try 
        {
            String url = "jdbc:sqlite:C:/Users/admin/Documents/NetBeansProjects/SQLiteStudio-3.2.1/database/Bootcamp.db";
            Connection  conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            Statement smt=conn.createStatement();
            String q = "Select * from Student_Database where Email='"+email+"'";
            ResultSet rs=smt.executeQuery(q);
            if(rs.next())
                return true;
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } 
        return false;
    }
    
    public void actionPerformed(ActionEvent a) 
    {
        Valid_checker();
    }
    //dispose current page and opens signin frame
    public void Login_page(MouseEvent evt)
    {
        Student_Signin Frame = new Student_Signin();
        this.main_frame.dispose();
    }
    public static void main(String[] args) {
        new Student_Signup();
    }
}