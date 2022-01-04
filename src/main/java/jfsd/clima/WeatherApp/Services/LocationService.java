package jfsd.clima.WeatherApp.Services;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	public JSONObject getAdditionalForecastsData(String locationkey) throws JSONException
	{
		String value="VbalEkmE5WiLNruw2VhGpsRM6zsr1cgU";
		String url="http://dataservice.accuweather.com/indices/v1/daily/1day/"+locationkey+"?apikey="+value;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String json = IOUtils.toString(entity.getContent());
		    JSONArray ForecastsArray = new JSONArray(json);
		    JSONObject FlightDelays=ForecastsArray.getJSONObject(0);
		    JSONObject Jogging=ForecastsArray.getJSONObject(3);
		    JSONObject Bicycling=ForecastsArray.getJSONObject(5);
		    JSONObject BeachAndPool=ForecastsArray.getJSONObject(11);
		    JSONObject Fishing=ForecastsArray.getJSONObject(14);
		    JSONObject Construction=ForecastsArray.getJSONObject(15);
		    JSONObject Asthma=ForecastsArray.getJSONObject(24);
		    JSONObject DrivingConditions=ForecastsArray.getJSONObject(41);
		    JSONObject ForecastsData=new JSONObject();
		    ForecastsData.put("FlightDelays", FlightDelays);
		    ForecastsData.put("Jogging", Jogging);
		    ForecastsData.put("Bicycling", Bicycling);
		    ForecastsData.put("BeachAndPool", BeachAndPool);
		    ForecastsData.put("Fishing", Fishing);
		    ForecastsData.put("Construction", Construction);
		    ForecastsData.put("Asthma", Asthma);
		    ForecastsData.put("DrivingConditions", DrivingConditions);
		    return ForecastsData;
		}
		catch(IOException ioe) 
		{System.out.println("Something went wrong on getting Additional Forecasts");
		ioe.printStackTrace();
		}
		catch(Exception e)
		{System.out.println("Unknown Error:");
		e.printStackTrace();
		}
		return null;
	}
	public JSONObject getLocationData(String location)
	{
		String value="VbalEkmE5WiLNruw2VhGpsRM6zsr1cgU";
		String url="http://dataservice.accuweather.com/locations/v1/search?apikey="+value+"&q="+location;
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
	public JSONObject getWeatherData(String latitude,String longitude)
	{
		String value="9ddd01c41e76dee15e6adfd6d66d83a6";
		String url="http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid="+value;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet get=new HttpGet(url);
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String json = IOUtils.toString(entity.getContent());
		    JSONObject MasterData = new JSONObject(json);
		    JSONArray weather=MasterData.getJSONArray("weather");
		    JSONObject WeatherObject=weather.getJSONObject(0);
		    JSONObject Climate=new JSONObject();
		    Climate.put("climate", WeatherObject.get("main"));
		    Climate.put("description", WeatherObject.get("description"));
		    JSONObject TemperatureObject=MasterData.getJSONObject("main");
		    JSONObject Temperature=new JSONObject();
		    Temperature.put("current", TemperatureObject.get("temp"));
		    Temperature.put("Minimum_Temp", TemperatureObject.get("temp_min"));
		    Temperature.put("Maximum_Temp", TemperatureObject.get("temp_max"));
		    Temperature.put("humidity", TemperatureObject.get("humidity"));
		    JSONObject WindObject=MasterData.getJSONObject("wind");
		    JSONObject Wind=new JSONObject();
		    Wind.put("speed", WindObject.get("speed"));
		    Wind.put("degree", WindObject.get("deg"));
		    JSONObject returnObject=new JSONObject();
		    returnObject.put("Climate", Climate);
		    returnObject.put("Temperature",Temperature);
		    returnObject.put("Wind", Wind);
		    return returnObject;
		}
		catch(IOException ioe) 
		{System.out.println("Something went wrong on getting Weather");
		ioe.printStackTrace();
		}
		catch(Exception e)
		{System.out.println("Unknown Error:");
		e.printStackTrace();
		}
		return null;
	}


}

