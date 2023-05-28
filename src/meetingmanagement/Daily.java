/*
STATUS: NOT DONE - HEADER NO-SHOW, DATA CAN"T BE CLICKED
  September 15th, 2021
  This is a pop up frame that suppose to display all the meeting in one day.
TO-DO: If meeting is clicked, display meetingInfo with correct data, Fix missing header 
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.time.DayOfWeek;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Daily extends JFrame implements ActionListener
{

  // Delcaring buttons, panel etc
  private JLabel textLabel;
  private JPanel textPanel;
  private JLabel dateTimeLabel;
  private String formattedTime;
  private String formattedDate;
  private String currentDay;
  private String dayFormatted;
  private JPanel timePanel;
  private JPanel topPanel;
  private JPanel dataPanel;
  private JButton newButton; // button for schedule new meeting
  private JButton backButton;
  private JPanel buttonPanel;

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  // declaring table variables 
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable dailyTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private int tableRow;
  private int tableColumn;
  private JButton infoButton;
  private String dayData;
  private final String[] COLUMN_HEADER = {"Time", "Priority", "Meeting Name"};

  public Daily(String dbName, String tableName, String[] columnNames)
  {
    super("Daily");

    // Construct the frame 
    this.setBounds(200, 200, 730, 550);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Today's Meeting </center>"
      + "<html", SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);

    // Constructing time 
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    formattedTime = currentDateTime.format(timeFormat);

    // Constructing day
    DayOfWeek currentDay = currentDateTime.getDayOfWeek();
    DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEEE");
    dayFormatted = currentDateTime.format(dayFormat);
    System.out.println("Today is " +dayFormatted);

    // Constructing date
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    formattedDate = currentDateTime.format(dateFormat);

    // formatting and adding time and date to panel
    dateTimeLabel = new JLabel("<html><center>" + formattedTime + "<br>" + formattedDate
      + "</center><html", SwingConstants.CENTER);
    this.timePanel = new JPanel(new FlowLayout());
    timePanel.add(dateTimeLabel);

    // Construct top panel as container to have frame side by side
    this.topPanel = new JPanel(new FlowLayout());
    topPanel.setLayout(new GridLayout(1, 2));
    topPanel.add(textPanel);
    topPanel.add(timePanel);
 
    // construct data 
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getDataDaily(tableName, columnNames);
    data = dbObj.to2dArray(dataList);
    dbObj.closeDbConn();

    // Constructing TABLE
    dailyTable = new JTable(data, columnNames);

    dailyTable.addMouseListener(new java.awt.event.MouseAdapter()
    {
      @Override
      public void mouseClicked(
        java.awt.event.MouseEvent evt)
      {
        tableRow = dailyTable.rowAtPoint(evt.getPoint());
        tableColumn = dailyTable.columnAtPoint(evt.getPoint());
        //System.out.println(tableRow + "," + tableColumn);
        if (tableRow == 0)
        {
          infoButton.setEnabled(false);
        }
        else
        {
          infoButton.setEnabled(true);
        }
      }
    });

    // Format Table
    dailyTable.setGridColor(Color.BLACK);
    dailyTable.setBackground(Login.BG_COLOR);

    // Format row
    dailyTable.setRowHeight(35);

    // Format columns 
    column = dailyTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(90);
    column = dailyTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(90);
    column = dailyTable.getColumnModel().getColumn(2);
    column.setPreferredWidth(400);

    // Format header
    header = dailyTable.getTableHeader();
    header.setBackground(Login.BG_COLOR);
    header.setForeground(Login.BG_COLOR);

    // Constructing scroll panel
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dailyTable);
    dailyTable.setFillsViewportHeight(true);

    // construct "schedule new meeting" button
    this.newButton = new JButton("Schedule New Meeting");
    newButton.addActionListener(this);

    // construct "back" button
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);

    // Construct "meetingInfo" button
    this.infoButton = new JButton("Meeting Information");
    infoButton.addActionListener(this);
    infoButton.setEnabled(false);

    // Constructing menu bar 
    this.mainBar = new JMenuBar();
    // constructing menu
    this.supportMenu = new JMenu("Menu");
    // construct menu items
    this.helpItem = new JMenuItem("Help");
    helpItem.addActionListener(this);
    this.logOutItem = new JMenuItem("Log Out");
    logOutItem.addActionListener(this);

    // adds all the button to button panel
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(infoButton);
    buttonPanel.add(newButton);
    buttonPanel.add(backButton);

    // constring panel for table
    this.dataPanel = new JPanel(new FlowLayout());

    //dataPanel.add(scrollTable);
    dataPanel.add(dailyTable);

    // adds menu to frame
    supportMenu.add(helpItem);
    supportMenu.add(logOutItem);
    mainBar.add(supportMenu);
    this.setJMenuBar(mainBar);

    // Adds panel to frame 
    this.add(topPanel, BorderLayout.NORTH);
    this.add(dataPanel, BorderLayout.CENTER);
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

    new Daily(dbName, tableName, columnNames);
  }

  // ActionLister for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Meeting Information"))
    {
      int row = tableRow;
      int col = tableColumn;
      // To test if row and col is correct 
      System.out.println(row + "," + col + " in welcome"); 
      new MeetingInformation(row, col);
      this.dispose();
    }
    else if (command.equals("Schedule New Meeting"))
    {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time"
    };
      this.dispose();
      new SetAppointment(dbName, tableName, columnNames);
      new ListOfPeople();
    }
    else if (command.equals("Back"))
    {
      this.dispose();
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
  }
}
