package com.gdc.robothelper.webservice.robot.news;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.registry.CommandExecutor;



public class CreatorNewRobotWebService {
	private static final Logger LOGGER=Logger.getLogger(RobotManager.class.toString());

	private static int generaNumeroAleatorio(int minimo, int maximo){
        
        int num=(int)Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }
	
	
	public static WebservicePortType getPort(){
		URL webServicesCreator = getWebServicesCreator();
		Webservice service;
		System.out.println("+++Consultando Webservices URL"+webServicesCreator);
		if(webServicesCreator!=null){
			service=new Webservice(webServicesCreator);
		}else{
			service= new Webservice();
		}
		return service.getWebservicePort();
	}
	
	public static URL getWebServicesCreator(){
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", 
					CommandExecutor.REGISTRY_TYPE.REG_SZ.getName());
			if(registry.equals("-1")){
				return null;
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
	public static String getIdRobot(String nameApp,String status,String location,
				String idApp, String retries ,String period,String startTime,String flujos){
		String robotID="-1";
		try{
			System.out.println("\n Obteniendo WebService Lllego un robot  nombre"+nameApp+"\n status"+status+
					"\nlocation"+location+" \nidapp"+idApp+"\nretires"+retries+ "\nperiod"+ period);
			

				fixUntrustCertificate();

//				int connectionTimeOutInMs = 5000;
				
//				Map<String, Object> context = ((BindingProvider)service).getRequestContext();
//				//Set timeout params
//				context.put("com.sun.xml.internal.ws.connect.timeout", connectionTimeOutInMs);
//				context.put("com.sun.xml.internal.ws.request.timeout", connectionTimeOutInMs);
//				context.put("com.sun.xml.ws.request.timeout", connectionTimeOutInMs);
//				context.put("com.sun.xml.ws.connect.timeout", connectionTimeOutInMs);
				WebservicePortType port = getPort();
								
	//			//TODO LEER ARCHIVO APP.XML / APPLICATION Y SACAR EL NOMBRE DE APLICAION Y ID
				//-1 id de aplicacion 
				
				try{
					if(flujos==null||flujos.equals("")){
						System.out.println("creadno rbotsimple");
//						 robotID= port.createRobot(nameApp,location , idApp,status, period, retries,startTime);
					}else{
						 robotID= port.createRobot(nameApp,location , idApp,status, period, retries,startTime,flujos);

					}
//					 robotID= port.createRobot(nameApp,location , idApp,status, period, retries,startTime,flujos);
					System.out.println(robotID);
	//				return robotID;
				}catch( WebServiceException e){
					System.out.println("retry .. webservices consult");

					robotID = port.createRobot(nameApp,location , idApp,status, period,retries,startTime,flujos);
					System.out.println(robotID);
					
				}
			}catch(WebServiceException ex){
//			return robotID;
				ex.printStackTrace();
				return robotID;
			}catch(Exception ex ){
				ex.printStackTrace();
			}
		return robotID;
		
		
		
//		int generaNumeroAleatorio = generaNumeroAleatorio(-1, -10000);
//		return ""+generaNumeroAleatorio;

	}
	
	public static boolean deleteRobot(long idRobot){
		try {
			fixUntrustCertificate();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		WebservicePortType port=getPort();
		String deleteRobot = port.deleteRobot(""+idRobot);
		LOGGER.info("deleting robotid"+idRobot +"webservices respose : "+ deleteRobot);
		
		
		return (deleteRobot.equals("1")?true:false);
		
	}
	
	public static String getDateServer(){
		WebservicePortType port = getPort();
		String now = port.getNow();
		return now;
	}
	
	public static void getElement(){
		WebservicePortType port=getPort();
//		String deleteRobot = port.deleteRobot(""+idRobot);
		System.out.println("element Robot");
		System.out.println(port.getDataRobot("453"));
	}
	
	
	public static void fixUntrustCertificate() throws KeyManagementException, NoSuchAlgorithmException{


        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
               
            
				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					
				}
				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					
				}

            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier allHostsValid = new HostnameVerifier() {
           

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
        };

        // set the  allTrusting verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
}
	public static void main(String[] args) {
		URL webServicesCreator = CreatorNewRobotWebService.getWebServicesCreator();
		System.out.println(webServicesCreator);
		boolean existeConexion = CreatorNewRobotWebService.existeConexion(webServicesCreator.toString());
		System.out.println("numero de id"+existeConexion);
//		String dateServer = CreatorRobotWebService.getDateServer();
//		System.out.println(dateServer);
//		SimpleDateFormat dateForm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date;
//		try {
//			date = dateForm.parse(dateServer);
//			System.out.println("date object"+date);
//			System.out.println(dateForm.format(date));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
	
	}
	
	
	public static Object getWebService(String wsdlLocation) {
	    try {
	    	fixUntrustCertificate();
	        QName qname = new QName("urn:webservice", "webservice");
	        URL url = new URL(wsdlLocation);
	        Webservice service = new Webservice(url, qname);
	        return null;
	    } catch (MalformedURLException ex) {
	    	ex.printStackTrace();
	        return ex;
	    } catch (WebServiceException ws) {
	        ws.printStackTrace();
	    	return ws;
	    }catch(Exception ex){
	    	return new WebServiceException();
	    }
	}
	 
	public static boolean existeConexion(String url) {
	    int i = 0;
	    boolean respuesta = false;
	    while (i < 3) {
	        Object obj = getWebService(url);
	        if (obj  == null) {
	            return true;
	        }
	        if ((obj  instanceof WebServiceException)) {
	            respuesta = false;
	        }
	        i++;
	    }
	    return respuesta;
	}
	
	
	
	
	
	
}
