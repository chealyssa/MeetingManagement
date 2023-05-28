/*
STATUS : DONE
  September 18th, 2021
  This is a general warning frame. Used for everything.
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

public class Warning extends JFrame implements ActionListener
{
  // Declaring variables used in changes frame
  private JPanel textPanel;
  private JLabel textLabel;
  private JPanel buttonPanel;
  private JButton backButton;
  private JPanel voidPanel;
  
  public Warning()
  {
    super("Warning");
    
    //frame size 
    this.setBounds(700, 600, 300, 100);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);
    
    // frame layout 
    this.setLayout(new BorderLayout());
    
    // Constructing the text labels with panel
    this.textLabel = new JLabel("Warning, something has happen.", 
                                SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);
    
    // Constructing continue button
    backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    // Constructing button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(backButton);
    
    
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
    new Warning();
  }
  
  // Listener for button
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Back"))
    {
      this.dispose();
    }
  }
}

