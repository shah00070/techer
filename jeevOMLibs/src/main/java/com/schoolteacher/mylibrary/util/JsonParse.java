package com.schoolteacher.mylibrary.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.model.Speciality;
import com.schoolteacher.mylibrary.model.State;

public class JsonParse {

	public JsonParse() {
	}

	public List<String> getParseJsonWCF(String sName, String lookupUrl, String type, Activity context) {
		List<String> listData = new ArrayList<String>();
		try {
			String temp = sName.replace(" ", "%20");
			String dictionaryString = getDictionaryString(JeevOMUtil.baseUrl + lookupUrl + temp);
			// Get Gson object
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// parse json string to object
			DataContainer rootObject = gson.fromJson(dictionaryString, DataContainer.class);
			if (type.equals("")) {
				List<String> dataDictionary = rootObject.getData().getEducationDegreeList();
				Iterator<String> iterator = dataDictionary.iterator();
				while (iterator.hasNext()) {
					listData.add(iterator.next());
				}
			} else if (type.equals("speciality_lookup")) {
				List<Speciality> dataDictionary = rootObject.getData().getSpecialities();
				Iterator<Speciality> iterator = dataDictionary.iterator();
				while (iterator.hasNext()) {
					listData.add(iterator.next().getName());
				}
			} 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listData;
	}

	public List<State> getParseJsonWCFForState(String sName, String lookupUrl, Activity context) {
		List<State> stateDataDictionary = null;
		try {
			String temp = sName.replace(" ", "%20");
			String dictionaryString = getDictionaryString(JeevOMUtil.baseUrl + lookupUrl );
			// Get Gson object
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// parse json string to object
			DataContainer rootObject = gson.fromJson(dictionaryString, DataContainer.class);
			stateDataDictionary = rootObject.getData().getStates();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return stateDataDictionary;
	}


	public String getDictionaryString(String url) {
		InputStream inputStream = null;
		String result = "";
		try {
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			// convert inputstream to string
			if (inputStream != null && responseCode == 200) {
				result = InputStreamToString.convertInputStreamToString(inputStream);
			} else {
				result = "Service Error";
			}
		} catch (Exception e) {
			result = "No Internet Connectivity";
		}
		return result;
	}

}
