package model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

public class RoomSource implements Serializable{
    private static final long serialVersionUID = -6372420317355322101L;

    @JsonSerialize
    private String roomName = "";

    @JsonSerialize
    private String sensor= "U";

    @JsonSerialize
    private GoogleCal calendar;

    public RoomSource() {
        super();
        this.roomName = "melbourne";
        this.sensor = "U";
        this.calendar = new GoogleCal();
    }

    public RoomSource setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public RoomSource setSensorStatus(String sensorStatus) {
        this.sensor = sensorStatus;
        return this;
    }

    public RoomSource setCalendar(GoogleCal calendar) {
        this.calendar = calendar;
        return this;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getSensor() {
        return sensor;
    }
}
