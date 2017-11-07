package Objects;

import java.util.Date;
import java.util.Date;

public class Letter {
    private int id;
    private String header;
    private String body;
    private Date date;
    private int sender_id;
    private int recepient_id;

    private User sender;
    private User recepient;

    public Letter() {
    }

    public Letter(int id, String header, String body, Date date, int sender_id, int recepient_id) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.date = date;
        this.sender_id = sender_id;
        this.recepient_id = recepient_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getRecepient_id() {
        return recepient_id;
    }

    public void setRecepient_id(int recepient_id) {
        this.recepient_id = recepient_id;
    }
}
