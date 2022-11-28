package vttp.workshopPrac.day22Workshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Day22WorkshopApplication  {

	public static void main(String[] args) {
		SpringApplication.run(Day22WorkshopApplication.class, args);
	}

	// @Override
	// public void run(String... args) {
	// 	// String email = args[2];
	// 	// String name = args[3];
	// 	// String phone = args[4];
	// 	// String confirmationDate = args[5];
	// 	// String comments =args[6];

	// 	MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
    //     form.add("email","look@look.com");
    //     form.add("name", "look");
    //     form.add("phone", "787878");
    //     form.add("confirmation_date", "2022-12-12");
    //     form.add("comments", "picky eater");

    //     //construct the request
    //     RequestEntity<MultiValueMap<String, String>> req = RequestEntity
    //                                                     .post("http://localhost:8080/api/rsvp")
    //                                                     .accept(MediaType.TEXT_HTML)
    //                                                     .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //                                                     .body(form);

	// 	//Create RestTemplate
	// 	RestTemplate template = new RestTemplate();

	// 	//Make Request
	// 	ResponseEntity<String> resp = template.exchange(req, String.class);

	// 	String payload = resp.getBody();
	// 	System.out.printf(">>>> Inserted following record: %s\n", payload);

	// 	System.exit(0);
	// }
	

}
