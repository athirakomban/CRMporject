package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ElementUtility;
import utility.WaitUtility;

public class ItemsPage {
	WebDriver driver;
	WaitUtility wait;
	ElementUtility elementutil;
	@FindBy(xpath="//span[text()='Items']")
	WebElement items;
	@FindBy(xpath="//a[@title='Add item']")
	WebElement additem;
	@FindBy(xpath="//input[@id='title']")
	WebElement title;
	@FindBy(xpath="//button[text()=' Save']")
	WebElement saveitem;
	@FindBy(xpath="//button[@class='close']")
	WebElement closeadditem;
	@FindBy(xpath="//input[@name='item_rate']")
	WebElement item_rate;
	@FindBy(xpath="//input[@type='search']")
	WebElement item_search;
	@FindBy(xpath="//table[@id='item-table']//tbody//tr[1]//td[1]")
	WebElement item_name;
	@FindBy(xpath="//table[@id='item-table']//tbody//tr[1]//td[5]//a")
	WebElement editbutton_item;
	@FindBy(xpath="//input[@name='title']")
	WebElement edit_item;
	@FindBy(xpath="//button[@type='submit']")
	WebElement saveitem_edit;
	@FindBy(xpath="//button[@class='close']")
	WebElement colse_edit;
	@FindBy(xpath="//a[@class='delete' and @title='Delete']")
	WebElement deleteItem;
	@FindBy(xpath="//button[@class='close' and @type='button']")
	WebElement closedeletemessage;
	@FindBy(xpath="//table[@id='item-table']//tbody//tr//td")
	WebElement norecordfound;
	public ItemsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtility(driver);
		elementutil= new ElementUtility(driver);
	}
	public void clickItems() {
		wait.waitForVisibility(items);
		items.click();
	}
	public void doCreateItems(String itemtitle, String itemrate) {
		clickItems();
		wait.waitForVisibility(additem);;
		additem.click();
		wait.waitForVisibility(title);
		title.sendKeys(itemtitle);
		wait.waitForVisibility(item_rate);
		item_rate.sendKeys(itemrate);
		wait.waitForElementClick(saveitem);
		saveitem.click();
		wait.waitForVisibility(closeadditem);
		closeadditem.click();
	}
	public String doSearchItem(String searchItem) {
		clickItems();
		wait.waitForVisibility(item_search);
		item_search.click();
		item_search.sendKeys(searchItem);
		String itemName=item_name.getText();
		return itemName;
	}
	public void doEditItem(String edititem) {
		wait.waitForVisibility(editbutton_item);
		editbutton_item.click();
		elementutil.clearAndSendKey(edit_item, edititem);
		wait.waitForVisibility(saveitem_edit);
		saveitem_edit.click();
		wait.waitForVisibility(colse_edit);
		colse_edit.click();
	}
	public String doDeleteItem() {
		wait.waitForVisibility(deleteItem);;
		deleteItem.click();
		wait.waitForVisibility(closedeletemessage);;
		closedeletemessage.click();
		String actualText=norecordfound.getText();
		return actualText;
	}
}
