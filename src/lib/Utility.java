
package lib;

import android.graphics.Bitmap;
import android.test.UiThreadTest;
import android.util.Log;
import com.android.uiautomator.core.UiDevice;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import junit.framework.TestCase;

public class Utility
{

	UiDevice device;
	Auto au = new Auto();
	Image mg = new Image();
	public String getRandomNumberString(int count)
	{
		String randomSequence = "";
		for (int i = 0; i < count; i++)
		{
			Integer number = Integer.valueOf((new Random()).nextInt(10));
			number.toString();
			randomSequence = (new StringBuilder(String.valueOf(randomSequence))).append(number.toString()).toString();
		}

		return randomSequence;
	}

	public long getFileSize(String path)
	{
		File file = new File(path);
		if (file.exists() && file.isFile())
			return file.getTotalSpace();
		else
			return -1L;
	}

	public String getRandomCharString(int count)
	{
		char alphaArray[] = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			'u', 'v', 'w', 'x', 'y', 'z'
		};
		String randomSequence = "";
		for (int i = 0; i < count; i++)
		{
			Character c = Character.valueOf(alphaArray[(new Random()).nextInt(alphaArray.length)]);
			randomSequence = (new StringBuilder(String.valueOf(randomSequence))).append(c.toString()).toString();
		}

		return randomSequence;
	}

	public boolean checkScreenshot(String name,int x, int y, int width, int height, double p) throws FileNotFoundException{
		String p1 = "/mnt/sdcard/DMTest/path/"+name+".png";
		String p2 = "/mnt/sdcard/DMTest/catch/"+name+".png";
		au.takescreenWitchPath(p2);
		Bitmap sub1 = mg.getSubImage(p1, x, y, width, height);
		Bitmap sub2 = mg.getSubImage(p2, x, y, width, height);
		return mg.sameAs(sub1, sub2, p);
		
	}
	
	
	public void execApp(String component, String action)
	{
		String cmd = (new StringBuilder("am start -a ")).append(action).append(" -n").append(component).toString();
		try
		{
			Process process = Runtime.getRuntime().exec(cmd);
			try
			{
				process.waitFor();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void execApp(String component, String action, ArrayList parameters)
	{
		String cmd = "am start ";
		StringBuilder cmdBuilder = new StringBuilder();
		cmdBuilder.append(cmd);
		if (action == null || !action.isEmpty())
			cmdBuilder.append((new StringBuilder(" -a ")).append(action).toString());
		if (!component.isEmpty())
			cmdBuilder.append((new StringBuilder(" -n ")).append(component).toString());
		String str;
		for (Iterator iterator = parameters.iterator();iterator.hasNext(); cmdBuilder.append((new StringBuilder(" ")).append(str).toString()))
			str = (String)iterator.next();

		System.err.println((new StringBuilder("cmd:")).append(cmdBuilder.toString()).toString());
		try
		{
			Process process = Runtime.getRuntime().exec(cmdBuilder.toString());
			try
			{
				process.waitFor();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void logClear()
	{
		String cmd = "logcat -c ";
		try
		{
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void logcat(String className)
	{
		String fileName = (new StringBuilder("/mnt/sdcard/DMtest/logcat/")).append(className).append(getCurrentDate()).append(".txt").toString();
		String cmd = (new StringBuilder("logcat -d -f ")).append(fileName).toString();
		try
		{
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 只使用与4.1以上系统
	 * @param testCase
	 */
	public void screenshotAndLog(TestCase testCase)
	{
		String PNG_PATH = (new StringBuilder(String.valueOf(getLatestFolder("/sdcard/dzsoftSmart/taskLogs")))).append(File.separator).toString();
		Throwable t = new Throwable();
		StackTraceElement ste = t.getStackTrace()[1];
		String pNG_NAME = (new StringBuilder(String.valueOf(ste.getMethodName()))).append(getCurrentDate()).append(".png").toString();
		if (pNG_NAME.contains("leAssert"))
		{
			ste = t.getStackTrace()[2];
			pNG_NAME = (new StringBuilder(String.valueOf(ste.getMethodName()))).append("_line").append(ste.getLineNumber()).append(getCurrentDate()).append(".png").toString();
		}
		String HIERARCHY_PATH = "/data/local/tmp/";
		String HIERARCHY_NAME = (new StringBuilder(String.valueOf(testCase.getClass().getName()))).append(getCurrentDate()).toString();
		String testName = testCase.getClass().getSimpleName();
		try
		{
			logcat((new StringBuilder(String.valueOf(HIERARCHY_PATH))).append(testName).toString());
			File HierarchyFile = new File((new StringBuilder(String.valueOf(HIERARCHY_PATH))).append(HIERARCHY_NAME).toString());
			if (HierarchyFile.exists())
				HierarchyFile.delete();
			UiDevice.getInstance().dumpWindowHierarchy(testCase.getClass().getName());
			File file = new File((new StringBuilder(String.valueOf(PNG_PATH))).append(pNG_NAME).toString());
			if (file.exists())
				file.delete();
			UiDevice.getInstance().takeScreenshot(file, 0.7F, 70);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMdd_HHmmss");
		String dataString = sdf.format(new Date());
		return dataString;
	}

	public String getMethodName(Throwable throwable)
	{
		String methodName = throwable.getStackTrace()[0].getMethodName();
		return methodName;
	}

	public String getTagValue(String fileName, String tag) throws IOException
	{
		String debug_tag;
		File file;
		BufferedReader reader;
		String targetString;
		debug_tag = getMethodName(new Throwable());
		file = new File(fileName);
		reader = null;
		targetString = null;
		if (!file.exists())
			return targetString;
		String tempString;
		reader = new BufferedReader(new FileReader(file));
		tempString = null;
		
		  while((tempString = reader.readLine()) != null){
			  if (tempString.contains(tag)){
					String s;
					int index = tempString.indexOf(":");
					targetString = tempString.substring(index + 1);
					Log.d(debug_tag, (new StringBuilder("targetString=")).append(targetString).toString());
					s  = targetString.trim();
					if (reader != null)
						try
						{
							reader.close();
						}
						catch (IOException e1)
						{
							e1.printStackTrace();
						}
					return s;
			  }
			  
		  }
		  
		if (reader != null)
			try
			{
				reader.close();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}

	
		Log.d(debug_tag, (new StringBuilder("targetString=")).append(targetString).toString());
		return targetString;
	}

	public String getLatestFolder(String path)
	{
		String target = null;
		File file = new File(path);
		if (!file.exists() || !file.isDirectory())
			target = null;
		long modifyTime = file.lastModified();
		File afile[];
		int j = (afile = file.listFiles()).length;
		for (int i = 0; i < j; i++)
		{
			File item = afile[i];
			if (item.lastModified() >= modifyTime)
			{
				if (item.isDirectory())
					target = getLatestFolder(item.getPath());
				if (item.isFile())
					target = item.getParent();
			}
		}

		return target;
	}
}
