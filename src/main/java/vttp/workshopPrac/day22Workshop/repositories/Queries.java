package vttp.workshopPrac.day22Workshop.repositories;

public class Queries {
    public static final String SQL_GET_ALL_RSPVS ="SELECT * FROM rsvp";
    public static final String SQL_GET_RSVP_BY_NAME ="SELECT * FROM rsvp  WHERE name LIKE ?";
    public static final String SQL_ADD_RSVP = "INSERT INTO rsvp(email, name, phone, confirmation_date, comments) VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_RSVP = "UPDATE rsvp SET name= ?, phone= ?, confirmation_date= ?, comments= ? WHERE email=?";
    public static final String SQL_RSVP_COUNT ="SELECT COUNT(*) AS rsvp_count FROM rsvp";
}
