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

import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.registry.CommandExecutor;

public class ClientWebService {

	
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
	
	
	private static URL getWebServicesConsult(){
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", 
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
		while(true){
//		
		String flujosByIdApp = getFlujosByIdApp(2);
		System.out.println(flujosByIdApp);
		System.out.println(ClientWebService.getAppName());
		 Thread.sleep(60000);
		}	
//		URL url;
//		try {
//			url = new URL(SisproRobotManagerHelperService.getUrl().toString());
//			System.out.println(url);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
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
