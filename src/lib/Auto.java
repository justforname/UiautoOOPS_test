package lib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Auto extends UiAutomatorTestCase {
	
	private UiDevice mDevice;
	
	public Auto(){
		mDevice = UiDevice.getInstance();
	}
	
	/**
	 * 长按
	 * @param text
	 * @throws UiObjectNotFoundException
	 */
	public static void clickLongOntext(String text) throws UiObjectNotFoundException {
		UiSelector selButton = new UiSelector().text(text);
		UiObject objclickTextButton =  new UiObject(selButton);
		if(objclickTextButton.exists()){
			objclickTextButton.longClick();
		}else{
			throw new UiObjectNotFoundException("Text not found on window");
		}
		
	}
	
	/**
	 * 通过 classname 获取可滑动对像
	 * @param classname
	 * @return
	 */
	public static UiScrollable getScrollitem(String classname){
		return new UiScrollable(new UiSelector().className(classname).scrollable(true));
		
	}
	
	/**
	 * 通过classname instance获取可滑动对像
	 * @param classname
	 * @param ins
	 * @return
	 */
	public static UiScrollable getScrollbyinstance(String classname, int ins){
		return new UiScrollable(new UiSelector().className(classname).instance(ins));
	}
	
	
	/**
	 * 启动应用
	 * @param str1 包名
	 * @param str2 Activity名
	 * @throws IOException
	 */
    public static void launchApplication(String str1, String str2, String waitname) throws IOException{
    	
    	String shellcmd = "am start -n " + str1 + "/" + str2;
		 Runtime.getRuntime().exec(shellcmd);
		 if(!"".equals(waitname)){
		 UiObject check = new UiObject(new UiSelector().text(waitname));
		 check.waitForExists(10000);
		 }
    }
    
    public static void launchApplication(String str1, String str2) throws IOException{
    	
    	String shellcmd = "am start -n " + str1 + "/" + str2;
		 Runtime.getRuntime().exec(shellcmd);
		 UiObject check = new UiObject(new UiSelector().text("广场"));
		 check.waitForExists(10000);
		 
    }
    /**
     * 退出应用
     * @throws UiObjectNotFoundException
     */
    public static void exitapplication() throws UiObjectNotFoundException{
    	clickByText("发现");
    	UiScrollable exl = new UiScrollable(new UiSelector().scrollable(true));
    	exl.scrollTextIntoView("关闭多米");
    	clickByText("关闭多米");
    }
    
    /**
     * 强制结束进程
     * @param pacname 应用程序包名
     * @throws IOException
     */
    public static void killProcess(String pacname) throws IOException{
    	String shellcmd = "am force-stop "+pacname;
    	Runtime.getRuntime().exec(shellcmd);
    	threadSleep(3000);
    }
    
    
    
    
    public static void threadSleep(int s){
    	try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    /**
     * 自定义名称截图
     * @param type 名称
     */
    public static void takescreen(String type){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd-hh-mm-ss");
        String filename = dateformat.format(new Date()).toString() + "-" + type +".png";
        File directory = new File("/mnt/sdcard/DMTest/TakeScreenshot");
        if (!directory.exists()) directory.mkdirs();

        //File fl = new File(directory+"/"+filename);
        
        Process process;
		try {
			process = Runtime.getRuntime().exec("screencap -p "+directory.getPath()+"/"+filename);
			try {
				process.waitFor();
				//System.out.println("DOS Sucess");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
        
//        try {
//        	UiDevice.getInstance().takeScreenshot(fl, 0.7F, 70);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally{
//			Process process;
//			try {
//				process = Runtime.getRuntime().exec("screencap -p "+directory.getPath()+"/"+filename);
//				try {
//					process.waitFor();
//					System.out.println("DOS Sucess");
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				//e2.printStackTrace();
//			}
//		}
    
	} 
    public static void takescreenWitchPath(String path){
    	//SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd-hh-mm-ss");
        //String filename = type +".png";
        //File directory = new File(path);
        //if (!directory.exists()) directory.mkdirs();
        Process process;
		try {
			process = Runtime.getRuntime().exec("screencap -p "+path);
			try {
				process.waitFor();
				//System.out.println("DOS Sucess");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
    
	}
    
    
    
    /**
     * 检查文字是否存在(字符串开始匹配)
     * @param srt
     * @return
     */
    public static boolean checkText(String srt){
    	UiObject check= new UiObject(new UiSelector().textStartsWith(srt));
    	if (!check.exists()){
    		return false;
    	}else{return true;}
    }
    
    /**
     * 连续多次点击Back
     * @param j 点击次数
     */
    public void pressBack(int j){
    	for(int i=0;i<j;i++){
    		getUiDevice().pressBack();
    		sleep(1000);
    	}
    }
    

    /**
     * 通过class instance index 点击
     * @param string class
     * @param i index
     * @param j instance
     * @throws UiObjectNotFoundException
     */
    public static void clickByClassindexAndInstance(String string,int i,int j) throws UiObjectNotFoundException{
    	
    	UiObject classobj=new UiObject(new UiSelector().className(string).instance(j).index(i));
    	classobj.clickAndWaitForNewWindow();
    }
    
   /**
    *  通过类获取对象index(绝对匹配)并点击对象
    * @param string
    * @param i
    * @throws UiObjectNotFoundException
    */
    public static void clickByClassIndex(String string,int i) throws UiObjectNotFoundException{
    	
    	UiObject classobj=new UiObject(new UiSelector().className(string).index(i));
    	classobj.clickAndWaitForNewWindow();
    }
    
    /**
     * 通过文本获取对象(开始匹配)并点击对象
     * @throws UiObjectNotFoundException 
     */
 	public static void clickByStartText(String string) throws UiObjectNotFoundException{
 	
 		//UiObject textobj=new UiObject(new UiSelector().textMatches(string));
 		UiObject textobj=new UiObject(new UiSelector().textStartsWith(string));
 		textobj.clickAndWaitForNewWindow();
 	}
 
    /**
     * 通过文本获取对象(正则匹配)并点击对象
     * @param string string
     * @throws UiObjectNotFoundException
     */
    public static void clickByText(String string) throws UiObjectNotFoundException{
    	
    	//UiObject textobj=new UiObject(new UiSelector().textMatches(string));
    	UiObject textobj=new UiObject(new UiSelector().text(string));
    	textobj.clickAndWaitForNewWindow();
    }
    
    /**
     * 通过描述获取对象(正则匹配)并点击对象
     * @param string
     * @throws UiObjectNotFoundException
     */
    public static void clickByDesc(String string) throws UiObjectNotFoundException{
    	
    	UiObject descobj=new UiObject(new UiSelector().descriptionMatches(string));
    	descobj.clickAndWaitForNewWindow();
    }
    
    
    /**
     * 通过文字按钮获取对象并点击对象
     * @param string
     * @throws UiObjectNotFoundException
     */
    public static void clickByButtonText(String string) throws UiObjectNotFoundException{
    	
    	UiObject descobj=new UiObject(new UiSelector().className("android.widget.Button").text(string));
    	descobj.clickAndWaitForNewWindow();
    }
    
    /**
     * 循环查找比对
     * @param words 关键词
     * @return
     */
    public static boolean checkSomeWords(String [] words){
    	for(int i =0; i<words.length;i++){
    		UiObject uij = new UiObject(new UiSelector().textStartsWith(words[i]));
    		if(!uij.exists()){
    			System.out.println("Missing:"+words[i]);
    			return false;
    		}
    	}
    	return true;
    }
	/**
	 * 滑动查找文本 并点击
	 * @param text
	 * @throws UiObjectNotFoundException
	 */
    public static void scrollTextIntoView(String text) throws UiObjectNotFoundException{
    	UiScrollable sl = new UiScrollable(new UiSelector().scrollable(true));
		sl.scrollTextIntoView(text);
		Auto.clickByText(text);
		
    }
    
    /**
     * 通过class名和instance获取
     * @param className
     * @param instance
     * @return
     */
    public static UiObject getViewByClassWithInstance(String className, int instance)
	{
		UiSelector selector = (new UiSelector()).className(className).instance(instance);
		UiObject retObject = new UiObject(selector);
		return retObject;
	}
    
    /**
     * 通过class名和index获取
     * @param className
     * @param index
     * @return
     */
    public static UiObject getViewByClassWithIndex(String className, int index)
	{
		UiSelector selector = (new UiSelector()).className(className).index(index);
		UiObject retObject = new UiObject(selector);
		return retObject;
	}
    
    /**
     * 方向滑动
     * @param w up down left right
     */
    public static void swipe(String w){
    	int x = UiDevice.getInstance().getDisplayWidth();
    	int y = UiDevice.getInstance().getDisplayHeight();
    	if("up".equals(w)){
    		UiDevice.getInstance().swipe(x/2, 2*y/3, x/2, y/3, 5);
    	}else if("down".equals(w)){
    		UiDevice.getInstance().swipe(x/2, y/3, x/2, 2*y/3, 5);
    	}else if("left".equals(w)){
    		UiDevice.getInstance().swipe(2*x/3, y/2, x/3, y/2, 5);
    	}else if("right".equals(w)){
    		UiDevice.getInstance().swipe(x/3, y/2, 2*x/3, y/2, 5);
    	}
    }
    /**
     * 获取特定列表中的一个元素
     * @param classname 列表class名称
     * @param childclass 被获取的子class名称
     * @param index 所在位置
     * @return
     * @throws UiObjectNotFoundException
     */
    public static UiObject getListChild(String classname, String childclass, int index) throws UiObjectNotFoundException{
    	return Auto.getAllList(classname).getChild(new UiSelector().className(childclass).index(index));
    	
    }
    /**
     * 获取元素列表中指定元素
     * @param classname 列表class名称
     * @param selector 被获取的selector
     * @return
     * @throws UiObjectNotFoundException
     */
    public static UiObject getListChild(String classname, UiSelector selector) throws UiObjectNotFoundException{
    	return Auto.getAllList(classname).getChild(selector);
    }
    
    /**
     * 输入文本
     * @param className
     * @param instance
     * @param words
     * @throws UiObjectNotFoundException
     */
    public static void inPutWords(String className, int instance, String words) throws UiObjectNotFoundException{
    	UiObject retObject = new UiObject(new UiSelector().className(className).instance(instance));
    	retObject.setText(words);
    }
    
    /**
     * 通过class名和text名获取
     * @param className
     * @param text
     * @return
     */
    public static UiObject getViewByClassWithText(String className, String text)
	{
		UiObject uiObject = new UiObject((new UiSelector()).className(className).textContains(text));
		return uiObject;
	}
    
    public static UiObject getViewByClass(String className)
	{
		UiObject uiObject = new UiObject((new UiSelector()).className(className));
		return uiObject;
	}
    
    
    /**
     * 通过text名获取
     * @param text
     * @return
     */
    public static UiObject getViewByText(String text)
	{
		UiObject uiObject = new UiObject((new UiSelector()).textContains(text));
		return uiObject;
	}
    /**
     * 多次点击特定keycode
     * @param keycode keycode
     * @param num 点击次数
     */
    public static void presskeycode(int keycode, int num){
    	for(int i =0; i<num;i++){
			UiDevice.getInstance().pressKeyCode(keycode);
			Auto.threadSleep(20);
		}
    }
    
    
    
    /**
     * 获取目标列表内容
     * @param classname
     * @return
     */
    public static UiCollection getAllList(String classname){
    	UiCollection list = new UiCollection(new UiSelector().className(classname));
    	return list;
    }
    
    public static void dmlog(String tag,String logsr){
    	Log.d(tag, logsr);
    }
	
}