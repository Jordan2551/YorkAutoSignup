import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class GUI {

	//COMPONENTS
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private TextArea consoleMessages;
	private JCheckBox enableWindowlessSignup;
	private JRadioButton rdbtnAddCourse;
	private Timer autoSignupTimer;
	private JTextField AC1;
	private JTextField AC2;
	private JTextField AC3;
	private JTextField TC1;
	private JTextField TC2;
	private JTextField TC3;
	
	private static SeleniumHelper seleniumHelper;
	private static Props props;
	private static ArrayList<String> coursesToEnroll;
	private JTextField signupEvery;


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
		frame.setBounds(100, 100, 600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDevelopedWith = new JLabel("Developed with <3 by JC Software");
		lblDevelopedWith.setBounds(69, 835, 432, 16);
		lblDevelopedWith.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedWith.setForeground(Color.blue);
		frame.getContentPane().add(lblDevelopedWith);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startAddCourse();			
			}
		});
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddCourse.setBounds(106, 358, 150, 83);
		frame.getContentPane().add(btnAddCourse);
		
		JButton btnTransferCourse = new JButton("Transfer Course");
		btnTransferCourse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTransferCourse.setBounds(317, 358, 150, 83);
		btnTransferCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTransferCourse();				
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
		lblCourse.setBounds(90, 481, 88, 16);
		frame.getContentPane().add(lblCourse);
		
		AC1 = new JTextField();
		AC1.setColumns(10);
		AC1.setBounds(162, 478, 103, 22);
		frame.getContentPane().add(AC1);
		
		AC2 = new JTextField();
		AC2.setColumns(10);
		AC2.setBounds(162, 510, 103, 22);
		frame.getContentPane().add(AC2);
		
		JLabel lblCourse_2 = new JLabel("Course #2:");
		lblCourse_2.setBounds(90, 513, 88, 16);
		frame.getContentPane().add(lblCourse_2);
		
		AC3 = new JTextField();
		AC3.setColumns(10);
		AC3.setBounds(162, 542, 103, 22);
		frame.getContentPane().add(AC3);
		
		JLabel lblCourse_1 = new JLabel("Course #3:");
		lblCourse_1.setBounds(90, 545, 88, 16);
		frame.getContentPane().add(lblCourse_1);
		
		TC1 = new JTextField();
		TC1.setColumns(10);
		TC1.setBounds(380, 478, 103, 22);
		frame.getContentPane().add(TC1);
		
		JLabel label = new JLabel("Course #1:");
		label.setBounds(308, 481, 88, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Course #2:");
		label_1.setBounds(308, 513, 88, 16);
		frame.getContentPane().add(label_1);
		
		TC2 = new JTextField();
		TC2.setColumns(10);
		TC2.setBounds(380, 510, 103, 22);
		frame.getContentPane().add(TC2);
		
		JLabel label_2 = new JLabel("Course #3:");
		label_2.setBounds(308, 545, 88, 16);
		frame.getContentPane().add(label_2);
		
		TC3 = new JTextField();
		TC3.setColumns(10);
		TC3.setBounds(380, 542, 103, 22);
		frame.getContentPane().add(TC3);
		
		JLabel lblMsgs = new JLabel("Console Log:");
		lblMsgs.setForeground(new Color(255, 0, 0));
		lblMsgs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMsgs.setBounds(233, 670, 88, 16);
		frame.getContentPane().add(lblMsgs);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox.setBounds(39, 13, 490, 107);
		frame.getContentPane().add(verticalBox);
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
		lblWhatWouldYou.setBounds(185, 318, 194, 20);
		frame.getContentPane().add(lblWhatWouldYou);
		lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatWouldYou.setForeground(new Color(0, 0, 128));
		lblWhatWouldYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		consoleMessages = new TextArea();
		consoleMessages.setFont(new Font("Dialog", Font.BOLD, 14));
		consoleMessages.setBounds(51, 692, 468, 107);
		frame.getContentPane().add(consoleMessages);
		
		JButton saveButton = new JButton("Save Changes");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		saveButton.setBounds(219, 597, 137, 44);
		frame.getContentPane().add(saveButton);
		
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
		
		JLabel snt = new JLabel("Sign Up Every (minutes):");
		snt.setBounds(140, 182, 150, 16);
		frame.getContentPane().add(snt);

		signupEvery = new JTextField();
		signupEvery.setColumns(10);
		signupEvery.setBounds(293, 179, 116, 22);
		frame.getContentPane().add(signupEvery);
		signupEvery.setText(props.getPropList().getProp("autoSignupTime"));
		
		enableWindowlessSignup = new JCheckBox("Enable Windowless Signup");
		enableWindowlessSignup.setBounds(197, 145, 181, 25);
		frame.getContentPane().add(enableWindowlessSignup);
		enableWindowlessSignup.setSelected(Props.getCheckBoxState(props.getPropList().getProp("enableWindowlessSignup")));
		
		JButton startTimedSignup = new JButton("Start ");
		startTimedSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int randomRange = new Random().nextInt(92110 - 10000) + 10000;
				long signupTime = Props.getNumber(signupEvery.getText());
				autoSignupTimer = new Timer();
				autoSignupTimer.scheduleAtFixedRate(new AutoSignupTimerTask(rdbtnAddCourse.isSelected()), 1000,  signupTime != -1 ? (signupTime * 60000) + randomRange  : 900000);//Default time (for incorrect input is 15 minutes)
				consoleMessages.setText(consoleMessages.getText() + "! Automatic signup has started. Your signup will begin in " + signupTime  + " minutes from now ! \n");
			}
		});
		startTimedSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startTimedSignup.setBounds(128, 248, 137, 29);
		frame.getContentPane().add(startTimedSignup);
		
		JButton stopAutoSignup = new JButton("Stop");
		stopAutoSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoSignupTimer.cancel();
				consoleMessages.setText(consoleMessages.getText() + "! Automatic signup has stopped ! \n");
			}
		});
		stopAutoSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stopAutoSignup.setBounds(277, 248, 137, 29);
		frame.getContentPane().add(stopAutoSignup);
		
		rdbtnAddCourse = new JRadioButton("Add Course");
		rdbtnAddCourse.setSelected(true);
		rdbtnAddCourse.setBounds(159, 214, 106, 25);
		frame.getContentPane().add(rdbtnAddCourse);
		
		JRadioButton rdbtnTransferCourse = new JRadioButton("Transfer Course");
		rdbtnTransferCourse.setBounds(269, 214, 127, 25);
		frame.getContentPane().add(rdbtnTransferCourse);
		
		ButtonGroup autoSignupGroup = new ButtonGroup();
		autoSignupGroup.add(rdbtnAddCourse);
		autoSignupGroup.add(rdbtnTransferCourse);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox_1.setBounds(39, 132, 490, 161);
		frame.getContentPane().add(verticalBox_1);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox_2.setBounds(39, 307, 490, 515);
		frame.getContentPane().add(verticalBox_2);

	}
	
	private void startAddCourse() {
		
		consoleMessages.setText(consoleMessages.getText() + "! Automatic course add started... ! \n");
		coursesToEnroll = new ArrayList<String>();
		coursesToEnroll.add(AC1.getText());
		coursesToEnroll.add(AC2.getText());
		coursesToEnroll.add(AC3.getText());
		seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"), enableWindowlessSignup.isSelected());
		seleniumHelper.signIn(username.getText(), password.getText());
		consoleMessages.setText(consoleMessages.getText() + "! Login to Passport York was successful ! \n");
		ArrayList<String> resultMessages = seleniumHelper.add(coursesToEnroll);
		for(int i = 0; i < resultMessages.size(); i++) {
			consoleMessages.setText(consoleMessages.getText() + resultMessages.get(i));
		}
		
	}
	
	private void startTransferCourse() {
		
		consoleMessages.setText(consoleMessages.getText() + "! Automatic course transfer started... ! \n");
		coursesToEnroll = new ArrayList<String>();
		coursesToEnroll.add(TC1.getText());
		coursesToEnroll.add(TC2.getText());
		coursesToEnroll.add(TC3.getText());
		seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"), enableWindowlessSignup.isSelected());
		seleniumHelper.signIn(username.getText(), password.getText());
		consoleMessages.setText(consoleMessages.getText() + "! Login to Passport York was successful ! \n");
		ArrayList<String> resultMessages = seleniumHelper.transfer(coursesToEnroll);
		for(int i = 0; i < resultMessages.size(); i++) {
			consoleMessages.setText(consoleMessages.getText() + resultMessages.get(i));
		}

	}
	
	
	public void saveChanges() {
		consoleMessages.setText(consoleMessages.getText() + props.saveProps(username.getText(), password.getText(), Props.getCheckBoxState(enableWindowlessSignup.isSelected()), signupEvery.getText(), AC1.getText(), AC2.getText(), AC3.getText(), TC1.getText(), TC2.getText(), TC3.getText()));
	}
	
	public class AutoSignupTimerTask extends TimerTask {
		
		boolean addCourses;
		
		public AutoSignupTimerTask(boolean addCourses) {
			super();
			this.addCourses = addCourses;
		}
		
		@Override
		public void run() {
			if(addCourses)
				startAddCourse();
			else
				startTransferCourse();
		}
	}
	
}


