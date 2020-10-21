package myProject.practest.test.helper.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import myProject.practest.test.helper.logger.LoggerHelper;

public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 3;
	Logger log = LoggerHelper.getLogger(Retry.class);

	public boolean retry(ITestResult arg0) {
		if (maxRetryCount > retryCount) {
			log.info("Retrying test :" + arg0.getName() + " with status " + resultStatus(arg0.getStatus()) + " for the"
					+ (retryCount + 1) + "times");
			retryCount++;
			return true;
		}
		return false;
	}

	public String resultStatus(int status) {
		String result = null;

		if (status == 1) {
			result = "SUCCESS";
		}
		if (status == 2) {
			result = "FAILURE";
		}
		if (status == 3) {
			result = "SKIP";
		}
		return result;

	}

}
