/*
STATUS: DONE 
  September 17th, 2021
  This class shows the user the duration of the meeting based on given end and 
  start time
NOTE: This class sets the duration for a meeting (shown in meeting info and overview)
add 12 to HH
 */
package meetingmanagement;

import java.util.ArrayList;

public class Duration
{

  // datafield declarations 
  private ArrayList<ArrayList<String>> dataList;
  private int startHour;
  private int startMin;
  private int endHour;
  private int endMin;
  private int resultCalc;
  private int resultHour;
  private int resultMins;
  private String result;

  public Duration(int startHour, int startMin, int endHour, int endMin)
  {
    //db Info 
    String dbName = "Meeting Management";
    String tableName = "Meetings";
    String[] columnNames =
    {
      "Day", "Time", "MeetingName", "Priority", "Invitees", "Duration"
    };
    this.startHour = startHour;
    this.startMin = startMin;
    this.endHour = endHour;
    this.endMin = endMin;

//    // Testing value
//    startHour = 7;
//    startMin = 40;
//    endHour = 16;
//    endMin = 10;
    JavaDatabase dbObj = new JavaDatabase(dbName);
    dataList = dbObj.getData(tableName, columnNames);

    // All the math
    if (endMin < startMin) // Different calculation if end minute is smaller
    {
      endHour = endHour - 1;
      endMin = endMin + 60;
      resultMins = endMin - startMin;
      resultHour = endHour - startHour;
      if (resultHour == 0)
      {
        result = resultMins + " minutes ";
      }
      else if (resultHour > 0)
      {
        if (resultHour == 1 && resultMins == 0)
        {
          result = resultHour + " hour ";
        }
        else if (resultHour > 1 && resultMins == 0)
        {
          result = resultHour + " hours ";
        }
        else if (resultHour == 1 && resultMins != 0)
        {
          result = resultHour + " hour and " + resultMins + " mins ";
        }
        else
        {
          result = resultHour + " hours and " + resultMins + " mins ";
        }
      }
    }
    else if (endMin >= startMin) //When the end minute is either bigger or same
    {
      resultMins = endMin - startMin;
      resultHour = endHour - startHour;
      if (resultHour == 0)
      {
        result = resultMins + " minutes ";
      }
      else if (resultHour > 0)
      {
        if (resultHour == 1 && resultMins == 0)
        {
          result = resultHour + " hour ";
        }
        else if (resultHour > 1 && resultMins == 0)
        {
          result = resultHour + " hours ";
        }
        else if (resultHour == 1 && resultMins != 0)
        {
          result = resultHour + " hour and " + resultMins + " mins ";
        }
        else
        {
          result = resultHour + " hours and " + resultMins + " mins ";
        }
      }
    }
    else if (startHour > endHour) // if user input a bigger start number
    {
      result = "0";
    }
    System.out.println(result);
  }

// Get and set method for result
  public String getResult()
  {
    return result;
  }

  public void setResult(String result)
  {
    this.result = result;
  }

  // Creating a toString method 
  @Override
  public String toString()
  {
    String duration = "Result is " + result;

    return duration;
  }

  public static void main(String[] args)
  {
    int startHour = 7;
    int startMin = 40;
    int endHour = 16;
    int endMin = 10;
    Duration calculation = new Duration(startHour, startMin, endHour, endMin);
    //new Duration(startTime, endTime);
  }
}
