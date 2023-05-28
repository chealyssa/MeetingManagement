/*
STATUS: NOT DONE
  September 18th, 2021
  This is a pop up frame that allows user to set appointment
Data here will be used to insert to DB
TO-DO: when given date and time, run through duplication .. when invitees is 
chosen, allow user to choose from list of people
 */
package meetingmanagement;

// imports (not organized)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SetAppointment extends JFrame implements ActionListener
{

  // Delcaring buttons, panel etc
  private JLabel textLabel;
  private JPanel textPanel;
  private JLabel dateTimeLabel;
  private String formattedTime;
  private String formattedDate;
  private JPanel timePanel;
  private JPanel topPanel;
  private JPanel inputPanel;
  private JLabel nameLabel;
  private JTextField nameField;
  private JPanel namePanel;
  private JLabel dateLabel;
  private JTextField dateField;
  private JPanel datePanel;
  private JLabel timeLabel;
  private JTextField timeField;
  private JPanel timeInputPanel;
  private JLabel inviteLabel;
  private JTextField inviteField;
  private JPanel invitePanel;
  private JPanel meetingInfoPanel;
  private JLabel meetingInfoLabel;
  private JButton prioritizeButton;
  private JButton setButton;
  private JButton backButton;
  private JPanel buttonPanel;
  private JButton peopleListButton;

  // Declaring table variable
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable meetingTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private int tableRow;
  private int tableColumn;
  private int gResult;
    private final String[] COLUMN_HEADER =
  {
    "Day", "Time"
  };

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  public SetAppointment(String dbName, String tableName, String[] columnNames)
  {
    super("Set Appoinement");

    // Construct the frame 
    this.setBounds(200, 200, 730, 550);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Set Appointment. </center>"
      + "<html", SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);

    // Constructing time 
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    formattedTime = currentDateTime.format(timeFormat);

    // Constructing date
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    formattedDate = currentDateTime.format(dateFormat);

    // formatting and adding time and date to panel
    dateTimeLabel = new JLabel("<html><center>" + formattedTime + "<br>" + formattedDate
      + "</center><html", SwingConstants.CENTER);
    this.timePanel = new JPanel(new FlowLayout());
    timePanel.add(dateTimeLabel);

    // Construct top panel as container 
    this.topPanel = new JPanel(new FlowLayout());
    topPanel.setLayout(new GridLayout(1, 2));
    topPanel.add(textPanel);
    topPanel.add(timePanel);

    // Construct all the inputs 
    nameLabel = new JLabel("Meeting name:");
    nameField = new JTextField(20);
    dateLabel = new JLabel("Select date:");
    dateField = new JTextField(22);
    timeLabel = new JLabel("Select time:");
    timeField = new JTextField(22);
    inviteLabel = new JLabel("Invitees:");
    inviteField = new JTextField(25);

    // construct small input panel (individually)
    this.namePanel = new JPanel(new FlowLayout());
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    this.datePanel = new JPanel(new FlowLayout());
    datePanel.add(dateLabel);
    datePanel.add(dateField);
    this.timeInputPanel = new JPanel(new FlowLayout());
    timeInputPanel.add(timeLabel);
    timeInputPanel.add(timeField);
    this.invitePanel = new JPanel(new FlowLayout());
    invitePanel.add(inviteLabel);
    invitePanel.add(inviteField);

    // construct input panel - bigger panel
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(namePanel);
    inputPanel.add(datePanel);
    inputPanel.add(timeInputPanel);
    inputPanel.add(invitePanel);

    // Upcoming meeting table construction 
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    data = dbObj.to2dArray(dataList);
    dbObj.closeDbConn();
    // Constructing table
    meetingTable = new JTable(data, COLUMN_HEADER);
    // Format Table
    meetingTable.setGridColor(Color.BLACK);
    meetingTable.setBackground(Login.BG_COLOR);
    // Format row
    meetingTable.setRowHeight(35);

    // Format columns 
    column = meetingTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(90);
    column = meetingTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(90);
    // Format header
    header = meetingTable.getTableHeader();
    header.setBackground(Login.BG_COLOR);
    header.setForeground(Login.BG_COLOR);
    
    // Constructing scroll panel
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(meetingTable);
    meetingTable.setFillsViewportHeight(true);

    // construct current meeting info panel
    this.meetingInfoPanel = new JPanel(new FlowLayout());
    this.meetingInfoPanel.setPreferredSize(new Dimension(300, 300)); // How to add Header "Upcoming Meetings"
    meetingInfoPanel.add(meetingTable);

    // construct prioritize button
    this.prioritizeButton = new JButton("Prioritize");
    prioritizeButton.addActionListener(this);

    // construct back button
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);

    // construct back button
    this.peopleListButton = new JButton("Show list of people");
    peopleListButton.addActionListener(this);

    // construct set appointment button
    this.setButton = new JButton("Set Appointment");
    setButton.addActionListener(this);

    // Constructing menu bar 
    this.mainBar = new JMenuBar();
    // constructing menu
    this.supportMenu = new JMenu("Menu");
    // construct menu items
    this.helpItem = new JMenuItem("Help");
    helpItem.addActionListener(this);
    this.logOutItem = new JMenuItem("Log Out");
    logOutItem.addActionListener(this);
    // adds menu to frame
    supportMenu.add(helpItem);
    supportMenu.add(logOutItem);
    mainBar.add(supportMenu);
    this.setJMenuBar(mainBar);

    // adds all the button to button panel
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(peopleListButton);
    buttonPanel.add(setButton);
    buttonPanel.add(prioritizeButton);
    buttonPanel.add(backButton);

    // Adds panel to frame 
    this.add(topPanel, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(meetingInfoPanel, BorderLayout.EAST);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  // Main method that shows the dialy frame 
  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time"
    };
    int result = 0;
    new SetAppointment(dbName, tableName, columnNames);
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
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };

    // read the data 
    String meetingDate;
    String meetingTime;
    String meetingName;
    String meetingPriority;
    String meetingInvites;
    int result;

    //connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection DbConn = objDb.getDbConn();

    if (command.equals("Prioritize"))
    {
      meetingDate = dateField.getText();
      meetingTime = timeField.getText();

      dateField.setText("");
      timeField.setText("");

      String query = "UPDATE Meetings SET Priority = 'high' WHERE Time = ? AND Day = ?";
      try
      {
        //prepared statement 
        PreparedStatement ps = DbConn.prepareStatement(query);

        // enter data to query
        ps.setString(1, meetingTime);
        ps.setString(2, meetingDate);

        //execute the query
        ps.executeUpdate();
        System.out.println("Meeting prioritized");
      }
      catch (SQLException se)
      {
        System.out.println("Error prioritizing data");
      }
    }
    else if (command.equals("Set Appointment"))
    {
      meetingName = nameField.getText();
      meetingDate = dateField.getText();
      meetingTime = timeField.getText();
      meetingInvites = inviteField.getText();

      // pass values to duplication calculation class
      Duplication calculation = new Duplication(meetingDate, meetingTime);
      result = calculation.getResult();

      nameField.setText("");
      dateField.setText("");
      timeField.setText("");
      inviteField.setText("");
      System.out.println("Result in setAppointment is " + result);
      if (result == 0)
      {
        try
        {
          String query = "INSERT INTO Meetings(Day, Time, MeetingName, "
                       + "Invitees) VALUES (?,?,?,?)";
          //prepared statement 
          PreparedStatement ps = DbConn.prepareStatement(query);

          // enter data to query
          ps.setString(3, meetingName);
          ps.setString(1, meetingDate);
          ps.setString(2, meetingTime);
          ps.setString(4, meetingInvites);

          //execute the query
          ps.executeUpdate();
          System.out.println("Data inserted succesfully");
          new MeetingConfirmation();
        }
        catch (SQLException se)
        {
          System.out.println("Error inserting data");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "There is already a meeting for this Time and Date",
          "Duplication Warning", JOptionPane.ERROR_MESSAGE);
      }
      // read data from database
      ArrayList<ArrayList<String>> data = objDb.getData(tableName, columnNames);
      System.out.println(data);
    }
    else if (command.equals("Back"))
    {
      new Welcome(dbName, tableName, columnNames);
      this.dispose();
    }
    else if (command.equals("Help"))
    {
      new Help();
    }
    else if (command.equals("Show list of people"))
    {
      new ListOfPeople();
    }
    else if (command.equals("Log Out"))
    {
      this.dispose();
      new Login();
    }
  }
}
