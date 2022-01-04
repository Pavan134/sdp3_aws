package jfsd.clima.WeatherApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jfsd.clima.WeatherApp.Beans.Admin;
import jfsd.clima.WeatherApp.Repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	public AdminRepository adminrepo;
	public List<Admin> getAdmin(String uname,String pwd){
		return adminrepo.findByUsernameAndPassword(uname, pwd);
	}

}
