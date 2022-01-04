package jfsd.clima.WeatherApp.AdditionalForecastsAPI;

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



public class AdditionalForecastsAPI {
	public JSONObject getAdditionalForecastsData(String locationkey)
	{
		ApplicationContext acb=new ClassPathXmlApplicationContext("spring.xml");
		AccuWeatherApiKey Accuapikey=(AccuWeatherApiKey) acb.getBean("AccuWeatherApiKey");
		String url="http://dataservice.accuweather.com/indices/v1/daily/1day/"+locationkey+"?apikey="+Accuapikey.getAccuapikey();
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

}
