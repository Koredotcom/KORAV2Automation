package com.org.kore.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.org.kore.utilities.DataReader;
import com.org.kore.utilities.TestDetails;

public class TestRunner {
	static TestDetails td;
	static ArrayList<TestDetails> tdlSeq;
	static ArrayList<TestDetails> tdlPar;
	static XmlTest testSeq;
	static XmlTest testPar;
	static ArrayList<XmlTest> testSeqList;
	static ArrayList<XmlTest> testparList;
	static ArrayList<XmlTest> testAllList;
	static XmlClass xc;
	static ArrayList<XmlClass> xclist;
	static ArrayList<XmlSuite> xslist;

	public static void main(String[] args) throws Exception {

		try {
			TestNG testSuite = new TestNG();
			testAllList = new ArrayList<>();
			XmlSuite xs = new XmlSuite();
			xs.setParallel(XmlSuite.ParallelMode.TESTS);
			String sheetName = "Regression";
			xs.setName("My Test Suite");
			String fileName = "TestRunner";
			DataReader dr = new DataReader();
			int noofRows = dr.getNoOfRows(fileName, sheetName);
			tdlSeq = new ArrayList<>();
			tdlPar = new ArrayList<>();
			xclist = new ArrayList<>();
			testSeq = new XmlTest();
			testSeqList = new ArrayList<>();
			testparList = new ArrayList<>();
			xslist = new ArrayList<>();
			for (int i = 1; i <= noofRows; i++) {
				td = new TestDetails();

				String packageName = dr.getValueWithRow(fileName, sheetName, i, "Package");
				String testName = dr.getValueWithRow(fileName, sheetName, i, "TestName");
				String browser = dr.getValueWithRow(fileName, sheetName, i, "Browser");
				String Execution = dr.getValueWithRow(fileName, sheetName, i, "Execution");
				String ExecutionType = dr.getValueWithRow(fileName, sheetName, i, "ExecutionType");
				String Environment = dr.getValueWithRow(fileName, sheetName, i, "Environment");

				if (Execution.equalsIgnoreCase("Yes") & ExecutionType.equalsIgnoreCase("Sequential")) {
					td.setPackageName(packageName);
					td.setTestName(testName);
					td.setBrowser(browser);
					td.setExecution(Execution);
					td.setExecutionType(ExecutionType);
					td.setEnvironment(Environment);
					tdlSeq.add(td);
					System.out.println(td.getTestName() + "===" + td.getExecutionType());
				} else if (Execution.equalsIgnoreCase("Yes") & ExecutionType.equalsIgnoreCase("Parallel")) {
					td.setPackageName(packageName);
					td.setTestName(testName);
					td.setBrowser(browser);
					td.setExecution(Execution);
					td.setExecutionType(ExecutionType);
					td.setEnvironment(Environment);
					tdlPar.add(td);
					System.out.println(td.getTestName() + "===" + td.getExecutionType());
				} else {
					System.out.println("Do Nothing");
				}

			}
			testSeq = new XmlTest();
			testSeq.setName("Sequential Tests");
			xclist = new ArrayList<>();
			System.out.println(tdlPar);
			System.out.println(tdlSeq);
			for (int i = 0; i < tdlSeq.size(); i++) {

				// Tests Creation

				xc = new XmlClass();
				xc.setName(tdlSeq.get(i).getPackageName() + "." + tdlSeq.get(i).getTestName());
				xclist.add(xc);
				testSeq.addParameter("browser", tdlSeq.get(i).getBrowser());
				testSeq.addParameter("environment", tdlSeq.get(i).getEnvironment());

			}
			testSeq.setXmlClasses(xclist);
			testSeqList.add(testSeq);
			testPar = new XmlTest();
			testPar.setName("Parallel Tests");
			testPar.setParallel(XmlSuite.ParallelMode.CLASSES);
			testPar.setThreadCount(tdlPar.size());
			xclist = new ArrayList<>();
			for (int i = 0; i < tdlPar.size(); i++) {

				// Tests Creation

				xc = new XmlClass();
				xc.setName(tdlPar.get(i).getPackageName() + "." + tdlPar.get(i).getTestName());
				xclist.add(xc);
				testPar.addParameter("browser", tdlPar.get(i).getBrowser());
				testPar.addParameter("environment", tdlPar.get(i).getEnvironment());

			}
			testPar.setXmlClasses(xclist);
			testparList.add(testPar);
			testAllList.addAll(testSeqList);
			testAllList.addAll(testparList);
			xs.setTests(testAllList);

			xslist.add(xs);

			// Create physical XML file based on the virtual XML content
			for (XmlSuite suite : xslist) {
				createXmlFile(suite);
			}
			List<String> suites = Lists.newArrayList();
			suites.add(path);
			testSuite.setTestSuites(suites);

			testSuite.run();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static String path;

	// This method will create an Xml file based on the XmlSuite data
	public static String createXmlFile(XmlSuite mSuite) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File("testngDyn.xml"));
			writer.write(mSuite.toXml());
			writer.flush();
			writer.close();
			path = new File("testngDyn.xml").getAbsolutePath();
			System.out.println(new File("testngDyn.xml").getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}