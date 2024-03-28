package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ElementUtility;
import utility.WaitUtility;

public class ClientsPage {
	WebDriver driver;
	WaitUtility wait;
	ElementUtility elementutil;
	@FindBy(xpath="//span[text()='Clients']")
	WebElement clients;
	@FindBy(xpath="//a[text()=' Add client']")
	WebElement addclientbutton;
	@FindBy(xpath="//input[@id='company_name']")
	WebElement companyname;
	@FindBy(xpath="//button[@type='submit']")
	WebElement save;
	@FindBy(xpath="//button[@class='close']")
	WebElement close;
	@FindBy(xpath="//input[@type='search']")
	WebElement searchclient;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[2]//a")
	WebElement companynametabledata;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[9]//a")
	WebElement editbutton;
	@FindBy(xpath="//input[@name='company_name']")
	WebElement company_name;
	@FindBy(xpath="//button[@type='submit']")
	WebElement editsave;
	@FindBy(xpath="//button[@class='close']")
	WebElement closedit;
	@FindBy(xpath="//a[@title='Delete client']")
	WebElement deleteclient;
	@FindBy(xpath="//div[@id='app-alert-h9w92']")
	WebElement deletealert;
	@FindBy(xpath="//button[@type='button' and @class='close' ]")
	WebElement dltalertclose;
	@FindBy(xpath="//table[@id='client-table']//tbody//tr[1]//td[1]")
	WebElement norecordfound;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement deleteconfirmation;
	public ClientsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtility(driver);
		elementutil= new ElementUtility(driver);
	}
	public void docreateClients(String companyName) {

		wait.waitForElementClick(clients);
		clients.click();
		wait.waitForElementClick(addclientbutton);
		addclientbutton.click(); 
		companyname.sendKeys(companyName);
		wait.waitForElementClick(save);
		save.click();
		wait.waitForElementClick(close);
		close.click();

	}
	public String doSearchClient(String searchvalue) {
		wait.waitForElementClick(clients);
		clients.click();
		wait.waitForVisibility(searchclient);
		searchclient.sendKeys(searchvalue);
		By locator=By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]");
		wait.waitForVisibility(locator);
		List<WebElement> clienttable=driver.findElements(By.xpath("//table[@id='client-table']//tbody//tr//td//a[contains(text(),'"+searchvalue+"')]"));
		wait.waitForVisibility(clienttable);
		int row=elementutil.getTableDataRowCount(clienttable, searchvalue);
		String message="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='client-table']//tbody//tr["+row+"]//td[2]"));
			message=tableRow.getText();
			System.out.println(message);
			
		}
		return message;
	}
	public void doEditCompanyname(String editcompanyname) {
		wait.waitForVisibility(editbutton);
		editbutton.click();
		wait.waitForVisibility(company_name);
		elementutil.clearAndSendKey(company_name, editcompanyname);
		wait.waitForElementClick(editsave);
		editsave.click();
		wait.waitForVisibility(closedit);
		closedit.click();
	}
	public String doDeleteCompanyName() {
		wait.waitForVisibility(addclientbutton);
		deleteclient.click();
		wait.waitForVisibility(deleteconfirmation);
		deleteconfirmation.click();
		wait.waitForVisibility(dltalertclose);
		dltalertclose.click();
		String actualText=norecordfound.getText();
		return actualText;
	}
	
}
