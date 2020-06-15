package com.kassi.orders;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

	private final String KELVINE_TO_CELSIUS = "KELVINE_TO_CELSIUS";
	private final String MILES_TO_KILOMETERS = "MILES_TO_KILOMETERS";
	private final String POUNDS_TO_KILOGRAM = "POUNDS_TO_KILOGRAM";
	private final double MILES = 1.60934;
	private final double KELVINE = -273.1;
	private final double POUND =  0.453592;
	private final String AUTHORIZED_USER = "MATIMBA";
	private final String header = "<h1>**</h1>";
	private ArrayList<String> users = null;
	Service () {
		//create users
		users = new ArrayList<String>();
		users.add("PHILLIP");
	} 
	@GetMapping(path = "convertKelvineToCelsius")
	public String convertKelvineToCelsius(@RequestParam("value") String kelvine, @RequestParam("username") String username) {
		String operation = "KELVINE_TO_CELSIUS";
		return Business(operation, kelvine, username);
	}
	@GetMapping(path = "convertPoundsToKilogram")
	public String convertPoundsToKilogram(@RequestParam("value") String pound, @RequestParam("username") String username) {
		String operation = "MILES_TO_KILOMETERS";
		return Business(operation, pound, username);
	}
	@GetMapping(path = "convertMilesToKilometers")
	public String convertMilesToKilometers(@RequestParam("value") String miles, @RequestParam("username") String username) {
		String operation = "POUNDS_TO_KILOGRAM";
		return Business(operation, miles, username);
	}
	private String sendErrorMessage(String convertion) {
		return "error performing " + convertion + " operation, please check the value and that you are authorised to use this service";
	}
	public boolean proceed(String value, String username) {
		if(value != null && !value.strip().isEmpty() && auth(username)) 
			return true;
		else 
			return false;
	}

	private boolean auth(String username) {
		if(username.strip().toUpperCase().equals(AUTHORIZED_USER) || users.contains(username.toUpperCase()))
			return true;
		else
			return false;
	}
	private String Business(String method, String value1, String username) {

		if(method.equals(KELVINE_TO_CELSIUS)) {
			double value = 0.0;
			if(proceed(value1, username)) {
				value = Double.parseDouble(value1);
				return header.replace("**", "Kelvine to Celsius: " + (value + KELVINE)+"<hr/> <h3>Instruction ran by : "+ username +"</h3>");
			} else {
				return sendErrorMessage("convertKelvineToCelsius");
			}
		}
		else if(method.equals(POUNDS_TO_KILOGRAM)) {
			double value = 0.0;
			if(proceed(value1, username)) {
				value = Double.parseDouble(value1);
				return header.replace("**", "Pounds to Kilogram: " + (value * POUND)+"<hr/> <h3>Instruction ran by : "+ username +"</h3>");
			} else {
				return sendErrorMessage("convertPoundsToKilogram");
			}
		}
		else if(method.equals(MILES_TO_KILOMETERS)) {

			double value = 0.0;
			if(proceed(value1, username)) {
				value = Double.parseDouble(value1);
				return header.replace("**", "Miles to Kilometers: " + (value * MILES)+"<hr/> <h3>Instruction ran by : "+ username +"</h3>");
			} else {
				return sendErrorMessage("convertMilesToKilometers");
			}
		} 
		return "no operation available";
	}
}
