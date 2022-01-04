package jfsd.clima.WeatherApp.Services;

import java.util.List;

import jfsd.clima.WeatherApp.Beans.subscribers;

public interface ServicesInterface 
{
	public List<subscribers> getSubscribers();
	
	public subscribers getSubscriber(int subscriber_id);
	
	public subscribers addSubscriber(subscribers subscriber);
	
	public subscribers updateSubscriber(subscribers subscriber);
	
	public void deleteSubscriber(int subscriber_id);
	
}
