/*
  STATUS : DONE!
  September 13th, 2021
  This a small pop-up frame that tells the user that changes has been made 
  Displays a small frame that tells/informs the user that changes are made.
 */
package meetingmanagement;

// imports (not organized)
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Changes extends JFrame implements ActionListener
{
  // Declaring variables used in changes frame
  private JPanel textPanel;
  private JLabel textLabel;
  private JPanel buttonPanel;
  private JButton okButton;
  private JPanel voidPanel;
  
  public Changes()
  {
    super("Warning");
    
    //frame size 
    this.setBounds(700, 600, 200, 100);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);
    
    // frame layout 
    this.setLayout(new BorderLayout());
    
    // Constructing the text labels with panel
    this.textLabel = new JLabel("Changes has been made.", SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);
    
    // Constructing button with panel with label
    okButton = new JButton("Okay");
    this.buttonPanel = new JPanel(new FlowLayout());
    okButton.addActionListener(this);
    buttonPanel.add(okButton);
    
    // construct void panel 
    this.voidPanel = new JPanel(new FlowLayout());
    
    // Adds element to frame 
    this.add(voidPanel, BorderLayout.NORTH);
    this.add(textPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Changes();
  }
  
  // ActionListener for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Okay"))
    {
      this.dispose();
    }
  }
}
