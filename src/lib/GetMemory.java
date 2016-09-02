package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.util.Log;


public class GetMemory extends Thread {
	
	private static long max_memory = 0;
	private static long count = 0;
	private static long sum = 0;
	private static boolean runs=true;
	String nativeArray[];
	String DalvikHeap[];
	String total[];
	String cmdcommand;
	Process p;
	InputStream input;
	BufferedReader reader;
	private SS dmc = new SS();
	
	public GetMemory(String mystring) {
		Log.d("TTXX" , "获取memory线程开始");
		//nativeArray = new String[7];
		//DalvikHeap = new String[7];
		total = new String[7];
		cmdcommand = (new StringBuilder("dumpsys meminfo ").append(mystring)).toString();
		
	}
	
	public void runstart(){
		if(dmc.MEMLOG)
			this.start();
			
	}
	
	
	public void run() {
		String line;
		while(true&&runs){
			try {
				p = Runtime.getRuntime().exec(cmdcommand);
				input = p.getInputStream();
				reader = new BufferedReader(new InputStreamReader(input));
				while((line = reader.readLine())!= null){
				
					this.getTotalData(line);
						
				}
				//Log.d("TTXX", "END");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
	
	
	public void getNativeData(String line)
	{
		int k = 0;
		if (line.matches(".*Native.*"))
		{
			String nativeHeap[] = line.split("\\s+");
			for (int i = 0; i < nativeHeap.length; i++)
				if (nativeHeap[i].matches("\\d+"))
					nativeArray[k++] = nativeHeap[i];
			Log.d("TTXX","Mem_"+nativeArray[0]);
		}
		//return nativeArray;
	}

	public void getDalvikData(String line)
	{
		int k = 0;
		if (line.matches(".*Dalvik.*"))
		{
			String nativeHeap[] = line.split("\\s+");
			for (int i = 0; i < nativeHeap.length; i++)
				if (nativeHeap[i].matches("\\d+"))
					DalvikHeap[k++] = nativeHeap[i];
			Log.d("TTXX","Mem_"+DalvikHeap[0]);
		}
		//return DalvikHeap;
	}

	public void getTotalData(String line)
	{
		int k = 0;
		if (line.matches(".*TOTAL.*"))
		{
			String nativeHeap[] = line.split("\\s+");
			for (int i = 0; i < nativeHeap.length; i++){
				if (nativeHeap[i].matches("\\d+"))
					total[k++] = nativeHeap[i];
			}
			Log.d("TTXX","Mem_"+total[0]);
			if(max_memory < Long.parseLong(total[0]))
				max_memory = Long.parseLong(total[0]);
			sum +=Long.parseLong(total[0]);
			count++;
			

		}
		//return total;
	}

	public int getMaxInt(ArrayList array)
	{
		int max = 0;
		if (array.size() > 0)
		{
			for (int i = 0; i < array.size(); i++)
				if (((Integer)array.get(i)).intValue() > max)
					max = ((Integer)array.get(i)).intValue();

		}
		return max;
	}
	
	
	
	public static void thread_stop() {
		runs=false;
		Log.d("TTXX" , "获取memory线程停止");
		Log.d("TTXX" , "avg_memory:" + sum/count);
		Log.d("TTXX" , "max_memory:" + max_memory);
	}

}
