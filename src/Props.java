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
	
	public String saveProps(String username, String password, String AC1, String AC2, String AC3, String TC1, String TC2, String TC3) {
		    	
		try {

			output = new FileOutputStream("config.properties");
			
			//Update the local property list to reflect changes
			propList.setProp("username", username);
			propList.setProp("password", password);
			propList.setProp("AC1", AC1);
			propList.setProp("AC2", AC2);
			propList.setProp("AC3", AC3);
			propList.setProp("TC1", TC1);
			propList.setProp("TC2", TC2);
			propList.setProp("TC3", TC3);
			
			//Update the config property list to reflect changes
			prop.setProperty("username", this.propList.getProp("username"));
			prop.setProperty("password", this.propList.getProp("password"));
			prop.setProperty("AC1", this.propList.getProp("AC1"));
			prop.setProperty("AC2", this.propList.getProp("AC2"));
			prop.setProperty("AC3", this.propList.getProp("AC3"));
			prop.setProperty("TC1", this.propList.getProp("TC1"));
			prop.setProperty("TC2", this.propList.getProp("TC2"));
			prop.setProperty("TC3", this.propList.getProp("TC3"));

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
			
	    	input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			propList.addProp("username", prop.getProperty("username"));
			propList.addProp("password", prop.getProperty("password"));
			propList.addProp("AC1", prop.getProperty("AC1"));
			propList.addProp("AC2", prop.getProperty("AC2"));
			propList.addProp("AC3", prop.getProperty("AC3"));
			propList.addProp("TC1", prop.getProperty("TC1"));
			propList.addProp("TC2", prop.getProperty("TC2"));
			propList.addProp("TC3", prop.getProperty("TC3"));			

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


