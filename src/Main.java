import java.util.ArrayList;

public class Main {

	static SeleniumHelper seleniumHelper;
	static Props props;
	static ArrayList<String> coursesToEnroll;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		props = new Props();
		//props.saveProps();
		props.readProps();
		coursesToEnroll = new ArrayList<String>();
		coursesToEnroll.add("K13Y03");
		coursesToEnroll.add("K13Y04");
		seleniumHelper = new SeleniumHelper(props.getPropList().getProp("username"), props.getPropList().getProp("password"), props.getPropList().getProp("chromeDriverPath"));
		seleniumHelper.signIn();
		seleniumHelper.transfer(coursesToEnroll);
	}

}
