package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ElementUtility;
import utility.WaitUtility;

public class ProjectsPage {
	WebDriver driver;
	WaitUtility wait;
	ElementUtility elementutil;
	@FindBy(xpath="//span[text()='Projects']")
	WebElement projects;
	@FindBy(xpath="//span[text()='All Projects']")
	WebElement allprojects;
	@FindBy(xpath="//input[@type='search']")
	WebElement project_search;
	@FindBy(xpath="//a[@title='Add project']")
	WebElement add_project;
	@FindBy(xpath="//input[@id='title']")
	WebElement project_title;
	@FindBy(xpath="//button[@type='submit']")
	WebElement addproject_save;
	@FindBy(xpath="//button[@class='close']")
	WebElement close_addproject;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr//td")
	WebElement project_norecordfound;
	@FindBy(xpath="//a[@class='edit' and @title='Edit project']")
	WebElement edit_project;
	@FindBy(xpath="//input[@id='title' and @type='text']")
	WebElement edittitle_project;
	@FindBy(xpath="//button[@type='submit']")
	WebElement save_projectedit;
	@FindBy(xpath="//a[@class='delete' and @title='Delete project']")
	WebElement delete_project;
	@FindBy(xpath="//button[@id='confirmDeleteButton']")
	WebElement projectdeleteconfirmation_button;
	@FindBy(xpath="//button[@type='button' and @data-dismiss='alert']")
	WebElement close_projectdeletealert;
	@FindBy(xpath="//table[@id='project-table']//tbody//tr//td[2]")
	WebElement searchedproject;
	@FindBy(xpath="//button[@type='button'  and @class='close']")
	WebElement edit_close;
	@FindBy(xpath="//input[@name='start_date']")
	WebElement startdate_project;
	public ProjectsPage(WebDriver driver) {
		this.driver=driver;
		wait= new WaitUtility(driver);
		elementutil= new ElementUtility(driver);
		PageFactory.initElements(driver,this);
	}
	public void doAddProject(String projectTitle, String startDate) {
		wait.waitForVisibility(projects);
		projects.click();
		wait.waitForVisibility(allprojects);
		allprojects.click();
		wait.waitForVisibility(add_project);
		add_project.click();
		wait.waitForVisibility(project_title);
		project_title.sendKeys(projectTitle);
		elementutil.scrollElement(startdate_project);
		wait.waitForVisibility(startdate_project);
		elementutil.dateSelect(startdate_project, startDate);
		wait.waitForVisibility(addproject_save);
		addproject_save.click();
		wait.waitForVisibility(close_addproject);
		close_addproject.click();
	}
	public String doSearchProject(String searchProjectName) {
		wait.waitForVisibility(projects);
		projects.click();
		wait.waitForVisibility(allprojects);
		allprojects.click();
		wait.waitForVisibility(project_search);
		project_search.sendKeys(searchProjectName);
		By locator=By.xpath("//table[@id='project-table']//tbody//td[2]//a[contains(text(),'"+searchProjectName+"')]");
		List<WebElement> projecttable=driver.findElements(By.xpath("//table[@id='project-table']//tbody//td[2]//a[contains(text(),'"+searchProjectName+"')]"));
		wait.waitForVisibility(projecttable);
		int row=elementutil.getTableDataRowCount(projecttable, searchProjectName);
		String message="";
		if(row!=0) 
		{
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='project-table']//tbody//tr["+row+"]//td[2]"));
			message=tableRow.getText();
			System.out.println(message);
		}
		return message;
	}
	public void doEditProject(String editTitle ) {
		wait.waitForVisibility(projects);
		projects.click();
		wait.waitForVisibility(allprojects);
		allprojects.click();
		wait.waitForVisibility(edit_project);
		edit_project.click();
		wait.waitForVisibility(edittitle_project);
		elementutil.clearAndSendKey(edittitle_project, editTitle);
		save_projectedit.click();
		wait.waitForVisibility(edit_close);
		edit_close.click();
	}
	public String doDeleteProject() {
		wait.waitForVisibility(projects);
		projects.click();
		wait.waitForVisibility(allprojects);
		allprojects.click();
		wait.waitForVisibility(delete_project);
		delete_project.click();
		wait.waitForVisibility(projectdeleteconfirmation_button);
		projectdeleteconfirmation_button.click();
		wait.waitForVisibility(close_projectdeletealert);
		close_projectdeletealert.click();
		String actualText=project_norecordfound.getText();
		return actualText;
	}

}

