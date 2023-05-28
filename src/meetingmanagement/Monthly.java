/*
STATUS: DONE
  September 18th, 2021
  This frame shows meeting in a calander form 
- Show correct calender
- For now it only works for year oct 2021 - may 2022
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Monthly extends JFrame implements ActionListener
{

  // Declaring buttons
  private JButton newButton; // button for schedule new meeting
  private JButton backButton;
  private JPanel buttonPanel;

  // Declaring variables for menu bar
  private JMenuBar mainBar;
  private JMenu supportMenu;
  private JMenuItem helpItem;
  private JMenuItem logOutItem;

  // Declaring table, panel and buttons
  private Object[][] data;
  private JTable monthlyTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private final String[] COLUMN_HEADER =
  {
    "Sunday", "Monday", "Tuesday", "Wednesday",
    "Thursday", "Friday", "Saturday"
  };
  
  // declaring calculation for months
  private String formattedMonth;
  private String testMonth;
  final static String OCTOBER_VALUE = "Oct";
  final static String NOVEMBER_VALUE = "Nov";
  final static String DECEMBER_VALUE = "Dec";
  final static String JANUARY_VALUE = "Jan";
  final static String FEBRUARY_VALUE = "Feb";
  final static String MARCH_VALUE = "Mar";
  final static String APRIL_VALUE = "Apr";
  final static String MAY_VALUE = "May";
  
  public Monthly()
  {
    super("Monthly calander (holidays and important dates) ");

    // Construct the frame 
    this.setBounds(200, 300, 600, 490);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // Getting the local month
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM");
    formattedMonth = currentDateTime.format(dateFormat);
    System.out.println(formattedMonth); // The current month 
    
    testMonth = "Nov"; // Use this to test all the months (MMM)
    
    // Constructing data 
    //<editor-fold defaultstate="collapsed" desc="Calander data for October">
    if (formattedMonth.equals(OCTOBER_VALUE))
    {
      data = new Object[6][7];
      //Row [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = " ";
      data[0][3] = " ";
      data[0][4] = " ";
      data[0][5] = "1";
      data[0][6] = "2";
      //Row [1]
      data[1][0] = "3";
      data[1][1] = "4";
      data[1][2] = "5";
      data[1][3] = "6";
      data[1][4] = "7";
      data[1][5] = "8";
      data[1][6] = "9";
      //Row [2]
      data[2][0] = "10";
      data[2][1] = "11";
      data[2][2] = "12";
      data[2][3] = "13";
      data[2][4] = "14";
      data[2][5] = "15";
      data[2][6] = "16";
      //Row [3]
      data[3][0] = "17";
      data[3][1] = "18";
      data[3][2] = "19";
      data[3][3] = "20";
      data[3][4] = "21";
      data[3][5] = "22";
      data[3][6] = "23";
      //Row [4]
      data[4][0] = "24";
      data[4][1] = "25";
      data[4][2] = "26";
      data[4][3] = "27";
      data[4][4] = "28";
      data[4][5] = "29";
      data[4][6] = "30";
      //Row [5]
      data[5][0] = "<html>31<br> Halloween! </html>";
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Calander data for November">
    else if (formattedMonth.equals(NOVEMBER_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = "1";
      data[0][2] = "<html>2<br> Election Day </html>";
      data[0][3] = "3";
      data[0][4] = "<html>4<br> Diwali! </html>";
      data[0][5] = "5";
      data[0][6] = "6";
      //Array [1]
      data[1][0] = "<html>7<br> Daylight Saving Ends</html>";
      data[1][1] = "8";
      data[1][2] = "9";
      data[1][3] = "10";
      data[1][4] = "<html>11<br> Veterans Day </html>";
      data[1][5] = "12";
      data[1][6] = "13";
      //Array [2]
      data[2][0] = "14";
      data[2][1] = "15";
      data[2][2] = "16";
      data[2][3] = "17";
      data[2][4] = "18";
      data[2][5] = "19";
      data[2][6] = "20";
      //Array [3]
      data[3][0] = "21";
      data[3][1] = "22";
      data[3][2] = "23";
      data[3][3] = "24";
      data[3][4] = "<html>25<br> Thanksgiving! </html>";
      data[3][5] = "26";
      data[3][6] = "27";
      //Array [4]
      data[4][0] = "28";
      data[4][1] = "29";
      data[4][2] = "30";
      data[4][3] = " ";
      data[4][4] = " ";
      data[4][5] = " ";
      data[4][6] = " ";
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Calander data for December">
    else if (formattedMonth.equals(DECEMBER_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = " ";
      data[0][3] = "1";
      data[0][4] = "2";
      data[0][5] = "3";
      data[0][6] = "4";
      //Array [1]
      data[1][0] = "5";
      data[1][1] = "6";
      data[1][2] = "7";
      data[1][3] = "8";
      data[1][4] = "9";
      data[1][5] = "10";
      data[1][6] = "11";
      //Array [2]
      data[2][0] = "12";
      data[2][1] = "13";
      data[2][2] = "14";
      data[2][3] = "15";
      data[2][4] = "16";
      data[2][5] = "17";
      data[2][6] = "18";
      //Array [3]
      data[3][0] = "19";
      data[3][1] = "20";
      data[3][2] = "21";
      data[3][3] = "22";
      data[3][4] = "23";
      data[3][5] = "<html>24<br> Christmas Eve </html>";
      data[3][6] = "<html> 25 <br> Christmas! </html>";
      //Array [4]
      data[4][0] = "26";
      data[4][1] = "27";
      data[4][2] = "28";
      data[4][3] = "29";
      data[4][4] = "30";
      data[4][5] = "<html>31<br> New Years's Eve </html>";
      data[4][6] = " ";
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Calander data for January">
    else if (formattedMonth.equals(JANUARY_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = " ";
      data[0][3] = " ";
      data[0][4] = " ";
      data[0][5] = " ";
      data[0][6] = "<html>1<br> New Year's Day </html>";
      //Array [1]
      data[1][0] = "2";
      data[1][1] = "3";
      data[1][2] = "4";
      data[1][3] = "5";
      data[1][4] = "6";
      data[1][5] = "7";
      data[1][6] = "8";
      //Array [2]
      data[2][0] = "9";
      data[2][1] = "10";
      data[2][2] = "11";
      data[2][3] = "12";
      data[2][4] = "13";
      data[2][5] = "14";
      data[2][6] = "15";
      //Array [3]
      data[3][0] = "16";
      data[3][1] = "17";
      data[3][2] = "18";
      data[3][3] = "19";
      data[3][4] = "20";
      data[3][5] = "21";
      data[3][6] = "22";
      //Array [4]
      data[4][0] = "23";
      data[4][1] = "24";
      data[4][2] = "25";
      data[4][3] = "26";
      data[4][4] = "27";
      data[4][5] = "28";
      data[4][6] = "29";
      //Array [5]
      data[5][0] = "30";
      data[5][1] = "31";
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Calander data for February">
    else if (formattedMonth.equals(FEBRUARY_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = "1";
      data[0][3] = "2";
      data[0][4] = "3";
      data[0][5] = "4";
      data[0][6] = "5";
      //Array [1]
      data[1][0] = "6";
      data[1][1] = "7";
      data[1][2] = "8";
      data[1][3] = "9";
      data[1][4] = "10";
      data[1][5] = "11";
      data[1][6] = "12";
      //Array [2]
      data[2][0] = "13";
      data[2][1] = "14";
      data[2][2] = "15";
      data[2][3] = "16";
      data[2][4] = "17";
      data[2][5] = "18";
      data[2][6] = "19";
      //Array [3]
      data[3][0] = "20";
      data[3][1] = "<html>21<br> Presidents' Day </html>";
      data[3][2] = "22";
      data[3][3] = "23";
      data[3][4] = "24";
      data[3][5] = "25";
      data[3][6] = "26";
      //Array [4]
      data[4][0] = "27";
      data[4][1] = "28";
      data[4][2] = "  ";
      data[4][3] = "  ";
      data[4][4] = "  ";
      data[4][5] = "  ";
      data[4][6] = "  ";
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Calander data for March">
    else if (formattedMonth.equals(MARCH_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = "1";
      data[0][3] = "2";
      data[0][4] = "3";
      data[0][5] = "4";
      data[0][6] = "5";
      //Array [1]
      data[1][0] = "6";
      data[1][1] = "7";
      data[1][2] = "8";
      data[1][3] = "9";
      data[1][4] = "10";
      data[1][5] = "11";
      data[1][6] = "12";
      //Array [2]
      data[2][0] = "<html>13<br> Daylight Saving Time </html>";
      data[2][1] = "14";
      data[2][2] = "15";
      data[2][3] = "16";
      data[2][4] = "17";
      data[2][5] = "18";
      data[2][6] = "19";
      //Array [3]
      data[3][0] = "20";
      data[3][1] = "21";
      data[3][2] = "22";
      data[3][3] = "23";
      data[3][4] = "24";
      data[3][5] = "25";
      data[3][6] = "26";
      //Array [4]
      data[4][0] = "27";
      data[4][1] = "28";
      data[4][2] = "29";
      data[4][3] = "30";
      data[4][4] = "31";
      data[4][5] = "  ";
      data[4][6] = "  ";
    }
    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Calander data for April">
    else if (formattedMonth.equals(APRIL_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = " ";
      data[0][1] = " ";
      data[0][2] = " ";
      data[0][3] = " ";
      data[0][4] = " ";
      data[0][5] = "<html>1<br> April Fools' Day </html>";
      data[0][6] = "<html>2<br> Ramadan </html>";
      //Array [1]
      data[1][0] = "3";
      data[1][1] = "4";
      data[1][2] = "5";
      data[1][3] = "6";
      data[1][4] = "7";
      data[1][5] = "8";
      data[1][6] = "9";
      //Array [2]
      data[2][0] = "10";
      data[2][1] = "11";
      data[2][2] = "12";
      data[2][3] = "13";
      data[2][4] = "14";
      data[2][5] = "<html>15<br> Tax Day </html>";
      data[2][6] = "16";
      //Array [3]
      data[3][0] = "<html>17<br> Easter </html>";
      data[3][1] = "<html>18<br> Tax Day </html>";
      data[3][2] = "19";
      data[3][3] = "20";
      data[3][4] = "21";
      data[3][5] = "<html>22<br> Earth Day </html>";
      data[3][6] = "23";
      //Array [4]
      data[4][0] = "24";
      data[4][1] = "25";
      data[4][2] = "26";
      data[4][3] = "27";
      data[4][4] = "28";
      data[4][5] = "29";
      data[4][6] = "30";
    }
    //</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Calander data for May">
    else if (formattedMonth.equals(MAY_VALUE))
    {
      data = new Object[6][7];
      //Array [0]
      data[0][0] = "<html>1<br> Eid al-Fitr </html>";
      data[0][1] = "2";
      data[0][2] = "3";
      data[0][3] = "4";
      data[0][4] = "5";
      data[0][5] = "6";
      data[0][6] = "7";
      //Array [1]
      data[1][0] = "8";
      data[1][1] = "9";
      data[1][2] = "10";
      data[1][3] = "11";
      data[1][4] = "12";
      data[1][5] = "13";
      data[1][6] = "14";
      //Array [2]
      data[2][0] = "15";
      data[2][1] = "16";
      data[2][2] = "17";
      data[2][3] = "18";
      data[2][4] = "19";
      data[2][5] = "20";
      data[2][6] = "21";
      //Array [3]
      data[3][0] = "22";
      data[3][1] = "23";
      data[3][2] = "24";
      data[3][3] = "25";
      data[3][4] = "26";
      data[3][5] = "27";
      data[3][6] = "28";
      //Array [4]
      data[4][0] = "29";
      data[4][1] = "<html>30<br> Memorial Day </html>";
      data[4][2] = "31";
      data[4][3] = "  ";
      data[4][4] = "  ";
      data[4][5] = "  ";
      data[4][6] = "  ";
    }
    //</editor-fold>     
    
    // Construct Table
    monthlyTable = new JTable(data, COLUMN_HEADER);

    //Format table
    monthlyTable.setGridColor(Color.BLACK);
    monthlyTable.setBackground(Login.BG_COLOR);

    // format row 
    monthlyTable.setRowHeight(63);

    // format columns - I feel like this is wrong
    column = monthlyTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(2);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(3);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(4);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(5);
    column.setPreferredWidth(40);
    column = monthlyTable.getColumnModel().getColumn(6);
    column.setPreferredWidth(40);

    // Format header
    header = monthlyTable.getTableHeader();
    header.setBackground(Color.LIGHT_GRAY);

    // Constructing scroll panel
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(monthlyTable);
    monthlyTable.setFillsViewportHeight(true);

    // construct "schedule new meeting" button
    this.newButton = new JButton("Schedule New Meeting");
    newButton.addActionListener(this);

    // construct "back" button
    this.backButton = new JButton("Back");
    backButton.addActionListener(this);

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
    buttonPanel.add(newButton);
    buttonPanel.add(backButton);

    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.CENTER);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Monthly();
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
