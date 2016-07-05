package PAYTPV;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * API de PAYTPV para Java. Métodos BankStore IFRAME/FULLSCREEN/XML/JET
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the 3-clause BSD License.
 *
 * This source file is subject to the 3-clause BSD License that is
 * bundled with this package in the LICENSE file.
 *
 * @package    PAYTPV 
 * @author     PAYTPV
 * @license    BSD License (3-clause)
 * @copyright  (c) 2010-2016, PAYTPV
 * @link       https://www.paytpv.com
 */
/**
 *
 * @author Girish
 */
public class PAYTPV_BankStoreGatewayService {

    public String dS_MERCHANT_MERCHANTSIGNATURE = "";
    private String dS_MERCHANT_MERCHANTCODE;
    private String dS_MERCHANT_TERMINAL;
    private String password;
    private String DS_MERCHANT_JETID;
    private String dS_ORIGINAL_IP;

    private final String endpoint = "https://secure.paytpv.com/gateway/xml-bankstore?wsdl";
    private final String endpointurl = "https://secure.paytpv.com/gateway/ifr-bankstore?";

    public PAYTPV_BankStoreGatewayService(String MERCHANT_MERCHANTCODE, String MERCHANT_TERMINAL, String Password, String MERCHANT_JETID, String IPAddr) {
        this.dS_MERCHANT_MERCHANTCODE = MERCHANT_MERCHANTCODE;
        this.dS_MERCHANT_TERMINAL = MERCHANT_TERMINAL;
        this.password = Password;
        this.DS_MERCHANT_JETID = MERCHANT_JETID;
        this.dS_ORIGINAL_IP = IPAddr;
    }

    /**
    * INTEGRATION XML BANKSTORE ---------------------------------------------- ----->
    */
        
    /**
	* Create a pre-authorization by web service
	* @param dS_IDUSER User ID in PayTPV User ID in PayTPV
	* @param dS_TOKEN_USER user Token in PayTPV user Token in PayTPV
	* @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100 Amount of payment 1 € = 100
	* @param transreference  unique identifier payment unique identifier payment
	* @param DS_MERCHANT_CURRENCY identifier transaction currency identifier transaction currency
	* @param dS_MERCHANT_PRODUCTDESCRIPTION Product Description Description Product Description
	* @param dS_MERCHANT_OWNER Cardholder Cardholder
	* @param dS_MERCHANT_SCORING scoring (optional) value of risk scoring the transaction scoring (optional) value of risk scoring the transaction (optional) value of risk scoring the transaction
	* @return Object A transaction response
	* @version 2.0 06/02/2016
	*/    
    public ServiceResponse create_preauthorization(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String transreference, String DS_MERCHANT_CURRENCY, String dS_MERCHANT_PRODUCTDESCRIPTION, String dS_MERCHANT_OWNER, String dS_MERCHANT_SCORING)
    {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + dS_MERCHANT_TERMINAL + dS_MERCHANT_AMOUNT + transreference + this.password);
        SoapObject soapReq = new SoapObject("create_preauthorization");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", transreference);
        soapReq.addProperty("DS_MERCHANT_CURRENCY", DS_MERCHANT_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_PRODUCTDESCRIPTION", dS_MERCHANT_PRODUCTDESCRIPTION);
        soapReq.addProperty("DS_MERCHANT_OWNER", dS_MERCHANT_OWNER);
        soapReq.addProperty("DS_MERCHANT_SCORING", dS_MERCHANT_SCORING);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @return 
     */
    public ServiceResponse preauthorization_confirm(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_ORDER + dS_MERCHANT_AMOUNT + this.password);
        SoapObject soapReq = new SoapObject("preauthorization_confirm");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @return 
     */
    public ServiceResponse preauthorization_cancel(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_ORDER + dS_MERCHANT_AMOUNT + this.password);

        SoapObject soapReq = new SoapObject("preauthorization_cancel");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @return 
     */
    public ServiceResponse deferred_preauthorization_confirm(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_ORDER + dS_MERCHANT_AMOUNT + this.password);

        SoapObject soapReq = new SoapObject("deferred_preauthorization_confirm");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @return 
     */
    public ServiceResponse deferred_preauthorization_cancel(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER) {
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_ORDER + dS_MERCHANT_AMOUNT + this.password);
        ServiceResponse result = new ServiceResponse();
        SoapObject soapReq = new SoapObject("deferred_preauthorization_cancel");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }
    
    /**
     * 
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @param dS_MERCHANT_IDENTIFIER Original reference card stored in the old system
     * @param DS_MERCHANT_CURRENCY identifier transaction currency identifier transaction currency
     * @param dS_MERCHANT_PRODUCTDESCRIPTION Product Description Product Description
     * @return 
     */
    public ServiceResponse execute_purchase_rtoken(String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER, String dS_MERCHANT_IDENTIFIER, String DS_MERCHANT_CURRENCY, String dS_MERCHANT_PRODUCTDESCRIPTION) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_AMOUNT + dS_MERCHANT_ORDER + dS_MERCHANT_IDENTIFIER + this.password);
        SoapObject soapReq = new SoapObject("execute_purchase_rtoken");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_IDENTIFIER", dS_MERCHANT_IDENTIFIER);
        soapReq.addProperty("DS_MERCHANT_CURRENCY", DS_MERCHANT_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_MERCHANT_PRODUCTDESCRIPTION", dS_MERCHANT_PRODUCTDESCRIPTION);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_MERCHANT_PAN card number without spaces or dashes bread card number without spaces or dashes
     * @param dS_MERCHANT_EXPIRYDATE expiry date of the card, expressed as "MMYY" (two - digit month and year in two digits) expiry date of the card, expressed as "MMYY" (two - digit month and year in two digits)
     * @param dS_MERCHANT_CVV2 Card code Card code
     * @param dS_MERCHANT_CARDHOLDERNAME Cardholder Card holder name
     * @return
     * @throws Exception 
     */
    public ServiceResponse add_user(String dS_MERCHANT_PAN, String dS_MERCHANT_EXPIRYDATE, String dS_MERCHANT_CVV2, String dS_MERCHANT_CARDHOLDERNAME) throws Exception {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_MERCHANT_PAN + dS_MERCHANT_CVV2 + this.dS_MERCHANT_TERMINAL + this.password);
        SoapObject soapReq = new SoapObject("add_user");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_MERCHANT_PAN", dS_MERCHANT_PAN);
        soapReq.addProperty("DS_MERCHANT_EXPIRYDATE", dS_MERCHANT_EXPIRYDATE);
        soapReq.addProperty("DS_MERCHANT_CVV2", dS_MERCHANT_CVV2);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_CARDHOLDERNAME", dS_MERCHANT_CARDHOLDERNAME);

        return callSoap(result, soapReq);

    }

    /**
     * 
     * @param dS_MERCHANT_JETTOKEN temporary user Token in PayTPV
     * @return
     */
    public ServiceResponse add_user_token(String dS_MERCHANT_JETTOKEN) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_MERCHANT_JETTOKEN + DS_MERCHANT_JETID + this.dS_MERCHANT_TERMINAL + this.password);
        SoapObject soapReq = new SoapObject("add_user_token");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_MERCHANT_JETTOKEN", dS_MERCHANT_JETTOKEN);
        soapReq.addProperty("DS_MERCHANT_JETID", DS_MERCHANT_JETID);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @return 
     */
    public ServiceResponse info_user(String dS_IDUSER, String dS_TOKEN_USER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + this.password);

        SoapObject soapReq = new SoapObject("info_user");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @return 
     */
    public ServiceResponse remove_user(String dS_IDUSER, String dS_TOKEN_USER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + this.password);
        SoapObject soapReq = new SoapObject("remove_user");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @param DS_MERCHANT_CURRENCY identifier transaction currency 
     * @param dS_MERCHANT_PRODUCTDESCRIPTION Product Description Product Description
     * @param dS_MERCHANT_OWNER Cardholder
     * @param dS_MERCHANT_SCORING scoring (optional) value of risk scoring the transaction scoring (optional) value of risk scoring the transaction
     * @return 
     */
    public ServiceResponse execute_purchase(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER, String DS_MERCHANT_CURRENCY, String dS_MERCHANT_PRODUCTDESCRIPTION, String dS_MERCHANT_OWNER, String dS_MERCHANT_SCORING) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_AMOUNT + dS_MERCHANT_ORDER + this.password);

        SoapObject soapReq = new SoapObject("execute_purchase");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_CURRENCY", DS_MERCHANT_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_PRODUCTDESCRIPTION", dS_MERCHANT_PRODUCTDESCRIPTION);
        soapReq.addProperty("DS_MERCHANT_OWNER", dS_MERCHANT_OWNER);
        soapReq.addProperty("DS_MERCHANT_SCORING", dS_MERCHANT_SCORING);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @param DS_MERCHANT_CURRENCY identifier transaction currency
     * @param dS_MERCHANT_PRODUCTDESCRIPTION Product Description
     * @param dS_MERCHANT_OWNER Cardholder
     * @return 
     */
    public ServiceResponse execute_purchase_dcc(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_AMOUNT, String dS_MERCHANT_ORDER, String DS_MERCHANT_CURRENCY, String dS_MERCHANT_PRODUCTDESCRIPTION, String dS_MERCHANT_OWNER) {

        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_AMOUNT + dS_MERCHANT_ORDER + this.password);

        ServiceResponse result = new ServiceResponse();
        SoapObject soapReq = new SoapObject("execute_purchase_dcc");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_PRODUCTDESCRIPTION", dS_MERCHANT_PRODUCTDESCRIPTION);
        soapReq.addProperty("DS_MERCHANT_OWNER", dS_MERCHANT_OWNER);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @param dS_MERCHANT_DCC_CURRENCY
     * @param dS_MERCHANT_DCC_SESSION
     * @return 
     */
    public ServiceResponse confirm_purchase_dcc(String dS_MERCHANT_ORDER, String dS_MERCHANT_DCC_CURRENCY, String dS_MERCHANT_DCC_SESSION) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_ORDER + dS_MERCHANT_DCC_CURRENCY + dS_MERCHANT_DCC_SESSION);

        SoapObject soapReq = new SoapObject("confirm_purchase_dcc");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_DCC_CURRENCY", dS_MERCHANT_DCC_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_DCC_SESSION", dS_MERCHANT_DCC_SESSION);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_MERCHANT_ORDER unique identifier payment
     * @param DS_MERCHANT_CURRENCY identifier transaction currency
     * @param dS_MERCHANT_AUTHCODE  AuthCode of the original operation to return
     * @param dS_MERCHANT_AMOUNT Amount of payment 1 € = 100
     * @return 
     */
    public ServiceResponse execute_refund(String dS_IDUSER, String dS_TOKEN_USER, String dS_MERCHANT_ORDER, String DS_MERCHANT_CURRENCY, String dS_MERCHANT_AUTHCODE, String dS_MERCHANT_AMOUNT) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + dS_MERCHANT_AUTHCODE + dS_MERCHANT_ORDER + this.password);

        SoapObject soapReq = new SoapObject("execute_refund");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_AUTHCODE", dS_MERCHANT_AUTHCODE);
        soapReq.addProperty("DS_MERCHANT_ORDER", dS_MERCHANT_ORDER);
        soapReq.addProperty("DS_MERCHANT_CURRENCY", DS_MERCHANT_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_AMOUNT", dS_MERCHANT_AMOUNT);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_MERCHANT_PAN card number without spaces or dashes
     * @param dS_MERCHANT_EXPIRYDATE expiry date of the card, expressed as "MMYY" (two - digit month and year in two digits) 
     * @param dS_MERCHANT_CVV2 Card code 
     * @param dS_SUBSCRIPTION_STARTDATE date subscription start yyyy-mm-dd 
     * @param dS_SUBSCRIPTION_ENDDATE Date End subscription yyyy-mm-dd
     * @param DS_SUBSCRIPTION_ORDER unique identifier payment
     * @param dS_SUBSCRIPTION_PERIODICITY Frequency of subscription. In days.
     * @param DS_SUBSCRIPTION_AMOUNT Amount of payment 1 € = 100 
     * @param DS_SUBSCRIPTION_CURRENCY identifier transaction currency 
     * @param dS_EXECUTE 
     * @param dS_MERCHANT_CARDHOLDERNAME Cardholder
     * @param dS_MERCHANT_SCORING scoring (optional) value of risk scoring the transaction 
     * @return 
     */
    public ServiceResponse create_subscription(String dS_MERCHANT_PAN, String dS_MERCHANT_EXPIRYDATE, String dS_MERCHANT_CVV2, String dS_SUBSCRIPTION_STARTDATE, String dS_SUBSCRIPTION_ENDDATE, String DS_SUBSCRIPTION_ORDER,
            String dS_SUBSCRIPTION_PERIODICITY, String DS_SUBSCRIPTION_AMOUNT, String DS_SUBSCRIPTION_CURRENCY, String dS_EXECUTE, String dS_MERCHANT_CARDHOLDERNAME, String dS_MERCHANT_SCORING) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_MERCHANT_PAN + dS_MERCHANT_CVV2 + this.dS_MERCHANT_TERMINAL + DS_SUBSCRIPTION_AMOUNT + DS_SUBSCRIPTION_CURRENCY + this.password);

        SoapObject soapReq = new SoapObject("create_subscription");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_MERCHANT_PAN", dS_MERCHANT_PAN);
        soapReq.addProperty("DS_MERCHANT_EXPIRYDATE", dS_MERCHANT_EXPIRYDATE);
        soapReq.addProperty("DS_MERCHANT_CVV2", dS_MERCHANT_CVV2);
        soapReq.addProperty("DS_SUBSCRIPTION_STARTDATE", dS_SUBSCRIPTION_STARTDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_ENDDATE", dS_SUBSCRIPTION_ENDDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_ORDER", DS_SUBSCRIPTION_ORDER);
        soapReq.addProperty("DS_SUBSCRIPTION_PERIODICITY", dS_SUBSCRIPTION_PERIODICITY);
        soapReq.addProperty("DS_SUBSCRIPTION_CURRENCY", DS_SUBSCRIPTION_CURRENCY);
        soapReq.addProperty("DS_SUBSCRIPTION_AMOUNT", DS_SUBSCRIPTION_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_EXECUTE", dS_EXECUTE);
        soapReq.addProperty("DS_MERCHANT_CARDHOLDERNAME", dS_MERCHANT_CARDHOLDERNAME);
        soapReq.addProperty("DS_MERCHANT_SCORING", dS_MERCHANT_SCORING);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_SUBSCRIPTION_STARTDATE date subscription start yyyy-mm-dd
     * @param dS_SUBSCRIPTION_ENDDATE Date End subscription yyyy-mm-dd
     * @param dS_SUBSCRIPTION_PERIODICITY Frequency of subscription. In days.
     * @param DS_SUBSCRIPTION_AMOUNT Amount of payment 1 € = 100
     * @param dS_EXECUTE
     * @return 
     */
    public ServiceResponse edit_subscription(String dS_IDUSER, String dS_TOKEN_USER, String dS_SUBSCRIPTION_STARTDATE, String dS_SUBSCRIPTION_ENDDATE, String dS_SUBSCRIPTION_PERIODICITY, String DS_SUBSCRIPTION_AMOUNT, String dS_EXECUTE) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + DS_SUBSCRIPTION_AMOUNT + this.password);

        SoapObject soapReq = new SoapObject("edit_subscription");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_SUBSCRIPTION_STARTDATE", dS_SUBSCRIPTION_STARTDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_ENDDATE", dS_SUBSCRIPTION_ENDDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_PERIODICITY", dS_SUBSCRIPTION_PERIODICITY);
        soapReq.addProperty("DS_SUBSCRIPTION_AMOUNT", DS_SUBSCRIPTION_AMOUNT);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_EXECUTE", dS_EXECUTE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @return 
     */
    public ServiceResponse remove_subscription(String dS_IDUSER, String dS_TOKEN_USER) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + this.password);

        SoapObject soapReq = new SoapObject("remove_subscription");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_IDUSER", dS_IDUSER);
        soapReq.addProperty("DS_TOKEN_USER", dS_TOKEN_USER);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param dS_IDUSER User ID in PayTPV
     * @param dS_TOKEN_USER user Token in PayTPV
     * @param dS_SUBSCRIPTION_STARTDATE date subscription start yyyy-mm-dd
     * @param dS_SUBSCRIPTION_ENDDATE Date End subscription yyyy-mm-dd
     * @param DS_SUBSCRIPTION_ORDER
     * @param dS_SUBSCRIPTION_PERIODICITY Frequency of subscription. In days.
     * @param DS_SUBSCRIPTION_AMOUNT Amount of payment 1 € = 100
     * @param DS_SUBSCRIPTION_CURRENCY identifier transaction currency
     * @param dS_MERCHANT_SCORING scoring (optional) value of risk scoring the transaction scoring (optional) value of risk scoring the transaction
     * @return 
     */
    public ServiceResponse create_subscription_token(String dS_IDUSER, String dS_TOKEN_USER, String dS_SUBSCRIPTION_STARTDATE, String dS_SUBSCRIPTION_ENDDATE,
            String DS_SUBSCRIPTION_ORDER, String dS_SUBSCRIPTION_PERIODICITY, String DS_SUBSCRIPTION_AMOUNT, String DS_SUBSCRIPTION_CURRENCY, String dS_MERCHANT_SCORING) {

        ServiceResponse result = new ServiceResponse();
        dS_MERCHANT_MERCHANTSIGNATURE = makeSHA1hash(this.dS_MERCHANT_MERCHANTCODE + dS_IDUSER + dS_TOKEN_USER + this.dS_MERCHANT_TERMINAL + DS_SUBSCRIPTION_AMOUNT + DS_SUBSCRIPTION_CURRENCY + this.password);

        SoapObject soapReq = new SoapObject("create_subscription_token");
        soapReq.addProperty("DS_MERCHANT_MERCHANTCODE", dS_MERCHANT_MERCHANTCODE);
        soapReq.addProperty("DS_MERCHANT_TERMINAL", dS_MERCHANT_TERMINAL);
        soapReq.addProperty("DS_SUBSCRIPTION_STARTDATE", dS_SUBSCRIPTION_STARTDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_ENDDATE", dS_SUBSCRIPTION_ENDDATE);
        soapReq.addProperty("DS_SUBSCRIPTION_ORDER", DS_SUBSCRIPTION_ORDER);
        soapReq.addProperty("DS_SUBSCRIPTION_PERIODICITY", dS_SUBSCRIPTION_PERIODICITY);
        soapReq.addProperty("DS_SUBSCRIPTION_AMOUNT", DS_SUBSCRIPTION_AMOUNT);
        soapReq.addProperty("DS_SUBSCRIPTION_CURRENCY", DS_SUBSCRIPTION_CURRENCY);
        soapReq.addProperty("DS_MERCHANT_MERCHANTSIGNATURE", dS_MERCHANT_MERCHANTSIGNATURE);
        soapReq.addProperty("DS_ORIGINAL_IP", dS_ORIGINAL_IP);
        soapReq.addProperty("DS_MERCHANT_SCORING", dS_MERCHANT_SCORING);

        return callSoap(result, soapReq);
    }

    /**
     * 
     * @param transreference  unique identifier payment 
     * @param amount Amount of payment 1 € = 100 Amount of payment 1 € = 100
     * @param currency identifier transaction currency identifier transaction currency
     * @param lang language language language
     * @param description description description
     * @param secure3d  secure3d secure3d
     * @return 
     */
    public ServiceResponse ExecutePurchaseUrl(String transreference, String amount, String currency, String lang, String description,
            String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(1);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse ExecutePurchaseTokenUrl(String transreference, String amount, String currency, String iduser, String tokenuser, String lang, String description,
            String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(109);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param lang language language
     * @return 
     */
    public ServiceResponse AddUserUrl(String transreference, String lang) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(107);
            operation.setReference(transreference);
            operation.setLanguage(lang);

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }
    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param startDate date subscription start yyyy-mm-dd
     * @param endDate Date End subscription yyyy-mm-dd
     * @param periodicity Frequency of subscription. In days.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse CreateSubscriptionUrl(String transreference, String amount, String currency, String startDate, String endDate, String periodicity, String lang, String description,
            String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(9);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setPeriodicity(periodicity);
            operation.setStartDate(startDate);
            operation.setEndDate(endDate);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param startDate date subscription start yyyy-mm-dd
     * @param endDate Date End subscription yyyy-mm-dd
     * @param periodicity Frequency of subscription. In days.
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse CreateSubscriptionTokenUrl(String transreference, String amount, String currency, String startDate, String endDate, String periodicity,
            String iduser, String tokenuser, String lang, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(110);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setPeriodicity(periodicity);
            operation.setStartDate(startDate);
            operation.setEndDate(endDate);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse CreatePreauthorizationUrl(String transreference, String amount, String currency, String lang,
            String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(3);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language language
     * @param description description description
     * @param secure3d  secure3d secure3d
     * @return 
     */
    public ServiceResponse PreauthorizationConfirmUrl(String transreference, String amount, String currency,
            String iduser, String tokenuser, String lang, String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(6);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse PreauthorizationCancelUrl(String transreference, String amount, String currency,
            String iduser, String tokenuser, String lang, String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(4);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }
            ServiceResponse check_user_exist = this.info_user(operation.getIdUser(), operation.getTokenUser());
            if (Integer.parseInt(check_user_exist.getDS_ERROR_ID()) != 0) {
                return check_user_exist;
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse ExecutePreauthorizationTokenUrl(String transreference, String amount, String currency,
            String iduser, String tokenuser, String lang, String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(111);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }
            ServiceResponse check_user_exist = this.info_user(operation.getIdUser(), operation.getTokenUser());
            if (Integer.parseInt(check_user_exist.getDS_ERROR_ID()) != 0) {
                return check_user_exist;
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @param scoring  scoring (optional) value of risk scoring the transaction 
     * @return 
     */
    public ServiceResponse DeferredPreauthorizationUrl(String transreference, String amount, String currency,
            String lang, String description, String secure3d, String scoring) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(13);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }
            if (scoring.length() > 0) {
                operation.setScoring(scoring);
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }

    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse DeferredPreauthorizationConfirmUrl(String transreference, String amount, String currency,
            String iduser, String tokenuser, String lang, String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(16);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }
            ServiceResponse check_user_exist = this.info_user(operation.getIdUser(), operation.getTokenUser());
            if (Integer.parseInt(check_user_exist.getDS_ERROR_ID()) != 0) {
                return check_user_exist;
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }
    /**
     * 
     * @param transreference  unique identifier payment
     * @param amount Amount of payment 1 € = 100
     * @param currency identifier transaction currency
     * @param iduser unique identifier system registered user.
     * @param tokenuser token code associated to IDUSER.
     * @param lang language language
     * @param description description
     * @param secure3d  secure3d
     * @return 
     */
    public ServiceResponse DeferredPreauthorizationCancelUrl(String transreference, String amount, String currency,
            String iduser, String tokenuser, String lang, String description, String secure3d) {
        ServiceResponse result = new ServiceResponse();
        OperationData operation = new OperationData();
        try {
            operation.setType(14);
            operation.setReference(transreference);
            operation.setAmount(amount);
            operation.setCurrency(currency);
            operation.setLanguage(lang);
            operation.setConcept(description);
            operation.setIdUser(iduser);
            operation.setTokenUser(tokenuser);

            if (secure3d != "false") {
                operation.setSecure3D(secure3d);
            }
            ServiceResponse check_user_exist = this.info_user(operation.getIdUser(), operation.getTokenUser());
            if (Integer.parseInt(check_user_exist.getDS_ERROR_ID()) != 0) {
                return check_user_exist;
            }

            operation.setHash(this.GenerateHash(operation, operation.getType())); //generate hash
            String lastrequest = ComposeURLParams(operation, operation.getType());

            result = CheckUrlError(lastrequest);
            result.setSoapResponse("<URL_REDIRECT>" + this.endpointurl + lastrequest + "</URL_REDIRECT>");

            if (Integer.parseInt(result.getDS_ERROR_ID()) > 0) {
                result.setRESULT("KO");
            } else {
                result.setRESULT("OK");
            }
            return result;
        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
            return result;
        }

    }
    
    /**
     *
     * @param inputData  string value to make md5 
     * @return
     */
    public String makeSHA512hash(String inputData) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(inputData.getBytes());

            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public String makeSHA256hash(String inputData) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(inputData.getBytes());

            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public String makeSHA1hash(String inputData) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(inputData.getBytes());

            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    private static String Md5HashString(String message) {

        String algorithm = "MD5";
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hashedBytes.length; i++) {
                stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return stringBuffer.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    private ServiceResponse callSoap(ServiceResponse result, SoapObject soapReq) {
        try {

            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SAAJClient.createSoapRequest(soapReq);
            //hit soapRequest to the server to get response                        
            SOAPMessage soapResponse = soapConnection.call(soapRequest, endpoint);
            String respo = SAAJClient.createSoapResponse(soapResponse);
            soapConnection.close();
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream input =  new ByteArrayInputStream(respo.toString().getBytes("UTF-8"));
            Document doc = dBuilder.parse(input);
            NodeList nList = doc.getElementsByTagName("SOAP-ENV:" + soapReq.getMethodName()+"Response");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            String errorcode =  eElement.getElementsByTagName("DS_ERROR_ID").item(0)
                  .getTextContent();
            if (Integer.parseInt(errorcode) > 0) {                
                result.setDS_ERROR_ID(errorcode);
                result.setRESULT("KO");
            }else{            
                result.setDS_ERROR_ID("0");
                result.setRESULT("OK");
            }
            result.setSoapResponse(respo);

        } catch (Exception e) {
            result.setDS_ERROR_ID("1011");
            result.setRESULT("KO");
        }
        return result;
    }

    /**
     * 
     * @param operationdata
     * @param operationtype
     * @return 
     */
    private String ComposeURLParams(OperationData operationdata, int operationtype) {
        String secureurlhash = "false";
        TreeMap<String, String> data = new TreeMap<String, String>();
        data.put("MERCHANT_MERCHANTCODE", this.dS_MERCHANT_MERCHANTCODE);
        data.put("MERCHANT_TERMINAL", this.dS_MERCHANT_TERMINAL);
        data.put("OPERATION", String.valueOf(operationtype));
        data.put("LANGUAGE", operationdata.getLanguage());
        data.put("MERCHANT_MERCHANTSIGNATURE", operationdata.getHash());
        data.put("URLOK", operationdata.getUrlOk());
        data.put("URLKO", operationdata.getUrlKo());
        data.put("MERCHANT_ORDER", operationdata.getReference());
        if (!operationdata.getSecure3D().equals("false")) {
            data.put("3DSECURE", operationdata.getSecure3D());
        }
        data.put("MERCHANT_AMOUNT", operationdata.getAmount());
        if (!operationdata.getConcept().equals("")) {
            data.put("MERCHANT_PRODUCTDESCRIPTION", operationdata.getConcept());
        }

        if ((int) operationtype == 1) { // Authorization (execute_purchase)
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 3) { // Preauthorization
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 6) { // Confirmaciï¿½n de Preauthorization
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
        } else if ((int) operationtype == 4) { // Cancelaciï¿½n de Preauthorization
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
        } else if ((int) operationtype == 9) { // Subscription
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("SUBSCRIPTION_STARTDATE", operationdata.getStartDate());
            data.put("SUBSCRIPTION_ENDDATE", operationdata.getEndDate());
            data.put("SUBSCRIPTION_PERIODICITY", operationdata.getPeriodicity());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 109) { // execute_purchase_token
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 110) { // create_subscription_token
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("SUBSCRIPTION_STARTDATE", operationdata.getStartDate());
            data.put("SUBSCRIPTION_ENDDATE", operationdata.getEndDate());
            data.put("SUBSCRIPTION_PERIODICITY", operationdata.getPeriodicity());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 111) { // create_preauthorization_token
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
        } else if ((int) operationtype == 13) { // Deferred Preauthorization
            data.put("MERCHANT_CURRENCY", operationdata.getCurrency());
            data.put("MERCHANT_SCORING", operationdata.getScoring());
        } else if ((int) operationtype == 16) { // Deferred Confirmaciï¿½n de Preauthorization
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
        } else if ((int) operationtype == 14) { // Deferred  Cancelaciï¿½n de Preauthorization
            data.put("IDUSER", operationdata.getIdUser());
            data.put("TOKEN_USER", operationdata.getTokenUser());
        }

        String content = "";
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (!content.equals("")) {
                content += "&";
            }
            content += java.net.URLEncoder.encode(pair.getKey().toString()) + "=" + java.net.URLEncoder.encode(data.get(pair.getValue().toString()));
            it.remove(); // avoids a ConcurrentModificationException

        }

        data.put("VHASH", makeSHA512hash(Md5HashString(content + Md5HashString(this.password))));
        //krsort(data);
        //var dd = new Dictionary<string, string>();
        //data.Reverse();
        it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (!secureurlhash.equals("")) {
                secureurlhash += "&";
            }
            secureurlhash += java.net.URLEncoder.encode(pair.getKey().toString()) + "=" + java.net.URLEncoder.encode(data.get(pair.getValue().toString()));
            it.remove(); // avoids a ConcurrentModificationException

        }
        return secureurlhash;
    }

    /**
     * 
     * @param urlgen
     * @return 
     */
    private ServiceResponse CheckUrlError(String urlgen) {
        ServiceResponse response = new ServiceResponse();
        try {
            URL url = new URL(urlgen);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = null;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            if (s.contains("ERROR")) {
                response.setDS_ERROR_ID("1021");
                response.setRESULT("KO");
            } else {
                response.setRESULT("OK");
                response.setDS_ERROR_ID("0");
            }
            return response;
        } catch (Exception ex) {
            response.setDS_ERROR_ID("1021");
            response.setRESULT("KO");
            return response;
        }
    }

    /**
     * 
     * @param operationdata
     * @param operationtype
     * @return 
     */
    private String GenerateHash(OperationData operationdata, int operationtype) {
        String hash = "false";

        switch (operationtype) {
            case 1: // Authorization (execute_purchase)
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 3: // Preauthorization
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 6: // Confirmaci�n de Preauthorization
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + Md5HashString(this.password));
                break;
            case 4: // Cancelaci�n de Preauthorization
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + Md5HashString(this.password));
                break;
            case 9: // Subscription
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;

            case 107: // Add_user
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + Md5HashString(this.password));
                break;
            case 109: // execute_purchase_token
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 110: // create_subscription_token
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 111: // create_preauthorization_token
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 13: // Preauthorization Diferida
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + operationdata.getCurrency() + Md5HashString(this.password));
                break;
            case 16: // Confirmaci�n de Preauthorization Diferida
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + Md5HashString(this.password));
                break;
            case 14: // Cancelaci�n de Preauthorization Diferida
                hash = Md5HashString(this.dS_MERCHANT_MERCHANTCODE + operationdata.getIdUser() + operationdata.getTokenUser() + this.dS_MERCHANT_TERMINAL + operationtype + operationdata.getReference() + operationdata.getAmount() + Md5HashString(this.password));
                break;
        }

        return hash;
    }
}
