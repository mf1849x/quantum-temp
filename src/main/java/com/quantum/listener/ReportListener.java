package com.quantum.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.remote.DriverCommand;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.CommandTracker;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriverCommandAdapter;
import com.quantum.utils.ConsoleUtils;
import com.quantum.utils.ReportUtils;

public class ReportListener extends QAFWebDriverCommandAdapter {
	
	@Override
	public void beforeCommand(QAFExtendedWebDriver driver, CommandTracker commandTracker) {
		if (commandTracker.getCommand().equalsIgnoreCase(DriverCommand.CLOSE)) {
			if (ConfigurationManager.getBundle().getString("remote.server").toLowerCase().contains(".perfectomobile.com"))
			{
				
				ConfigurationManager.getBundle().addProperty("executionId", driver.getCapabilities().getCapability("executionId"));


			}
		}
	}
	
	
	@Override
	public void afterCommand(QAFExtendedWebDriver driver, CommandTracker commandTracker) {
		if (commandTracker.getCommand().equalsIgnoreCase(DriverCommand.CLOSE)) {
			if (ConfigurationManager.getBundle().getString("remote.server").toLowerCase().contains(".perfectomobile.com"))
			{
				try {
					//ReportUtils.generateTestReport(ConfigurationManager.getBundle().getString("executionId"));
					  String myUsername = ConfigurationManager.getBundle().getString("perfecto.capabilities.user");
					 
		                String myPassword = ConfigurationManager.getBundle().getString("perfecto.capabilities.password");
		                String reportKey = (driver.getCapabilities().getCapability("reportKey")).toString();
		                String host = ConfigurationManager.getBundle().getString("remote.server").split("/")[2];
		                // Retrieve the URL to the Execution Summary PDF Report
		                String downloadURL = "https://"+host+"/services/reports/" + reportKey + "?operation=download&user="+myUsername+"&password="+myPassword+"&format=pdf";
		                System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@ "+downloadURL+"\n");
		                getPDFSummaryReport(downloadURL);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					ConsoleUtils.logError(e.toString());
				}
			}
		}
	}
	/**
	 * Gets the suite PDF Report from Perfecto
	 * @return
	 * @throws IOException
	 */
	public static String getPDFSummaryReport(String myAPIoperation) throws IOException{
		String filePath=getReportDirectory();
		String fileName=ConfigurationManager.getBundle().getString("perfecto.report.name");
		if(fileName != null && !fileName.isEmpty())
		{
			fileName="Suite_Report";
		}
		boolean downloadComplete = false;
		for (int attempt = 1; attempt <= 12 && !downloadComplete; attempt++) {
			HttpGet httpGet = new HttpGet(myAPIoperation);	 

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(httpGet);
			FileOutputStream fileOutputStream = null;
			downloadComplete = false;
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (HttpStatus.SC_OK == statusCode) {
					
					File file = null;
					if(System.getProperty("os.name").contains("Windows")) {
						file = new File(System.getProperty("user.dir")+"\\"+ filePath+"\\"+fileName+".pdf");
					}
					else {
						file = new File(System.getProperty("user.dir")+"/"+ filePath+"/"+fileName+".pdf");
					}
					try{
						file.getParentFile().mkdirs();
						file.delete();
					}catch(Exception e){}
					fileOutputStream = new FileOutputStream(file);
					IOUtils.copy(response.getEntity().getContent(), fileOutputStream);
					filePath = file.getAbsolutePath().toString();
					System.out.println("\n################################################################");
					System.out.println("Saved " + "Execution report" + " to: " + file.getAbsolutePath());
					System.out.println("################################################################\n");
					downloadComplete = true;
				} else if (HttpStatus.SC_NO_CONTENT == statusCode) {

					// if the execution is being processed, the server will respond with empty response and status code 204
					System.out.println("\nThe server responded with 204 (no content). " +
							"The execution is still being processed. Attempting again in 5 sec ");
					Thread.sleep(5000);
				} else {
					String errorMsg = IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
					System.err.println("Error downloading file. Status: " + response.getStatusLine() + ".\nInfo: " + errorMsg);
					downloadComplete = true;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				EntityUtils.consumeQuietly(response.getEntity());
				IOUtils.closeQuietly(fileOutputStream);
			}
		}
		if (!downloadComplete) {
			System.err.println("The execution is still being processed. No more download attempts");
		}		
		return filePath;
	}
	private static String getReportDirectory() {
		try {
			ConfigurationManager.getBundle().getString("perfecto.report.location");
			if (!ConfigurationManager.getBundle().getString("perfecto.report.location").equals("")) {
				return ConfigurationManager.getBundle().getString("perfecto.report.location");
			} else {
				return "perfectoReports";
			}
		} catch (Exception ex) {
			return "perfectoReports";
		}
	}
}