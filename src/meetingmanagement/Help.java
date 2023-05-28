/*
STATUS: DONE
  September 17th, 2021
  This is a medium size pop up frame that shows the user how to use the program 
  This frame shows a picture of an instruction.
NOTE: The picture is not clear...
 */
package meetingmanagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Help extends JFrame implements ActionListener
{
  // Declaring image 
  public final URL HELP_PATH = getClass().getResource("HelpPic.png");
  private final ImageIcon HELP_IMAGE = new ImageIcon(new ImageIcon(
      HELP_PATH).getImage().getScaledInstance(
      825, 488, Image.SCALE_DEFAULT));
  
  // Declaring all the label, panel and button.
  private JPanel textPanel;
  private JLabel textLabel;
  private JLabel picLabel;
  private JPanel buttonPanel;
  private JButton backButton;
  
  public Help()
  {
    super("Help is here");
    
    // frame size
    this.setBounds(400, 200, 830, 550);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Login.BG_COLOR);
    
    // frame layout 
    this.setLayout(new BorderLayout());
    
    // This seemed useless so I removed it (keeping it here just incase)
//    // Constructing the text labels with panel
//    this.textLabel = new JLabel("I hope you find this helpful.", SwingConstants.CENTER);
//    this.textPanel = new JPanel(new FlowLayout());
//    textPanel.add(textLabel);
    
    // Construct label to put picture with the panel
    this.picLabel = new JLabel(HELP_IMAGE);
    
    // Construct back button
    backButton = new JButton("Back");
    backButton.addActionListener(this);
    
    //Constructing panel for back button
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Login.BG_COLOR);
    buttonPanel.add(backButton);
    
    // Adding image to frame
    this.add(picLabel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Help();
  }
  
  // ActionListener for buttons
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
