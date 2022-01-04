package jfsd.clima.WeatherApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jfsd.clima.WeatherApp.Beans.subscribers;

@Repository
public interface SubscriberRepo extends JpaRepository<subscribers,Integer> {
	public List<subscribers> findByEmail(String email);
	public List<subscribers> findByPlace(String place);
	
	

}
