/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PAYTPV;

import java.util.HashMap;

/**
 *
 * @author PayTPV
 */
class SoapObject {

    public SoapObject(String methodName) {
        this.methodName = methodName;
    }

    
    private String methodName;

    /**
     * Get the value of methodName
     *
     * @return the value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Set the value of methodName
     *
     * @param methodName new value of methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    HashMap<String, String> map = new HashMap<String, String>();
    void addProperty(String parameterName, String parameterValue) {
        map.put(parameterName, parameterValue);
    }

}
