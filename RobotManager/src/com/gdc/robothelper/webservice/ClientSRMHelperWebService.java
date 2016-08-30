package com.gdc.robothelper.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.registry.CommandExecutor;


public class ClientSRMHelperWebService {

	
	private static SisproRobotManagerHelper getPort(){
		URL webServicesConsult = getWebServicesConsult();
		SisproRobotManagerHelperService service;
		System.out.println("+++Consultando Webservices URL"+webServicesConsult);

		if (webServicesConsult!=null) {
			service= new SisproRobotManagerHelperService(webServicesConsult);
		}else{
			service=new SisproRobotManagerHelperService();
		}
		SisproRobotManagerHelper port= service.getSisproRobotManagerHelperPort();
		return port;
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
	
	public static String getAppName(){
		try{
			fixUntrustCertificate();

			String applications = getPort().getApplications();
			if(!applications.equals("-1")){
				return applications;
			}else{
				return null;
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public static String getFlujosByIdApp(long idApp){
		try{
			fixUntrustCertificate();

			String flowsByApplicationId = getPort().getFlowsByApplicationId(idApp);
			if(!flowsByApplicationId.equals("-1")){
				return flowsByApplicationId;
			}else{
				return null;
			}
		}catch(Exception ex){
			
		}
		return null;
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
//		boolean existeConexion = ClientWebService.existeConexion("http://samyg2.sispro.mx:8080/helper/SRMHelper?wsdl");
		System.out.println(ClientSRMHelperWebService.getWebServicesConsult().toString());
		boolean existeConexion = ClientSRMHelperWebService.existeConexion(ClientSRMHelperWebService.getWebServicesConsult().toString());
		System.out.println(existeConexion);
	}
	public static Object getWebService(String wsdlLocation) {
	    try {
	    	fixUntrustCertificate();
	        QName qname = new QName("http://webservice.samyg.gdc.com/", "SisproRobotManagerHelperService");
	        URL url = new URL(wsdlLocation);
	        SisproRobotManagerHelperService service = new SisproRobotManagerHelperService(url, qname);
	        return null;
	    } catch (MalformedURLException ex) {
	        return ex;
	    } catch (WebServiceException ws) {
	        return ws;
	    } catch (KeyManagementException e) {
			e.printStackTrace();
			return e;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return e;
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
	        	((WebServiceException) obj).printStackTrace();
	            respuesta = false;
	        }
	        i++;
	    }
	    return respuesta;
	}
	
	public static void fixUntrustCertificate() throws KeyManagementException, NoSuchAlgorithmException{


        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
               
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
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
					// TODO Auto-generated method stub
					
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
	


}
