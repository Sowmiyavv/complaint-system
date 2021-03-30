package CollegeComplaintManagement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/* OTP class
*  This class sends Otp to user mail and stores detail in database
*/
class otp_java implements ActionListener
{
   
    //otp Frame
    JFrame otp_frame = new JFrame("Student");
    JButton submit = new JButton();
    JButton Back = new JButton();
    JLabel otp = new JLabel();
    JLabel Resend = new JLabel();
    JTextField otp_number = new JTextField();
    static String Generate_Number = "";
    String Rollno,Name,Email,Gender,Password;
       
    /*Otp_frame
    *  Create frame
    *  calls otp sender and Otp generator function 
    */
    public void Otp_Frame(String rollno,String username,String email,String gender,String password)
    {
        
        Container con = otp_frame.getContentPane();
        con.setLayout(null);
        
        Rollno = rollno;
        Name = username;
        Email = email;
        Gender = gender;
        Password = password;
        
        otp.setFont(new java.awt.Font("Tahoma", 1, 36));
        otp.setText("OTP");
        otp.setBounds(378,143,104,44);
        con.add(otp);
        
        otp_number.setFont(new java.awt.Font("Tahoma", 0, 18));
        otp_number.setText("");
        otp_number.setBounds(252,232,336,35);
        con.add(otp_number);
        
        Resend.setFont(new java.awt.Font("Tahoma", 0, 14));
        Resend.setText("Resend");
        Resend.setBounds(540,287,53,17);
        con.add(Resend);
       
        Resend.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Resend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Resend_Otp();
            }
        });
        
        submit.setFont(new java.awt.Font("Tahoma", 0, 18));
        submit.setText("SUBMIT");
        submit.setBounds(359,325,129,43);
        con.add(submit);
        
        Back.setFont(new java.awt.Font("Tahoma", 0, 18));
        Back.setText("Back");
        Back.setBounds(100,465,100,30);
        con.add(Back);
        
        Generate_Number = Otp_Generate();     //return values from otp_generate is stores
        
        Otp_Sender();
        
        Back.addActionListener(this);
        submit.addActionListener(this);
         
        otp_frame.getContentPane().setBackground(Color.white);
        otp_frame.setSize(750,730);
        otp_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        otp_frame.setVisible(true);
    }
      /*Opt_Checker
    * check whether Otp generated and enter are same
    * IF same , call Detail_Add_Database methode
    * ELSE , Displays Error
    */
    public void Otp_Checker(){
        
        String otp_num = otp_number.getText();
        if(otp_num.equals(Generate_Number))
        {
            Detail_Add_Database();
            new Main_frame();
            this.otp_frame.dispose();
        }
        else{
            JOptionPane.showMessageDialog(otp_frame,"Invalid OTP Number");
        }
    }
    /* Otp_Generate
    * Create random string with integers
    * @return Generate_number
    */
    public String Otp_Generate(){
        
        Random rand = new Random();
        for(int i=0;i<6;i++)
        {
            int otp_num = rand.nextInt(9);
            Generate_Number = Generate_Number + Integer.toString(otp_num);
        }
        return Generate_Number;
    }
    /* Otp_Sender
    *  Send otp to admin through gmail
    */
    public void Otp_Sender()
    {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");     // setting simple mail transfer protocol 
        properties.setProperty("mail.smtp.port", "465");                // setting port value 465
        properties.put("mail.smtp.socketFactory.port", "465");          //
        properties.put("mail.smtp.socketFactory.class",                 //
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");          //setting authentication true 
        Session session = Session.getDefaultInstance(properties
                , new javax.mail. Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){ 
                        return new PasswordAuthentication("18tuit117@skct.edu.in","skct9876");
                    }
                });                                                //authentication of user and password
        try 
        {
            MimeMessage message  = new MimeMessage(session);
            message.setFrom(new InternetAddress("18tuit17@skct.edu.in"));     // setting from address 
            message.addRecipient(Message.RecipientType.TO           
                    , new InternetAddress(Email));                            // Setting to address
            message.setText("OTP Number - '"+Generate_Number+"'");            // text message
            message.setSubject("Regesiter For Complaint From");               // mail subject  
            Transport.send((message));                                         // sends mail
            System.out.println("Sent message successfully...");
        }catch (MessagingException max){
            max.printStackTrace();
        }
    }
    /*Detail_Add_Database
     *add detail to the database
    */
    public void Detail_Add_Database()
    {
        try 
        {   //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp","project","livends@2020");
            //Creating Statement
            Statement  st = conn.createStatement();
            //insert Query
            String query="insert into Student_Database"
                    + " values('"+Name+"','"+Rollno+"',"
                    + "'"+Email+"','"+Gender+"','"+Password+"')";
            //Executing Query
            st.executeUpdate(query);
            conn.close();
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == submit)
            Otp_Checker();
        else if(ae.getSource() == Back)
        {
            new Student_Signup();
            this.otp_frame.dispose();
        }
    }
    /*Resent_Otp
    * resend send mail to admin   
    */
    public void Resend_Otp()
    {
        Generate_Number = "";
        Generate_Number = Otp_Generate();
        Otp_Sender();
    }
}

public class Student_Otp extends otp_java
{
    public static void main(String[] args)
    {
        otp_java O = new otp_java();
        O.Otp_Frame("18tuit117","Sabarinath","18tuit117@skct.edu.in","male","@sabari1234");
    }
}

