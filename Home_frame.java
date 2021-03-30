package CollegeComplaintManagement;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
class Page
{
    JFrame Page_Frame = new JFrame("Complaint Management");
    JButton Admin_Button = new JButton();
    JButton Student_Button = new JButton();
    JLabel label_student=new JLabel("STUDENT");
    JLabel label_admin=new JLabel("ADMIN");
    JLabel label_title=new JLabel("COLLEGE COMPLAINT MANAGEMENT");
    //Page Construct Calls Frame methode
    Page()
    {
        Frame();
    }
    //Frame methode is used to display frame 
    public void Frame()
    {
        Container con = Page_Frame.getContentPane();
        con.setLayout(null);
        
        ImageIcon image1 = new ImageIcon("F:\\Javaprogram\\src\\"
                + "CollegeComplaintManagement\\Admin_.jpeg");
        Admin_Button.setFont(new java.awt.Font("Tahoma", 0, 18));
        Admin_Button.setIcon(image1);
        Admin_Button.setBounds(139,135,250,300);
        con.add(Admin_Button);
        label_admin.setFont(new java.awt.Font("Tahoma", 0, 18));
        label_admin.setBounds(220, 460, 150, 20);
        con.add(label_admin);
        //Onclick of Admin_Button Admin_page is called
        Admin_Button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){Admin_page();
            }
        });
        
        ImageIcon image2 = new ImageIcon("F:\\Javaprogram\\src\\"
                + "CollegeComplaintManagement\\student_.jpeg");
        Student_Button.setFont(new java.awt.Font("Tahoma", 0, 18));
        Student_Button.setIcon(image2);
        Student_Button.setBounds(480,135,250,300);
        con.add(Student_Button);
        label_student.setFont(new java.awt.Font("Tahoma", 0, 18));
        label_student.setBounds(570, 460, 150, 20);
        con.add(label_student);
        
        
        label_title.setFont(new java.awt.Font("Tahoma", 0, 20));
        label_title.setBounds(300, 0, 350, 20);
        con.add(label_title);
        //Onclick of Student_Button ,Studnet page methode is called
        Student_Button.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent evt){
                Student_page();}
        });
           
        Page_Frame.getContentPane().setBackground(Color.white);
        Page_Frame.setSize(900,730);
        Page_Frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Page_Frame.setVisible(true);
    }    
    //Admin_page methode invokes Admint_SignIn Constructor and dispose current Frame
    public void Admin_page()
    {
        new Admit_SignIn();
        this.Page_Frame.dispose();
    }
    //Student_page methode invokes Student_SignIn Constructor and dispose current Frame
    public void Student_page()
    {
        Student_Signin Student_page = new Student_Signin();
        this.Page_Frame.dispose();
    }
}
//Home_frame creates anonymous class of Page class 
public class Home_frame 
{
    public static void main(String[] args)
    {
        new Page();
    }
}

