/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PAYTPV;

import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.*;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import sun.net.www.http.HttpClient;

/**
 *
 * @author PayTPV
 */
public class Java_PAYTPV_ExampleCall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Sample Calls of Service 
            
            PAYTPV_BankStoreGatewayService service = new PAYTPV_BankStoreGatewayService("1br6407g", "4473", "fnz5kc7pxw0y4hb963qt", "", "1.2.3.4");
            //service.add_user("4539232076648253", "0517", "123", "card holder");

            service.info_user("35649551", "VG1kNU5YWXBaVDF");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
