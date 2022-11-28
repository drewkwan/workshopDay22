package vttp.workshopPrac.day22Workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.workshopPrac.day22Workshop.models.Rsvp;
import vttp.workshopPrac.day22Workshop.repositories.rsvpRepository;
import vttp.workshopPrac.day22Workshop.services.RsvpService;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RsvpRESTController {
    
    @Autowired 
    private rsvpRepository rsvpRepo;

    @Autowired 
    private RsvpService rsvpService;

    @GetMapping(path="/rsvps") 
    public ResponseEntity<String>  getAllRsvps() {
        //Query the db
        List<Rsvp> rsvps = rsvpRepo.getAllRsvp();

        //Build the result 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Rsvp r:rsvps) {
            arrayBuilder.add(r.toJSON());
        }
        JsonArray result = arrayBuilder.build();
        
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }

    //Task 2
    @GetMapping(path="/rsvp") 
    public ResponseEntity<String> getRsvpByName(@RequestParam String name) {
        List<Rsvp> rsvps = rsvpRepo.getRsvpByName(name);
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Rsvp r: rsvps) {
            arrayBuilder.add(r.toJSON());
        }
        JsonArray result = arrayBuilder.build();
        if (result.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                                .add("error", "no such rsvp exists for %s".formatted(name))
                                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }
    }


    //Task 3
    @PostMapping(path="/rsvp", consumes =MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public ResponseEntity<String> createRsvp(@RequestBody MultiValueMap<String, String> form) throws Exception {

        Rsvp rsvp = new Rsvp();
        rsvp.setName(form.getFirst("name"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setPhoneNumber(form.getFirst("phoneNumber"));
        rsvp.setConfirmationDate(form.getFirst("confirmationDate"));
        rsvp.setComments(form.getFirst("comments"));
        //Query the db
        if(!rsvpService.createRsvp(rsvp)) {
            JsonObject err = Json.createObjectBuilder()
                                .add("error", "rsvp failed for".formatted(rsvp.getName()))
                                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        } else {

        return new ResponseEntity<String>("Thank you for rsvp-ing!", HttpStatus.OK);
        }
       
    }

    @PutMapping(path="/rsvp/{email}", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> updateRsvp(@RequestParam MultiValueMap<String,String> form, @RequestParam String email) throws Exception{
        Rsvp rsvp = new Rsvp();
        rsvp.setName(form.getFirst("name"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setPhoneNumber(form.getFirst("phoneNumber"));
        rsvp.setConfirmationDate(form.getFirst("confirmationDate"));
        rsvp.setComments(form.getFirst("comments"));
        //Query the db
        if(!rsvpService.updateRsvp(rsvp, email)) {
            JsonObject err = Json.createObjectBuilder()
                                .add("error", "rsvp failed to update for".formatted(rsvp.getEmail()))
                                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        } else {

        return new ResponseEntity<String>("Your rsvp has been updated!", HttpStatus.OK);
        }
    }

    @GetMapping(path="/rsvps/count")
    public ResponseEntity<String> getRsvpCount() {
        //Query the db
        Integer rsvpCount = rsvpRepo.getRsvpCount();

        //Build the result 
        JsonObject result = Json.createObjectBuilder().add("rsvp count", rsvpCount).build();
        
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }



}
