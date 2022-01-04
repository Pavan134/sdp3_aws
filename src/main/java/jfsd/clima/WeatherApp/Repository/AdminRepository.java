package jfsd.clima.WeatherApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jfsd.clima.WeatherApp.Beans.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	public List<Admin> findByUsernameAndPassword(String uname,String pwd);

}
