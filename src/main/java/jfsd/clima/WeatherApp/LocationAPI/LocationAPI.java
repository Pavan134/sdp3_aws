package jfsd.clima.WeatherApp.LocationAPI;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jfsd.clima.WeatherApp.Beans.AccuWeatherApiKey;



public class LocationAPI {
	public JSONObject getLocationData(String location)
	{
		ApplicationContext acb=new ClassPathXmlApplicationContext("spring.xml");
		AccuWeatherApiKey Accuapikey=(AccuWeatherApiKey) acb.getBean("AccuWeatherApiKey");
		String url="http://dataservice.accuweather.com/locations/v1/search?apikey="+Accuapikey.getAccuapikey()+"&q="+location;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String json = IOUtils.toString(entity.getContent());
		    JSONArray LocationsArray = new JSONArray(json);
		    JSONObject LocationsObject = LocationsArray.getJSONObject(0); 
		    JSONObject GeoPosition= LocationsObject.getJSONObject("GeoPosition");
		    JSONObject Continent=LocationsObject.getJSONObject("Region");
		    JSONObject Country=LocationsObject.getJSONObject("Country");
		    JSONArray DistrictArray=LocationsObject.getJSONArray("SupplementalAdminAreas");
		    JSONObject District=DistrictArray.getJSONObject(0);
		    
		    JSONObject LocationData=new JSONObject();
		    LocationData.put("Key", LocationsObject.get("Key"));
		    LocationData.put("City", LocationsObject.get("LocalizedName"));
		    LocationData.put("City_Type", LocationsObject.get("Type"));
		    LocationData.put("Latitude", GeoPosition.get("Latitude"));
		    LocationData.put("Longitude", GeoPosition.get("Longitude"));
		    LocationData.put("Continent", Continent.get("LocalizedName"));
		    LocationData.put("Country", Country.get("LocalizedName"));
		    LocationData.put("District", District.get("LocalizedName"));
		    
//		    System.out.println(LocationsObject.get("LocalizedName"));
//		    System.out.println(LocationsObject.get("Key"));
//		    System.out.println(LocationsObject.get("Type"));
//		    System.out.println("Latitude : "+GeoPosition.get("Latitude")+"  Longitude : "+GeoPosition.get("Longitude"));
//		    System.out.println(Continent.get("LocalizedName"));
//		    System.out.println(Country.get("LocalizedName"));
//		    System.out.println(District.get("LocalizedName"));
		    
		    
		    return LocationData;
			
		}
		catch(IOException ioe) 
		{System.out.println("Something went wrong on getting Location");
//		ioe.printStackTrace();
		}
		catch(Exception e)
		{System.out.println("Unknown Error:");
//		e.printStackTrace();
		}
		return null;
	}
}
