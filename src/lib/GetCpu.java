package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class GetCpu extends Thread{
	
	public static String appname ;
	public static List<String> cpuresult_string = new ArrayList<String>();
	public static List<Float> cpuresult_float = new ArrayList<Float>();
	Pattern cpupattern = Pattern.compile("([0-9]{1,3})%");
	private static boolean runs=true;
	
	private SS dmc = new SS();

	
	public GetCpu(String mystring) {
		appname = mystring;
		// TODO Auto-generated constructor stub
	}
	
	public void runstart(){
		if(dmc.CPULOG)
			this.start();
	}



	public void run() {
		Log.d("TTXX" , "获取cpu使用率线程开始");
		String[] cmdString = {"/system/bin/sh" , "-c" , "top -d 2"};
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = runtime.exec(cmdString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		while (runs) {
			String line;
			try {
				line = reader.readLine();
				if (line.toString().contains(appname) == true) {
					//Log.d("TTT",line.toString());
					Matcher cpumatcher = cpupattern.matcher(line.toString());
					if (cpumatcher.find()) {
						if(!cpumatcher.group(1).equals("0")){
							cpuresult_string.add(cpumatcher.group(1));
							Log.d("TTXX", "Cpu_"+cpuresult_string.get(cpuresult_string.size()-1));
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void thread_stop() {
		//GetCpu.currentThread().stop();
		runs=false;
		Log.d("TTXX" , "获取cpu使用率线程停止");
		float sum_cpu = 0;
		float max_cpu = 0;
		for (int i = 0 ; i < cpuresult_string.size() ; i++) {
			float tmp=Float.valueOf(cpuresult_string.get(i)).floatValue();
			if(tmp>0.0001){
				cpuresult_float.add(tmp);
			}
		}
		for (int i = 0 ; i < cpuresult_float.size() ; i++) {
			
			if (max_cpu < cpuresult_float.get(i)) {
				max_cpu = cpuresult_float.get(i);
			}
			sum_cpu = sum_cpu + cpuresult_float.get(i);
		}
		Log.d("TTXX" , "avg_cpu:" + sum_cpu/cpuresult_float.size());
		Log.d("TTXX" , "max_cpu:" + max_cpu);
	}
}