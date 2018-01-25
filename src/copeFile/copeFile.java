package copeFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class copeFile {
	
	 public static void main(String[] args) throws IOException{
		 System.out.println("输入源文件夹");
		 Scanner sc = new Scanner(System.in);
		 String src = sc.nextLine();
		 System.out.println("输入目标文件夹");
		 String des = sc.nextLine();
		 System.out.println("输入文件类型");
		 String format = sc.nextLine();
		 copeFiles(src,format, des);
		 sc.close();
		 System.out.println("完成");
	  }
	  
	 /**
	  * 
	  * @param fromFile 复制的源文件
	  * @param toFile 复制的目标文件
	  * @throws IOException
	  */
	public static void copyFile01(File fromFile,File toFile) throws IOException{
	    FileInputStream ins = new FileInputStream(fromFile);
	    FileOutputStream out = new FileOutputStream(toFile);
	    byte[] b = new byte[1024];
	    int n=0;
	    while((n=ins.read(b))!=-1){
	        out.write(b, 0, n);
	    }
	    
	    ins.close();
	    out.close();
	}
	 
    /**
     * 
     * @param src 复制的源文件夹路径
     * @param format 文件格式
     * @param des 复制的目标文件夹
     * @throws IOException
     */
    public static void copeFiles(String src,String format,String des) throws IOException{   
        // 获得指定文件对象  
        File file = new File(src);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();  
        
        for(int i=0;i<array.length;i++){
        	if(array[i].isDirectory()){
        		copeFiles(array[i].getPath(),format, 
        				des+array[i].getPath().substring(src.length(),array[i].getPath().length()));
        	}else{
        		//String name = array[i].getName();
            	//System.out.println(name);
        		//获取文件自根目录以后的目录
            	String name = array[i].getPath().substring(src.length(),array[i].getPath().length());
            	//获取文件格式，不包括点
            	String fileType = name.substring(name.lastIndexOf(".", name.length()));
            	if(fileType.toLowerCase().equals(("."+format).toLowerCase())){
            		String from = array[i].getPath();
            		// System.out.println(from);
            		 String to = des+"\\"+name;
            		 if(!new File(to).exists()){
            			 new File(des).mkdirs();
            		 }
            		 //System.out.println(to);
            		File srcfile = new File(from);
            		File desfile = new File(to);
            		 copyFile01(srcfile,desfile);
            		
            	}
        	}
        	
        }
        
        
    }  
  
}
