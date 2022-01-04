package jfsd.clima.WeatherApp.Beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class subscribers 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String email;
	private String user_name;
	private long phone_number;
	private String place;
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public subscribers(String email,String u_name,long ph_num)
	{
		super();
		this.email=email;
		this.user_name=u_name;
		this.phone_number=ph_num;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public subscribers()
	{
		super();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
