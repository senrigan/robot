package com.gdc.srm.util.os;

import java.nio.file.Path;

public interface OperatingSystem {

	public Path getDefaultInstallationPath();

	public String getArchitecture();

	public boolean createLinks(Path installationPath, String client) throws Exception;

}
