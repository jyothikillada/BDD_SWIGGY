package utilities;

import java.util.ResourceBundle;



public class ConfigFileReader {

	public String getReportConfigPath(){
		ResourceBundle resourceBundle = null;
		String reportConfigPath = resourceBundle.getString("reportConfigPath");
		
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
}
