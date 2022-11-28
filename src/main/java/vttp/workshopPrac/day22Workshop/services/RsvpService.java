package vttp.workshopPrac.day22Workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.workshopPrac.day22Workshop.models.Rsvp;
import vttp.workshopPrac.day22Workshop.repositories.rsvpRepository;

@Service
public class RsvpService {

    @Autowired
    private rsvpRepository rsvpRepo;

    public boolean createRsvp (final Rsvp rsvp) throws Exception {
        
        int count = rsvpRepo.createRsvp(rsvp);
        System.out.printf("Insert Count: %d\n", count);
        return count > 0;
    }

    public boolean updateRsvp(final Rsvp rsvp, String email) throws Exception {
        int count = rsvpRepo.updateRsvp(rsvp, email);
        System.out.printf("Update count: %d\n", count);
        return count>0;
    }

    

    
}
