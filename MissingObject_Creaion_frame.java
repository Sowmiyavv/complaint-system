package CollegeComplaintManagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
class MissingObject_Creaion_frame extends JFrame implements ActionListener ,KeyListener {
    private static JLabel main_heading,label_missingobject;
    private static JLabel label_object_description,label_lost_location,label_icon,label_mobile;    
    private static JTextField textfield_missingobject,textfield_lost_location,texfield_mobile;
    private static JTextArea textarea_description;
    private static JButton jbutton_submit,jbutton_importImage,jbutton_back;
    private static JComboBox jcombobox_object;
    private static String str_missingobject="NIl",str_lostLocation="NIL",str_description="NIL",str_warning=" ",str_mobile; 
    Beans bd=new Beans();
    /*add_missing_object
    * inserts missing object into database
    */
    private void add_missing_object(){
        try {
            //loading Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connectiom
            Connection con=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp", "project","livends@2020");
            //Creating PreparedStatement
            PreparedStatement ps=con.prepareStatement("insert into "
                    + "missing_object values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, bd.str_name);
            ps.setString(2, bd.str_rollno);
            ps.setString(3, str_mobile);
            ps.setString(4,bd.getStr_email());
            ps.setString(5, str_missingobject);
            ps.setString(6, str_description);
            ps.setString(7, str_lostLocation);
            ps.setDate(8, Date.valueOf(LocalDate.now()));
            ps.setString(9,"Not Found");
            ps.setString(10, "Not Specified");
            ps.executeUpdate();
            System.out.println("Sucess");
        }catch (Exception e) {
            System.out.println("error => "+e);
        }
    }
    //constructor
    MissingObject_Creaion_frame(){
        setSize(870,630);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        main_heading = new JLabel("      MISSING OBJECT REGISTRATION FORM");

        main_heading.setBounds(0,20,820,70);
        add(main_heading);
        main_heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        main_heading.setOpaque(true);
        main_heading.setBackground(new Color(0x005E90));;

        label_missingobject = new JLabel("OBJECT");
        
        add(label_missingobject);
        label_missingobject.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_missingobject.setBounds(150,140,120,20);

        String objects[]={"","Phone","ID Card","Bag","Key","Wallet","Helmet","Other"};
        jcombobox_object=new JComboBox(objects);
        add(jcombobox_object);
        jcombobox_object.setBounds(260, 140, 200, 20);
        
        label_object_description = new JLabel("DESCRIPTION");

        add(label_object_description);
        label_object_description.setBounds(110,190,120,20);
        label_object_description.setFont(new Font("Times New Roman",Font.BOLD,15));

        textarea_description = new JTextArea();

        add(textarea_description);
        textarea_description.setLineWrap(true);
        textarea_description.setWrapStyleWord(true);
        textarea_description.setBounds(260,190,200,150);
        textarea_description.addKeyListener(this);
        
        label_lost_location = new JLabel("LOST LOCATION");
        add(label_lost_location);
        label_lost_location.setBounds(90,370,200,20);
        label_lost_location.setFont(new Font("Times New Roman",Font.BOLD,15));
        
        textfield_lost_location = new JTextField();
        add(textfield_lost_location);
        textfield_lost_location.setBounds(260,370,200,20);
        textfield_lost_location.addKeyListener(this);
    
        label_mobile=new JLabel("MOBILE NUMBER");
        label_mobile.setBounds(80, 420, 150, 20);
        add(label_mobile);
        label_mobile.setFont(new Font("Times New Roman",Font.BOLD,15));
        
        texfield_mobile=new JTextField();
        add(texfield_mobile);
        texfield_mobile.setBounds(260, 420, 200, 20);
        texfield_mobile.addKeyListener(this);
 
        jbutton_back = new JButton("BACK");
        add(jbutton_back);
        jbutton_back.setBounds(240,470,100,20);
        jbutton_back.setFont(new Font("Times New Roman",Font.BOLD,15));
        jbutton_back.addActionListener(this);

        jbutton_submit = new JButton("APPLY");
        add(jbutton_submit);
        jbutton_submit.setBounds(360,470,100,20);
        jbutton_submit.setFont(new Font("Times New Roman",Font.BOLD,15));
        jbutton_submit.addActionListener(this);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement"
                + "\\lostandfound.jpeg")
                .getImage().getScaledInstance(270, 280, Image.SCALE_DEFAULT));
        label_icon = new JLabel();
        label_icon.setIcon(imageIcon);
        add(label_icon);
        label_icon.setBounds(520,170,250,280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob=e.getSource();
        if(ob.equals(jbutton_submit)){
            //check whether user is null
            if("NIL".equals(bd.getStr_name())){
                str_warning=str_warning+"Name ";
            }
            //check whether user has not selected any object
            else if("".equals(jcombobox_object.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(rootPane, "Missing object is not entered!!");
                
            }
            //check whether description is NUll
            else if("NIL".equals(str_description)){
                JOptionPane.showMessageDialog(rootPane,"Description is missing." );
            }
            //check whether lostlocation is NULL 
            else if("NIL".equals(str_lostLocation)){
                JOptionPane.showMessageDialog(rootPane, "Lost location is missing.");
            }
            //check whether str_mobile contain only number
            else if(str_mobile.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[!-.]).*")){
                JOptionPane.showMessageDialog(rootPane, "Invalid Mobile Number");
            }
            //check whether str_mobile length is 10 
            else if(str_mobile.length()!=10){
                JOptionPane.showMessageDialog(rootPane, "Invalid Mobile Number");
            }
            add_missing_object();
        }
        if(ob.equals(jbutton_back)){
            dispose();
            new Main_frame();
        }
    }
    @Override
    public void keyTyped(KeyEvent e){
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
    Object ob=e.getSource();
        //updates  lost location string
        if(ob.equals(textfield_lost_location)){
            str_lostLocation=textfield_lost_location.getText();
        }
        //updates description string
        if(ob.equals(textarea_description)){
            str_description=textarea_description.getText();
        }
        //update mobile number str
        if(ob.equals(texfield_mobile)){
            str_mobile=texfield_mobile.getText();
        }
    }
    public static void main(String[] args){
        new MissingObject_Creaion_frame();
    }
}

       
      