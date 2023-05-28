/*
STATUS: DONE
  September 18th, 2021
  This frame shows multiple people's name that allow user to select.
  This frame will appear when a user is rescheduling or setting (does not work yet)
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListOfPeople extends JFrame implements ActionListener
{
  // Declaring the list, panel and button
  private JList list;
  private JLabel textLabel;
  private JPanel textPanel;
  private JButton backButton;
  private JButton inviteButton;
  private JPanel buttonPanel;
  private JScrollPane ScrollList;
  private DefaultListModel peopleList;
  
  public ListOfPeople()
  {
    super("List of people");
    
    // frame size 
    this.setBounds(930, 300, 300, 200);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);
    
    this.setLayout(new BorderLayout());
    
    // Construct data (list of people)
    peopleList = new DefaultListModel();
    peopleList.addElement("Luus Martí");
    peopleList.addElement("Lujayn Accardi");
    peopleList.addElement("Jerrold Abatescianni");
    peopleList.addElement("Stefanija Mittelman");
    peopleList.addElement("Josias Hancock");
    peopleList.addElement("Aureliana Barret");
    peopleList.addElement("Roberta Quickley");
    peopleList.addElement("Radoslav Dixon");
    peopleList.addElement("Radoslav Dixon");
    peopleList.addElement("Atropos Ivanov");
    
    // Constructing JList 
    list = new JList(peopleList);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(-1);
    list = new JList(peopleList);
    
    // Construnting scrollpane
    ScrollList = new JScrollPane(list);
    ScrollList.setPreferredSize(new Dimension(200,100));
    
    // Constructing the button - putting in panel
    backButton = new JButton("Back");
    backButton.addActionListener(this);
    inviteButton = new JButton("Invite");
    inviteButton.addActionListener(this);
    buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(backButton);
    
    // Adds element to panel
    this.add(ScrollList, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    this.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new ListOfPeople();
  }
  
  // Listener for the buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if(command.equals("Back"))
    {
      this.dispose();
    }
  }
}
