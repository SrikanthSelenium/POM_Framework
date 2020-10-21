package myProject.practest.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import myProject.practest.test.helper.resource.ResourceHelper;

public class ExtentManager {

	private static ExtentReports extent;
	private static File reportDirectory;
	private static String fileName;

	public static ExtentReports getInstance() throws IOException {
		if (extent == null) {
			return createInstance(ExtentResultsReportPath());
		} else {
			return extent;
		}
	}

	public static String ExtentResultsReportPath() throws IOException {
		reportDirectory = new File(ResourceHelper.getResourcePath("src\\main\\resources\\reports"));
		if (!reportDirectory.exists()) {
			reportDirectory.mkdir();
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		fileName = "Extentreport" + "_" + format.format(calendar.getTime()) + ".html";
		return reportDirectory + "/" + fileName;
	}

	public static synchronized ExtentReports createInstance(String fileName) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;

	}

}
