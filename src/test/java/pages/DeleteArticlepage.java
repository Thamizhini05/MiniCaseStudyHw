package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DeleteArticlepage {
	WebDriver driver;
	@FindBy(xpath = "//button[@class='btn btn-sm'][1]")
	WebElement deleteBtn;
	
	public DeleteArticlepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void deleteArticle()
	{
		deleteBtn.click();
		Alert alert=driver.switchTo().alert();
		//alert.accept();
		alert.accept();
		
	}
	
}
