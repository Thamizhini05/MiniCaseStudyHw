package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DeleteArticlepage;
import pages.LoginValidationTest;
import pages.NewArticlePage;
import pages.UpdateArticlePage;



public class ArticlePage {
	WebDriver driver;
	LoginValidationTest loginPage;
	NewArticlePage newArticlePage;
	UpdateArticlePage updateArticle;
	DeleteArticlepage deleteArticle;
	public ArticlePage() {
		TestBase.initDriver();
		driver=TestBase.getDriver();
		loginPage=new LoginValidationTest(driver);
		newArticlePage=new NewArticlePage(driver);
		updateArticle=new UpdateArticlePage(driver);
		deleteArticle=new DeleteArticlepage(driver);
	}
	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/");
	}
	@Test(priority=1)
	public void loginTest() {
		loginPage.loginTest("thamizh.athiappan@zucisystems.com","priyanka");
	}
	@Test(priority=2)
	public void newArticleCreation() {
		newArticlePage.newArticleCreation();
	}
	@Test(priority=3)
	public void updateTestArticle() {
		updateArticle.updateTestArticle();
	}
	@Test(priority=4)
	public void deleteTestArticle() {
		deleteArticle.deleteTestArticle();
		boolean check = driver.findElement(By.xpath("//div[contains(text(),'Articles not available.')]")).isDisplayed();
	    Assert.assertTrue(check);
	}
	
}
