package com.org.kore.runner;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class CheckUrCode {

	static ArrayList<String> expectedValetNamesList = new ArrayList<String>();

	static int passcount = 0;
	static int failcount = 0;
	static int othercount = 0;
	static int totalcount = 0;

	public static void main(String[] a) throws Exception {
		// copyReportFile();

		int tcount = 100;

		File f = new File("source.html");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html><head><style>table {font-family: arial, sans-serif; border-collapse: collapse;width: 30%;}td, th {border: 1px solid #dddddd;text-align: center;padding: 8px;}tr:nth-child(even) {background-color: #dddddd;}</style></head>");
		
		bw.write("<body><h2>Work Assist Automation Execution Report</h2>");
		bw.write(
				"<table><tr><th>Status</th> <th>Count</th></tr><tr><td>PASS</td><td>"+tcount+"</td></tr><tr><td>FAIL</td><td>5</td></tr><tr><td>TOTAL</td><td>15</td></tr>");
		bw.write("<table></table>");
		bw.write("</table></body></html>");
		bw.close();
		Desktop.getDesktop().browse(f.toURI());
	}

	public static void copyReportFile() {
		Path result = null;

		String dir = System.getProperty("user.dir");
		System.out.println(dir);

		String source;
		source = new File(dir + "/ReportGenerator/HtmlReport_2021-03-02-15-41-10/TestReport.html").getPath();
		source.toString();

		String finalpath;
		finalpath = new File(dir + "/ReportGenerator/JenkinsReport/TestReport.html").getPath();
		finalpath.toString();

		/*
		 * String source; source = new
		 * File("C:/Users/Jayakrishna.Dandru/Desktop/Copy/File1/TestReport.html"
		 * ).getPath(); source.toString();
		 * 
		 * String finalpath; finalpath = new
		 * File("C:/Users/Jayakrishna.Dandru/Desktop/Copy/File2/TestReport.html"
		 * ).getPath(); finalpath.toString();
		 */
		try {
			// result = Files.move(Paths.get(src), Paths.get(dest));
			result = Files.copy(Paths.get(source), Paths.get(finalpath));
		} catch (IOException e) {
			System.out.println("Exception while moving file: " + e.getMessage());
		}
		if (result != null) {
			System.out.println("File moved successfully.");
		} else {
			System.out.println("File movement failed.");
		}
	}

	private static void copyFileUsingJava7Files(File source, File dest) throws IOException {

		Files.copy(source.toPath(), dest.toPath());
	}

	public static void getFirstChar() {
		String name = "Jaya";
		char first = name.charAt(0);
		String myStr = Character.toString(first);
		System.out.println(myStr);

	}

	public static void stringsplit() {
		String commaSeparated = "item1 , item2 , item3";
		if (commaSeparated.contains(",")) {
			String result[] = commaSeparated.trim().split("\\s*,\\s*");
			for (String a : result)
				System.out.println(a);
		}
	}

	public static int convertToMnts(int hrs) {

		int mnts = hrs * 60;
		return mnts;

	}

	public static List<String> generateListFromStrings(String one, String two) {
		List<String> list = new ArrayList<String>();
		list.add(one);
		list.add(two);
		System.out.println(list);
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().equals(" ")) {
				it.remove();
			}
		}

		for (String s : list) {
			System.out.println("'" + s + "'");
		}

		return list;

	}

	@SuppressWarnings("null")
	public static List<String> totalGroupParticipants() {

		String origina = "dileep.pisati@kore.com,rrakash.rochkari@kore.com,pamana.kommula@kore.com";
		String updated = "abcd.xyz@kore.com";

		List<String> list = new ArrayList<String>();
		List<String> listB = new ArrayList<String>();
		// List<Object> list = new ArrayList<Object>();
		listB.add("abcd.xyz@kore.com, dileep.pisati@kore.com,rrakash.rochkari@kore.com,pamana.kommula@kore.com");
		list.add(origina);
		list.add(updated);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		System.out.println(listB);

		boolean isEqual = list.equals(listB);

		/*
		 * Iterator<String> it = list.iterator(); while (it.hasNext()) { if
		 * (it.next().equals(" ")) { it.remove(); } }
		 * 
		 * for (String s : list) { System.out.println("'" + s + "'"); }
		 */

		return list;

		/*
		 * String finall = origina+","+updated; System.out.println(finall);
		 * 
		 * List<String> al = new ArrayList<String>(); al =
		 * Arrays.asList(finall); System.out.println(al); Collections.sort(al);
		 * System.out.println(al);
		 */

	}

	public static String formatDate(String originalDate) {

		Date now = new Date(originalDate);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
		System.out.println(simpleDateformat.format(now));
		String weekday = simpleDateformat.format(now);

		List<String> dateList = Arrays.asList(originalDate.split("/"));
		String mnth;
		System.out.println(dateList);
		String day;
		if (dateList.get(1).startsWith("0")) {
			day = dateList.get(1).substring(1);
		} else {
			day = dateList.get(1);
		}
		if (dateList.get(0).startsWith("0")) {
			mnth = dateList.get(0).substring(1);
		} else {
			mnth = dateList.get(0);
		}
		String formattedDate = weekday + " " + mnth + "/" + day;
		System.out.println("Today is " + formattedDate);
		return formattedDate;
	}

	public static String getDay(String Date) {
		String day;
		List<String> dateList = Arrays.asList(Date.split("/"));
		if (dateList.get(1).startsWith("0")) {
			day = dateList.get(1).substring(1);
		} else {
			day = dateList.get(1);
		}
		return day;
	}

	public void getSum(int k) {
		List<Integer> numlist = new ArrayList<>();

		numlist.add(2);
		numlist.add(3);
		int result = 0;

		for (int i = 0; i < numlist.size(); i++) {
			for (int j = 0; j < k; j++) {
				result += numlist.get(j) / 2;
			}

		}

	}

}
