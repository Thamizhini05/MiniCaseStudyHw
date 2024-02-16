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
	    WebElement check;
	    
	    @FindBy(xpath="//span[contains(text(),'Title already exists.. ')]")
	    WebElement duplicate;
	    
	   public NewArticlePage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
	   public void newArticleCreation(String newTitle,String articleAbout,String textArea,String entertagbtn) {
		   createNewArticlebtn.click();
		   newArticleTitle.clear();
		   newArticleTitle.sendKeys(newTitle);
		   newArticleAbout.clear();
		   newArticleAbout.sendKeys(articleAbout);
		   articleTextArea.clear();
		   articleTextArea.sendKeys(textArea);
		   //enterTag.clear();
		   enterTag.clear();
		   enterTag.sendKeys(entertagbtn);
		   publishArticlebtn.click();
		 }
	   
	   public String newArticleValidate() {
			  return  check.getText();
		   }
	   
	   public String duplicateArticleValidate() {
		   return duplicate.getText();
	   }

}


