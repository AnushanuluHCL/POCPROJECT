package com.sat.testbase;

import java.io.File;
//import java.io.File;
//import ws.schild.jave.AudioAttributes;
//import ws.schild.jave.Encoder;
//import ws.schild.jave.EncoderException;
//import ws.schild.jave.VideoAttributes;
//import ws.schild.jave.EncodingAttributes;
//import ws.schild.jave.InputFormatException;
//import ws.schild.jave.MultimediaObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase   {
	public WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	Wait wait = new Wait();
	public TestBase driverFactory;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//	ATUTestRecorder  recorder;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/sat/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver initialization() throws Exception {
		Thread.sleep(2000);
		String browserName = prop.getProperty("browser");
		System.out.println("clearing task manager");
		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		// Runtime.getRuntime().exec("taskkill /F /IM cmd.exe /T");

		// Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
		// Runtime.getRuntime().exec("taskkill /F /IM cmd.exe /T");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Testutil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(Testutil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		
		return getDriver();

	}
	
	public void QuitBrowser() throws Exception 
	{
		getDriver().quit();
	}

	public void UrlLaunch() {

		System.out.println(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));
		Wait.untilPageLoadComplete(getDriver());
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public void clearCache() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.get("chrome://settings/passwords");
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
		Thread.sleep(5000);
	}

	public void generateAllureReport() throws Exception {

		System.out.println("generating the report");
		Thread.sleep(3000);

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		String reportfolder = "allure-report_" + timeStamp;

		String resultspath = System.getProperty("user.dir") + "\\target\\allure-results";
		System.out.println("Final Report folder should be = " + resultspath + "\\" + reportfolder);	
		
		try {
			java.nio.file.Path path = Paths.get(System.getProperty("user.dir") + "\\target" + "\\" + reportfolder);
			// java.nio.file.Files;
			Files.createDirectories(path);
			System.out.println("Directory is created!");
		} catch (IOException e) {
			System.err.println("Failed to create directory!" + e.getMessage());
		}

		executeShellCmd("cmd.exe /c cd " + System.getProperty("user.dir"));
		executeShellCmd("cmd.exe /c cd " + resultspath);
		
		 //executeShellCmd("cmd.exe /c move " + resultspath+" "+System.getProperty("user.dir") + "\\target" + "\\" + reportfolder);
		 
		 executeShellCmd("cmd.exe /c allure serve " + " " +resultspath);
		 
		// executeShellCmd("cmd.exe /c cd " +System.getProperty("user.dir") + "\\target" + "\\" + reportfolder+"\\"+"allure-results");
		// executeShellCmd("cmd.exe /c allure serve "+System.getProperty("user.dir") + "\\target" + "\\" + reportfolder+"\\"+"allure-results");
		 

	}
	
	
	public void executeShellCmd(String shellCmd) {
		try {
			System.out.println("running the command " + shellCmd);
			Process process = Runtime.getRuntime().exec(shellCmd);
			//Thread.sleep(3000);
			//process.destroy();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in Executing the command " + shellCmd);
		}
	}
	
	public String getFilePath()
    {
        File destination = new File("Video-Recordings");
        String filepath = destination.getAbsolutePath();
         
        System.out.println("------file path--------" +filepath);
        return filepath;
    }
	
//	public void startRecording() throws Exception
//	{
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
//		String videoName = "TC_ATU_Test_Video" + timeStamp;
//		   String videoDir = getFilePath();
//		   
//		  
//	         
//	       recorder = new ATUTestRecorder(videoDir,videoName,false);
//	     
//
//		 //recorder = new ATUTestRecorder("C:\\Users\\TP_HCLPO-MP1\\Recordings_" + timeStamp,false);
//	recorder.start();
////	String Chromepath = “E:\\chromedriver.exe”;
////	System.setProperty(“webdriver.chrome.driver”, Chromepath);
////	driver = new ChromeDriver();
////	driver.manage().window().maximize();
////	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
////	driver.get(“https://www.ignatiuz.com/”);
//
//	}
//	
//	public void stopRecording() throws Exception
//	{
//	recorder.stop();
//	}




//	public void AviToMp4(String oldPath,String newPath,UploadRecord uploadRecord,String type) {
//	//File source = new File("C:\\shiping\\1.avi");
//	//File target = new File("C:\\shiping\\2019-06-27333333Test.mp4");
//			File source = new File(oldPath);
//		    File target = new File(newPath);
//		    Log.info("Path before conversion:"+oldPath);
//		    Log.info("Converted path:"+newPath);
//		    AudioAttributes audio = new AudioAttributes(); 
//			audio.setCodec("libmp3lame");//Audio encoding format
//			audio.setBitRate(new Integer(800000));
//			audio.setChannels(new Integer(1)); 
//			//audio.setSamplingRate(new Integer(22050)); 
//			VideoAttributes video = new VideoAttributes(); 
//			video.setCodec("libx264");//Video encoding format
//			video.setBitRate(new Integer(3200000));
//			video.setFrameRate(new Integer(15));//If the number setting is small, the video will freeze
//			EncodingAttributes attrs = new EncodingAttributes(); 
//			attrs.setFormat("mp4");
//			attrs.setAudioAttributes(audio); 
//			attrs.setVideoAttributes(video); 
//			Encoder encoder = new Encoder();  
//			MultimediaObject multimediaObject = new MultimediaObject(source);
//			try {
//				Log.info("avi to MP4 --- conversion start:"+new Date());
//				encoder.encode(multimediaObject, target, attrs);
//				Log.info("avi to MP4 --- conversion end:"+new Date());
//			} catch (IllegalArgumentException e) {
//				//TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InputFormatException e) {
//				//TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (EncoderException e) {
//				//TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	


}
