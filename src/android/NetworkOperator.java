package com.vodafone.networkoperator;

import android.telephony.TelephonyManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NetworkOperator extends CordovaPlugin {
	
	public static final String OPERATOR_NAME = "getOperator";

   @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	
        if (OPERATOR_NAME.equals(action)){
		
            try
			{
				String operatorInfo			= 	getOperatorName();
				PluginResult pluginResult 	= 	new PluginResult(PluginResult.Status.OK, operatorInfo);
				pluginResult.setKeepCallback(true);
				callbackContext.sendPluginResult(pluginResult);
				return true;
			}
			catch (Exception e)
			{
				System.err.println("Exception: " + e.getMessage());
				callbackContext.error(e.getMessage());
				return false;
			}
            
        }
        return false;
    }

    private String getOperatorName()
		throws JSONException
		{
			JSONObject fileInfo = new JSONObject();
			TelephonyManager telephonyManager = (TelephonyManager)cordova.getActivity().getSystemService("phone");
			String networkOperator = telephonyManager.getNetworkOperator();
			if (networkOperator != null)
			{
			  JSONArray MCC = new JSONArray();
			  JSONObject json = new JSONObject();
			  json.put("name", networkOperator.substring(0, 3));
			  MCC.put(json);
			  json = new JSONObject();
			  json.put("code", networkOperator.substring(3, networkOperator.length()));
			  MCC.put(json);
			  fileInfo.put("MCC", MCC);
			}
			return fileInfo.toString();
		}
}
