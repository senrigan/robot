package com.gdc.srm.fix.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.jar.Attributes;
import java.util.regex.Matcher;


public class AppZip
{
    List<String> fileList;
    private static String OUTPUT_ZIP_FILE;
    private static String SOURCE_FOLDER ;
    private static int BUFFER_SIZE = 10240;

	
    public AppZip(String sorceFolder,String destination , String nameFile){
    	fileList = new ArrayList<String>();
    	SOURCE_FOLDER=sorceFolder;
    	generateFileList(new File(SOURCE_FOLDER));
    	String separator=File.separator;
    	if(!destination.endsWith(separator)){
    		destination+=separator;
    	}
    	OUTPUT_ZIP_FILE=destination+nameFile;
    }
	
  
    public void zipIt(){
    	zipIt(OUTPUT_ZIP_FILE);
//    	createJarArchive(OUTPUT_ZIP_FILE);
    }
    
    
    public void jarIt(ManifestInfo manifest){
    	Manifest createManifest = createManifest(manifest);
    	createJarArchive(OUTPUT_ZIP_FILE,createManifest);
    }
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile){
     byte[] buffer = new byte[1024];
    	
     try{
    		
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
    		
    		
    	for(String file : this.fileList){
    			
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
               
        	FileInputStream in = 
                       new FileInputStream(SOURCE_FOLDER + File.separator + file);
       	   
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
               
        	in.close();
    	}
    		
    	zos.closeEntry();
    	//remember close it
    	zos.close();
          
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
    
    private Manifest createManifest(ManifestInfo manifestInfo){
    	Manifest manifest=new Manifest();
        Attributes global = manifest.getMainAttributes();
        global.put(Attributes.Name.MANIFEST_VERSION,manifestInfo.getManifestVersion());
        global.put(new Attributes.Name("Created-By"),manifestInfo.getCreateBy());
        global.put(Attributes.Name.MAIN_CLASS, manifestInfo.getMainClass());
        global.put(Attributes.Name.CLASS_PATH, manifestInfo.getClassPath());
        return manifest;
    }
    protected void createJarArchive(String zipFile, Manifest manifest) {
        try {
          byte buffer[] = new byte[BUFFER_SIZE];
          // Open archive file
          FileOutputStream stream = new FileOutputStream(zipFile);
          JarOutputStream out = new JarOutputStream(stream,manifest);
          for(String file : this.fileList){
        	  File nodeFile=new File(file);
        	  if (nodeFile == null || !nodeFile.exists()
                      || nodeFile.isDirectory())
                    continue; // Just in case...
        	  String pathFolder=new File(SOURCE_FOLDER).toString();
//        	  String filePath=nodeFile.toString().replaceAll(pathFolder+File.separator, "");
              String filePath=Paths.get(pathFolder).relativize(Paths.get(nodeFile.toURI())).toString();
              String macher = Matcher.quoteReplacement("\\");
              filePath=filePath.replaceAll(macher, "/");
        	  // Add archive entry
              JarEntry jarAdd = new JarEntry(filePath);
              jarAdd.setTime(nodeFile.lastModified());
              if(!jarAdd.getName().contains("MANIFEST.MF")){
            	  out.putNextEntry(jarAdd);

                  // Write file to archive
                  FileInputStream in = new FileInputStream(nodeFile);
                  while (true) {
                    int nRead = in.read(buffer, 0, buffer.length);
                    if (nRead <= 0)
                      break;
                    out.write(buffer, 0, nRead);
                  }
                  in.close();
              }
              
        	  
          }
          out.close();
          stream.close();
        } catch (Exception ex) {
          ex.printStackTrace();;
        }
      }
    
    
    /**
     * Traverse a directory and get all files,
     * and add the file into fileList  
     * @param node file or directory
     */
    public void generateFileList(File node){

    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}
		
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}
 
    }

    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file){
//    	return file.substring(SOURCE_FOLDER.length()+1, file.length());
    	return file.toString();
    }
    
    public static void writeInJar(File file, JarOutputStream out) throws IOException{
    	  byte buffer[] = new byte[BUFFER_SIZE];
    	 // Write file to archive
        FileInputStream in = new FileInputStream(file);
        while (true) {
          int nRead = in.read(buffer, 0, buffer.length);
          if (nRead <= 0)
            break;
          out.write(buffer, 0, nRead);
        }
        in.close();
    }
   public static void main(String[] args) {
	AppZip zip=new AppZip("/home/senrigan/Documents/bot","/home/senrigan/Documents/", "bot.jar");
	ManifestInfo info=new ManifestInfo();
	info.setManifestVersion("1.0");
	info.setMainClass("com.gdc.robot.Main");
	info.setCreateBy("1.7.0_76-b13 (Oracle Corporation)");
//	String classPath="lib/xstream-1.4.8.jar lib/xmlpull-1.1.3.1.jar lib/xpp3_min-1.1.4c.jar lib/log4j-1.2.17.jar lib/jacob-1.14.3.jar lib/jettison-1.3.7.jar lib/stax-api-1.0.1.jar";

//	info.setClassPath(classPath);
	zip.jarIt(info);
   }
}