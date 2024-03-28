package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.Constants;
import page.LoginPage;
import utility.ExcelRead;

public class LoginTest extends BaseTest{

	@Test(dataProvider="userData")
	public void verifyLogin(String username,String password) {
		LoginPage lp=new LoginPage(driver);
		String actualmenu=lp.doLogin(username,password);
		String expectedmenu="Dashboard";
		Assert.assertEquals(actualmenu,expectedmenu);
	}
	@DataProvider

	public Object[][] userData() throws Exception{

		Object[][] data=ExcelRead.getDataFromExcel(Constants.testdata, 
				"Logindata");


		return data;
	}
}
