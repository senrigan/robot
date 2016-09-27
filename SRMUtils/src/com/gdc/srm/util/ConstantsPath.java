package com.gdc.srm.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gdc.srm.register.util.Environment;


public class ConstantsPath {
	
	public static Path getTempPath(){
		String tempFolder = Environment.getTempPath().toString();
		String separator=File.separator;
		if(!tempFolder.endsWith(separator)){
			tempFolder+=separator;
		}
		tempFolder+="gdcIntaller"+separator;
		return Paths.get(tempFolder);
	}

}
