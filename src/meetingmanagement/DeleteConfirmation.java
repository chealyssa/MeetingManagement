/*
STATUS : DONE
  September 17th, 2021
  This is a small pop-up frame that tells the user that he/she is about to 
  delete a meeting.
  There is two buttons, back/cancel and continue
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

public class DeleteConfirmation extends JFrame implements ActionListener
{
  // Declaring variables used in changes frame
  private JPanel textPanel;
  private JLabel textLabel;
  private JPanel buttonPanel;
  private JButton continueButton;
  private JPanel voidPanel;
  
  public DeleteConfirmation()
  {
    super("Warning");
    
    //frame size 
    this.setBounds(700, 600, 300, 100);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);
    
    // frame layout 
    this.setLayout(new BorderLayout());
    
    // Constructing the text labels with panel
    this.textLabel = new JLabel("You deleted a meeting.", 
                                SwingConstants.CENTER);
    this.textPanel = new JPanel(new FlowLayout());
    textPanel.add(textLabel);
    
    // Constructing continue button
    continueButton = new JButton("Continue");
    continueButton.addActionListener(this);
        
    // Constructing button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(continueButton);
    
    // construct void panel (empty space for aesthetic purposes)
    this.voidPanel = new JPanel(new FlowLayout());
    
    // Adds element to frame 
    this.add(voidPanel, BorderLayout.NORTH);
    this.add(textPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new DeleteConfirmation();
  }
  
  // ActionLister for buttons
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    
    if (command.equals("Continue"))
    {
      System.out.println("Data deleted from database");
      this.dispose();
    }
  }
}
