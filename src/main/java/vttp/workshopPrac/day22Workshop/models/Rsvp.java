package vttp.workshopPrac.day22Workshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Rsvp {
    private String email;
    private String name;
    private String phoneNumber;
    private String confirmationDate;
    

    private String comments;

    public String getConfirmationDate() {
            return confirmationDate;
    }
    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Rsvp create(SqlRowSet rs) {
        Rsvp r = new Rsvp();
        r.setEmail(rs.getString("email"));
        r.setName(rs.getString("name"));
        r.setPhoneNumber(rs.getString("phone"));
        r.setConfirmationDate(rs.getString("confirmation_date"));
        r.setComments(rs.getString("comments"));

        return r;

    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("email", getEmail())
                .add("name", getName())
                .add("phoneNumber", getPhoneNumber())
                .add("confirmationDate", getConfirmationDate())
                .add("comments", getComments())
                .build();
    }

}
