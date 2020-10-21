package myProject.practest.test.helper.resource;

public class ResourceHelper {

	public static String getResourcePath(String path) {
		String basepath = System.getProperty("user.dir");
		return basepath + "/" +path;
	}

}
