/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAYCOMET;

/**
 *
 * @author PayCOMET
 */
public class ServiceResponse {
    
    private String DS_ERROR_ID;    
    private String RESULT;    
    private String soapResponse;

    /**
     * Get the value of soapResponse
     *
     * @return the value of soapResponse
     */
    public String getSoapResponse() {
        return soapResponse;
    }

    /**
     * Set the value of soapResponse
     *
     * @param soapResponse new value of soapResponse
     */
    public void setSoapResponse(String soapResponse) {
        this.soapResponse = soapResponse;
    }


    /**
     * Get the value of RESULT
     *
     * @return the value of RESULT
     */
    public String getRESULT() {
        return RESULT;
    }

    /**
     * Set the value of RESULT
     *
     * @param RESULT new value of RESULT
     */
    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    /**
     * Get the value of DS_ERROR_ID
     *
     * @return the value of DS_ERROR_ID
     */
    public String getDS_ERROR_ID() {
        return DS_ERROR_ID;
    }

    /**
     * Set the value of DS_ERROR_ID
     *
     * @param DS_ERROR_ID new value of DS_ERROR_ID
     */
    public void setDS_ERROR_ID(String DS_ERROR_ID) {
        this.DS_ERROR_ID = DS_ERROR_ID;
    }

}
