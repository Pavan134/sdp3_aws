package jfsd.clima.WeatherApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jfsd.clima.WeatherApp.Beans.subscribers;
import jfsd.clima.WeatherApp.Repository.SubscriberRepo;
@Service
public class Service_Implementations implements ServicesInterface
{
	@Autowired
	SubscriberRepo repo;

	@Override
	public List<subscribers> getSubscribers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public subscribers getSubscriber(int subscriber_id) {
		// TODO Auto-generated method stub
		return repo.findById(subscriber_id).get();
		
	}

	@Override
	public subscribers addSubscriber(subscribers subscriber) {
		// TODO Auto-generated method stub
		return repo.save(subscriber);
	}

	@Override
	public subscribers updateSubscriber(subscribers subscriber) {
		// TODO Auto-generated method stub
		return repo.save(subscriber);
	}

	@Override
	public void deleteSubscriber(int subscriber_id) {
		// TODO Auto-generated method stub
		subscribers entity=repo.getOne(subscriber_id);
		repo.delete(entity); 
		
	}
//	public List<subscribers> getByEmail(String email) {
//		return (List<subscribers>)repo.findByEmail(email);
//	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	public List<subscribers> getByPlace(String place){
		return (List<subscribers>)repo.findByPlace(place);
	}
	

}
