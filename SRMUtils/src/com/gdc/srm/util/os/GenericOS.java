package com.gdc.srm.util.os;

import java.nio.file.Path;

import com.gdc.srm.register.util.Environment;


public class GenericOS implements OperatingSystem {

	@Override
	public Path getDefaultInstallationPath() {
		return Environment.getUserHome();
	}

	@Override
	public String getArchitecture() {
	    return System.getProperty("os.arch");
	}

	@Override
	public boolean createLinks(Path installationPath, String client) {
		throw new UnsupportedOperationException();
	}

}
