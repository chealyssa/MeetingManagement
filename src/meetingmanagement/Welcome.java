/*
STATUS: DONE -- NO MENU BUTTTON, HEADER NO-SHOW, DATA CAN"T BE CLICKED - RENAME
  September 15th, 2021
  This is the main frame that shows meeting that is upcoming 

TRYING TO PASS THE INT VALUE HERE TO MEETING INFORMATION SO I CAN USE IT AS AN ARRAY
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class Welcome extends JFrame implements ActionListener
{

  // Delcaring buttons, panel etc
  private JLabel textLabel;
  private JPanel textPanel;
  private JPanel timePanel;
  private JLabel dateTimeLabel;
  private String formattedTime;
  private String formattedDate;
  private JPanel topPanel;
  private JPanel dataPanel;
  private JButton dailyButton;
  private JButton weeklyButton;
  private JButton monthlyButton;
  private JPanel buttonPanel;
  private JButton overviewButton;
  private JButton infoButton;

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  // declaring table variables 
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable mainTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private int tableRow;
  private int tableColumn;
  private final String[] COLUMN_HEADER =
  {
    "Day", "Time", "Priority", "Meeting Name"
  };

  public Welcome(String dbName, String tableName, String[] columnNames)
  {
    super("Welcome");

    // Construct the frame 
    this.setBounds(200, 200, 730, 550);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Welcome (Username) </center>"
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

    // TABLE CONSTRUCTION STARTS HERE 
    // construct data 
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    data = dbObj.to2dArray(dataList);
    dbObj.closeDbConn();

    // Constructing TABLE
    mainTable = new JTable(data, COLUMN_HEADER);

    mainTable.addMouseListener(new java.awt.event.MouseAdapter()
    {
      @Override
      public void mouseClicked(
        java.awt.event.MouseEvent evt)
      {
        tableRow = mainTable.rowAtPoint(evt.getPoint());
        tableColumn = mainTable.columnAtPoint(evt.getPoint());
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
    mainTable.setGridColor(Color.BLACK);
    mainTable.setBackground(Login.BG_COLOR);

    // Format row
    mainTable.setRowHeight(35);

    // Format columns 
    column = mainTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(90);
    column = mainTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(90);
    column = mainTable.getColumnModel().getColumn(2);
    column.setPreferredWidth(300);
    column = mainTable.getColumnModel().getColumn(3);
    column.setPreferredWidth(90);

    // dummy data (acts as header for priority)
    // Format header
    header = mainTable.getTableHeader();
    header.setBackground(Login.BG_COLOR);
    header.setForeground(Login.BG_COLOR);

    // Constructing scroll panel
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(mainTable);
    mainTable.setFillsViewportHeight(true);

    // Construct overview button
    this.overviewButton = new JButton("Overview");
    overviewButton.addActionListener(this);

    // construct "daily" button
    this.dailyButton = new JButton("Daily");
    dailyButton.addActionListener(this);

    // construct "weekly" button
    this.weeklyButton = new JButton("Weekly");
    weeklyButton.addActionListener(this);

    // construct "monthly" button
    this.monthlyButton = new JButton("Monthly");
    monthlyButton.addActionListener(this);

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
    buttonPanel.add(dailyButton);
    buttonPanel.add(weeklyButton);
    buttonPanel.add(monthlyButton);
    buttonPanel.add(overviewButton);

    // construct panel for table
    this.dataPanel = new JPanel(new FlowLayout());
    dataPanel.add(mainTable);

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

  // Main method that shows the main frame 
  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };
    new Welcome(dbName, tableName, columnNames);
  }

  // Listener for button
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Meeting Information"))
    {
      //db Info 
      String dbName = "Meeting Management";
      String tableName = "Meetings";
      String[] columnNames =
      {
        "Day", "Time", "MeetingName", "Priority", "Invitees"
      };
      int row = tableRow;
      int col = tableColumn;
      // Test if the guven row and col is correct
      System.out.println(row + "," + col + " in welcome");
      new MeetingInformation(row, col);
      //this.dispose();
    }
    else if (command.equals("Daily"))
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
    else if (command.equals("Weekly"))
    {
      //db Info 
      String dbName = "Meeting Management";
      String tableName = "Meetings";
      String[] columnNames =
      {
        "Day", "Time"
      };
      new Weekly(dbName, tableName, columnNames);
    }
    else if (command.equals("Monthly"))
    {
      new Monthly();
    }
    else if (command.equals("Overview"))
    {
      //db Info 
      String dbName = "Meeting Management";
      String tableName = "Meetings";
      String[] columnNames =
      {
        "MeetingName", "Duration"
      };
      new Overview(dbName, tableName, columnNames);
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
