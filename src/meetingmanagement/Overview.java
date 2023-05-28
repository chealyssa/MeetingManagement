/*
STATUS: DONE
  September 15th, 2021
  This is a pop up frame that shows the overview of past meeting with their 
  actual duration
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Overview extends JFrame implements ActionListener 
{
  // Delcaring buttons, panel etc
  private JLabel textLabel;
  private JPanel textPanel;
  private JPanel dataPanel;
  private JButton backButton;
  private JPanel buttonPanel;
  
  // declaring table variables 
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable overviewTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private final String[] COLUMN_HEADER = {"Meeting Name", "Duration"};
  
  public Overview(String dbName, String tableName, String[] columnNames)
  {
    super("Overview");

    // Construct the frame 
    this.setBounds(800, 180, 600, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Meeting Overview </center> "
                         + "<html", SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);
    
    // TABLE CONSTRUCTION STARTS HERE 

    // Constructing table data
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    data = dbObj.to2dArray(dataList);
    dbObj.closeDbConn();
    
    // Constructing table
    overviewTable = new JTable(data, COLUMN_HEADER);
    
    // Format Table
    overviewTable.setGridColor(Color.BLACK);
    overviewTable.setBackground(Login.BG_COLOR);
    
    // Format row
    overviewTable.setRowHeight(20);
    
    // Format columns 
    column = overviewTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(400);
    column = overviewTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(200);  
    
    // Format header
    header = overviewTable.getTableHeader();
    header.setBackground(Login.BG_COLOR);
    
    // Constructing scroll panel
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(overviewTable);
    overviewTable.setFillsViewportHeight(true);
        
    // construct back button
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    // adds all the button to button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(backButton);
    
    // Adds panel to frame 
    // Header does not show when I have something at NORTH
    //this.add(textPanel, BorderLayout.NORTH); 
    this.add(scrollTable, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    this.setVisible(true);
  }

  // Main method that shows the dialy frame 
  public static void main(String[] args)
  { 
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames = {"MeetingName", "Duration"};
    new Overview(dbName, tableName, columnNames);
  }
  
  // Listener for button
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Back"))
    {
      this.dispose();
    }
  }

}

