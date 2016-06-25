package com.gdc.srm.fix.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExtracterJar {
	private static String tempFolder ="";
	public static void extractJar(String jarFile, File directory)throws IOException{
		 JarInputStream jarInput = new JarInputStream(new FileInputStream(jarFile));
		 JarEntry jarEntry=null;
	     while((jarEntry=jarInput.getNextJarEntry())!=null){
	    	 File file=new File(directory,jarEntry.getName());
	    	 if (jarEntry.isDirectory()){
	        	 if (!file.exists())
	                 file.mkdirs();
	         }else{ 
	        	 File dir = new File(file.getParent());
	             if (!dir.exists())
	            	 dir.mkdirs();
	             byte[] bytes = new byte[1024];
	             InputStream inputStream   = new BufferedInputStream(jarInput);
	             FileOutputStream fileOutputStream = new FileOutputStream(file);
	             int read = -1;
	             while ((read = inputStream.read(bytes)) != -1) {
			            fileOutputStream.write(bytes, 0, read);
			        }
	             fileOutputStream.close();             
	         }
	     }
    }
	public static void extractJar(Path toDirectory, HashMap<String,JarEntry> filesToSkip){
		JarFile file = null;
		ZipFile zip=null;
        try {
            file = new JarFile(Environment.getSelfJar().toFile());
            Enumeration<JarEntry> entries = file.entries();
            zip=new ZipFile(Environment.getSelfJar().toFile());
           
            Path tempFolderPath = toDirectory;
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                Path tempFile=tempFolderPath.resolve(name);
                File fileTemp = tempFile.toFile();
                if(!filesToSkip.containsKey(fileTemp.getName())){
                	
                	try{
                   	 if(entry.isDirectory() ){
                        	if(Files.exists(tempFile)){
                        		Files.delete(tempFile);
                        	}
                        	Files.createDirectories(tempFile);
                        }else{
                        	if(Files.exists(tempFile)){
                        		Files.delete(tempFile);
                        	}
                        }
                        Files.createFile(tempFile);
                        InputStream in = zip.getInputStream(entry);
                        FileOutputStream out = new FileOutputStream(tempFile.toFile());
                        byte[] buffer = new byte[4096];
                        int read = in.read(buffer);
                        while (read != -1) {
                            out.write(buffer, 0, read);
                            read = in.read(buffer);
                        }
                        out.close();
                        in.close();
                   }catch(IOException ex){
//                   	ex.printStackTrace();
                   }
                }else{
                	System.out.println("metiendo a el hasmap "+fileTemp.getName()+" "+entry.getName());
                	filesToSkip.put(fileTemp.getName(), entry);
                }
                
               
               
                

            }
            zip.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
	}
	public static void extractJar(String jarFile, File directory,ArrayList<String> excludeDirectory )throws IOException{
		 JarInputStream jarInput = new JarInputStream(new FileInputStream(jarFile));
		 JarEntry jarEntry=null;
	     while((jarEntry=jarInput.getNextJarEntry())!=null){
	    	
	    	 File file=new File(directory,jarEntry.getName());
	         if(!excludeDirectory.contains(file.getName())){
	        	 if (jarEntry.isDirectory()){
		        	 if (!file.exists())
		                 file.mkdirs();
		         }else{ 
		        	 File dir = new File(file.getParent());
		             if (!dir.exists())
		            	 dir.mkdirs();
		             byte[] bytes = new byte[1024];
		             InputStream inputStream   = new BufferedInputStream(jarInput);
		             FileOutputStream fileOutputStream = new FileOutputStream(file);
		             int read = -1;
		             while ((read = inputStream.read(bytes)) != -1) {
				            fileOutputStream.write(bytes, 0, read);
				        }
		             fileOutputStream.close();             
		         }
	         }
	    
	     }
	}
	public static String getTempPath(){
		return tempFolder;
	}
	public static boolean createDir(String path,String tempDirName){
		String directoryPath=path;
		if(directoryPath.endsWith("/")){
			directoryPath+=tempDirName;
		}else{
			directoryPath+="/"+tempDirName;
		}
		
		File file=new File(directoryPath);
		if(!file.exists()){
			if(file.mkdir()){
				tempFolder=file.getPath();
				return true;
			}
		}else{
			if(file.delete()){
				if(file.mkdirs()){
					tempFolder=file.getPath();
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		ExtracterJar.extractJar("C:\\Users\\senrigan\\AppData\\Local\\Temp\\gdcIntaller\\inMonitor\\bot-1.0.jar",new File("C:\\Users\\senrigan\\AppData\\Local\\Temp\\gdcIntaller\\inMonitor\\test"));
	}
}
