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
* get college complaint detail from user and inserts it into database
*/
public class Complainthostel implements ActionListener {
    JFrame frame_complainthostel;
    JLabel text_title, label_complaint, label_block, label_uniqueid,label5,
            success_message, error_message, hostel_icon;
    JLabel label_Room_no,label_complaint_description;
    JButton button_back, button_apply;
    JTextArea complaint_textarea;
    JTextField jtextfeild_complaint;
    JComboBox combobox_block,combobox_room;
    JPasswordField password_field;
    JDialog success_box, error_box;   
    String block,complaint,uniqueid;
    final String block1[]={"","A block","B block","C block","D block"};     //block in hostel
    final String Room[]={"","100","101","102","103","104","105","106","107"}; //room no in hostel
    Beans bd=new Beans();
    public Complainthostel()
    {
        
        frame_complainthostel = new JFrame("GRIEVANCE PORTAL");
        frame_complainthostel.setSize(870,630);
        frame_complainthostel.setLayout(null);
        frame_complainthostel.setLocationRelativeTo(null);
        frame_complainthostel.setVisible(true);

        text_title = new JLabel("      HOSTEL COMPLAINT REGISTRATION FORM");

        text_title.setBounds(0,20,820,70);
        frame_complainthostel.add(text_title);
        text_title.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text_title.setOpaque(true);
        //text_title.setBackground(new Color(0xAC354E));
        text_title.setBackground(new Color(0x005E90));
   
        label_complaint = new JLabel("COMPLAINT");

        frame_complainthostel.add(label_complaint);
        label_complaint.setBounds(90,150,120,20);
        label_complaint.setFont(new Font("Times New Roman",Font.BOLD,15));

        jtextfeild_complaint=new JTextField();
        frame_complainthostel.add(jtextfeild_complaint);
        jtextfeild_complaint.setBounds(260, 150, 200, 20);
        
        complaint_textarea = new JTextArea();
        
        label_complaint_description=new JLabel("COMPLAINT DESCRIPTION");
        frame_complainthostel.add(label_complaint_description);
        label_complaint_description.setFont(new Font("Times New Roman",Font.BOLD,15));
        label_complaint_description.setBounds(30, 200, 230, 20);
        
        frame_complainthostel.add(complaint_textarea);
        complaint_textarea.setLineWrap(true);
        complaint_textarea.setWrapStyleWord(true);
        complaint_textarea.setBounds(260,200,200,150);

        label_block = new JLabel("BLOCK");

        frame_complainthostel.add(label_block);
        label_block.setBounds(90,380,120,20);
        label_block.setFont(new Font("Times New Roman",Font.BOLD,15));
      

        combobox_block = new JComboBox(block1);

        frame_complainthostel.add(combobox_block);
        combobox_block.setBounds(260,380,200,20);

        combobox_room=new JComboBox(Room);
        frame_complainthostel.add(combobox_room);
        combobox_room.setBounds(260,420, 200, 20);
        
        label_Room_no=new JLabel("ROOM NO");
        label_Room_no.setBounds(90,420,120,20);
        label_Room_no.setFont(new Font("Times New Roman",Font.BOLD,15));
        frame_complainthostel.add(label_Room_no);
        
        label_uniqueid = new JLabel("UNIQUE ID");

        frame_complainthostel.add(label_uniqueid);
        label_uniqueid.setBounds(90,470,120,20);
        label_uniqueid.setFont(new Font("Times New Roman",Font.BOLD,15));

        password_field = new JPasswordField();

        frame_complainthostel.add(password_field);
        password_field.setBounds(260,470,200,20);

        ImageIcon gifIcon = new ImageIcon(new ImageIcon("F:\\Javaprogram\\src\\CollegeComplaintManagement\\hostel.jpeg").getImage().
                getScaledInstance(250, 280, Image.SCALE_DEFAULT));
        hostel_icon = new JLabel();
        hostel_icon.setIcon(gifIcon);
        frame_complainthostel.add(hostel_icon);
        hostel_icon.setBounds(520,170,250,280);


        button_back = new JButton("BACK");
        frame_complainthostel.add(button_back);
        button_back.setBounds(370,520,100,20);
        button_back.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_back.addActionListener(this);

        button_apply = new JButton("APPLY");
        frame_complainthostel.add(button_apply);
        button_apply.setBounds(250,520,100,20);
        button_apply.setFont(new Font("Times New Roman",Font.BOLD,15));
        button_apply.addActionListener(this);

        frame_complainthostel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            Connection conn=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp","project","livends@2020");
            System.out.println("Connection to SQLite has been established.");
            //Creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from complaint_college where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "UID is already used");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection    
            Connection conn=DriverManager.getConnection("jdbc:mysql://"
                    + "localhost/bootcamp","project","livends@2020");
            System.out.println("Connection to SQLite has been established.");
            //Creating Statement
            Statement smt=conn.createStatement();
            String q = "Select * from complaint_hostel where UID='"+UID+"'";
            //Executing Query
            ResultSet rs=smt.executeQuery(q);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "UID is already used");
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
        complaint= complaint_textarea.getText();
        block = combobox_block.getSelectedItem().toString();
        uniqueid = password_field.getText();
        if(complaint.isEmpty()||block.isEmpty()||uniqueid.isEmpty())
        {
            JOptionPane.showMessageDialog(label_complaint,"Enter all feilds");
            return;
        }
        if(CheckUID(uniqueid)){
            return;
        }
        insert_into_database_hostel();
    }
    void insert_into_database_hostel(){
         try {
             //loading DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            //Creating Connection
            Connection con=DriverManager.getConnection("jdbc:mysql:"
                    + "//localhost/bootcamp", "project","livends@2020");
            //Creating PerparedStatement
            PreparedStatement ps=con.prepareStatement("insert into"
                    + " complaint_hostel values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, password_field.getText());
            ps.setString(2, bd.str_name);
            ps.setString(3, bd.getStr_rollno());
            ps.setString(4,combobox_block.getSelectedItem().toString());
            ps.setString(5, combobox_room.getSelectedItem().toString());
            ps.setString(6, jtextfeild_complaint.getText());
            ps.setString(7, complaint_textarea.getText());
            ps.setDate(8, Date.valueOf(LocalDate.now()));
            ps.setString(9, "Pending");
            ps.setDate(10,Date.valueOf(LocalDate.now()));
            //Executing Query
            ps.executeUpdate();
            System.out.println("Sucess");
        } 
        catch (Exception e) {
            System.out.println("error => "+e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button_back))
        {
            frame_complainthostel.dispose();
            new Main_frame();
        }
        if(e.getSource().equals(button_apply)){
            mandatory();
        }
    }
    public static void main(String[] args) {
        new Complainthostel();
    }
}
