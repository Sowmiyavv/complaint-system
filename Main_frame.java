package CollegeComplaintManagement;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
/*Main_frame
* this class enable navigatoion to complaint registration and view frame
*/
public class Main_frame extends JFrame implements ActionListener {
    JLabel label_clg_icon, label_welcome, label_complaint_icon,label4,
            label_complaint_here,label_view_icon;
    JLabel label_status2,label_status1,label_missing_icon,label10
            ,label_missing,label_logout,label_location_cateroy,label_name;
    JLabel label_day,label_date;
    JButton jbutton_next_missing,jbutton_next_complaint,jbutton_next_view,
            jbutton_logout,jbutton_college,jbutton_hostel;
    JButton jbutton_college_view,jbutton_hostel_view;
    JDialog d1;
    Beans bd=new Beans();
    Date currentdate = new Date();
    //constructor
    public Main_frame()
    {
        ImageIcon skctIcon= new ImageIcon(new ImageIcon("C:\\Users\\hp"
                + "\\Downloads\\clg.jpeg").getImage().
                getScaledInstance(600, 100, Image.SCALE_DEFAULT));
        label_clg_icon = new JLabel();
        label_clg_icon.setIcon(skctIcon);
        add(label_clg_icon);
        label_clg_icon.setBounds(140,20,600,100);

        label_welcome = new JLabel("WELCOME..!!");
        add(label_welcome);
        label_welcome.setFont(new Font("Times New Roman",Font.ITALIC,30));
        label_welcome.setOpaque(true);
       // label2.setBackground(new Color(0x50A2D2));
        label_welcome.setBounds(30,180,180,30);

        label_name = new JLabel(bd.getStr_name());
        add(label_name);
        label_name.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label_name.setBounds(80,220,180,30);

        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        label_day = new JLabel(day.format(currentdate));
        add(label_day);
        label_day.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label_day.setBounds(80,300,150,30);

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        label_date = new JLabel(date.format(currentdate));
        add(label_date);
        label_date.setFont(new Font("Times New Roman",Font.ITALIC,22));
        label_date.setBounds(80,340,150,30);

        ImageIcon complaintIcon= new ImageIcon(new ImageIcon("C:\\Users\\hp\\"
                + "Downloads\\complaint.jpeg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        label_complaint_icon = new JLabel();
        label_complaint_icon.setIcon(complaintIcon);
        add(label_complaint_icon);
        label_complaint_icon.setBounds(300,160,100,100);

        label4 = new JLabel("Greviance's are our first concern");
        add(label4);
        label4.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label4.setBounds(440,165,300,20);

        label_complaint_here = new JLabel("Complains here...!!");
        add(label_complaint_here);
        label_complaint_here.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_complaint_here.setBounds(440,210,300,20);

        ImageIcon next1= new ImageIcon(new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\"
                + "next.jpeg").getImage().getScaledInstance(50,
                        50, Image.SCALE_DEFAULT));
        jbutton_next_complaint = new JButton();
        add(jbutton_next_complaint);
        jbutton_next_complaint.setIcon(next1);
        jbutton_next_complaint.setFont(new Font("Times New Roman",Font.BOLD,20));
        jbutton_next_complaint.setBounds(660,200,50,50);
        jbutton_next_complaint.addActionListener(this);

        ImageIcon viewIcon= new ImageIcon(new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement"
                + "\\views.jpeg").getImage().
                getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        label_view_icon = new JLabel();
        label_view_icon.setIcon(viewIcon);
        add(label_view_icon);
        label_view_icon.setBounds(300,300,100,100);

        label_status1 = new JLabel("See your happy status here");
        add(label_status1);
        label_status1.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_status1.setBounds(440,305,300,20);

        jbutton_next_view = new JButton();
        add(jbutton_next_view);
        jbutton_next_view.setIcon(next1);
        jbutton_next_view.setFont(new Font("Times New Roman",Font.BOLD,20));
        jbutton_next_view.setBounds(660,340,50,50);
        jbutton_next_view.addActionListener(this);

        label_status2 = new JLabel("View Status here..!!");
        add(label_status2);
        label_status2.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_status2.setBounds(440,350,200,20);

        ImageIcon missIcon = new ImageIcon(new ImageIcon("C:\\Users\\hp"
                + "\\Downloads\\missing.jpeg").getImage()
                .getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        label_missing_icon = new JLabel();
        label_missing_icon.setIcon(missIcon);
        add(label_missing_icon);
        label_missing_icon.setBounds(300,460,100,100);

        label10 = new JLabel("Catch your missing items here");
        add(label10);
        label10.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label10.setBounds(440,465,300,20);

        jbutton_next_missing = new JButton();
        add(jbutton_next_missing);
        jbutton_next_missing.setIcon(next1);
        jbutton_next_missing.setFont(new Font("Times New Roman",Font.BOLD,20));
        jbutton_next_missing.setBounds(660,500,50,50);
        jbutton_next_missing.addActionListener(this);

        label_missing = new JLabel("Enter misssing details.!!");
        add(label_missing);
        label_missing.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_missing.setBounds(440,510,200,20);

        ImageIcon logoutIcon= new ImageIcon(new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\power.jpeg").getImage().
                getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jbutton_logout = new JButton();
        add(jbutton_logout);
        jbutton_logout.setIcon(logoutIcon);
        jbutton_logout.setFont(new Font("Times New Roman",Font.BOLD,20));
        jbutton_logout.setBounds(30,530,25,25);
        jbutton_logout.addActionListener(this);
        label_logout  = new JLabel("LOGOUT");
        add(label_logout);
        label_logout.setFont(new Font("Times New Roman",Font.ITALIC,15));
        label_logout.setBounds(62,533,100,20);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*Location_complanit
    * display dialogbox to choose by
    */
    public void Location_complaint()
    {
        d1 = new JDialog();

        d1.setSize(280,260);
        d1.setLayout(null);
        d1.setLocationRelativeTo(null);
        d1.setVisible(true);

        label_location_cateroy= new JLabel("SELECT LOCATION");

        d1.add(label_location_cateroy);
        label_location_cateroy.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_location_cateroy.setBounds(30,45,200,20);

        jbutton_college = new JButton("COLLEGE");
        d1.add(jbutton_college);
        jbutton_college.setBounds(90,100,100,20);
        jbutton_college.setFont(new Font("Times New Roman",Font.ITALIC,15));
        jbutton_college.addActionListener(this);

        jbutton_hostel = new JButton("HOSTEL");
        d1.add(jbutton_hostel);
        jbutton_hostel.setBounds(90,140,100,20);
        jbutton_hostel.setFont(new Font("Times New Roman",Font.ITALIC,15));
        jbutton_hostel.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(jbutton_next_complaint))
        {
            Location_complaint();
        }
        if(e.getSource().equals(jbutton_college))
        {
            d1.dispose();
            this.dispose();
            new Complaintcollege();
        }
        if(e.getSource().equals(jbutton_hostel))
        {
            d1.dispose();
            this.dispose();
            new Complainthostel();
        }
        if(e.getSource().equals(jbutton_logout))
        { 
            new Student_Signin();
            this.dispose();
        }
        if(e.getSource().equals(jbutton_next_missing)){
            new MissingObject_Creaion_frame();
            this.dispose();
        }
        if(e.getSource().equals(jbutton_next_view)){
            Location_view();
        }
        if(e.getSource().equals(jbutton_college_view)){
            new Complaint_college_view().Frame();
            d1.dispose();
            this.dispose();
        }
        if(e.getSource().equals(jbutton_hostel_view)){
            new Complaint_hostel_view().Frame();
            d1.dispose();
            this.dispose();
        }
    }
    /*Location View
    * display dialogbox to select type of complaint
    */
    void Location_view(){
       
        d1 = new JDialog();

        d1.setSize(280,260);
        d1.setLayout(null);
        d1.setLocationRelativeTo(null);
        d1.setVisible(true);

        label_location_cateroy= new JLabel("SELECT LOCATION");

        d1.add(label_location_cateroy);
        label_location_cateroy.setFont(new Font("Times New Roman",Font.ITALIC,20));
        label_location_cateroy.setBounds(30,45,200,20);

        jbutton_college_view = new JButton("COLLEGE");
        d1.add(jbutton_college_view);
        jbutton_college_view.setBounds(90,100,100,20);
        jbutton_college_view.setFont(new Font("Times New Roman",Font.ITALIC,15));
        jbutton_college_view.addActionListener(this);

        jbutton_hostel_view = new JButton("HOSTEL");
        d1.add(jbutton_hostel_view);
        jbutton_hostel_view.setBounds(90,140,100,20);
        jbutton_hostel_view.setFont(new Font("Times New Roman",Font.ITALIC,15));
        jbutton_hostel_view.addActionListener(this);
    }   
    public static void main(String[] args) {
       new Main_frame();
    }
}
