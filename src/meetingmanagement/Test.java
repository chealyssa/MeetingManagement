/*
STATUS: DONE
This is a class where all the calculation will be tested 
NOTE: Will only test math equations
 */
package meetingmanagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

public class Test
{

  final static String OCTOBER_VALUE = "Oct";
  final static String NOVEMBER_VALUE = "Nov";
  final static String DECEMBER_VALUE = "Dec";
  final static String JANUARY_VALUE = "Jan";
  final static String FEBRUARY_VALUE = "Feb";
  final static String MARCH_VALUE = "Mar";
  final static String APRIL_VALUE = "Apr";
  final static String MAY_VALUE = "May";

  private Object[][] data;
  private JLabel dateTimeLabel;
  private String formattedMonth;
  private String testTime;
  private String timeString;
  private char timeChar1;
  private char timeChar2;
  private String timeString1;
  private String timeString2;
  private String checkZero;

  public Test()
  {
    checkZero = "0"; // check zero since its string
    testTime = "0330"; // test time

    timeChar1 = testTime.charAt(0); // first value of the time
    //System.out.println(timeChar1);
    timeString1 = String.valueOf(timeChar1); // char to string
    System.out.println(timeString1); // prints 0

    timeChar2 = testTime.charAt(1); // second value of the time
    //System.out.println(timeChar2);
    timeString2 = String.valueOf(timeChar2); // char to string
    System.out.println(timeString2); // prints 3

    if (timeString1.equals(checkZero))
    {
      
      //System.out.println(a);
    }

//    // Getting the local month
//    LocalDateTime currentDateTime = LocalDateTime.now();
//    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM");
//    formattedMonth = currentDateTime.format(dateFormat);
//    System.out.println(formattedMonth);
//    
//    // Construct table
//    // Constructing data (Just the date for now)
//    String number1 = "number2";
//    String number2 = "number1";
//    String number3 = "number2";
//
//    if (number1.equals(number2))
//    {
//      data = new Object[5][7];
//      //Array [0]
//      data[0][0] = " ";
//      data[0][1] = " ";
//      data[0][2] = " ";
//      data[0][3] = "1";
//      data[0][4] = "2";
//      data[0][5] = "3";
//      data[0][6] = "4";
//      //Array [1]
//      data[1][0] = "5";
//      data[1][1] = "6";
//      data[1][2] = "7";
//      data[1][3] = "8";
//      data[1][4] = "9";
//      data[1][5] = "10";
//      data[1][6] = "11";
//      //Array [2]
//      data[2][0] = "12";
//      data[2][1] = "14";
//      data[2][2] = "15";
//      data[2][3] = "16";
//      data[2][4] = "17";
//      data[2][5] = "18";
//      data[2][6] = "19";
//      //Array [3]
//      data[3][0] = "20";
//      data[3][1] = "21";
//      data[3][2] = "22";
//      data[3][3] = "23";
//      data[3][4] = "24";
//      data[3][5] = "25";
//      data[3][6] = "26";
//    }
//    else if (number1.equals(number3))
//    {
//      data = new Object[5][7];
//      //Array [0]
//      data[0][0] = " ";
//      data[0][1] = " ";
//      data[0][2] = " ";
//      data[0][3] = "0";
//      data[0][4] = "0";
//      data[0][5] = "0";
//      data[0][6] = "0";
//      //Array [1]
//      data[1][0] = "0";
//      data[1][1] = "0";
//      data[1][2] = "0";
//      data[1][3] = "0";
//      data[1][4] = "0";
//      data[1][5] = "10";
//      data[1][6] = "11";
//      //Array [2]
//      data[2][0] = "12";
//      data[2][1] = "14";
//      data[2][2] = "15";
//      data[2][3] = "16";
//      data[2][4] = "17";
//      data[2][5] = "18";
//      data[2][6] = "19";
//      //Array [3]
//      data[3][0] = "20";
//      data[3][1] = "21";
//      data[3][2] = "22";
//      data[3][3] = "23";
//      data[3][4] = "24";
//      data[3][5] = "25";
//      data[3][6] = "26";
//    }
//    // skips column 1 since those are reserved for time
//    for (int i = 0; i < 5; i++) 
//    {
//      for (int j = 1; j < 5; j++)
//      {
//        System.out.println(i+ "," +j);
//      }
//    }
  }

  public static void main(String[] args)
  {
    new Test();
    //System.out.println("Some calculation testing is happening here");
  }
}
