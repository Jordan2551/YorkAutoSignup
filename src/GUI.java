import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GUI {

	//COMPONENTS
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private TextArea consoleMessages;
	private JTextField AC1;
	private JTextField AC2;
	private JTextField AC3;
	private JTextField TC1;
	private JTextField TC2;
	private JTextField TC3;
	
	private static SeleniumHelper seleniumHelper;
	private static Props props;
	private static ArrayList<String> coursesToEnroll;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDevelopedWith = new JLabel("Developed with <3 by JC Software");
		lblDevelopedWith.setBounds(69, 687, 432, 16);
		lblDevelopedWith.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedWith.setForeground(Color.blue);
		frame.getContentPane().add(lblDevelopedWith);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				consoleMessages.setText(consoleMessages.getText() + "! Automatic course add started... ! \n");
				
				coursesToEnroll = new ArrayList<String>();
				coursesToEnroll.add(AC1.getText());
				coursesToEnroll.add(AC2.getText());
				coursesToEnroll.add(AC3.getText());
				seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"));
				seleniumHelper.signIn(username.getText(), password.getText());
				consoleMessages.setText(consoleMessages.getText() + "! Login to Passport York was successful ! \n");
				ArrayList<String> resultMessages = seleniumHelper.add(coursesToEnroll);
				for(int i = 0; i < resultMessages.size(); i++) {
					consoleMessages.setText(consoleMessages.getText() + resultMessages.get(i));
				}
					
			}
		});
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddCourse.setBounds(106, 204, 150, 83);
		frame.getContentPane().add(btnAddCourse);
		
		JButton btnTransferCourse = new JButton("Transfer Course");
		btnTransferCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTransferCourse.setBounds(317, 204, 150, 83);
		btnTransferCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				consoleMessages.setText(consoleMessages.getText() + "! Automatic course transfer started... ! \n");
				
				coursesToEnroll = new ArrayList<String>();
				coursesToEnroll.add(TC1.getText());
				coursesToEnroll.add(TC2.getText());
				coursesToEnroll.add(TC3.getText());
				seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"));
				seleniumHelper.signIn(username.getText(), password.getText());
				consoleMessages.setText(consoleMessages.getText() + "! Login to Passport York was successful ! \n");
				ArrayList<String> resultMessages = seleniumHelper.transfer(coursesToEnroll);
				for(int i = 0; i < resultMessages.size(); i++) {
					consoleMessages.setText(consoleMessages.getText() + resultMessages.get(i));
				}
				
			}
		});
		frame.getContentPane().add(btnTransferCourse);
		
		JLabel lblPassportYorkInfo = new JLabel("Passport York Credentials");
		lblPassportYorkInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassportYorkInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassportYorkInfo.setForeground(new Color(255, 0, 0));
		lblPassportYorkInfo.setBounds(51, 28, 432, 16);
		frame.getContentPane().add(lblPassportYorkInfo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(69, 79, 88, 16);
		frame.getContentPane().add(lblUsername);
		
		username = new JTextField();
		username.setBounds(140, 76, 116, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(286, 79, 88, 16);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(351, 76, 116, 22);
		frame.getContentPane().add(password);
		
		JLabel lblCourse = new JLabel("Course #1:");
		lblCourse.setBounds(90, 327, 88, 16);
		frame.getContentPane().add(lblCourse);
		
		AC1 = new JTextField();
		AC1.setColumns(10);
		AC1.setBounds(162, 324, 103, 22);
		frame.getContentPane().add(AC1);
		
		AC2 = new JTextField();
		AC2.setColumns(10);
		AC2.setBounds(162, 356, 103, 22);
		frame.getContentPane().add(AC2);
		
		JLabel lblCourse_2 = new JLabel("Course #2:");
		lblCourse_2.setBounds(90, 359, 88, 16);
		frame.getContentPane().add(lblCourse_2);
		
		AC3 = new JTextField();
		AC3.setColumns(10);
		AC3.setBounds(162, 388, 103, 22);
		frame.getContentPane().add(AC3);
		
		JLabel lblCourse_1 = new JLabel("Course #3:");
		lblCourse_1.setBounds(90, 391, 88, 16);
		frame.getContentPane().add(lblCourse_1);
		
		TC1 = new JTextField();
		TC1.setColumns(10);
		TC1.setBounds(380, 324, 103, 22);
		frame.getContentPane().add(TC1);
		
		JLabel label = new JLabel("Course #1:");
		label.setBounds(308, 327, 88, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Course #2:");
		label_1.setBounds(308, 359, 88, 16);
		frame.getContentPane().add(label_1);
		
		TC2 = new JTextField();
		TC2.setColumns(10);
		TC2.setBounds(380, 356, 103, 22);
		frame.getContentPane().add(TC2);
		
		JLabel label_2 = new JLabel("Course #3:");
		label_2.setBounds(308, 391, 88, 16);
		frame.getContentPane().add(label_2);
		
		TC3 = new JTextField();
		TC3.setColumns(10);
		TC3.setBounds(380, 388, 103, 22);
		frame.getContentPane().add(TC3);
		
		JLabel lblMsgs = new JLabel("Console Log:");
		lblMsgs.setForeground(new Color(255, 0, 0));
		lblMsgs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMsgs.setBounds(233, 516, 88, 16);
		frame.getContentPane().add(lblMsgs);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox.setBounds(39, 13, 490, 107);
		frame.getContentPane().add(verticalBox);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setBounds(180, 144, 194, 20);
		frame.getContentPane().add(lblWhatWouldYou);
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setForeground(new Color(0, 0, 128));
		lblWhatWouldYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		consoleMessages = new TextArea();
		consoleMessages.setFont(new Font("Dialog", Font.BOLD, 14));
		consoleMessages.setBounds(51, 538, 468, 107);
		frame.getContentPane().add(consoleMessages);
		
		JButton saveButton = new JButton("Save Changes");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		saveButton.setBounds(219, 443, 137, 44);
		frame.getContentPane().add(saveButton);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox_1.setBounds(39, 133, 490, 541);
		frame.getContentPane().add(verticalBox_1);
		
		
		//Get properties from config file and populate the appropriate fields
		props = new Props();	
		props.readProps();
		username.setText(props.getPropList().getProp("username"));
		password.setText(props.getPropList().getProp("password"));
		AC1.setText(props.getPropList().getProp("AC1"));
		AC2.setText(props.getPropList().getProp("AC2"));
		AC3.setText(props.getPropList().getProp("AC3"));
		TC1.setText(props.getPropList().getProp("TC1"));
		TC2.setText(props.getPropList().getProp("TC2"));
		TC3.setText(props.getPropList().getProp("TC3"));

	}
	
	public void saveChanges() {
		consoleMessages.setText(consoleMessages.getText() + props.saveProps(username.getText(), password.getText(),AC1.getText(), AC2.getText(), AC3.getText(), TC1.getText(), TC2.getText(), TC3.getText()));
	}
	
	
}
