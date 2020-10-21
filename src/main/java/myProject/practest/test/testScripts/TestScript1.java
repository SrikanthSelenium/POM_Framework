package myProject.practest.test.testScripts;




import org.testng.annotations.Test;

import myProject.practest.test.helper.assertion.AssertionHelper;
import myProject.practest.test.testbase.TestBase;

public class TestScript1 extends TestBase{

	@Test
	public void tests1() {
		AssertionHelper.makeFalse();
	}

	@Test
	public void tests2() {
		AssertionHelper.makeTrue();
	}

}
