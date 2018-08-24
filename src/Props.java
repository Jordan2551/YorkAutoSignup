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
	
	public void saveProps() {
		    	
		try {

			output = new FileOutputStream("config.properties");
			
			// set the properties value
			prop.setProperty("username", "user");
			prop.setProperty("password", "pass");
			prop.setProperty("chromeDriverPath", "path");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
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
			propList.addProp("chromeDriverPath", prop.getProperty("chromeDriverPath"));

		} catch (IOException ex) {
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
		
	}


	
}


