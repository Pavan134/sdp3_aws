package jfsd.clima.WeatherApp.Beans;

public class Forecast {
	public String validityDate;
	public String city;
	public String predictedMaxTemp;
	public String getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPredictedMaxTemp() {
		return predictedMaxTemp;
	}
	public void setPredictedMaxTemp(String predictedMaxTemp) {
		this.predictedMaxTemp = predictedMaxTemp;
	}
	

}
