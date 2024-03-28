package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import page.ClientsPage;
import page.LoginPage;

public class ClientsTest extends BaseTest {
	@Test(priority=1,retryAnalyzer = generaltest.Retry.class, groups = { "smoke", "regression" })
	public void verifyCreateClient() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ClientsPage cp= new ClientsPage(driver);
		cp.docreateClients("AE4");
		String actual=cp.doSearchClient("AE4");
		Assert.assertEquals(actual, "AE4");
	}
	@Test(priority=2, retryAnalyzer = generaltest.Retry.class, groups = { "smoke", "regression" })
	public void verifyClientSearch() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ClientsPage cp= new ClientsPage(driver);
		String actual=cp.doSearchClient("AE4");
		Assert.assertEquals(actual, "AE4");//
	}
	@Test(priority=3, retryAnalyzer = generaltest.Retry.class,groups = {"regression"})
	public void verifyEditClient() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");//login
		ClientsPage cp= new ClientsPage(driver);
		cp.doSearchClient("AE4");
		cp.doEditCompanyname("ABJ");
		String actual=cp.doSearchClient("ABJ");
		Assert.assertEquals(actual,"ABJ");

	}
	@Test(priority=4,retryAnalyzer = generaltest.Retry.class, groups = {"regression"})
	public void verifyDeleteCompanyName() {
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");//login
		ClientsPage cp= new ClientsPage(driver);
		cp.doSearchClient("ABJ");
		String actualText=cp.doDeleteCompanyName();
		String expctedText="No record found.";
		Assert.assertEquals(actualText,expctedText);

	}
}
