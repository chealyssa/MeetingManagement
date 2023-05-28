/*
Status: NOT DONE
  September 18th, 2021
  This class shows the user the meeting information.
  Duration and countdown not yet
 */
package meetingmanagement;

// Imports (not organized)
import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import org.apache.derby.impl.sql.compile.TableName;

public class MeetingInformation extends JFrame implements ActionListener
{

  // Declaring variable for frame
  private JLabel nameLabel;
  private JPanel textPanel;
  private JPanel outputPanel;
  private JLabel dateLabel;
  private JPanel datePanel;
  private JLabel timeLabel;
  private JPanel timePanel;
  private JLabel attendeesLabel;
  private JPanel attendeesPanel;
  private JLabel countdownLabel;
  private JPanel countdownPanel;
  private JLabel durationLabel;
  private JPanel durationPanel;
  private JPanel buttonPanel;
  private JButton rescheduleButton;
  private JButton deleteButton;
  private JButton exitButton;
  private JButton prioritizeButton;
  private JButton durationButton;

  // Database stuff
  private int tableRow;
  private int tableColumn;
  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private int gRow;
  private int gCol;
  private String nameData;
  private String dateData;
  private String timeData;
  private String priorityData;
  private String inviteesData;
  private String durationData;

  public MeetingInformation(int row, int col)
  //public MeetingInformation(int row, int col)
  {
    super("Meeting Information");
//    // testing the row and col values 
//    System.out.println(row + "," + col + " in meeting Info");

    // Construct the frame 
    this.setBounds(200, 300, 600, 330);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);

    this.setLayout(new BorderLayout());

    // construct the row value 
    gRow = row;
    gCol = col;

    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees", "Duration"
    };

    // Test print all the data in column 1
    int columnCount = columnNames.length;
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    //data = dbObj.to2dArray(dataList);
    nameData = dataList.get(gRow).get(2);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);
    inviteesData = dataList.get(gRow).get(4);
    durationData = dataList.get(gRow).get(5);

//    System.out.println("name: " + nameData + " from database");
//    System.out.println("date: " + dateData + " from database");
//    System.out.println("time: " + timeData + " from database");
//    System.out.println("Invitees: " + inviteesData + " from database");
    // Constructing text with panel - meeting name
    nameLabel = new JLabel("<html><center>" + nameData + "</center>"
      + "<html", SwingConstants.CENTER);

    this.textPanel = new JPanel();

    this.textPanel.setPreferredSize(
      new Dimension(600, 50));
    textPanel.add(nameLabel);

    // constructing meeting information (output) panel
    this.outputPanel = new JPanel(new FlowLayout());

    // USE THE VALUES FROM THE DATABASE
    // Constructing date output
    dateLabel = new JLabel("Date: " + dateData);

    this.datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    this.datePanel.setPreferredSize(
      new Dimension(600, 30));
    datePanel.add(dateLabel);

    // Constructing time output
    timeLabel = new JLabel("Time: " + timeData);
    this.timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    this.timePanel.setPreferredSize(
      new Dimension(600, 30));
    timePanel.add(timeLabel);

    // Constructing attendees output
    attendeesLabel = new JLabel("<html> Attendees: " + inviteesData + " </html>");
    this.attendeesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    this.attendeesPanel.setPreferredSize(
      new Dimension(600, 50));
    attendeesPanel.add(attendeesLabel);

    // Constucting duration output
    durationLabel = new JLabel("Duration: " + durationData);
    this.durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    this.durationPanel.setPreferredSize(
      new Dimension(600, 30));
    durationPanel.add(durationLabel);

    // Adds all the element into the frame 
    outputPanel.add(datePanel);
    outputPanel.add(timePanel);
    outputPanel.add(durationPanel);
    outputPanel.add(attendeesPanel);

    // Constructing all the buttons
    this.buttonPanel = new JPanel(new FlowLayout());
    this.rescheduleButton = new JButton("Reschedule"); // Reschedule button
    rescheduleButton.addActionListener(this);
    this.prioritizeButton = new JButton("Prioritize"); //Prioritize button
    prioritizeButton.addActionListener(this);
    this.deleteButton = new JButton("Delete"); //Delete button
    deleteButton.addActionListener(this);
    this.exitButton = new JButton("Exit"); //exit button
    exitButton.addActionListener(this);
    this.durationButton = new JButton("Set Duration"); //exit button
    durationButton.addActionListener(this);

    // Adds all the button to panel
    buttonPanel.add(durationButton);
    buttonPanel.add(prioritizeButton);
    buttonPanel.add(rescheduleButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(exitButton);

    // Adds all to frame
    this.add(textPanel, BorderLayout.NORTH);
    this.add(outputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees", "Duration"
    };

    int row = 2;
    int col = 2;
    //new MeetingInformation(row, col);
    new MeetingInformation(row, col);
  }

  // Listener for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    //creates frame for result
    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
    
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };

    // Test print all the data in column 1
    int columnCount = columnNames.length;
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    //data = dbObj.to2dArray(dataList);
    dateData = dataList.get(gRow).get(0);
    timeData = dataList.get(gRow).get(1);
    // create the conection
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection dbConnection = objDb.getDbConn();

    if (command.equals("Reschedule"))
    {
      int row = gRow;
      int col = tableColumn;
      new Reschedule(row, col);
    }
    else if (command.equals("Delete"))
    {
      String query = "DELETE FROM Meetings WHERE Day = '" + dateData + "' "
        + "AND Time ='" + timeData + "'";
      System.out.println(query);
      try
      {
        // prepare statement 
        PreparedStatement ps = dbConnection.prepareStatement(query);
        ps.executeUpdate();
        System.out.println("Data deleted succesfully");
      }
      catch (Exception se)
      {
        System.out.println("Error deleting data");
      }
      new Welcome(dbName, tableName, columnNames);
      this.dispose();
      new DeleteConfirmation();
    }
    if (command.equals("Prioritize"))
    {
      String query = "UPDATE Meetings SET Priority = 'High' "
        + "WHERE Time ='" + timeData + "'AND Day ='" + dateData + "'";
      try
      {
        //prepared statement 
        PreparedStatement ps = dbConnection.prepareStatement(query);
        //execute the query
        ps.executeUpdate();
        System.out.println("Meeting prioritized");
        JOptionPane.showMessageDialog(frame,
          "Meeting prioritized",
          "Prioritize",
          JOptionPane.PLAIN_MESSAGE);
      }
      catch (SQLException se)
      {
        System.out.println("Error prioritizing data");
      }
    }
    if (command.equals("Set Duration"))
    {
      int row = gRow;
      int col = tableColumn;
      new SetDuration(row, col);
    }
    else if (command.equals("Exit"))
    {
      this.dispose();
      new Welcome(dbName, tableName, columnNames);
    }
  }
}
