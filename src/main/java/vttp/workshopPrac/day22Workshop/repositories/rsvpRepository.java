package vttp.workshopPrac.day22Workshop.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.workshopPrac.day22Workshop.models.Rsvp;
import static vttp.workshopPrac.day22Workshop.repositories.Queries.*; 

@Repository
public class rsvpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rsvp> getAllRsvp() {

        //perofrm the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_RSPVS);

        final List<Rsvp> rsvps = new LinkedList<>();
        //Populate list 
        while (rs.next()) {
            rsvps.add(Rsvp.create(rs));
        }
        return rsvps;
        
    }

    public List<Rsvp> getRsvpByName(String name) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_RSVP_BY_NAME, "%%%s%%".formatted(name));

        final List<Rsvp> rsvps  = new LinkedList<>();
        while(rs.next()) {
            rsvps.add(Rsvp.create(rs));
        }
        return rsvps;
    }

    public Integer getRsvpCount() {
        //Query the db
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_RSVP_COUNT);

        int rsvpCount =  0;

        while (rs.next()) {
            rsvpCount = rs.getInt("rsvp_count");
        }

        return rsvpCount;
    }

    public Integer createRsvp(Rsvp rsvp) throws Exception {
        return jdbcTemplate.update(SQL_ADD_RSVP, 
                                rsvp.getEmail(),
                                rsvp.getName(),
                                rsvp.getPhoneNumber(),
                                rsvp.getConfirmationDate(),
                                rsvp.getComments());
    }

    public Integer updateRsvp(Rsvp rsvp, String email) throws Exception {
        return jdbcTemplate.update(SQL_UPDATE_RSVP,
                            rsvp.getName(),
                            rsvp.getPhoneNumber(),
                            rsvp.getConfirmationDate(),
                            rsvp.getComments(),
                            email);
    }
    
}
