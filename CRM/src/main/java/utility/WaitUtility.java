package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	WebDriverWait wait;

	public WaitUtility(WebDriver driver){

		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

	}

	public void waitForVisibility(WebElement element) {


		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForVisibility(By locator) {


		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitForVisibility(List<WebElement> element) {


		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public void waitForElementClick(WebElement element) {


		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
