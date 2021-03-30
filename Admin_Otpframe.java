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
*  This class sends Otp to default admin and stores admin detail in database
*/
class Admin_otpframe implements ActionListener
{
    //otp Frame
    JFrame otp_frame = new JFrame();
    JButton submit = new JButton();
    JButton Back = new JButton();
    JLabel otp = new JLabel();
    JLabel Resend = new JLabel();
    JTextField otp_number = new JTextField();
    static String Generate_Number = "";
    static String Name,Email,Password="Nil",Rollno="Nil"; 
    
    /*Otp_frame
    *  Create frame
    *  calls otp sender and Otp generator function 
    */
    public void Otp_Frame(String username,String email,String password)
    {
        
        Container con = otp_frame.getContentPane();
        con.setLayout(null);
        
        Name = username;
        Email = email;
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
        Back.setText("Next");
        Back.setBounds(100,465,100,30);
        con.add(Back);
        
        Generate_Number = Otp_Generate();            //return values from otp_generate is stores
        
        Otp_Sender(Generate_Number);
        
        Back.addActionListener(this);
        submit.addActionListener(this);
         
        otp_frame.getContentPane().setBackground(Color.white);
        otp_frame.setSize(860,570);
        otp_frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        otp_frame.setVisible(true);
    }
    /*Opt_Checker
    * check whether Otp generated and enter are same
    * IF same , call Detail_Add_Database methode
    * ELSE , Displays Error
    */
    public void Otp_Checker(String Name,String email,String Password)
    { 
        String otp_num = otp_number.getText();
        if(otp_num.equals(Generate_Number))        //equal user user input and Generated otp
        {
            Detail_Add_Database(Name,email,Password);
            new  Admin_main_frame();
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
    public String Otp_Generate()
    {
        Random rand = new Random();                 //
        for(int i=0;i<6;i++)
        {
            int otp_num = rand.nextInt(10);
            Generate_Number = Generate_Number + Integer.toString(otp_num);
        }
        return Generate_Number;
    }
    /* Otp_Sender
    *  Send otp to admin through gmail
    */
    public void Otp_Sender(String OTP)
    {
       Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");     // setting simple mail transfer protocol 
        properties.setProperty("mail.smtp.port", "465");                // setting port value 465
        properties.put("mail.smtp.socketFactory.port", "465");          //
        properties.put("mail.smtp.socketFactory.class",                 //
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");          //setting authentication true 
 
        Session session = Session.getDefaultInstance(properties,
                new javax.mail. Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){ 
                        return new PasswordAuthentication("18tuit117@skct.edu.in"
                                ,"skct9876");
                    }
                });                                                         //authentication of user and password
        try 
        {
            MimeMessage message  = new MimeMessage(session);                
            message.setFrom(new InternetAddress("18tuit17@skct.edu.in"));   // setting from address 
            message.addRecipient(Message.RecipientType.TO,                  
                    new InternetAddress("18tucs135@skct.edu.in"));          // Setting to address
            message.setText("This is your OTP "+OTP);                       // text message
            message.setSubject("Complaint Verification OTP");               // mail subject
            Transport.send((message));                                      // sends mail
            //System.out.println("Sent message successfully...");
        }
        catch (MessagingException max)
        {
            max.printStackTrace();
        }
    }
    /*Detail_Add_Database
     *add detail to the database
    */
    public void Detail_Add_Database(String Name,String email,String Password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost/bootcamp","project","livends@2020");      
            System.out.println("Connection to SQLite has been established.");
            Statement  st = conn.createStatement();
            String query="insert into admin_database values('"+Name+"',"
                    + "'"+email+"','"+Password+"')";
            st.executeUpdate(query);
            conn.close();
           
        }
        catch (Exception e)
        {
            System.out.println("error "+e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == submit)
            Otp_Checker(Name,Email,Password);
        else if(ae.getSource() == Back)
        {
            Admit_Signup s = new Admit_Signup();
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
        Otp_Sender(Generate_Number);
    } 
}

   
