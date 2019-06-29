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
	private JTextField AC6;
	private JTextField AC5;
	private JTextField AC4;
	private JTextField AC7;
	private JTextField TC4;
	private JTextField TC5;
	private JTextField TC6;
	private JTextField TC7;


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
		frame.setBounds(100, 100, 600, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDevelopedWith = new JLabel("Developed with <3 by JC Software");
		lblDevelopedWith.setBounds(69, 936, 432, 16);
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
		
		//Get properties from config file and populate the appropriate fields
		props = new Props();	
		props.readProps();
		username.setText(props.getPropList().getProp("username"));
		password.setText(props.getPropList().getProp("password"));
		
		enableWindowlessSignup = new JCheckBox("Enable Windowless Signup");
		enableWindowlessSignup.setBounds(197, 145, 181, 25);
		frame.getContentPane().add(enableWindowlessSignup);
		enableWindowlessSignup.setSelected(Props.getCheckBoxState(props.getPropList().getProp("enableWindowlessSignup")));
		
		ButtonGroup autoSignupGroup = new ButtonGroup();
		
		JLabel lblCourse_5 = new JLabel("Course #6:");
		lblCourse_5.setBounds(90, 638, 88, 16);
		frame.getContentPane().add(lblCourse_5);
		
		AC6 = new JTextField();
		AC6.setText((String) null);
		AC6.setColumns(10);
		AC6.setBounds(162, 635, 103, 22);
		frame.getContentPane().add(AC6);
		AC6.setText(props.getPropList().getProp("AC6"));

		JLabel lblCourse_1 = new JLabel("Course #3:");
		lblCourse_1.setBounds(90, 545, 88, 16);
		frame.getContentPane().add(lblCourse_1);
		
		AC1 = new JTextField();
		AC1.setColumns(10);
		AC1.setBounds(162, 478, 103, 22);
		frame.getContentPane().add(AC1);
		AC1.setText(props.getPropList().getProp("AC1"));
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox_1.setBounds(39, 132, 490, 161);
		frame.getContentPane().add(verticalBox_1);
		
		JLabel lblMsgs = new JLabel("Console Log:");
		lblMsgs.setForeground(new Color(255, 0, 0));
		lblMsgs.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMsgs.setBounds(233, 778, 88, 16);
		frame.getContentPane().add(lblMsgs);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		verticalBox.setBounds(39, 13, 490, 107);
		frame.getContentPane().add(verticalBox);
		
		JLabel snt = new JLabel("Sign Up Every (minutes):");
		snt.setBounds(140, 182, 150, 16);
		frame.getContentPane().add(snt);
		
		rdbtnAddCourse = new JRadioButton("Add Course");
		rdbtnAddCourse.setSelected(true);
		rdbtnAddCourse.setBounds(159, 214, 106, 25);
		frame.getContentPane().add(rdbtnAddCourse);
		autoSignupGroup.add(rdbtnAddCourse);
		
		JLabel lblCourse_10 = new JLabel("Course #7:");
		lblCourse_10.setBounds(308, 669, 88, 16);
		frame.getContentPane().add(lblCourse_10);
		
		JLabel label = new JLabel("Course #1:");
		label.setBounds(308, 481, 88, 16);
		frame.getContentPane().add(label);
		
		JLabel lblCourse_3 = new JLabel("Course #4:");
		lblCourse_3.setBounds(90, 574, 88, 16);
		frame.getContentPane().add(lblCourse_3);
		
		JLabel lblCourse_7 = new JLabel("Course #4:");
		lblCourse_7.setBounds(308, 574, 88, 16);
		frame.getContentPane().add(lblCourse_7);
		
				signupEvery = new JTextField();
				signupEvery.setColumns(10);
				signupEvery.setBounds(293, 179, 116, 22);
				frame.getContentPane().add(signupEvery);
				signupEvery.setText(props.getPropList().getProp("autoSignupTime"));
				
				TC5 = new JTextField();
				TC5.setText((String) null);
				TC5.setColumns(10);
				TC5.setBounds(380, 603, 103, 22);
				frame.getContentPane().add(TC5);
				TC5.setText(props.getPropList().getProp("TC5"));

				TC3 = new JTextField();
				TC3.setColumns(10);
				TC3.setBounds(380, 542, 103, 22);
				frame.getContentPane().add(TC3);
				TC3.setText(props.getPropList().getProp("TC3"));
				
				AC3 = new JTextField();
				AC3.setColumns(10);
				AC3.setBounds(162, 542, 103, 22);
				frame.getContentPane().add(AC3);
				AC3.setText(props.getPropList().getProp("AC3"));
				
				TC1 = new JTextField();
				TC1.setColumns(10);
				TC1.setBounds(380, 478, 103, 22);
				frame.getContentPane().add(TC1);
				TC1.setText(props.getPropList().getProp("TC1"));
				
				JLabel lblWhatWouldYou = new JLabel("What would you like to do?");
				lblWhatWouldYou.setBounds(185, 318, 194, 20);
				frame.getContentPane().add(lblWhatWouldYou);
				lblWhatWouldYou.setHorizontalAlignment(SwingConstants.CENTER);
				lblWhatWouldYou.setForeground(new Color(0, 0, 128));
				lblWhatWouldYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
				
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
				
				JLabel label_1 = new JLabel("Course #2:");
				label_1.setBounds(308, 513, 88, 16);
				frame.getContentPane().add(label_1);
				
				AC7 = new JTextField();
				AC7.setText((String) null);
				AC7.setColumns(10);
				AC7.setBounds(162, 666, 103, 22);
				frame.getContentPane().add(AC7);
				AC7.setText(props.getPropList().getProp("AC7"));

				JLabel lblCourse_9 = new JLabel("Course #6:");
				lblCourse_9.setBounds(308, 638, 88, 16);
				frame.getContentPane().add(lblCourse_9);
				
				consoleMessages = new TextArea();
				consoleMessages.setFont(new Font("Dialog", Font.BOLD, 14));
				consoleMessages.setBounds(51, 800, 468, 107);
				frame.getContentPane().add(consoleMessages);
				
				JLabel lblCourse_2 = new JLabel("Course #2:");
				lblCourse_2.setBounds(90, 513, 88, 16);
				frame.getContentPane().add(lblCourse_2);
				
				JRadioButton rdbtnTransferCourse = new JRadioButton("Transfer Course");
				rdbtnTransferCourse.setBounds(269, 214, 127, 25);
				frame.getContentPane().add(rdbtnTransferCourse);
				autoSignupGroup.add(rdbtnTransferCourse);
				
				JLabel lblCourse_8 = new JLabel("Course #5:");
				lblCourse_8.setBounds(308, 606, 88, 16);
				frame.getContentPane().add(lblCourse_8);
				
				TC4 = new JTextField();
				TC4.setText((String) null);
				TC4.setColumns(10);
				TC4.setBounds(380, 571, 103, 22);
				frame.getContentPane().add(TC4);
				TC4.setText(props.getPropList().getProp("TC4"));

				JLabel lblCourse_4 = new JLabel("Course #5:");
				lblCourse_4.setBounds(90, 606, 88, 16);
				frame.getContentPane().add(lblCourse_4);
				
				JLabel lblCourse = new JLabel("Course #1:");
				lblCourse.setBounds(90, 481, 88, 16);
				frame.getContentPane().add(lblCourse);
				
				TC6 = new JTextField();
				TC6.setText((String) null);
				TC6.setColumns(10);
				TC6.setBounds(380, 635, 103, 22);
				frame.getContentPane().add(TC6);
				TC6.setText(props.getPropList().getProp("TC6"));

				AC2 = new JTextField();
				AC2.setColumns(10);
				AC2.setBounds(162, 510, 103, 22);
				frame.getContentPane().add(AC2);
				AC2.setText(props.getPropList().getProp("AC2"));
				
				JButton saveButton = new JButton("Save Changes");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveChanges();
					}
				});
				saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				saveButton.setBounds(213, 718, 137, 44);
				frame.getContentPane().add(saveButton);
				
				JLabel lblCourse_6 = new JLabel("Course #7:");
				lblCourse_6.setBounds(90, 669, 88, 16);
				frame.getContentPane().add(lblCourse_6);
				
				AC4 = new JTextField();
				AC4.setText((String) null);
				AC4.setColumns(10);
				AC4.setBounds(162, 571, 103, 22);
				frame.getContentPane().add(AC4);
				AC4.setText(props.getPropList().getProp("AC4"));

				JLabel label_2 = new JLabel("Course #3:");
				label_2.setBounds(308, 545, 88, 16);
				frame.getContentPane().add(label_2);
				
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
				
				TC7 = new JTextField();
				TC7.setText((String) null);
				TC7.setColumns(10);
				TC7.setBounds(380, 666, 103, 22);
				frame.getContentPane().add(TC7);
				TC7.setText(props.getPropList().getProp("TC7"));

				AC5 = new JTextField();
				AC5.setText((String) null);
				AC5.setColumns(10);
				AC5.setBounds(162, 603, 103, 22);
				frame.getContentPane().add(AC5);
				AC5.setText(props.getPropList().getProp("AC5"));

				
				TC2 = new JTextField();
				TC2.setColumns(10);
				TC2.setBounds(380, 510, 103, 22);
				frame.getContentPane().add(TC2);
				TC2.setText(props.getPropList().getProp("TC2"));

	}
	
	private void startAddCourse() {
		
		consoleMessages.setText(consoleMessages.getText() + "! Automatic course add started... ! \n");
		coursesToEnroll = new ArrayList<String>();
		coursesToEnroll.add(AC1.getText());
		coursesToEnroll.add(AC2.getText());
		coursesToEnroll.add(AC3.getText());
		coursesToEnroll.add(AC4.getText());
		coursesToEnroll.add(AC5.getText());
		coursesToEnroll.add(AC6.getText());
		coursesToEnroll.add(AC7.getText());
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
		coursesToEnroll.add(TC4.getText());
		coursesToEnroll.add(TC5.getText());
		coursesToEnroll.add(TC6.getText());
		coursesToEnroll.add(TC7.getText());
		seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"), enableWindowlessSignup.isSelected());
		seleniumHelper.signIn(username.getText(), password.getText());
		consoleMessages.setText(consoleMessages.getText() + "! Login to Passport York was successful ! \n");
		ArrayList<String> resultMessages = seleniumHelper.transfer(coursesToEnroll);
		for(int i = 0; i < resultMessages.size(); i++) {
			consoleMessages.setText(consoleMessages.getText() + resultMessages.get(i));
		}

	}
	
	
	public void saveChanges() {
		consoleMessages.setText(consoleMessages.getText() + props.saveProps(username.getText(), password.getText(), Props.getCheckBoxState(enableWindowlessSignup.isSelected()), signupEvery.getText(), AC1.getText(), AC2.getText(), AC3.getText(), AC4.getText(),AC5.getText(),AC6.getText(),AC7.getText(), TC1.getText(), TC2.getText(), TC3.getText(), TC4.getText(), TC5.getText(), TC6.getText(), TC7.getText()));
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


