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
	public void invalidloginTest() {

		extentTest=extentReports.createTest("Invalid Login Test");
		loginPage.invalidLoginTest("thamizhinika.20it@kongu.edu","123tami");
		Assert.assertEquals(loginPage.checkInValidLogin(),"Wrong email/password combination");
	}
	@Test(priority=2)
	public void validLoginTest() {
		
		extentTest=extentReports.createTest("Valid Login Test");
		loginPage.validLoginTest("thamizhinika.20it@kongu.edu","123tamil");
		String name=loginPage.checkValidLogin();
		Assert.assertEquals(name,"Thamizhini Athiappan");
	}
	
	@Test(priority=3)
	public void duplicateArticleTest() {
		 extentTest=extentReports.createTest("Duplicate Article Test");
		newArticlePage.newArticleCreation("Automation Testing","Needs of Automation Testing","Automation Tester","Testing");
		Assert.assertEquals(newArticlePage.duplicateArticleValidate(),"Title already exists..");
	}
	
	@Test(priority=4)
	public void newArticleCreation() {
		
		extentTest=extentReports.createTest("New Article Test");
		newArticlePage.newArticleCreation("Testing in IT","Importance of Testing","Testing makes the major role in IT field","Testing");
		String headerName=driver.findElement(By.xpath("//h1[contains(text(),'Testing in IT')]")).getText();
		System.out.println("New Article Title-"+headerName);
	    Assert.assertEquals(headerName,"Testing in IT");
	}
	
	@Test(priority=5)
	public void updateTestArticle() {
		
		extentTest=extentReports.createTest("Update Article Test");
		updateArticle.updateArticle("Role of Testing","About Testing","Testing Projects");
		String articlename=driver.findElement(By.xpath("//h1[contains(text(),'Role of Testing')]")).getText();
		System.out.println("Updated article name"+articlename);
		//System.out.println("Artilce Title Updated");
		 Assert.assertEquals(articlename,"Role of Testing");
	}
	
	@Test(priority=6)
	public void deleteTestArticle() {
		extentTest=extentReports.createTest("Delete Article Test");
		deleteArticle.deleteArticle();
		String element=driver.findElement(By.xpath("//div[contains(text(),'Articles not available.')]")).getText();
        Assert.assertEquals(element,"Articles not available.");

	}
	
	@AfterMethod
	  public void teardown(ITestResult result) {
		  extentTest.assignAuthor("Tester-Thamizhini Athiappan")
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
