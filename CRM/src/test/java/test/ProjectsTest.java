package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.ProjectsPage;


public class ProjectsTest extends BaseTest{
	@Test(priority=1, retryAnalyzer = generaltest.Retry.class, groups = {"smoke","regression"})
	public void verifyCreateProject() {
		SoftAssert softassert= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doAddProject("www","2024-03-21");
		String actualproject=pp.doSearchProject("www");
		softassert.assertEquals(actualproject,"www");
		softassert.assertAll();
		
	}
	@Test(priority=2,retryAnalyzer = generaltest.Retry.class, groups = {"regression","smoke"})
	public void verifySearchProject() {
		SoftAssert softassert= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		String createdproject=pp.doSearchProject("www");
		softassert.assertEquals(createdproject,"www");
		softassert.assertAll();
	}
	@Test(priority=3, groups = {"regression"})
	public void verifyEditProject() {
		SoftAssert softassert= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doSearchProject("www");
		pp.doEditProject("bbb");
		String editedproject=pp.doSearchProject("bbb");
		softassert.assertEquals(editedproject,"bbb");
		softassert.assertAll();
	}
	@Test(priority=4,retryAnalyzer = generaltest.Retry.class, groups = {"regression"})
	public void verifyDeleteProject() {
		SoftAssert softassert= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doSearchProject("bbb");
		String actualText=pp.doDeleteProject();
		softassert.assertEquals(actualText,"No record found.");
		softassert.assertAll();
	}
}
