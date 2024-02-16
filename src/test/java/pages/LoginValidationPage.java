package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginValidationPage {
	@FindBy(xpath="//a[@href='#/login']")
    WebElement loginButton;
    @FindBy(xpath="//input[@name='email']")
    WebElement email;
    @FindBy(xpath="//input[@name='password']")
    WebElement pswd;
    @FindBy(xpath="//button[contains(text(),'Login')]")
    WebElement loginbtn;
    @FindBy(xpath="//div[contains(text(),'Thamizhini Athiappan')]")
    WebElement validLoginCheck;
    @FindBy(xpath="//li[contains(text(),'Wrong email/password combination')]")
    WebElement invalidLoginCheck;
    
    public LoginValidationPage (WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
    public void validLoginTest(String strmail,String strpswd) {
  	 
      //loginButton.click();
      email.clear();
  	  email.sendKeys(strmail);
  	  pswd.clear();
      pswd.sendKeys(strpswd);
  	  //loginbtn.click();
      loginbtn.click();
  	}
    
    public void invalidLoginTest(String strmail,String strpswd) {
     	 
        loginButton.click();
    	email.sendKeys(strmail);
        pswd.sendKeys(strpswd);
    	loginbtn.click();
    	}
    
     public String checkValidLogin() {
      	 return validLoginCheck.getText();
      }
   
     public String checkInValidLogin() {
  	   return invalidLoginCheck.getText();
     }
    

}


