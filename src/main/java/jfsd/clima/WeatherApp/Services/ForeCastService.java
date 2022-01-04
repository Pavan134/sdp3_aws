package jfsd.clima.WeatherApp.Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jfsd.clima.WeatherApp.Beans.Forecast;

@Service
public class ForeCastService {
	private static String Virus_URL="https://raw.githubusercontent.com/4akhilkumar/Weather-Application-Stuff/master/submission.csv";
	private List<Forecast> allStats=new ArrayList<>();
	public List<Forecast> getAllForecast() {
		return allStats;
	}
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void getForecast() throws IOException, InterruptedException {
		List<Forecast> newStats=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest req=HttpRequest.newBuilder().uri(URI.create(Virus_URL))
				.build();
		HttpResponse<String> res=client.send(req, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader=new StringReader(res.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			Forecast forecast=new Forecast();
			forecast.setValidityDate(record.get("validityDate"));
			forecast.setCity(record.get("city"));
			forecast.setPredictedMaxTemp(record.get("predictedMaxTemp"));
			newStats.add(forecast);
		}
		this.allStats=newStats;
		     
	}

}
