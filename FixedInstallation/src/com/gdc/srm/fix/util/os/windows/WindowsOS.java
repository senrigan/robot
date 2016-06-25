package com.gdc.srm.fix.util.os.windows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.gdc.srm.fix.util.CommandExecutor;
import com.gdc.srm.fix.util.Installation;
import com.gdc.srm.fix.util.Language;
import com.gdc.srm.fix.util.os.OperatingSystem;

import mslinks.ShellLink;

public class WindowsOS implements OperatingSystem {

    @Override
    public Path getDefaultInstallationPath() {
        return Paths.get(System.getenv("ProgramFiles"));
    }

    @Override
    public String getArchitecture() {
        return System.getenv("PROCESSOR_ARCHITECTURE");
    }

    public void createShortcut(String src, String workingDir, String cmdArgs, String iconLocation, String dest)
            throws IOException {
        ShellLink.createLink(src).setWorkingDir(workingDir).setCMDArgs(cmdArgs).setIconLocation(iconLocation)
            .saveTo(dest);
    }
    private void addRegistryCommnProgramsFolderApp(Path appFolder){
    	try {
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "commandFolder", appFolder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public boolean createLinks(Path installationPath, String client) {
        String KEY = "HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders";

        // Getting Common Program
        try {
            String commonProgramsStr = WindowsUtil.readRegistry(KEY, "Common Programs", "REG_SZ");
            Path destDir = Paths.get(commonProgramsStr, Language.get("application.name"));
            addRegistryCommnProgramsFolderApp(destDir);
            if (Files.notExists(destDir)) {
                Files.createDirectory(destDir);
                Installation.addFile(destDir.toString());
            }

            Path newClient = destDir.resolve("SysproRobotManager.lnk");
            if (Files.notExists(newClient)) {
                try {
                    this.createShortcut(installationPath.resolve("SRM.exe").toString(),
                        installationPath.toString(), "SysproRobotManager", Installation.getInstallaPath().resolve("images")
                            .resolve("gdc_logo.ico").toString(), newClient.toString());
                    Installation.addFile(newClient.toString());
                } catch (Exception e) {
                    e.printStackTrace();
//                    Installer.getInstance().addMessage(
//                        "Ocurrio un error al crear el acceso directo para crear un nuevo cliente.");
                }
            }



            Path uninstall = destDir.resolve("Uninstaller.lnk");
            if (Files.notExists(uninstall)) {
                try {
                    this.createShortcut(installationPath.resolve("uninstaller.exe").toString(),
                        installationPath.toString(), "",
                        Installation.getInstallaPath().resolve("images").resolve("unistaller.ico").toString(),
                        uninstall.toString());
                    Installation.addFile(uninstall.toString());
                } catch (Exception e) {

//                    Installer.getInstance().addMessage("Ocurrio un error al crear el acceso directo al desinstalador.");
                }
            }

        } catch (Exception e) {
//            Installer.getInstance().addMessage("Ocurrio un error al crear los iconos en el Menu de Inicio.");
        }
        return false;
    }

}
