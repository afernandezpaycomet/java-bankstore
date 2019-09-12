/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PAYCOMET;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
/**
 *
 * @author PayCOMET
 */
public class SAAJClient {
    public static SOAPMessage createSoapRequest(SoapObject soapReq) throws Exception{
		 MessageFactory messageFactory = MessageFactory.newInstance();
		 SOAPMessage soapMessage = messageFactory.createMessage();
		 SOAPPart soapPart = soapMessage.getSOAPPart();
    	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
    	         //soapEnvelope.addNamespaceDeclaration("end", "http://endpoint.concretepage.com/");
		 SOAPBody soapBody = soapEnvelope.getBody();
                 HashMap<String, String> map = soapReq.map;
		 SOAPElement soapElement = soapBody.addChildElement(soapReq.getMethodName());
                 Iterator it = map.entrySet().iterator();                  
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        System.out.println(pair.getKey() + " = " + pair.getValue());
                        SOAPElement element1 = soapElement.addChildElement(pair.getKey().toString());
                        element1.addTextNode(pair.getValue().toString());
                        it.remove(); // avoids a ConcurrentModificationException
                    }
		 //SOAPElement element1 = soapElement.addChildElement("arg0");
		 //element1.addTextNode("EveryOne");
		 soapMessage.saveChanges();
		 System.out.println("----------SOAP Request------------");
		 soapMessage.writeTo(System.out);
		 return soapMessage;
	 }
    public static String createSoapResponse(SOAPMessage soapResponse) throws Exception  {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.println("\n----------SOAP Response-----------");
                
                StringWriter writer = new StringWriter();                
                StreamResult result = new StreamResult(writer);
                transformer.transform(sourceContent, result);
                String strResult = writer.toString();		
                return strResult.toString();
	 }
      
}
