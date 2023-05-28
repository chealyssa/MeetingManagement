/*
STATUS: DONE
  September 18th, 2021
  This is a pop up frame that allows user to reschedule 
NOTE: tThis is an UPDATE for DB 
TO-DO: Cant display current selected meeting yet
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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Reschedule extends JFrame implements ActionListener
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
  private JButton rescheduleButton;
  private JButton backButton;
  private JPanel buttonPanel;
  private JButton peopleListButton;

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  // Declairng items for database 
  private ArrayList<ArrayList<String>> dataList;
  private int gRow;
  private String nameData;
  private String dateData;
  private String timeData;
  private String inviteesData;

  public Reschedule(int row, int col)
  {
    super("Reschedule");
    // testing the row and col values 
    System.out.println(row + "," + col + " in reschedule");
    // Construct the frame 
    this.setBounds(200, 300, 700, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // construct the row value 
    gRow = row;
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    nameData = dataList.get(gRow).get(2);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);
    inviteesData = dataList.get(gRow).get(4);

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Reschedule. </center>"
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
    nameLabel = new JLabel("Meeting name");
    nameField = new JTextField(30);
    dateLabel = new JLabel("Select date");
    dateField = new JTextField(32);
    timeLabel = new JLabel("Select time");
    timeField = new JTextField(32);
    inviteLabel = new JLabel("Invitees");
    inviteField = new JTextField(34);

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

    // construct current meeting info panel 
    this.meetingInfoPanel = new JPanel(new FlowLayout());
    this.meetingInfoPanel.setPreferredSize(new Dimension(200, 300));
    meetingInfoLabel = new JLabel(
      "<html><center>Current Meeting Information<br>"
      + "<br>"
      + "<br>"
      + "Name: " + nameData + "<br> "
      + "<br>"
      + "Date & time: " + dateData + " " + timeData + "<br>"
      + "<br>"
      + "Invitees: " + inviteesData + "</center></html>");
    meetingInfoPanel.add(meetingInfoLabel);

    // construct back button
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);

    // construct back button
    this.peopleListButton = new JButton("Show list of people");
    peopleListButton.addActionListener(this);
    
    // construct reschedule button
    this.rescheduleButton = new JButton("Reschedule");
    rescheduleButton.addActionListener(this);

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
    buttonPanel.add(rescheduleButton);
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
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };
    int row = 2;
    int col = 2;
    new Reschedule(row, col);
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
    String meetingInvites;

    //connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);
    dataList = objDb.getData(tableName, columnNames);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);
    Connection DbConn = objDb.getDbConn();
    
    if (command.equals("Back"))
    {
      this.dispose();
      new Welcome(dbName, tableName, columnNames);
    }
    else if (command.equals("Reschedule")) // for time and day, use the global 
    //value from meeting info after read
    {
      meetingName = nameField.getText();
      meetingDate = dateField.getText();
      meetingTime = timeField.getText();
      meetingInvites = inviteField.getText();

      nameField.setText("");
      dateField.setText("");
      timeField.setText("");
      inviteField.setText("");
      
      String query = "UPDATE Meetings SET Time = ?, "
        + "Day = ?, "
        + "MeetingName = ?, "
        + "Invitees = ? "
        + "WHERE Time ='" +timeData+ "' AND Day ='"+dateData+ "'"; 
      System.out.println(query);
      try
      {
        //prepared statement 
        PreparedStatement ps = DbConn.prepareStatement(query);

        // enter data to query
        ps.setString(1, meetingTime);
        ps.setString(2, meetingDate);
        ps.setString(3, meetingName);
        ps.setString(4, meetingInvites);

        //execute the query
        ps.executeUpdate();
        System.out.println("Meeting has been reschedule");
        new Changes();
      }
      catch (SQLException se)
      {
        se.printStackTrace();
        System.out.println("Error rescheduling data");
      }
    }
    else if (command.equals("Help"))
    {
      new Help();
    }
    else if (command.equals("Log Out"))
    {
      this.dispose();
      new Login();
    }
    else if (command.equals("Show list of people"))
    {
      new ListOfPeople();
    }
    // read data from database
    ArrayList<ArrayList<String>> data = objDb.getData(tableName, columnNames);
    System.out.println(data);
  }
}
