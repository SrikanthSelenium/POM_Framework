package myProject.practest.test.testScripts;

import org.testng.annotations.Test;

import myProject.practest.test.helper.assertion.AssertionHelper;
import myProject.practest.test.testbase.TestBase;

public class TestScript2 extends TestBase{

	@Test
	public void testss1() {
		AssertionHelper.makeTrue();
	}

	@Test
	public void testss2() {
		AssertionHelper.makeTrue();
	}

}
