package jfsd.clima.WeatherApp.Controllers;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jfsd.clima.WeatherApp.Beans.Admin;
import jfsd.clima.WeatherApp.Beans.Forecast;
import jfsd.clima.WeatherApp.Beans.subscribers;
import jfsd.clima.WeatherApp.Services.AdminService;
import jfsd.clima.WeatherApp.Services.EmailService;
import jfsd.clima.WeatherApp.Services.ForeCastService;
import jfsd.clima.WeatherApp.Services.LocationService;
import jfsd.clima.WeatherApp.Services.Service_Implementations;



@RestController
public class AppController 
{
	@Autowired
	Service_Implementations service;
	@Autowired
	public LocationService locservice;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ForeCastService forservice;
	
	@RequestMapping("/")
	public ModelAndView getIndex()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return  mv;
	}
	@GetMapping("/subscribers/{subscriber_id}")
	public subscribers getSubscriber(@PathVariable String subscriber_id)
	{
		return this.service.getSubscriber(Integer.parseInt(subscriber_id));
	}
	@GetMapping("/gotosubscribe")
	public ModelAndView registerSubscriber()
	{
		ModelAndView mv = new ModelAndView("subscriber-register","subscriber",new subscribers());
		mv.setViewName("subscribe");
		return mv;
	}
	@PostMapping("/gotosubscribe")
	public ModelAndView addSubscriber(@ModelAttribute("subscriber")subscribers subscriber) throws MessagingException
	{
		ModelAndView mv = new ModelAndView();
//		List<subscribers> check=service.getByEmail(subscriber.getEmail());
//		if(check!=null) {
//			String msg="You have subscribed already";
//			mv.addObject("msg",msg);
//			mv.setViewName("subscribe");
//			return mv;
//		}
		this.service.addSubscriber(subscriber);
		String sub="<h1>You have subscribed</h1><br><a href='/unsubscibe?email="+subscriber.getEmail()+"'";
		emailService.sendHtmlMessage(subscriber.getEmail(), "Subscription", sub);
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PutMapping("/subscribers")
	public subscribers updateSubscriber(@RequestBody subscribers subscriber)
	{
		return this.service.updateSubscriber(subscriber);
	}
	@DeleteMapping("/subscribers/{subscriber_id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String subscriber_id)
	{
		try
		{
			this.service.deleteSubscriber(Integer.parseInt(subscriber_id));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping("/searchLocation")
	ModelAndView Search(Model model,@RequestParam(value="location") String Location)
	{
		ModelAndView mv = new ModelAndView();
		Location=Location.replace(" ", "%20");
		try {
		JSONObject LocationData=locservice.getLocationData(Location);
		if(LocationData!=null)
		{
		JSONObject WeatherData=locservice.getWeatherData(String.valueOf(LocationData.get("Latitude")),String.valueOf(LocationData.get("Longitude")));
		JSONObject ForecastsData=locservice.getAdditionalForecastsData(String.valueOf(LocationData.get("Key")));
		model.addAttribute("LocationData",LocationData);
		model.addAttribute("WeatherData",WeatherData);
		model.addAttribute("AdditionalForecasts",ForecastsData);
		mv.addObject("LocationData", LocationData);
		mv.addObject("WeatherData", WeatherData);
		mv.addObject("AdditionalForecasts", ForecastsData);
		}
		else
		{
			System.out.println("Location Not Found");
		}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		mv.setViewName("index");
		return mv;
		
	}
	@GetMapping("/admin-login")
	public ModelAndView AdminLogin() {
		ModelAndView mv = new ModelAndView("admin-login","admin",new Admin());
		mv.setViewName("login");
		return mv;
	}
	@PostMapping("/admin-login")
	public ModelAndView AdminLogin(@ModelAttribute("admin")Admin admin,HttpSession session) {
		ModelAndView mv=new ModelAndView();
		List<Admin> admins=adminService.getAdmin(admin.getUsername(), admin.getPassword());
		if(admins!=null) {
			session.setAttribute("username", admin.getUsername());
			mv.setViewName("redirect:/subscribers");
			
			
			
		}
		else {
			mv = new ModelAndView();
			mv.setViewName("login");
			String msg="Admin Login Failed. Please Try Again";
			mv.addObject("msg",msg);
		}
		return mv;
	}
	@GetMapping("/subscribers")
	public ModelAndView subscribers(HttpSession session) {
		String admin=(String)session.getAttribute("username");
		ModelAndView mv=new ModelAndView();
		List<subscribers> subs=service.getSubscribers();
		
		mv.addObject("admin",admin);
		mv.addObject("subscriber",subs);
		mv.setViewName("subscribers");
		return mv;
	}
	@GetMapping("/admin-logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("username");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		String msg = "LoggedOut Successfully";
		mv.addObject("msg",msg);
		return mv;
	}
	@GetMapping("/unsubscribe")
	public ModelAndView unsubscribe(@RequestParam(value="id")String id) {
		int ID=Integer.parseInt(id);
		ModelAndView mv=new ModelAndView();
		System.out.println(service.getSubscriber(ID));
		service.delete(ID);
		
		mv.setViewName("redirect:/");
		return mv;
	}
	@GetMapping("/future")
	public ModelAndView getFuture() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("futuretemp");
		return mv;
	}
	@GetMapping("/forecast")
	public ModelAndView getForecast() {
		ModelAndView mv=new ModelAndView();
		List<Forecast> forecast=forservice.getAllForecast();
		mv.addObject("forecast",forecast);
		mv.setViewName("forecast");
		return mv;
	}
	@GetMapping("/futuretemp")
	public ModelAndView getFutureTemp(@RequestParam(value="date")String date,@RequestParam(value="place")String place) {
		ModelAndView mv=new ModelAndView();
		List<Forecast> forecast=forservice.getAllForecast();
		for(Forecast cast:forecast) {
			if(cast.getCity().equals(place) && cast.getValidityDate().equals(date)) {
				System.out.print(cast.getCity());
				mv.addObject("cast",cast);
				break;
			}
		}
		return mv;
		
	}
	@GetMapping("/searchplace")
	public ModelAndView searchPlace(@RequestParam(value="place")String place,HttpSession session) {
		String admin=(String)session.getAttribute("username");
		ModelAndView mv=new ModelAndView();
		List<subscribers> subs=service.getByPlace(place);
		for(subscribers sub:subs) {
			System.out.print(sub.getEmail());
		}
		mv.addObject("admin",admin);
		mv.addObject("subscriber",subs);
		mv.setViewName("subscribers");
		return mv;
	}
	

}
