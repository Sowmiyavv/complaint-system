package CollegeComplaintManagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
/*Complaintcollege
* get complaint detail from user and inserts it into database
*/
public class Complaintcollege extends JFrame implements ActionListener {
    JLabel label_complaint_description, label_complaintcollege_title,
            label_complaintcollege_date, label_complaintcollege_complaint,
            label_complaintcollege_block,
            label_complaintcollege_uniqueid,label_complaintcollege_messagesucess,
            label_complaintcollege_messagerror,icon1;
    JButton button1,jbutton_complaintcollege_apply;
    JTextArea jtextarea_complaintcollege_complaintDescription;
    JTextField jtextfeild_complaint;
    JComboBox jcombobox_block,jcombobox_classNo;
    JPasswordField jpasswordl_complaintcollege_uniqueid;
    JDialog jdialog_complaintcollege_boxsucess,jdialog_complaintcollege_boxerror;
    static String Str_complaintcollege_complaintDescription="NIL",
           Str_complaintcollege_complaint="NIl",Str_uniqueid;
    Beans bd=new Beans();
    static String Str_complaintcollege_block;   
    private final JLabel label_classNo;
    
    //Constructor
    public Complaintcollege()
    {        
        setSize(870,630);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        label_complaintcollege_title = new JLabel("      COLLEGE COMPLAINT REGISTRATION FORM");

        label_complaintcollege_title.setBounds(0,20,820,70);
        add(label_complaintcollege_title);
        label_complaintcollege_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label_complaintcollege_title.setOpaque(true);
        //.setBackground(new Color(0xAC354E));
        label_complaintcollege_title.setBackground(new Color(0x005E90));
        
        label_complaintcollege_complaint = new JLabel("COMPLAINT");

        add(label_complaintcollege_complaint); 
        label_complaintcollege_complaint.setBounds(140,150,120,20);
        label_complaintcollege_complaint.setFont(new Font("Times New Roman",Font.BOLD,15));

        label_complaint_description=new JLabel("COMPLAINT DESCRIPTION");
        add(label_complaint_description);
        label_complaint_description.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_complaint_description.setBounds(30, 200, 230, 20);
        
        jtextfeild_complaint=new JTextField();
        add(jtextfeild_complaint);
        jtextfeild_complaint.setBounds(260, 150, 200, 20);
        
        jtextarea_complaintcollege_complaintDescription = new JTextArea();

        add(jtextarea_complaintcollege_complaintDescription);
        jtextarea_complaintcollege_complaintDescription.setLineWrap(true);
        jtextarea_complaintcollege_complaintDescription.setWrapStyleWord(true);
        jtextarea_complaintcollege_complaintDescription.setBounds(260,200,200,150);

        label_complaintcollege_block = new JLabel("BLOCK");

        add(label_complaintcollege_block);
        label_complaintcollege_block.setBounds(170,370,120,20);
        label_complaintcollege_block.setFont(new Font("Times New Roman",Font.BOLD,15));
        String block1[]={"","MAIN","LIBRARY","ECE","CSE","IT","EEE","MECH","PARKING"};

        jcombobox_block = new JComboBox(block1);
  
        add(jcombobox_block);
        jcombobox_block.setBounds(260,370,200,20);
      
        label_classNo=new JLabel("CLASS NO");
        add(label_classNo);
        label_classNo.setBounds(150,420,120,20);
        label_classNo.setFont(new Font("Times New Roman",Font.BOLD,15));  
        String classNo[]={"","CR01","CR02","CR03","CR04","CR05","CR06","CR07","CR08","CR09"};
        jcombobox_classNo=new JComboBox(classNo);
        add(jcombobox_classNo);
        jcombobox_classNo.setBounds(260,420, 200, 20);      
        label_complaintcollege_uniqueid = new JLabel("UNIQUE ID");

        add(label_complaintcollege_uniqueid);
        label_complaintcollege_uniqueid.setBounds(140,470,120,20);
        label_complaintcollege_uniqueid.setFont(new Font("Times New Roman",Font.BOLD,15));

        jpasswordl_complaintcollege_uniqueid = new JPasswordField();
        add(jpasswordl_complaintcollege_uniqueid);
        jpasswordl_complaintcollege_uniqueid.setBounds(260,470,200,20);

        button1 = new JButton("BACK");
        add(button1);
        button1.setBounds(200,510,100,20);
        button1.setFont(new Font("Times New Roman",Font.BOLD,15));
        button1.addActionListener(this);

        jbutton_complaintcollege_apply = new JButton("APPLY");
        add(jbutton_complaintcollege_apply);
        jbutton_complaintcollege_apply.setBounds(350,510,100,20);
        jbutton_complaintcollege_apply.setFont(new Font("Times New Roman",Font.BOLD,15));
        jbutton_complaintcollege_apply.addActionListener(this);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\college.jpeg")
                .getImage().getScaledInstance(250, 280, Image.SCALE_DEFAULT));
        icon1 = new JLabel();
        icon1.setIcon(imageIcon);
        add(icon1);
        icon1.setBounds(520,170,250,280);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GRIEVANCE PORTAL");
    }
    /*CheckUID
    * Checks whether unqiue id entered is already used in the database
    * IF used already ,error message
    * ELSE , inserts complaint into database
    */
    boolean CheckUID(String UID){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql"
                    + "://localhost/bootcamp","project","livends@2020");
            //Creating Statement
            Statement smt=conn.createStatement();
            
            String q = "Select * from complaint_college where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(rootPane, "UID is already used");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection conn=DriverManager.getConnection("jdbc:mysql"
                    + "://localhost/bootcamp","project","livends@2020");
            System.out.println("Connection to SQLite has been established.");
            //Creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from complaint_hostel where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(rootPane, "UID is already used");
                return true;
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return false;
    }
    /*mandatory
    * checks whether all the textfields are NULL
    * IF  NULL, @return false
    * checks whether unqiueid is unique
    * IF Unique,@return true and call insert_data_db methode
    * ELSE @return true
    */
    public void mandatory()
    {
        Str_complaintcollege_complaint=jtextfeild_complaint.getText();
        Str_complaintcollege_complaintDescription= jtextarea_complaintcollege_complaintDescription.getText();
        Str_complaintcollege_block = jcombobox_block.getSelectedItem().toString();
        System.err.println(Str_complaintcollege_block+" "
                +Str_complaintcollege_complaintDescription+" ");
        Str_uniqueid = jpasswordl_complaintcollege_uniqueid.getText();
        if(Str_complaintcollege_complaint.equals("") ||Str_complaintcollege_block.equals("")||Str_uniqueid.equals("")){
            JOptionPane.showMessageDialog(rootPane, "Enter all feilds");
            return;
        }
        else if(CheckUID(Str_uniqueid)){
            return;
        }
        else{
            Insert_data_db();
            JOptionPane.showMessageDialog(rootPane,"Complaint registered");
            jtextarea_complaintcollege_complaintDescription.setText("");
            jtextfeild_complaint.setText("");
            jcombobox_block.setSelectedIndex(0);
            jcombobox_classNo.setSelectedIndex(0);
            jpasswordl_complaintcollege_uniqueid.setText("");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button1)){
            dispose();
            new Main_frame();
        }
        if(e.getSource().equals(jbutton_complaintcollege_apply)){
            bd.setStr_complaint(jtextfeild_complaint.getText().toString());
            mandatory();
        }
    }
    /*Insert_data_db
    * insert complaint into database
    */
    void Insert_data_db(){
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/bootcamp","project","livends@2020");
            //Creating PreparedStatement
            PreparedStatement ps=con.prepareStatement("insert into complaint_college values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,Str_uniqueid);
            ps.setString(2, bd.getStr_name());
            ps.setString(3,bd.getStr_rollno());            
            ps.setString(4,jcombobox_block.getSelectedItem().toString() );
            ps.setString(5, jcombobox_classNo.getSelectedItem().toString());
            ps.setString(6,jtextfeild_complaint.getText());
            ps.setString(7,jtextarea_complaintcollege_complaintDescription.getText());
            ps.setDate(8,Date.valueOf(LocalDate.now()));
            ps.setString(9,"Pending");
            ps.setDate(10,Date.valueOf(LocalDate.now()));
            //Executing Query
            ps.executeUpdate();
            System.out.print("sucess----");
        } 
        catch (Exception e){
            System.out.println("project.Complaintcollege.Insert_data_db() "+e);
        }
    }
    public static void main(String[] args) {
        new Complaintcollege();
    }
}