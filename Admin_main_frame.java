package CollegeComplaintManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/*Admin_main_frame
* this class enable navigatoion between admin_complaint view and return Object table
*/
public class Admin_main_frame implements ActionListener{

    JFrame frame_mainform;
    JLabel label_skcticon,label_welcome,label1_name;
    JLabel  label2_day,label3_date,label_viewicon, label_sentence1;
    JLabel   label_missingicon,label_view, label_sentence2,location_title,label_logout;
    JButton next2_button,next3_button,logout_button;
    String name="ADMIN";
    Date currentdate = new Date();       

    public Admin_main_frame()                                //constructor
    {

        frame_mainform = new JFrame("SELECT PORTAL");
        ImageIcon skctIcon= new ImageIcon(new ImageIcon(
                "F:\\Javaprogram\\src\\CollegeComplaintManagement\\clg.jpeg").
                getImage().getScaledInstance(600, 100, Image.SCALE_DEFAULT));
        label_skcticon = new JLabel();
        label_skcticon.setIcon(skctIcon);
        frame_mainform.add(label_skcticon);
        label_skcticon.setBounds(140,20,600,100);

        label_welcome = new JLabel("WELCOME..!!");
        frame_mainform.add(label_welcome);
        label_welcome.setFont(new Font("Times New Roman",Font.ITALIC,30));
        label_welcome.setOpaque(true);
        // label2.setBackground(new Color(0x50A2D2));
        label_welcome.setBounds(30,195,180,30);

        label1_name = new JLabel(name);
        frame_mainform.add(label1_name);
        label1_name.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label1_name.setBounds(80,235,180,30);

        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        label2_day = new JLabel(day.format(currentdate));
        frame_mainform.add(label2_day);
        label2_day.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label2_day.setBounds(80,340,150,30);

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        label3_date = new JLabel(date.format(currentdate));
        frame_mainform.add(label3_date);
        label3_date.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label3_date.setBounds(80,385,150,30);

        ImageIcon viewIcon= new ImageIcon(new ImageIcon(
                "F:\\Javaprogram\\src\\CollegeComplaintManagement\\views.jpeg")
                .getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));        
        label_viewicon = new JLabel();
        label_viewicon.setIcon(viewIcon);
        frame_mainform.add(label_viewicon);
        label_viewicon.setBounds(300,200,100,100);

        label_sentence1 = new JLabel("See your happy status here");
        frame_mainform.add(label_sentence1);
        label_sentence1.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_sentence1.setBounds(440,225,300,20);

       
        ImageIcon next1= new ImageIcon(new ImageIcon(
                "F:\\Javaprogram\\src\\CollegeComplaintManagement\\next.jpeg")
                .getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        next2_button = new JButton();
        frame_mainform.add(next2_button);
        next2_button.setIcon(next1);
        next2_button.setFont(new Font("Times New Roman",Font.BOLD,20));
        next2_button.setBounds(665,265,50,50);
        next2_button.addActionListener(this);

        label_view = new JLabel("View Status here..!!");
        frame_mainform.add(label_view);
        label_view.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_view.setBounds(440,270,200,20);
               
        ImageIcon missIcon = new ImageIcon(new ImageIcon(
                "F:\\Javaprogram\\src\\CollegeComplaintManagement\\missing.jpeg").getImage().
                getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        label_missingicon = new JLabel();
        label_missingicon.setIcon(missIcon);
        frame_mainform.add(label_missingicon);
        label_missingicon.setBounds(300,370,100,100);

        label_sentence2 = new JLabel("Catch your missing items here");
        frame_mainform.add(label_sentence2);
        label_sentence2.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_sentence2.setBounds(440,385,300,20);


        next3_button = new JButton();
        frame_mainform.add(next3_button);
        next3_button.setIcon(next1);
        next3_button.setFont(new Font("Times New Roman",Font.BOLD,20));
        next3_button.setBounds(665,430,50,50);
        next3_button.addActionListener(this);

        location_title = new JLabel("Enter misssing details.!!");
        frame_mainform.add(location_title);
        location_title.setFont(new Font("Times New Roman",Font.ITALIC,20));
        location_title.setBounds(440,430,200,20);

        ImageIcon logoutIcon= new ImageIcon(new ImageIcon(
                "F:\\Javaprogram\\src\\CollegeComplaintManagement\\power.jpeg")
                .getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        logout_button = new JButton();
        frame_mainform.add(logout_button);
        logout_button.setIcon(logoutIcon);
        logout_button.setFont(new Font("Times New Roman",Font.BOLD,20));
        logout_button.setBounds(30,530,25,25);
        
        label_logout = new JLabel("LOGOUT");
        frame_mainform.add(label_logout);
        label_logout.setFont(new Font("Times New Roman",Font.ITALIC,15));
        label_logout.setBounds(62,533,100,20);


        frame_mainform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame_mainform.setSize(900,650);
        frame_mainform.setLayout(null);
        frame_mainform.setLocationRelativeTo(null);
        frame_mainform.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        if(o.equals(next2_button)){
           new Admin_complaint_view().frame();
           this.frame_mainform.dispose();
        }
        if(o.equals(next3_button)){
            new Return_object();
            this.frame_mainform.dispose();
        }
    }
    public static void main(String[] args) {
        new Admin_main_frame();
    }
}

