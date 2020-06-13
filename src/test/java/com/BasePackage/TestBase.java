package com.BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public String startTime;
	public static int indexSI = 1;
//	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/testReport/Report"
				+ formater.format(calendar.getTime()) + ".html", false);
		test = extent.startTest("Verify application Click");
		test.log(LogStatus.INFO, "Browser started");
		extent.addSystemInfo("Project Manager", "Abhinav Ojha").addSystemInfo("Project Name", "Automation")
				.addSystemInfo("Environment", "Automation Testing").addSystemInfo("Selenium Version", "2.46");

		/* htmlReporter.config().setChartVisibilityOnOpen(false);
		 htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		 htmlReporter.config().setReportName("My Own Report");
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 htmlReporter.config().setTheme(Theme.DARK);*/
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/testScreenshot/" + screenshotName
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}

	public void init() throws IOException {
		String log4jConfPath = "/src/test/java/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		loadPropertiesFile();
		selectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir") + "/src/test/java/config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
	}

	public void selectBrowser(String browser) {
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equalsIgnoreCase("firefox")) {
				// https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				// https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.out.println(System.getProperty("os.name"));
			if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			System.out.println(System.getProperty("os.name"));
			if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = TestBase.getScreenhot(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(test);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		extent.endTest(test);
		extent.flush();
		driver.close();
	}

}
