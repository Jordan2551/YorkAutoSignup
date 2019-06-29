
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Props {

	private Properties prop;
	private OutputStream output;
	private InputStream input;
	private PropList propList;
	
	public Props(){
		prop = new Properties();
		output = null;
		input = null;
    	propList = new PropList();
	}
	
	public String saveProps(String username, String password , String enableWindowlessSignup, String autoSignupTime, String AC1, String AC2, String AC3, String AC4, String AC5, String AC6, String AC7, String TC1, String TC2, String TC3, String TC4, String TC5, String TC6, String TC7) {
		    	
		try {

			output = new FileOutputStream("config.properties");
			
			//Update the local property list to reflect changes
			propList.setProp("username", username);
			propList.setProp("password", password);
			propList.setProp("enableWindowlessSignup", enableWindowlessSignup);
			propList.setProp("autoSignupTime", autoSignupTime);
			propList.setProp("AC1", AC1);
			propList.setProp("AC2", AC2);
			propList.setProp("AC3", AC3);
			propList.setProp("AC4", AC4);
			propList.setProp("AC5", AC5);
			propList.setProp("AC6", AC6);
			propList.setProp("AC7", AC7);
			propList.setProp("TC1", TC1);
			propList.setProp("TC2", TC2);
			propList.setProp("TC3", TC3);
			propList.setProp("TC4", TC1);
			propList.setProp("TC5", TC2);
			propList.setProp("TC6", TC3);
			propList.setProp("TC7", TC1);
			//Update the config property list to reflect changes
			prop.setProperty("username", this.propList.getProp("username"));
			prop.setProperty("password", this.propList.getProp("password"));
			prop.setProperty("enableWindowlessSignup", this.propList.getProp("enableWindowlessSignup"));
			prop.setProperty("autoSignupTime", this.propList.getProp("autoSignupTime"));
			prop.setProperty("AC1", this.propList.getProp("AC1"));
			prop.setProperty("AC2", this.propList.getProp("AC2"));
			prop.setProperty("AC3", this.propList.getProp("AC3"));
			prop.setProperty("AC4", this.propList.getProp("AC4"));
			prop.setProperty("AC5", this.propList.getProp("AC5"));
			prop.setProperty("AC6", this.propList.getProp("AC6"));
			prop.setProperty("AC7", this.propList.getProp("AC7"));
			prop.setProperty("TC1", this.propList.getProp("TC1"));
			prop.setProperty("TC2", this.propList.getProp("TC2"));
			prop.setProperty("TC3", this.propList.getProp("TC3"));
			prop.setProperty("TC4", this.propList.getProp("TC4"));
			prop.setProperty("TC5", this.propList.getProp("TC5"));
			prop.setProperty("TC6", this.propList.getProp("TC6"));
			prop.setProperty("TC7", this.propList.getProp("TC7"));

			prop.store(output, null);
			
			return "! Changes have been successfully saved ! \n";
			
		} catch (IOException io) {
			io.printStackTrace();
			return "! Saving changes has FAILED. Stack trace: " + io.getMessage() + "! \n";
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public void readProps() {
		
		try {
			File f = new File("config.properties");
			f.createNewFile();
	    	input = new FileInputStream(f);
			// load a properties file
			prop.load(input);

			propList.addProp("username", prop.getProperty("username"));
			propList.addProp("password", prop.getProperty("password"));
			propList.addProp("enableWindowlessSignup", prop.getProperty("enableWindowlessSignup"));
			propList.addProp("autoSignupTime", prop.getProperty("autoSignupTime"));
			propList.addProp("AC1", prop.getProperty("AC1"));
			propList.addProp("AC2", prop.getProperty("AC2"));
			propList.addProp("AC3", prop.getProperty("AC3"));
			propList.addProp("AC4", prop.getProperty("AC4"));
			propList.addProp("AC5", prop.getProperty("AC5"));
			propList.addProp("AC6", prop.getProperty("AC6"));
			propList.addProp("AC7", prop.getProperty("AC7"));
			propList.addProp("TC1", prop.getProperty("TC1"));
			propList.addProp("TC2", prop.getProperty("TC2"));
			propList.addProp("TC3", prop.getProperty("TC3"));	
			propList.addProp("TC4", prop.getProperty("TC4"));
			propList.addProp("TC5", prop.getProperty("TC5"));
			propList.addProp("TC6", prop.getProperty("TC6"));		
			propList.addProp("TC7", prop.getProperty("TC7"));		


		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public PropList getPropList() {
		return this.propList;
	}
	
	public static String getCheckBoxState(boolean state) {
		return state ? "true" : "false"; 
	}
	
	public static boolean getCheckBoxState(String state) {
		return state != null && state.equals("true") ? true : false; 
	}
	
	public static long getNumber(String value) {
		try{
			return Long.valueOf(value);
		}catch(NumberFormatException e) {
			return -1;
		}
	}
	
	class PropList {
		
		private ArrayList<String> propNames;
		private ArrayList<String> propValues;
		
		public PropList() {
			propNames = new ArrayList<String>();
			propValues = new ArrayList<String>();
		}
		
		public void addProp(String propName, String propValue) {
			this.propNames.add(propName);
			this.propValues.add(propValue);
		}
		
		public String getProp(String propertyName) {
			return this.propValues.get((propNames.indexOf(propertyName)));
		}

		public void setProp(String propertyName, String value) {
			this.propValues.set(propNames.indexOf(propertyName), value);
		}
		
	}


	
}


