package com.smw.wsc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0-milestone2
 * 2016-06-06T16:41:21.869+08:00
 * Generated source version: 3.0.0-milestone2
 * 
 */
@WebServiceClient(name = "sales", 
                  wsdlLocation = "http://192.168.1.118/TTPOS/sales.asmx?wsdl",
                  targetNamespace = "http://tempurl.org") 
public class Sales extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempurl.org", "sales");
    public final static QName SalesSoap12 = new QName("http://tempurl.org", "salesSoap12");
    public final static QName SalesSoap = new QName("http://tempurl.org", "salesSoap");
    static {
        URL url = null;
        try {
            url = new URL("http://192.168.1.118/TTPOS/sales.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Sales.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://192.168.1.118/TTPOS/sales.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Sales(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Sales(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sales() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns SalesSoap
     */
    @WebEndpoint(name = "salesSoap12")
    public SalesSoap getSalesSoap12() {
        return super.getPort(SalesSoap12, SalesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SalesSoap
     */
    @WebEndpoint(name = "salesSoap12")
    public SalesSoap getSalesSoap12(WebServiceFeature... features) {
        return super.getPort(SalesSoap12, SalesSoap.class, features);
    }
    /**
     *
     * @return
     *     returns SalesSoap
     */
    @WebEndpoint(name = "salesSoap")
    public SalesSoap getSalesSoap() {
        return super.getPort(SalesSoap, SalesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SalesSoap
     */
    @WebEndpoint(name = "salesSoap")
    public SalesSoap getSalesSoap(WebServiceFeature... features) {
        return super.getPort(SalesSoap, SalesSoap.class, features);
    }

}
