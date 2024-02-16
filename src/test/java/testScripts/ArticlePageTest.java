package testScripts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import CommonUtils.Utility;
import base.TestBase;
import pages.DeleteArticlepage;
import pages.LoginValidationPage;
import pages.NewArticlePage;
import pages.UpdateArticlePage;



public class ArticlePageTest {
	WebDriver driver;
	LoginValidationPage loginPage;
	NewArticlePage newArticlePage;
	UpdateArticlePage updateArticle;
	DeleteArticlepage deleteArticle;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	
	public ArticlePageTest() {
		TestBase.initDriver();
		driver=TestBase.getDriver();
		loginPage=new LoginValidationPage(driver);
		newArticlePage=new NewArticlePage(driver);
		updateArticle=new UpdateArticlePage(driver);
		deleteArticle=new DeleteArticlepage(driver);
	}
	
	@BeforeTest
	public void setupExtent() {
		extentReports=new ExtentReports();
		spark=new ExtentSparkReporter("test-output/SparkReport.html")
				   .viewConfigurer()
				   .viewOrder()
				   .as(new ViewName[] {
						 ViewName.DASHBOARD,
						 ViewName.TEST,
						 ViewName.AUTHOR,
						 ViewName.DEVICE,
						 ViewName.LOG
				   }).apply();
	    extentReports.attachReporter(spark);
	}
	
	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/");
	}
	
	@Test(priority=1)
	public void loginTest() {
		extentTest=extentReports.createTest("Login Page Test");
		loginPage.loginTest("thamizhinika.20it@kongu.edu","123tamil");
		String name=driver.findElement(By.xpath("//div[contains(text(),'Thamizhini Athiappan')]")).getText();
		Assert.assertEquals(name,"Thamizhini Athiappan");

	}
	
	@Test(priority=2)
	public void newArticleCreation() {
		
		extentTest=extentReports.createTest("New Article Test");
		newArticlePage.newArticleCreation("Testing in IT","Importance of Testing","Testing makes the major role in IT field","Testing");
		String headerName=driver.findElement(By.xpath("//h1[contains(text(),'Testing in IT')]")).getText();
		System.out.println("New Article Title-"+headerName);
	    Assert.assertEquals(headerName,"Testing in IT");
	}
	
	@Test(priority=3)
	public void updateTestArticle() {
		
		extentTest=extentReports.createTest("Update Article Test");
		updateArticle.updateArticle("Role of Testing","About Testing","Testing Projects");
		String articlename=driver.findElement(By.xpath("//h1[contains(text(),'Role of Testing')]")).getText();
		System.out.println("Updated article name"+articlename);
		 Assert.assertEquals(articlename,"Role of Testing");
	}
	
	@Test(priority=4)
	public void deleteTestArticle() {
		extentTest=extentReports.createTest("Delete Article Test");
		deleteArticle.deleteArticle();
		Alert alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Want to delete the article?");
		alert.accept();

	}
	
	@AfterMethod
	  public void teardown(ITestResult result) {
		  extentTest.assignAuthor("Tester- Thamizhini ")
		  .assignCategory("Regression Test")
		  .assignDevice(System.getProperty("os.name"))
		  .assignDevice(System.getProperty("os.version"));
		  
		  if(ITestResult.FAILURE==result.getStatus()) {
			  extentTest.log(Status.FAIL,result.getThrowable().getMessage());
			  String strPath=Utility.getScreenshotPath(driver);
			   extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(strPath).build());
		  }
	}
	
	@AfterTest
	   public void finishExtent() {
		  extentReports.flush();
	  }
	
}
