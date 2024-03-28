package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import page.LoginPage;
import page.ProjectsPage;


public class ProjectsTest extends BaseTest{
	@Test(priority=1, retryAnalyzer = generaltest.Retry.class, groups = {"smoke","regression"})
	public void verifyCreateProject() {
		SoftAssert s= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doAddProject("www","2024-03-21");
		String actualproject=pp.doSearchProject("www");
		s.assertEquals(actualproject,"www");
		
	}
	@Test(priority=2,retryAnalyzer = generaltest.Retry.class, groups = {"regression","smoke"})
	public void verifySearchProject() {
		SoftAssert s= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		String createdproject=pp.doSearchProject("www");
		s.assertEquals(createdproject,"www");
	}
	@Test(priority=3, groups = {"regression"})
	public void verifyEditProject() {
		SoftAssert s= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doSearchProject("www");
		pp.doEditProject("bbb");
		String editedproject=pp.doSearchProject("bbb");
		s.assertEquals(editedproject,"bbb");	
	}
	@Test(priority=4,retryAnalyzer = generaltest.Retry.class, groups = {"regression"})
	public void verifyDeleteProject() {
		SoftAssert s= new SoftAssert();
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin@admin.com", "12345678");
		ProjectsPage pp=new ProjectsPage(driver);
		pp.doSearchProject("bbb");
		String actualText=pp.doDeleteProject();
		s.assertEquals(actualText,"No record found.");
	}
}
