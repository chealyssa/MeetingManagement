/*
STATUS : THIS NEEDS MAJOR UPDATE
  September 18th, 2021
  This class allows the user to insert anew time and date once a meeting is 
  finish, this willl be used in overview
NOTE: This frame updates the duration of the meting which updates the database
 */
package meetingmanagement;

// imports (not organized)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SetDuration extends JFrame implements ActionListener
{

  // Declaring frame color
  public static final Color BG_COLOR = new Color(248, 249, 250);

  // Declaring varibles used in login frame
  private JPanel inputPanel;
  private JPanel buttonPanel;
  private JButton setButton;
  private JButton backButton;
  private JPanel startPanel;
  private JLabel startLabel;
  private JTextField startHourField;
  private JTextField startMinField;
  private JPanel endPanel;
  private JLabel endLabel;
  private JTextField endHourField;
  private JTextField endMinField;
  private JPanel bigPanel;
  private JLabel textLabel;
  private JPanel textPanel;
  private JLabel colon1Label;
  private JLabel colon2Label;
  
  // Declairng items for database 
  private ArrayList<ArrayList<String>> dataList;
  private int gRow;
  private String dateData;
  private String timeData;

  public SetDuration(int row, int col)
  {
    super("Meeting Info Edit");

    // testing the row and col values 
    System.out.println(row + "," + col + " in SetDuration");

    // Constructing the frame 
    this.setBounds(400, 345, 430, 260);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(BG_COLOR);

    // construct the row value 
    gRow = row;

    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "Duration"
    };
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);

    // frame layout
    this.setLayout(new BorderLayout());

    // Constructing the text labels with panel
    this.textLabel = new JLabel("<html><center> Meeting Information Edit <br>"
      + "Use 24-hour Time (HH:MM) </center></html>",
      SwingConstants.CENTER);
    this.textPanel = new JPanel();
    textPanel.add(textLabel);

    // Constructing colon label 
    colon1Label = new JLabel(":");
    colon2Label = new JLabel(":");
    
    // Constructing start time label and textfield 
    startLabel = new JLabel("Start Time: ");
    startHourField = new JTextField(10);
    startMinField = new JTextField(10);

    // Constructing end time label and textfield 
    endLabel = new JLabel("End Time:  ");
    endHourField = new JTextField(10);
    endMinField = new JTextField(10);

    // Constructing done button 
    this.setButton = new JButton("Set");
    setButton.addActionListener(this);

    // Constructing back button 
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);

    // Constructing panel for start time
    this.startPanel = new JPanel(new FlowLayout());
    startPanel.add(startLabel);
    startPanel.add(startHourField);
    startPanel.add(colon1Label);
    startPanel.add(startMinField);

    // Constructing panel for end time
    this.endPanel = new JPanel(new FlowLayout());
    endPanel.add(endLabel);
    endPanel.add(endHourField);
    endPanel.add(colon2Label);
    endPanel.add(endMinField);

    // Constructing the panel 
    this.inputPanel = new JPanel();
    this.inputPanel.setPreferredSize(new Dimension(500, 200));
    this.bigPanel = new JPanel();

    // add panel to frame + adding panel into panel
    this.add(bigPanel, BorderLayout.CENTER);
    this.bigPanel.add(textPanel);
    this.bigPanel.add(inputPanel);
    this.inputPanel.add(startPanel);
    this.inputPanel.add(endPanel);

    // add to button panel and adds to frame
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(backButton);
    buttonPanel.add(setButton);
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Makes the frame visible 
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "Duration"
    };
    int row = 2;
    int col = 2;
    new SetDuration(row, col);
  }

  // Listener for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "Duration"
    };

    //creates frame for result
    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

    // read the data
    String startHourInput = startHourField.getText();
    String startMinInput = startMinField.getText();
    String endHourInput = endHourField.getText();
    String endMinInout = endMinField.getText();
    String duration;

    //connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);
    dataList = objDb.getData(tableName, columnNames);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);
    Connection DbConn = objDb.getDbConn();

    if (command.equals("Back"))
    {
      this.dispose();
    }
    else if (command.equals("Set"))
    {
      // Get data from textfield 
      int startHour = Integer.parseInt(startHourInput);
      int startMin = Integer.parseInt(startMinInput);
      int endHour = Integer.parseInt(endHourInput);
      int endMin = Integer.parseInt(endMinInout);

      // Pass value of start and end time to Duration class
      Duration calculation = new Duration(startHour, startMin, endHour ,endMin);
      duration = calculation.getResult();
      if (!duration.equals(0))
      {
        try
        {
          // time and data doesnt have data
          String query = "UPDATE Meetings SET Duration = '" + duration + "'"
            + "WHERE Time = '" + timeData + "' AND Day = '" + dateData + "'";
          System.out.println(query);

          //prepared statement 
          PreparedStatement ps = DbConn.prepareStatement(query);

          //execute the query
          ps.executeUpdate();
          System.out.println("Duration set succesfullly");

          // Displays the meeting duration
          JOptionPane.showMessageDialog(frame,
            "Meeting is " + duration + "long.",
            "Duration",
            JOptionPane.PLAIN_MESSAGE);
        }
        catch (SQLException se)
        {
          System.out.println("Error setting duration");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Time entered incorrectly, please check",
          "Duration Warning", JOptionPane.ERROR_MESSAGE);
      }
      // read data from database
      ArrayList<ArrayList<String>> data = objDb.getData(tableName, columnNames);
      System.out.println(data);
    }
  }
}
