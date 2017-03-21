package model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class GoogleCal implements Serializable{
    private static final long serialVersionUID = -9055889177407528084L;

    @JsonSerialize
    private String status = "U";

    @JsonSerialize
    private String title = "";

    @JsonSerialize
    private String booker = "";

    @JsonSerialize
    private String desc = "";

    public GoogleCal setStatus(String status) {
        this.status = status;
        return this;
    }

    public GoogleCal setTitle(String title) {
        this.title = title;
        return this;
    }

    public GoogleCal setBooker(String booker) {
        this.booker = booker;
        return this;
    }

    public GoogleCal setDescription(String description) {
        this.desc = description;
        return this;
    }
}

