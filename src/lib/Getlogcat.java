package lib;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

public class Getlogcat{
	static String TAG = "liliTest";
	private static boolean runs=true;
	private Thread mythread;
	private SS dmc = new SS();
	
	public void runget(){
		mythread = new Thread(new Runnable() {
			public void run() {
				getlog();
			}
		});
		if(dmc.GETLOG)
			mythread.start();
	}
	
	private static void getlog(){
		int n = 0;
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhh-mm-ss");
		String filename;
		String shell = "logcat";  
        try
        {  
            Process process = Runtime.getRuntime().exec(shell);
            InputStream inputStream = process.getInputStream();
              
              
//            boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);  
//            File dir = null;  
//            if (sdCardExist)  
//            {  
//                dir = new File(Environment.getExternalStorageDirectory().toString()  
//                        + File.separator + "logcatwyx.txt");  
//                if (!dir.exists())  
//                {  
//                    dir.createNewFile();  
//                }  
//
//            }  
            
            File dir1 = new File("/mnt/sdcard/DMtest/log");
            if(!dir1.exists())
            	dir1.mkdirs();
            File dir;
            
            //dir = new File(dir1+"/"+filename);
            byte[] buffer = new byte[1024];  
             
            try{  
                //FileOutputStream fos = new FileOutputStream(dir);
                while(runs){
                	
                	int bytesLeft = 5 * 1024 * 1024; // Or whatever
                	filename = new StringBuilder(date.format(new Date()).toString()).append("_").append(n).append(".log").toString();
                	dir = new File(dir1+"/"+filename);
                	FileOutputStream fos = new FileOutputStream(dir);
                	
                try{
                    while (bytesLeft > 0 && runs){
                        int read = inputStream.read(buffer, 0, Math.min(bytesLeft,buffer.length));
                        if (read == -1){
                            throw new EOFException("Unexpected end of data");
                        }
                        //n++;
                        fos.write(buffer, 0, read);
                        bytesLeft -= read;
                        //Log.e("NNNN", "kk"+bytesLeft);
                    }
                } finally{
                    fos.close(); // Or use Guava's
                                 // Closeables.closeQuietly,
                    //or try-with-resources in Java 7
                }
                n++;
                }
            } finally{
                inputStream.close();  
            }
            
//            String logcat = convertStreamToString(inputStream);  
//            outputFile2SdTest(logcat, "logwyx.txt");  
            Log.v(TAG, "LOGCAT = ok" );  
        } catch (IOException e){  
            e.printStackTrace();  
        }
		
	}
	
	
	public void stop_thread(){
		//mythread.stop();
		runs = false;
		
	}
	
}
