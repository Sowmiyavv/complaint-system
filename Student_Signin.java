package CollegeComplaintManagement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
/*Student_Sign
* Approve application based on user mail and password
* Send a
*/
class Student_Signin implements ActionListener
{
    JFrame Login = new JFrame("Student");
    JLabel Login_label = new JLabel("Log In");
    JLabel email_label = new JLabel();
    JLabel password_label = new JLabel();
    JLabel Register = new JLabel();
    JTextField email_text = new JTextField();
    JPasswordField password_text = new JPasswordField();
    JLabel image = new JLabel();
    JButton submit = new JButton(); 
    LocalDate dates[]=new LocalDate[10];
    Beans bd=new Beans();
    //constructor
    Student_Signin()
    {
        submit_frame();
    }
    /*submit_frame
    * Display the sign in frame
    */
    public void submit_frame()
    {
        Container con = Login.getContentPane();
        con.setLayout(null);
        
        Login_label.setFont(new java.awt.Font("Tahoma", 1, 36));
        Login_label.setText("Sign In");
        Login_label.setBounds(465,85,131,44);
        con.add(Login_label);
        
        email_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_label.setText("Email Id");
        email_label.setBounds(465,179,310,25);
        con.add(email_label);
        
        email_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        email_text.setBounds(465,209,310,25);
        con.add(email_text);
        
        password_label.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_label.setText("Password");
        password_label.setBounds(465,259,310,25);
        con.add(password_label);
        
        password_text.setFont(new java.awt.Font("Tahoma", 0, 18));
        password_text.setBounds(465,289,310,25);
        con.add(password_text);
        
        submit.setFont(new java.awt.Font("Tahoma", 0, 18));
        submit.setText("Submit");
        submit.setBounds(465,349,130,43);
        con.add(submit);
        
        submit.addActionListener((ActionListener)this);
        
        ImageIcon image1 = new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\signin.jpeg");
        image.setIcon(image1);
        image.setBounds(60,110, 295, 350);
        con.add(image);
        
        Register.setFont(new java.awt.Font("Tahoma", 0, 14));
        Register.setText("Create an account");
        Register.setBounds(120,500,310,30);
        Register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Register_page(evt);
            }
        });
        con.add(Register);
        
        Login.getContentPane().setBackground(Color.white);
        Login.setSize(830,700);
        Login.setLocationRelativeTo(null);
        Login.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Login.setVisible(true);
    }
    
    
    //close current frame and opens Signup frame
    public void Register_page(MouseEvent evt)
    {
        Student_Signup Student_signup_to_Student_Signin = new Student_Signup();
        this.Login.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent a)
    {
        Valid_Checker();
    }
    /*Remainder_college
    * compare today's date and last remainder date from the complaint_college Database
    * send mail based on conditions to all admin
    */
    void Remainder_college(){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql://"
                    + "localhost/bootcamp", "project","livends@2020");
            //creatig Statement
            Statement smt=conn.createStatement();
            //creating PreparedStatement
            PreparedStatement ps=conn.prepareStatement("update complaint_"
                    + "college set last_reminder=? where UID=?");
            //Executing Query
            ResultSet admin=smt.executeQuery("select email from admin_database");
            System.out.println(admin.getFetchSize());
            admin.last();                                   //moves to last row
            String admin_emails[]=new String[admin.getRow()]; //no of rows
            admin.beforeFirst();                              
            int i=0;
            //store all admin mail id in admin_emails
            while(admin.next()){
                admin_emails[i++]=admin.getString("email");
            }
            System.out.println(Arrays.toString(admin_emails)+"   "+i);
            //Executing Query
           ResultSet res=smt.executeQuery("Select * from "
                   + "complaint_college where RegNo='"+bd.getStr_rollno()+"'");
           while(res.next()){
                ps.setDate(1,Date.valueOf(LocalDate.now()));
                ps.setString(2,res.getString("UID"));
                 //finding difference between to day
                Period diff=Period.between(res.getDate("last_"
                        + "reminder").toLocalDate(), LocalDate.now());
                int diff_date=diff.getDays();
                if(diff_date>=5&&"Pending".equals(res.getString("Status"))){
                    System.out.println(res.getString("UID"));
                    System.out.print("testing admin");
                    int j=0;
                    //sending Mail to all admins
                    while(j<i){
                        Remaindermail(bd.getStr_name(),res.getString("complaint"),admin_emails[j]);
                        j++;
                    }
                }
                ps.executeUpdate();
            }
            System.out.println("Testing0");   
        } catch (Exception e){
            System.out.println("             + "+e);
        }
    }
    /*Remainder_hostel
    * compare today's date and last remainder date from the complaint_hostel Database
    * send mail based on conditions to all admin
    */
     void Remainder_hostel(){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp", "project","livends@2020");
            //Creating Statement
            Statement smt=conn.createStatement();
            //Creating PreparedStatement
            PreparedStatement ps=conn.prepareStatement("update complaint_hostel"
                    + " set last_reminder=? where UID=?");
            ResultSet admin=smt.executeQuery("select email from admin_database");
            admin.last();                                             //moves to last row
            String admin_emails[]=new String[admin.getRow()];         //no of row is given as size
            admin.beforeFirst();                                      //admin moves to beforefirst row
            int i=0;
            while(admin.next()){
                admin_emails[i++]=admin.getString("email");
            }
            System.out.println(Arrays.toString(admin_emails)+"   "+i);
            ResultSet res=smt.executeQuery("Select * from "
                   + "complaint_hostel where RegNo='"+bd.getStr_rollno()+"'");
            while(res.next()){
                System.out.print("testing");
                ps.setDate(1,Date.valueOf(LocalDate.now()));
                ps.setString(2,res.getString("UID"));
                //finding difference between to day
                Period diff=Period.between(res.getDate("last_reminder").toLocalDate()
                        , LocalDate.now());
                int diff_date=diff.getDays();
                System.out.println(diff_date);
                //if diff_date is greater then 5 then mail is send 
                if(diff_date>=5&&"Pending".equals(res.getString("Status"))){
                    int j=0;
                    while(j<i){
                        Remaindermail(bd.getStr_name(),res.getString("Complaint"),admin_emails[j]);
                        j++;
                    }
                }
                //last remainder is updated in database
                ps.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("             + "+e);
        }
    }
    void Remaindermail(String user,String complaint,String mail_id){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
 
        Session session = Session.getDefaultInstance(properties, new javax.mail. Authenticator(){ 
            protected PasswordAuthentication getPasswordAuthentication(){ 
                return new PasswordAuthentication("18tuit117@skct.edu.in","skct9876"); }});
        try 
        {
            MimeMessage message  = new MimeMessage(session);
            message.setFrom(new InternetAddress("18tuit17@skct.edu.in"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail_id));
            message.setText("The "+user+"'s is fan com yet to be resolved");
            message.setSubject("Remainder Mail");
            Transport.send((message));
            System.out.println("Sent message successfully...");
        }
        catch (Exception max)
        {
            max.printStackTrace();
        }
    }
    public void Valid_Checker()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
        //check whether email is NULL
        if("".equals(email))
            JOptionPane.showMessageDialog(Login,"Enter The Email");
        //check whether password is Null
        else if("".equals(Password))
            JOptionPane.showMessageDialog(Login,"Enter The Password");
        //check whether the pssword in database matches
        else if(Database_Check())
            JOptionPane.showMessageDialog(Login,"Invalid Email And Password");
        else
        {
            Remainder_college();
            Remainder_hostel();
            new Main_frame();
            this.Login.dispose();
        }
    }
    //check whether the user has registered in database 
    public boolean Database_Check()
    {
        String email = email_text.getText();
        String Password = password_text.getText();
        
        System.out.println(email);
        System.out.println(Password);
          
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp", "project","livends@2020");
            //creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from Student_Database"
                    + " where Student_Email='"+email+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next())
            {   
                //check whether password matches
                if(Password.equals(rs.getString("Student_Password"))){
                    bd.setStr_name(rs.getString("Student_Name"));
                    bd.setStr_rollno(rs.getString("Student_Rollno"));
                    bd.setStr_email(rs.getString("Student_Email"));
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
 
        return true;
    }
    public static void main(String[] args) {
        new Student_Signin();
    }
}