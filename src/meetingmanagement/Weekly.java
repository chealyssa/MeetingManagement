/*
STATUS: DONE but header doesnt show.
  September 15th, 2021
  This is a pop up frame that suppose to display all the meeting in a week.
WEEKLY - get data from database. if at that time, and this date = 0 (blue) means free
WEEKLY - get data from database. if at that time, and this date = 1 (red) means busy
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

public class Weekly extends JFrame implements ActionListener
{

  // Delcaring buttons, panel etc
  private JLabel textLabel;
  private JPanel textPanel;
  private JLabel dateTimeLabel;
  private String formattedTime;
  private String formattedDate;
  private JPanel timePanel;
  private JPanel topPanel;
  private JPanel dataPanel;
  private JButton newButton; // button for schedule new meeting
  private JButton backButton;
  private JPanel buttonPanel;

  // declaring table variables 
  private Object[][] data;
  private JTable weeklyTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private final String[] COLUMN_HEADER =
  {
    " ", "Mon", "Tue", "Wed", "Thu", "Fri",
    "Sat", "Sun"
  };
  private ArrayList<ArrayList<String>> dataList;
  private String availibilityData;
  private String dayData;
  private String timeData;

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  // Weekly algorithm variable 
  boolean isEmpty = false;

  public Weekly(String dbName, String tableName, String[] columnNames)
  {
    super("Weekly");

    // Construct the frame 
    this.setBounds(200, 300, 600, 470);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Constructing text with panel
    textLabel = new JLabel("<html><center> Weekly Meeting </center>"
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
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);

    // construct data - Static data
    data = new Object[9][8];
    data[0][0] = "9:00AM";
    data[1][0] = "10:00AM";
    data[2][0] = "11:00AM";
    data[3][0] = "12:00PM";
    data[4][0] = "1:00PM";
    data[5][0] = "2:00PM";
    data[6][0] = "3:00PM";
    data[7][0] = "4:00PM";
    data[8][0] = "5:00PM";

    // To decide whether busy or free
    for (int r = 1; r < dataList.size(); r++)
    {
      dayData = dataList.get(r).get(0); // column 0 is always day
      System.out.println("Day is " + dayData); // To test
      timeData = dataList.get(r).get(1); // column 1 is always time
      System.out.println("Time is " + timeData); // To test
      // Finding out is the specific day and time is busy or not
      //<editor-fold defaultstate="collapsed" desc="Monday Schedule">
      if (dayData.equals("Monday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][1] = "Busy";
      }
      else if (dayData.equals("Monday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][1] = "Busy";
      }
      //</editor-fold> 
      //<editor-fold defaultstate="collapsed" desc="Tuesday Schedule">
      if (dayData.equals("Tuesday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][2] = "Busy";
      }
      else if (dayData.equals("Tuesday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][2] = "Busy";
      }
      //</editor-fold> 
      //<editor-fold defaultstate="collapsed" desc="Wednesday Schedule">
      if (dayData.equals("Wednesday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][3] = "Busy";
      }
      else if (dayData.equals("Wednesday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][3] = "Busy";
      }
      //</editor-fold> 
      //<editor-fold defaultstate="collapsed" desc="Thursday Schedule">
      if (dayData.equals("Thursday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][4] = "Busy";
      }
      else if (dayData.equals("Thursday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][4] = "Busy";
      }
      //</editor-fold>   
      //<editor-fold defaultstate="collapsed" desc="Friday Schedule">
      if (dayData.equals("Friday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][5] = "Busy";
      }
      else if (dayData.equals("Friday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][5] = "Busy";
      }
      //</editor-fold> 
      //<editor-fold defaultstate="collapsed" desc="Saturday Schedule">
      if (dayData.equals("Saturday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][6] = "Busy";
      }
      else if (dayData.equals("Saturday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][6] = "Busy";
      }
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Sunday Schedule">
      if (dayData.equals("Sunday") && timeData.equals("9:00AM")) // Monday 9:00AM
      {
        data[0][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("10:00AM")) // Monday 10:00AM
      {
        data[1][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("11:00AM")) // Monday 11:00AM
      {
        data[2][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("12:00PM")) // Monday 12:00PM
      {
        data[3][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("1:00PM")) // Monday 1:00PM
      {
        data[4][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("2:00PM")) // Monday 2:00PM
      {
        data[5][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("3:00PM")) // Monday 3:00PM
      {
        data[6][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("4:00PM")) // Monday 4:00PM
      {
        data[7][7] = "Busy";
      }
      else if (dayData.equals("Sunday") && timeData.equals("5:00PM")) // Monday 5:00PM
      {
        data[8][7] = "Busy";
      }
      //</editor-fold>       
    }

    // Constructing TABLE
    weeklyTable = new JTable(data, COLUMN_HEADER);

    // Format Table
    weeklyTable.setGridColor(Color.BLACK);

    weeklyTable.setBackground(Login.BG_COLOR);

    // Format row
    weeklyTable.setRowHeight(
      35);

    // Format columns 
    column = weeklyTable.getColumnModel().getColumn(0);

    column.setPreferredWidth(
      90);
    column = weeklyTable.getColumnModel().getColumn(1);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(2);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(3);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(4);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(5);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(6);

    column.setPreferredWidth(
      80);
    column = weeklyTable.getColumnModel().getColumn(7);

    column.setPreferredWidth(
      80);

    // Format header
    header = weeklyTable.getTableHeader();

    header.setBackground(Login.BG_COLOR);

    header.setForeground(Login.BG_COLOR);

    // Constructing scroll panel
    scrollTable = new JScrollPane();

    scrollTable.setViewportView(weeklyTable);

    weeklyTable.setFillsViewportHeight(
      true);

    // construct "schedule new meeting" button
    this.newButton = new JButton("Schedule New Meeting");

    newButton.addActionListener(
      this);

    // construct "back" button
    this.backButton = new JButton("Back");

    backButton.addActionListener(
      this);

    // Constructing menu bar 
    this.mainBar = new JMenuBar();
    // constructing menu

    this.supportMenu = new JMenu("Menu");
    // construct menu items

    this.helpItem = new JMenuItem("Help");

    helpItem.addActionListener(
      this);

    this.logOutItem = new JMenuItem("Log Out");

    logOutItem.addActionListener(
      this);
    // adds menu to frame
    supportMenu.add(helpItem);

    supportMenu.add(logOutItem);

    mainBar.add(supportMenu);

    this.setJMenuBar(mainBar);

    // adds all the button to button panel
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    buttonPanel.add(newButton);

    buttonPanel.add(backButton);

    // constring panel for table
    this.dataPanel = new JPanel(new FlowLayout());

    dataPanel.add(scrollTable);

    // Adds panel to frame 
    this.add(topPanel, BorderLayout.NORTH);

    this.add(dataPanel, BorderLayout.CENTER);

    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(
      true);
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
    new Weekly(dbName, tableName, columnNames);
  }

  // Listener for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Schedule New Meeting"))
    {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time"
    };
      new SetAppointment(dbName, tableName, columnNames);
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
