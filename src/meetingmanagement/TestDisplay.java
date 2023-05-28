/*
STATUS : NOT DONE, NOT TESTED 
  September 21st, 2021
  This is a frame where it tested the database display

 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Map.entry;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TestDisplay extends JFrame
{

  // Database data variables
  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;

  // table variable 
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;
  private String currentDay;
  private String dayFormatted;
  private String dayData;
  final int[] ids = new int[]
  {
    1, 39, 45, 55, 22
  };
//RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//  public boolean include(Entry entry) {
//    L currentObject = (L) (entry.getValue(0));
//    for(int i=0;i<ids.length;i++){
//        if(currentObject.getId()==ids[i]){
//            return true;
//        }
//    }
//    return false;
//  }
//};
//
//TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
//sorter.setRowFilter(filter);
//yourTable.setRowSorter(sorter);
  private TableModel model;

  public TestDisplay(String dbName, String tableName, String[] columnNames)
  {
    // format the frame 
    super("Display");
    this.setBounds(100, 50, 800, 600);
    this.getContentPane().setBackground(Color.LIGHT_GRAY);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Constructing day 
    LocalDateTime currentDateTime = LocalDateTime.now();
    DayOfWeek currentDay = currentDateTime.getDayOfWeek();
    DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEEE");
    dayFormatted = currentDateTime.format(dayFormat);
    System.out.println(dayFormatted);

    // construct data 
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);
    data = dbObj.to2dArray(dataList);
    dbObj.closeDbConn();

    RowFilter<Object, Object> filter = new RowFilter<Object, Object>()
    {
      public boolean include(Entry entry)
      {
        TestDisplay currentObject = (TestDisplay) (entry.getValue(0));
        for (int r = 0; r < dataList.size(); r++)
        {
          dayData = dataList.get(r).get(0);
          if (dayFormatted.equals(dayData)) 
          {
            return true;
          }
        }
        return false;
      }
    };
    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
    sorter.setRowFilter(filter);
    dbTable.setRowSorter(sorter);

    // construct table
    dbTable = new JTable(data, columnNames);

    // format table 
    dbTable.setBackground(Login.BG_COLOR);

    // format headers
    header = dbTable.getTableHeader();

    header.setBackground(Login.BG_COLOR);

    header.setForeground(Login.BG_COLOR);

    //format rows
    dbTable.setRowHeight(
      25);

    //format columns
    column = dbTable.getColumnModel().getColumn(0);

    column.setPreferredWidth(
      30);
    column = dbTable.getColumnModel().getColumn(1);

    column.setPreferredWidth(
      30);
    column = dbTable.getColumnModel().getColumn(2);

    column.setPreferredWidth(
      50);
    column = dbTable.getColumnModel().getColumn(3);

    column.setPreferredWidth(
      30);
    column = dbTable.getColumnModel().getColumn(4);

    column.setPreferredWidth(
      50);

    scrollTable = new JScrollPane();

    scrollTable.getViewport()
      .add(dbTable);
    dbTable.setFillsViewportHeight(
      true);

    this.add(scrollTable, BorderLayout.CENTER);

    this.setVisible(
      true);

  }

  public static void main(String[] args)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees"
    };

    new TestDisplay(dbName, tableName, columnNames);
  }
}
