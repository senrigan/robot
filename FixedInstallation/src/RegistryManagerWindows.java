

import java.nio.file.Path;
import java.nio.file.Paths;

import com.gdc.srm.fix.util.CommandExecutor;
import com.gdc.srm.fix.util.Constants;
import com.gdc.srm.fix.util.Installation;
import com.gdc.srm.fix.util.Language;

public class RegistryManagerWindows {
	public static void registryUnistallation(){
		String regPath=Installation.UnistallRegistry+"\\SRM";
		Path installPath = Installation.getInstallaPath();
		Path imagePath = installPath.resolve("images").resolve("gdc_logo.ico");
		Path unistallPath = installPath.resolve("uninstaller.exe");
		
		String unistallerExe=" \""+unistallPath.toString()+"\"";
		System.out.println("******instalando varaiables de registry para panel de control"+unistallerExe);
		try{
			CommandExecutor.addRegistryWindows(regPath, "DisplayIcon", 
					imagePath.toString(), CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "DisplayName", 
					Language.get("registry.unistallcontrol.name"), CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "InstallLocation", 
					installPath.toString(), CommandExecutor.REGISTRY_TYPE.REG_EXPAND_SZ);
			System.out.println("**** registrando unistallaptj"+unistallerExe);
			CommandExecutor.addRegistryWindows(regPath, "UninstallPath",unistallerExe, CommandExecutor.REGISTRY_TYPE.REG_SZ);
			System.out.println("**** registrando unistallaString"+unistallerExe);

			CommandExecutor.addRegistryWindows(regPath, "UninstallString",unistallerExe , CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "NoModify",
					Language.get("registry.unistallcontrol.nomodify") , CommandExecutor.REGISTRY_TYPE.REG_DWORD);
			CommandExecutor.addRegistryWindows(regPath, "NoRepair", Language.get("registry.unistallcontrol.norepair"), CommandExecutor.REGISTRY_TYPE.REG_DWORD);
			
			CommandExecutor.addRegistryWindows(regPath, "Publisher", Language.get("registry.unistallcontrol.publisher"),
					CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			
			CommandExecutor.addRegistryWindows(regPath, "VersionMajor",
					Language.get("registry.unistallcontrol.versionmajor"), CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "VersionMinor",
					Language.get("registry.unistallcontrol.versionminor"), CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
//		Language.load();
//		RegistryManagerWindows.registryUnistallation();
		String regPath=Installation.UnistallRegistry+"\\SRM";
		String ubicationRegist;
		Path installPath=null;
		try {
			ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
			installPath = Paths.get(ubicationRegist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Path imagePath = installPath.resolve("images").resolve("gdc_logo.ico");
		Path unistallPath = installPath.resolve("uninstaller.exe");
		
		String unistallerExe=""+unistallPath.toString()+"";
		System.out.println("******instalando varaiables de registry para panel de control"+unistallerExe);
		try{
			String addRegistryWindows = CommandExecutor.addRegistryWindows(regPath, "DisplayIcon", 
					imagePath.toString(), CommandExecutor.REGISTRY_TYPE.REG_SZ);
			System.out.println("comman"+addRegistryWindows);
			CommandExecutor.addRegistryWindows(regPath, "DisplayName", 
					"SRM", CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "InstallLocation", 
					installPath.toString(), CommandExecutor.REGISTRY_TYPE.REG_EXPAND_SZ);
			System.out.println("**** registrando unistallaptj"+unistallerExe);
			CommandExecutor.addRegistryWindows(regPath, "UninstallPath",unistallerExe, CommandExecutor.REGISTRY_TYPE.REG_SZ);
			System.out.println("**** registrando unistallaString"+unistallerExe);

			CommandExecutor.addRegistryWindows(regPath, "UninstallString",unistallerExe , CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "NoModify",
					"1" , CommandExecutor.REGISTRY_TYPE.REG_DWORD);
			CommandExecutor.addRegistryWindows(regPath, "NoRepair", "no", CommandExecutor.REGISTRY_TYPE.REG_DWORD);
			
			CommandExecutor.addRegistryWindows(regPath, "Publisher", "gdc",
					CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			
			CommandExecutor.addRegistryWindows(regPath, "VersionMajor",
					"b", CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
			CommandExecutor.addRegistryWindows(regPath, "VersionMinor",
					"0", CommandExecutor.REGISTRY_TYPE.REG_SZ);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
}
