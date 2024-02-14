package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewArticlePage {
		@FindBy(xpath="//a[@href='#/editor']")
	   WebElement createNewArticlebtn;
	   
	   @FindBy(xpath="//input[@placeholder='Article Title']")
	   WebElement newArticleTitle;
	   
	   @FindBy(xpath="//input[@class='form-control ' and @name='description']")
	   WebElement newArticleAbout;
	   
	   @FindBy(xpath="//textArea[@rows='8']")
	   WebElement articleTextArea;
	   
	    @FindBy(xpath="//input[@placeholder='Enter tags']")
	   WebElement enterTag;
	    
	    @FindBy(xpath="//button[@type='submit']")
	    WebElement publishArticlebtn;
	    
	    @FindBy(xpath="//h1[contains(text(),'Testing in IT')]")
	    WebElement titleBtn;
	    
	   public NewArticlePage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
	   public void newArticleCreation() {
		   createNewArticlebtn.click();
		   newArticleTitle.sendKeys("Testing in IT");
		   newArticleAbout.sendKeys("Importance of Testing");
		   articleTextArea.sendKeys("Testing makes a major role in the IT field");
		   enterTag.sendKeys("Testing");
		   publishArticlebtn.click();
		   String tilteHeaderName=titleBtn.getText();
		   System.out.println("New Article Page Title-"+tilteHeaderName);
		   Assert.assertEquals(tilteHeaderName,"Testing in IT");
		 }

}
