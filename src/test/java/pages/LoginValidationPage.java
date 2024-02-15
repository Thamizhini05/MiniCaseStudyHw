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
    
    public LoginValidationPage (WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
    public void loginTest(String strmail,String strpswd) {
  	 
      loginButton.click();
  	  email.sendKeys(strmail);
      pswd.sendKeys(strpswd);
  	  loginbtn.click();
  	}

}


