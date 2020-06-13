package com.TestCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Vlookup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BasePackage.TestBase;
import com.PageLibrary.Opencart_Menu;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Abhinav 01-Jun-2020
 */

public class Opencart_Click extends TestBase {
	Opencart_Menu opencartMenu;
	static Logger log = Logger.getLogger(Opencart_Click.class.getName());

	@BeforeClass
	public void setUP() throws IOException {
		init();
		test = extent.startTest("Click On Menu", "This test will verify click on menu");
		test.log(LogStatus.PASS, "Basic set up for test is done");
	}

	@Test(priority = 1, enabled = true, description = "Check Click")
	public void FuncCeck() throws Exception {

		opencartMenu = new Opencart_Menu(driver);
		// ExtentTest parent = extent.startTest("Opencart_Click");
		// parent.log(LogStatus.INFO, "Info");
		opencartMenu.clickOnDesktopMenu();
		opencartMenu.clickOnPCMenu();
		opencartMenu.clickOnDesktopMenu();
		opencartMenu.clickOnMACMenu();
		getScreenhot(driver, "Test");
		opencartMenu.clickOnDesktopMenu();
		opencartMenu.clickOnShowAllDesktops();
		opencartMenu.verifyLogo();

		// Buttons.clickMakerButtonSave();
		// Alert alert=driver.switchTo().alert();
		// System.out.println(alert.getText());
		// alert.accept();

		// parent.appendChild(child1).appendChild(child2).appendChild(child3);

		extent.endTest(test);
		test.log(LogStatus.PASS, " Test Pass");
		closeBrowser();
	}

}
