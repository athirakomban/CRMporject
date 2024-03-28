package page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	@FindBy(xpath="//input[@id='password']")
	WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	WebElement signin;
	@FindBy(xpath="//span[text()='Dashboard']")
	WebElement dashboard;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String doLogin(String username,String Password)
	{

		email.sendKeys(username);
		password.sendKeys(Password);
		signin.click();
		String dashboardmenu=dashboard.getText();
		return dashboardmenu;

	}

}
