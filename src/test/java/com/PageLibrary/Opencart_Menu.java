package com.PageLibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.BasePackage.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Abhinav 01-Jun-2020
 */

public class Opencart_Menu extends TestBase {
	WebDriver driver;
	static Logger log = Logger.getLogger(Opencart_Menu.class.getName());

	By menuDesktops = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a");
	By menuPC = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/div/ul/li[1]/a");
	By menuMAC = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/div/ul/li[2]/a");
	By menuShowAllDesktops = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/a");

	By menuLaptopDesktops = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/div/div/ul/li[2]/a");
	By menuMacs = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[2]/div/div/ul/li[1]/a");
	By menuWindows = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[2]/div/div/ul/li[2]/a");
	By menuShowAllLaptopDesktops = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[2]/div/a");

	By menuComponents = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/a");
	By menuMouse = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[1]/a");
	By menuMonitors = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[2]/a");
	By menuPrinters = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[3]/a");
	By menuScanners = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[4]/a");
	By menuWebCammera = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[5]/a");
	By menuShowAllComponnets = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[3]/div/div/ul/li[4]/a");

	By menuTablets = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[4]/a");

	By menuSoftware = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[4]/a");

	By menuPhones = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[6]/a");

	By menuCameras = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[6]/a");

	By menuPlayers = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[8]/a");
	By menuPlayers1 = By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[8]/div/div/ul[1]/li[1]/a");
	By siteLogo	= By.xpath("//*[@id=\"logo\"]/a/img");

	public Opencart_Menu(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnDesktopMenu() {
		driver.findElement(menuDesktops).click();
		test.log(LogStatus.PASS, "Click on Menu Desktop");
	}

	public void clickOnPCMenu() {
		driver.findElement(menuPC).click();
		test.log(LogStatus.PASS, "Click on PC Menu");
	}

	public void clickOnMACMenu() {
		driver.findElement(menuMAC).click();
		test.log(LogStatus.PASS, "Click on MAC Menu");
	}

	public void clickOnShowAllDesktops() {
		driver.findElement(menuShowAllDesktops).click();
		test.log(LogStatus.PASS, "Click on All Desktop Menu");
	}

	public void verifyLogo() {
		String src = driver.findElement(siteLogo).getAttribute("src");
		log.info(src);
		if (src.equals("")) {
			test.log(LogStatus.PASS, "verify logo");
		} else {
			test.log(LogStatus.FAIL, "verify logo");
		}
	}
}