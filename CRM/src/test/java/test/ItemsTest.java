package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.Constants;
import page.ItemsPage;
import page.LoginPage;
import utility.ExcelRead;

public class ItemsTest extends BaseTest {

	@Test(priority=1,retryAnalyzer = generaltest.Retry.class, groups = { "smoke", "regression" })
	public void verifyCreateItems() throws InvalidFormatException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ItemsPage ip=new ItemsPage(driver);
		ip.doCreateItems("item1",ExcelRead.getDataFromExcel(Constants.testdata, "Item", 1, 0));
		String actualsearcheditem=ip.doSearchItem("item1");
		Assert.assertEquals(actualsearcheditem, "item1");
	}
	@Test(priority=2,groups = { "smoke", "regression" })
	public void verifySearchItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ItemsPage ip=new ItemsPage(driver);
		String actualsearcheditem=ip.doSearchItem("item1");
		Assert.assertEquals(actualsearcheditem, "item1");
	}
	@Test(priority=3, groups = { "regression" })
	public void verifyEditItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ItemsPage ip=new ItemsPage(driver);
		ip.doSearchItem("item1");
		ip.doEditItem("BItem");
		String actualnewItem=ip.doSearchItem("BItem");
		String expectednewItem=("BItem");
		Assert.assertEquals(actualnewItem, expectednewItem);
	}
	@Test(priority=4, groups = { "regression" })
	public void verifyDeleteItem() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ItemsPage ip=new ItemsPage(driver);
		ip.doSearchItem("BItem");
		String actualnewItem=ip.doDeleteItem();
		String expectednewItem=("No record found.");
		Assert.assertEquals(actualnewItem, expectednewItem);
	}
	
}

