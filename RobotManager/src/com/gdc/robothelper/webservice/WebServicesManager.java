package com.gdc.robothelper.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.news.CreatorNewRobotWebService;
import com.gdc.robothelper.webservice.robot.news.Webservice;
import com.gdc.robothelper.webservice.robot.news.WebservicePortType;
import com.gdc.robothelper.webservice.robot.olds.CreatorOldRobotWebService;

public class WebServicesManager {
	
	public static WebservicePortType getNewWebServicesCreator(){
		URL wsdlUrl=getWebServicesCreatorUrl();
		if(checkNewVersionCreator(wsdlUrl.toString())){
			CreatorNewRobotWebService service=new CreatorNewRobotWebService();
			return service.getPort();
		}
		return null;
	}
	
	
	public static com.gdc.robothelper.webservice.robot.olds.WebservicePortType getOLDWebServicesCreator(){
		URL wsdlUrl=getWebServicesCreatorUrl();
		if(checkOldVersionCreator(wsdlUrl.toString())){
			CreatorOldRobotWebService service=new CreatorOldRobotWebService();
			return service.getPort();
		}
		return null;
	}
	
	
	
	public static SisproRobotManagerHelper getWebServicesHelper(){
		URL wsdlUrl = getWebServicesConsult();
		if(checkWebServicesConsult(wsdlUrl.toString())){
			SisproRobotManagerHelperService service= new SisproRobotManagerHelperService(wsdlUrl);
			return service.getSisproRobotManagerHelperPort();
		}
		return null;
	}
	
	public static boolean canConnectToConsultWebservices(){
		URL wsdlUrl = getWebServicesConsult();
		System.out.println("consulting webservicesconsult"+wsdlUrl);
		if(checkWebServicesConsult(wsdlUrl.toString())){
			return true;
		}
		return false;
	}
	
	
	
	
	public static boolean checkNewVersionCreator(String url){
		return CreatorNewRobotWebService.existeConexion(url);
	}
	
	public static boolean checkOldVersionCreator(String url){		
		return CreatorOldRobotWebService.existeConexion(url);
	}
	
	
	public static boolean checkWebServicesConsult(String url){
		return ClientSRMHelperWebService.existeConexion(url);
	}
	
	
	public static URL getWebServicesConsult(){
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", 
					CommandExecutor.REGISTRY_TYPE.REG_SZ.getName());
			if(registry.equals("-1")){
				return SisproRobotManagerHelperService.getUrl();
			}

			URL url=new URL(registry);
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static URL getWebServicesCreatorUrl(){
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", 
					CommandExecutor.REGISTRY_TYPE.REG_SZ.getName());
			if(registry.equals("-1")){
				return Webservice.getUrl();
//				return new URL("https://samyg2.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl");
			}
			URL url=new URL(registry);
			return url;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		getWebServicesCreator();
		String dateServer = getDateServer();
		System.out.println("date servder"+dateServer);
	}
	
	public static String getDateServer(){
		WebservicePortType newWebServicesCreator = getNewWebServicesCreator();
		if(newWebServicesCreator!=null){
			return newWebServicesCreator.getNow();
		}else{
			com.gdc.robothelper.webservice.robot.olds.WebservicePortType oldWebServicesCreator = getOLDWebServicesCreator();
			if(oldWebServicesCreator!=null){
				return oldWebServicesCreator.getNow();
			}
		}
		return null;
		
	}
	public static String getcalculateDateServer() {
		String dateServerString = getDateServer();
		System.out.println("date server"+dateServerString);
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateServer;
		String format="";
		
		
		
		try {
			dateServer = formate.parse(dateServerString);
			Calendar cal=Calendar.getInstance();
			cal.setTime(dateServer);
			cal.add(Calendar.MINUTE, 15);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date calculateTime = cal.getTime();
			format = formate.format(calculateTime);
			System.out.println(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("calculated date"+format);
		return format;
	}

}
