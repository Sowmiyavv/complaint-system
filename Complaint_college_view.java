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
/*Complaint_college_view
* This class views complaints from database 
* vector is used to display data in the form of table
*/
public class Complaint_college_view extends JPanel {
    private Connection conn = null;
    static JButton table_title,jbutton_back,jbutton_update;
    public Complaint_college_view() {
        
	Vector columnNames = new Vector();      
        Vector data = new Vector();
        
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("College Complaint Table");
        table_title.setPreferredSize(new Dimension(400,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD, 30));
        table_title.setBackground(new Color(32,136,203));
        
        
        add(table_title);

        try {
            jbutton_back = new JButton("Back");
            jbutton_update = new JButton("Update Status");
            
            Class.forName("com.mysql.jdbc.Driver");     //loading driver
            //creating Connection
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost/bootcamp","project","livends@2020");
            
            String sql = "select * from complaint_college";
            //creating Statement
            Statement stmt = conn.createStatement();
            //excuting Query
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            //adding Columns in tabel
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            //adding rows in table
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
  
            @Override
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
        scrollPane.setPreferredSize(new Dimension(1700, 500));
        add(scrollPane);
        
                
    }
    /* Frame
    * Creates frame to display table
    */
    void Frame(){
            JFrame frame = new JFrame("Complaints Registered");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Complaint_college_view newContentPane = new Complaint_college_view();
                newContentPane.setOpaque(true); 
                newContentPane.setLayout(new FlowLayout());
                
                jbutton_back.setPreferredSize(new Dimension(120,40));
                jbutton_update.setPreferredSize(new Dimension(200,40));
                
                jbutton_back.setBackground(new Color(32,136,203));
                jbutton_update.setBackground(new Color(32,136,203));
                
                jbutton_back.setForeground(Color.WHITE);
                jbutton_update.setForeground(Color.WHITE);
                
                jbutton_back.setFont(new Font("Segoe UI",Font.BOLD, 20));
                jbutton_update.setFont(new Font("Segoe UI",Font.BOLD, 20));
                
                jbutton_back.setBorder(new LineBorder(Color.BLACK));
                jbutton_update.setBorder(new LineBorder(Color.BLACK));
                
                newContentPane.add(jbutton_back);
                newContentPane.add(jbutton_update);
                frame.setContentPane(newContentPane);
                jbutton_back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new Main_frame();
                }
            });
                jbutton_update.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    updateCollege f = new updateCollege();
                    frame.dispose();
                    f.setVisible(true);
                }
                
            });
                
                frame.pack();
                frame.setVisible(true);
                frame.setSize(1700,700);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Complaint_college_view().Frame();
    }
}
/* updateCollege
*  Displays update frame
*  Update status in the database
*/ 
class updateCollege extends JFrame{
    JLabel jlabel_unique;
    JTextField jtextfield_text;
    JButton table_title,jbutton_update,jbutton_back;
    public updateCollege(){
        
        JPanel P = new JPanel();
  
        UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        table_title = new JButton("Update Status");
        table_title.setPreferredSize(new Dimension(300,40));
        table_title.setEnabled(false);
        table_title.setFont(new Font("Segoe UI",Font.BOLD, 30));
        table_title.setBackground(new Color(32,136,203));
        P.add(table_title);
        
        jlabel_unique = new JLabel("Enter Unique ID:  ");
        jtextfield_text = new JTextField();
        jtextfield_text.setPreferredSize(new Dimension(120,30));
        jbutton_update = new JButton("Update");
        jbutton_update.setPreferredSize(new Dimension(130,40));
        jbutton_update.setBackground(new Color(32,136,203));
        jbutton_update.setForeground(Color.WHITE);
        jbutton_update.setFont(new Font("Segoe UI",Font.BOLD, 20));
        jbutton_update.setBorder(new LineBorder(Color.BLACK));
        
        jbutton_back=new JButton("Back");
        jbutton_back.setPreferredSize(new Dimension(130,40));
        jbutton_back.setBackground(new Color(32,136,203));
        jbutton_back.setForeground(Color.WHITE);
        jbutton_back.setFont(new Font("Segoe UI",Font.BOLD, 20));
        jbutton_back.setBorder(new LineBorder(Color.BLACK));
        jbutton_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Complaint_college_view().Frame();
            }
        });
        jlabel_unique.setFont(new Font("Segoe UI",Font.PLAIN,20));
        P.add(jlabel_unique);
        P.add(jtextfield_text);
        P.add(jbutton_update);
        P.add(jbutton_back);
        add(P);
        jbutton_update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String str_uniqueid = jtextfield_text.getText();
                String str_res = "Resolved";
                try{
                    //loading Driver
                    Class.forName("com.mysql.jdbc.Driver");
                    //Creating Connection
                    Connection conn=DriverManager.getConnection(
                            "jdbc:mysql://localhost/bootcamp"
                            ,"project","livends@2020");
                    // update status query
                    String sql = "update complaint_college SET Status = ?"
                            + " WHERE UID=?";
                    //Creating PreparedStatement
                    PreparedStatement pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,str_res);
                    pstmt.setString(2,str_uniqueid);
                    //Executing Query
                    pstmt.executeUpdate();
                    String s = "select * from Complaint_college "
                            + "WHERE UID='" + str_uniqueid + "'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(s);
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null
                                ,"Updation Success!"
                                ,"Success",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        
                        new Complaint_college_view().Frame();
                        
                    }else{
                        
                        JOptionPane.showMessageDialog(null,
                                "Error! Name not found.","Failed",
                                JOptionPane.INFORMATION_MESSAGE);
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
