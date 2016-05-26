
package com.gdc.robothelper.webservice.robot;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "webservice", targetNamespace = "urn:webservice", wsdlLocation = "https://samyg2.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl")
//@WebServiceClient(name = "webservice", targetNamespace = "urn:webservice", wsdlLocation = "https://samyg2test.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl")
//@WebServiceClient(name = "webservice", targetNamespace = "urn:webservice", wsdlLocation = "https://samyg2pp.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl")


public class Webservice
    extends Service
{

    private final static URL WEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICE_EXCEPTION;
    private final static QName WEBSERVICE_QNAME = new QName("urn:webservice", "webservice");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://samyg2.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl");
//        	 url = new URL("https://samyg2test.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl");
//            url = new URL("https://samyg2pp.sispro.mx/wsendusersamyg2/wssamyg/status/soapFlujosV2.php?wsdl");

        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICE_WSDL_LOCATION = url;
        WEBSERVICE_EXCEPTION = e;
    }

    public Webservice() {
        super(__getWsdlLocation(), WEBSERVICE_QNAME);
    }

    public Webservice(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICE_QNAME, features);
    }

    public Webservice(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICE_QNAME);
    }

    public Webservice(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICE_QNAME, features);
    }

    public Webservice(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Webservice(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebservicePortType
     */
    @WebEndpoint(name = "webservicePort")
    public WebservicePortType getWebservicePort() {
        return super.getPort(new QName("urn:webservice", "webservicePort"), WebservicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebservicePortType
     */
    @WebEndpoint(name = "webservicePort")
    public WebservicePortType getWebservicePort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:webservice", "webservicePort"), WebservicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICE_EXCEPTION!= null) {
            throw WEBSERVICE_EXCEPTION;
        }
        return WEBSERVICE_WSDL_LOCATION;
    }
    
    public static URL getUrl(){
    	return WEBSERVICE_WSDL_LOCATION;
    }

}
