package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class UpdateArticlePage {
	@FindBy(xpath="(//a[contains(text(),' Edit Article')])[1]")
    WebElement editBtn;
    
    @FindBy(xpath="//input[@placeholder='Article Title']")
    WebElement articleTitle;
    
    @FindBy(xpath="//input[@class='form-control ' and @name='description']")
    WebElement articleAbout;
    
    @FindBy(xpath="//textArea[@rows='8']")
    WebElement textArea;
    
     @FindBy(xpath="//input[@placeholder='Enter tags']")
    WebElement enterTags;
     
     @FindBy(xpath="//button[contains(text(),'Update Article')]")
     WebElement updateBtn;
     
     @FindBy(xpath="(//a[@href='#/'])[2]")
     WebElement homeBtn;
     
     @FindBy(xpath="//h1[contains(text(),'Role of Testing')]")
     WebElement titleHeaderBtn;
     
     public  UpdateArticlePage(WebDriver driver) {
    	 PageFactory.initElements(driver,this);
     }
 
	
	public void updateArticle(String articleHeading,String aboutTheArticle,String text) {
	         editBtn.click();
	    	 articleTitle.clear();
	    	 articleTitle.sendKeys(articleHeading);
	    	 articleAbout.clear();
	    	 articleAbout.sendKeys(aboutTheArticle);
	    	 textArea.clear();
	    	 textArea.sendKeys(text);
	    	 updateBtn.click();

}
}


