import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHelper {

	private String username;
	private String password;
	private String chromeDriverPath;
	WebDriver driver;
	
	public SeleniumHelper(String username, String password, boolean enabledWindowlessSignup) {
		this.username = username;
		this.password = password;
		//Load the Chromedriver and set the system path for the chromedriver
		this.chromeDriverPath = chromeDriverPath;
		ChromeOptions options = new ChromeOptions();
		if(enabledWindowlessSignup) {
			options.addArguments("--headless");
		}
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromeDriver.exe");		
		driver = new ChromeDriver(options);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void signIn(String SignInUsername, String SignInPassword) {
		
		driver.get("https://wrem.sis.yorku.ca/Apps/WebObjects/REM.woa/wa/DirectAction/rem");
		
		//PASSPORT YORK LOGIN
		WebElement username = driver.findElement(By.name("mli"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.name("dologin"));
		username.sendKeys(SignInUsername);
		password.sendKeys(SignInPassword);
		login.click();
		driver.get("https://wrem.sis.yorku.ca/Apps/WebObjects/REM.woa/wa/DirectAction/rem");
		
		//SELECT ACADEMIC SESSION
		Select academicSession = new Select(driver.findElement(By.name("3.5.1.27.1.11.0")));
		WebElement academicSessionSubmit = driver.findElement(By.name("3.5.1.27.1.13"));
		academicSession.selectByVisibleText("FALL/WINTER 2018-2019 UNDERGRADUATE STUDENTS");
		academicSessionSubmit.click();
				
	}
	
	//TRANSFER SUCCESS 5.1.27.11.7
	//ADD SUCCESS 5.1.27.19.7
	public ArrayList<String> add(ArrayList<String> coursesToEnroll){
		
		ArrayList<String> resultMessages = new ArrayList<String>();
		
		for(int i = 0; i < coursesToEnroll.size(); i++) {
			
			if(coursesToEnroll.get(i).length() > 0) {
				
				//ADD COURSE 1
				WebElement add = driver.findElement(By.name("5.1.27.1.23"));
				add.click();
	
				//ADD COURSE 2
				WebElement courseInput = driver.findElement(By.name("5.1.27.7.7"));
				WebElement addCourse = driver.findElement(By.name("5.1.27.7.9"));
				courseInput.sendKeys(coursesToEnroll.get(i));
				addCourse.click();
				
				//ADD CONFIRM
				WebElement addConfirm = driver.findElement(By.name("5.1.27.11.9"));
				addConfirm.click();
	
				//TRANSFER RESULT
				WebElement addResultSubmit = driver.findElement(By.name("5.1.27.27.9"));
				boolean addResult = driver.getPageSource().contains("The course has not been added.");
	
				addResultSubmit.click();
				
				if(addResult)
				{
					resultMessages.add("! The course has not been added ! \n");

				}
				else {
					resultMessages.add("! The course has been successfully added ! \n");
				    JOptionPane.showMessageDialog(null, "Course has been successfully added!");
				}
			
			}
			
		}

	    driver.quit();
		return resultMessages;
		
	}
	
	//TRANSFER SUCCESS 5.1.27.11.7
	//ADD SUCCESS 5.1.27.19.7
	public ArrayList<String> transfer(ArrayList<String> coursesToEnroll){
		
		ArrayList<String> resultMessages = new ArrayList<String>();

		for(int i = 0; i < coursesToEnroll.size(); i++) {
			
			if(coursesToEnroll.get(i).length() > 0) {
				
				//TRANSFER COURSE 1
				WebElement transfer = driver.findElement(By.name("5.1.27.1.27"));
				transfer.click();
	
				//TRANSFER COURSE 2
				WebElement courseInput = driver.findElement(By.name("5.1.27.5.7"));
				WebElement transferCourse = driver.findElement(By.name("5.1.27.5.9"));
				courseInput.sendKeys(coursesToEnroll.get(i));
				transferCourse.click();
				
				//TRANSFER CONFIRM
				WebElement transferConfirm = driver.findElement(By.name("5.1.27.7.9"));
				transferConfirm.click();
	
				//TRANSFER RESULT
				WebElement transferResultSubmit = driver.findElement(By.name("5.1.27.13.9"));
				boolean transferResult = driver.getPageSource().contains("The course has not been transfered.");
	
				transferResultSubmit.click();
				
				if(transferResult)
				{
					resultMessages.add("! The course has not been transferred ! \n");
				}
				else {
					resultMessages.add("! The course has been successfully transferred ! \n");
				    JOptionPane.showMessageDialog(null, "Course has been successfully transferrerd!");
				}
				
			}
			
		}

		driver.quit();
		return resultMessages;
		
	}
	

	
	

}
