package lib;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TakeScreen extends UiAutomatorTestCase {
	/**
	 * 只有4.1以上支持该方法
	 * @param type
	 */
	public static void takescreen(String type){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = dateformat.format(new Date()).toString() + "-" + type +".png";
        File directory = new File("/mnt/sdcard/DMTest/TakeScreenshot");
        if (!directory.exists()) directory.mkdirs();
        File fl = new File(directory+"/"+filename);
        UiDevice.getInstance().takeScreenshot(fl, 0.7F, 70);
		
	}
	
}
