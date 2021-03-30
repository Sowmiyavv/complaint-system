package CollegeComplaintManagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/*Admin_Complaint_view
* This class views complaints from database 
* vector is used to display data in the form of table
*/
public class Admin_complaint_view extends JPanel {
    private Connection conn = null;
    static JButton table_title,jbutton_back,jbutton_update,jbutton_delete;
    public Admin_complaint_view() {
        
	Vector columnNames = new Vector();
        Vector data = new Vector();
        
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("College Complaint Table");
        table_title.setPreferredSize(new Dimension(400,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD,30));
        table_title.setBackground(new Color(32,136,203));
        
        
        add(table_title);

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost/bootcamp", "project","livends@2020");
            String sql = "select * from complaint_college";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data.addElement(row);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable table = new JTable(data, columnNames) {
            public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);

                    if (o != null) {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(60);
        columnModel.getColumn(1).setPreferredWidth(10);
        columnModel.getColumn(2).setPreferredWidth(10);
        columnModel.getColumn(3).setMaxWidth(150);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(150);
        columnModel.getColumn(7).setPreferredWidth(30);
        columnModel.getColumn(8).setPreferredWidth(30);
 
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI",Font.BOLD, 20));
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(32,136,203));
        tableHeader.setForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI",Font.PLAIN,16));
        table.setRowHeight(35);
        table.setGridColor(Color.BLACK);
        table.setDefaultEditor(Object.class, null);
        
        //Align data to center.
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Integer.class, centerRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1700, 300));
        add(scrollPane);
        
        Vector columnNames1 = new Vector();
        Vector data1 = new Vector();
        
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("Hostel Complaint Table");
        table_title.setPreferredSize(new Dimension(400,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD,30));
        table_title.setBackground(new Color(32,136,203));
        
        
        add(table_title);

        try {
            jbutton_back = new JButton("Back");
            jbutton_update = new JButton("Update Status");
            jbutton_delete = new JButton("Delete Record");
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost/bootcamp", "project","livends@2020");

            String sql = "select * from complaint_hostel";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames1.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data1.addElement(row);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable table1 = new JTable(data1, columnNames1) {
            public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);

                    if (o != null) {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };
        
        TableColumnModel columnModel1 = table1.getColumnModel();
        columnModel1.getColumn(0).setMaxWidth(60);
        columnModel1.getColumn(1).setPreferredWidth(10);
        columnModel1.getColumn(2).setPreferredWidth(10);
        columnModel1.getColumn(3).setMaxWidth(150);
        columnModel1.getColumn(4).setPreferredWidth(20);
        columnModel1.getColumn(5).setPreferredWidth(70);
        columnModel1.getColumn(6).setPreferredWidth(150);
        columnModel1.getColumn(7).setPreferredWidth(30);
        columnModel1.getColumn(8).setPreferredWidth(30);
 
        JTableHeader tableHeader1 = table1.getTableHeader();
        tableHeader1.setFont(new Font("Segoe UI",Font.BOLD, 20));
        tableHeader1.setOpaque(false);
        tableHeader1.setBackground(new Color(32,136,203));
        tableHeader1.setForeground(Color.WHITE);
        table1.setFont(new Font("Segoe UI",Font.PLAIN,16));
        table1.setRowHeight(35);
        table1.setGridColor(Color.BLACK);
        table1.setDefaultEditor(Object.class, null);
        
        //Align data to center.
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        table1.setDefaultRenderer(String.class, centerRenderer1);
        table1.setDefaultRenderer(Integer.class, centerRenderer1);
        table1.getColumnModel().getColumn(7).setCellRenderer(centerRenderer1);
        

        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setPreferredSize(new Dimension(1700, 400));
        add(scrollPane1);
        }
       
    /* frame
    *  Creates frame to display table
    */
    public void frame() {
                JFrame frame = new JFrame("Complaints Registered");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Admin_complaint_view newContentPane = new Admin_complaint_view();
                newContentPane.setOpaque(true); 
                newContentPane.setLayout(new FlowLayout());
                
                jbutton_back.setPreferredSize(new Dimension(120,40));
                jbutton_update.setPreferredSize(new Dimension(200,40));
                jbutton_delete.setPreferredSize(new Dimension(200,40));
                
                jbutton_back.setBackground(new Color(32,136,203));
                jbutton_update.setBackground(new Color(32,136,203));
                jbutton_delete.setBackground(new Color(32,136,203));
                
                jbutton_back.setForeground(Color.WHITE);
                jbutton_update.setForeground(Color.WHITE);
                jbutton_delete.setForeground(Color.WHITE);
                
                jbutton_back.setFont(new Font("Segoe UI",Font.BOLD, 20));
                jbutton_update.setFont(new Font("Segoe UI",Font.BOLD, 20));
                jbutton_delete.setFont(new Font("Segoe UI",Font.BOLD, 20));
                
                jbutton_back.setBorder(new LineBorder(Color.BLACK));
                jbutton_update.setBorder(new LineBorder(Color.BLACK));
                jbutton_delete.setBorder(new LineBorder(Color.BLACK));
                
                newContentPane.add(jbutton_back);
                newContentPane.add(jbutton_update);
                newContentPane.add(jbutton_delete);
                frame.setContentPane(newContentPane);
               
                jbutton_update.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Adminupdate1 update = new Adminupdate1();
                        frame.dispose();
                        update.setVisible(true);
                    }
                
                });
                jbutton_delete.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Admindelete delete = new Admindelete();
                        frame.dispose();
                        delete.setVisible(true);  
                    }
                    
                });
                jbutton_back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        new Admin_main_frame();
                    }
                });
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1700, 1000);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                
            }
        
        public static void main(String[] args) {
            Admin_complaint_view ob = new Admin_complaint_view();
            ob.frame();
        }
    }
/* Adminupdate1
*  Displays update frame
*  Update status in the database
*/ 
class Adminupdate1 extends JFrame{
    JLabel jlabel_unique;
    JTextField jtextfield_text;
    JButton table_title,jbutton_update,jbutton_back;
    public Adminupdate1(){
        
        JPanel jpanel_update = new JPanel();
        
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("Update Status");
        table_title.setPreferredSize(new Dimension(300,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD, 30));
        table_title.setBackground(new Color(32,136,203));
        jpanel_update.add(table_title);
        
        jlabel_unique = new JLabel("Enter Unique ID:  ");
        jtextfield_text = new JTextField();
        jtextfield_text.setPreferredSize(new Dimension(120,30));
        jbutton_update = new JButton("Update");
        jbutton_update.setPreferredSize(new Dimension(130,40));
        jbutton_update.setBackground(new Color(32,136,203));
        jbutton_update.setForeground(Color.WHITE);
        jbutton_update.setFont(new Font("Segoe UI",Font.BOLD, 20));
        jbutton_update.setBorder(new LineBorder(Color.BLACK));
         
        jbutton_back = new JButton("Back");
        jbutton_back.setPreferredSize(new Dimension(130,40));
        jbutton_back.setBackground(new Color(32,136,203));
        jbutton_back.setForeground(Color.WHITE);
        jbutton_back.setFont(new Font("Segoe UI",Font.BOLD, 20));
        jbutton_back.setBorder(new LineBorder(Color.BLACK));
        jlabel_unique.setFont(new Font("Segoe UI",Font.PLAIN,20));
        
        jpanel_update.add(jlabel_unique);
        jpanel_update.add(jtextfield_text);
        jpanel_update.add(jbutton_update);
        jpanel_update.add(jbutton_back);
        add(jpanel_update);
         jbutton_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new   Admin_complaint_view().frame();
            }
        });
        jbutton_update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String str_uniqueid = jtextfield_text.getText();
                String str_res = "Processing";
                try{
                    Class.forName("com.mysql.jdbc.Driver");
            
                    Connection conn=DriverManager.getConnection(
                            "jdbc:mysql://localhost/bootcamp", "project","livends@2020");                
                    String sql_college = "update complaint_college"
                            + " SET Status = ? WHERE UID=?";
                    
                    PreparedStatement pstmt_college;                    
                    pstmt_college = conn.prepareStatement(sql_college);
                    pstmt_college.setString(1,str_res);
                    pstmt_college.setString(2,str_uniqueid);                                      
                    pstmt_college.executeUpdate();
                    
                    String s_college = "select * from Complaint_college"
                            + " WHERE UID='" + str_uniqueid + "'";                    
                    Statement st1 = conn.createStatement();                    
                    ResultSet rs_college = st1.executeQuery(s_college);
                    
                    if(rs_college.next()){
                        JOptionPane.showMessageDialog(null,"Updation Success!"
                                ,"Success",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new Admin_complaint_view().frame();
                    }
                    else{
                        String sql_hostel = "update complaint_hostel"
                                + " SET Status = ? WHERE UID=?";
                        PreparedStatement pstmt_hostel;
                        pstmt_hostel = conn.prepareStatement(sql_hostel);
                        pstmt_hostel.setString(1,str_res);
                        pstmt_hostel.setString(2,str_uniqueid);
                        pstmt_hostel.executeUpdate();
                        
                        String s_hostel = "select * from complaint_hostel"
                                + " WHERE UID='" + str_uniqueid + "'";
                        Statement st2 = conn.createStatement();
                        ResultSet rs_hostel = st2.executeQuery(s_hostel);
                        
                        if(rs_hostel.next()){
                            JOptionPane.showMessageDialog(null,"Updation Success!","Success",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new Admin_complaint_view().frame();
                        }
                        else{
                        JOptionPane.showMessageDialog(null,"Error! Unique ID not found.","Failed",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                } 
            }
        });
        setLocationRelativeTo(null);
        setSize(400,250);
        setVisible(true);
    }
}
/* Admindelete
*  Displays delete frame
*  delete data from database
*/ 
class Admindelete extends JFrame{
    JLabel jlabel_unique;
    JTextField jtextfield_text;
    JButton table_title,jbutton_delete;
    public Admindelete(){
        JPanel jpanel_delete = new JPanel();
        
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("Delete Record");
        table_title.setPreferredSize(new Dimension(300,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD, 30));
        table_title.setBackground(new Color(32,136,203));
        jpanel_delete.add(table_title);
        
        jlabel_unique = new JLabel("Enter Unique ID:  ");
        jtextfield_text = new JTextField();
        jtextfield_text.setPreferredSize(new Dimension(120,30));
        jbutton_delete = new JButton("Delete");
        jbutton_delete.setPreferredSize(new Dimension(130,40));
        jbutton_delete.setBackground(new Color(32,136,203));
        jbutton_delete.setForeground(Color.WHITE);
        jbutton_delete.setFont(new Font("Segoe UI",Font.BOLD, 20));
        jbutton_delete.setBorder(new LineBorder(Color.BLACK));
        
        jlabel_unique.setFont(new Font("Segoe UI",Font.PLAIN,20));
        jpanel_delete.add(jlabel_unique);
        jpanel_delete.add(jtextfield_text);
        jpanel_delete.add(jbutton_delete);
        add(jpanel_delete);
        jbutton_delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String str_uniqueid = jtextfield_text.getText();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
            
                     Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/bootcamp", "project","livends@2020");                    
                    String str_status = "Resolved";

                    String s_college = "select * from complaint_college WHERE UID='" + str_uniqueid + "' AND Status='"+str_status+"'";                    
                    Statement st1 = conn.createStatement();                    
                    ResultSet rs_college = st1.executeQuery(s_college);
                    
                    String sql_college = "delete from complaint_college WHERE UID=? AND Status=?";                    
                    PreparedStatement pstmt_college=conn.prepareStatement(sql_college);                    
                    pstmt_college.setString(1,str_uniqueid);   
                    pstmt_college.setString(2,str_status);                                   
                    pstmt_college.executeUpdate();
                    
                    if(rs_college.next()){
                        JOptionPane.showMessageDialog(null,"Deletion Success!","Success",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new Admin_complaint_view().frame();
                    }
                    else{                     
                        String s_hostel = "select * from complaint_hostel WHERE UID='" + str_uniqueid + "'  AND Status='"+str_status+"'";
                        Statement st2 = conn.createStatement();
                        ResultSet rs_hostel = st2.executeQuery(s_hostel);
                        
                        String sql_hostel = "delete from complaint_hostel WHERE UID=? AND Status=?";
                        PreparedStatement pstmt_hostel = conn.prepareStatement(sql_hostel);
                        pstmt_hostel.setString(1,str_uniqueid);
                        pstmt_hostel.setString(2,str_status);
                        pstmt_hostel.executeUpdate();
                        
                        if(rs_hostel.next()){
                            JOptionPane.showMessageDialog(null,"Deletion Success!"
                                    ,"Success",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new Admin_complaint_view().frame();
                        }
                        else{
                            JOptionPane.showMessageDialog(null
                                    ,"Error! Unique ID not found.","Failed",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                } 
            }
        });
        setLocationRelativeTo(null);
        setSize(400,250);
        setVisible(true);
    }
}