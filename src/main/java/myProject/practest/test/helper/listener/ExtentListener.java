
package myProject.practest.test.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import myProject.practest.test.helper.logger.LoggerHelper;

public class ExtentListener implements ITestListener{
	
	private Logger log = LoggerHelper.getLogger(ExtentListener.class);

	//public ExtentReports extent;
	//public ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		//test.log(Status.INFO, result.getName()+" started");
		//Reporter.log(result.getMethod().getMethodName()+" Test started..");
		Reporter.log(result.getMethod().getMethodName()+" Test Started..");
		log.info(result.getMethod().getMethodName()+" Test Started..");
	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, result.getName()+" Passed..");
		//Reporter.log(result.getMethod().getMethodName()+" Test Passed..");
		Reporter.log(result.getMethod().getMethodName()+" Test Passed..");
		log.info(result.getMethod().getMethodName()+" Test Passed..");
		
	}

	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, result.getThrowable());
		//Reporter.log(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
		
	}

	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getThrowable());
		//Reporter.log(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Skipped.."+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" Test Skipped.."+result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		//extent = ExtentManager.getInstance();
		//test = extent.createTest(context.getName());
		//Reporter.log(context.getName()+" Test Started..");
		Reporter.log(context.getCurrentXmlTest().getName()+" Class Started..");
		log.info(context.getCurrentXmlTest().getName()+" Class Started..");
		
	}

	public void onFinish(ITestContext context) {
		//extent.flush();
		//extent.close();
		 Reporter.log(context.getName()+" Class Finished..");
	     log.info(context.getName()+" Class Finished..");
		
	}

}


