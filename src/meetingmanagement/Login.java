/*
STATUS : DONE
  September 7th, 2021 
  This is a login frame, before user can access main frame
  How it works:
    username == username && password == password (true)
NOTE: Username and Password is "test" all lowercase
 */
package meetingmanagement;

// imports (not organized)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener
{

  // Declaring frame color
  public static final Color BG_COLOR = new Color(248, 249, 250);

  // Declaring varibles used in login frame
  private JPanel inputPanel;
  private JPanel buttonPanel;
  private JButton loginButton;
  private JPanel userPanel;
  private JLabel userLabel;
  private JTextField userField;
  private JPanel passPanel;
  private JLabel passLabel;
  private JTextField passField;
  private JPanel voidPanel;

  public Login()
  {
    super("Login");

    // Constructing the frame 
    this.setBounds(400, 345, 500, 200);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(BG_COLOR);

    // frame layout
    this.setLayout(new BorderLayout());

    // Constructing user label and textfield 
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);

    // Constructing pass label and textfield 
    passLabel = new JLabel("Password:  ");
    passField = new JTextField(20);

    // Constructing login button 
    this.loginButton = new JButton("Login");
    loginButton.addActionListener(this);

    // add text panel
    this.userPanel = new JPanel(new FlowLayout());
    userPanel.add(userLabel);
    userPanel.add(userField);
    this.passPanel = new JPanel(new FlowLayout());
    passPanel.add(passLabel);
    passPanel.add(passField);

    // create the bigger panel 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(userPanel);
    inputPanel.add(passPanel);

    // add to button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(loginButton);

    // construct void panel (as a space for aesthetic)
    this.voidPanel = new JPanel(new FlowLayout());
    voidPanel.setPreferredSize(new Dimension(500, 25));

    // Place everything in frame
    this.add(voidPanel, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Makes the frame visible 
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Login();
  }

  // Listener for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    String username;
    String password;
    String userTest = "test"; // THIS IS THE USERNAME
    String passTest = "test"; // THIS IS THE PASSWORD

    if (command.equals("Login"))
    {
      username = userField.getText();
      password = passField.getText();
      // If the username and password match
      if (username.equals(userTest) && password.equals(passTest))
      {
        //db Info 
        String dbName = "Meeting Management";
        String tableName = "Meetings";
        String[] columnNames =
        {
          "Day", "Time", "MeetingName", "Priority", "Invitees"
        };
        System.out.println("Login succesful");
        new Welcome(dbName, tableName, columnNames);
        this.dispose();
      }
      // If either the password or username does not match
      else
      {
        JOptionPane.showMessageDialog(null, "Password or Username incorrect(both is \"test\")",
          "Warning", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
