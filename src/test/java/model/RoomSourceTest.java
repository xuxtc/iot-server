package model;

import com.google.gson.Gson;
import model.GoogleCal;
import model.RoomSource;
import org.junit.Test;

public class RoomSourceTest {
    Gson gson = new Gson();

    @Test
    public void testRoomSource() {
        System.out.print(gson.toJson(new RoomSource()));
    }

    @Test
    public void testRoomSourceWithParameters() {
        GoogleCal gcm = new GoogleCal();
        RoomSource rsm = new RoomSource();

        gcm.setBooker("booker");
        gcm.setStatus("F");
        gcm.setTitle("Title");
        gcm.setDescription("descriotion bhahahaha");

        rsm.setCalendar(gcm);
        rsm.setRoomName("Melbourne");
        rsm.setSensorStatus("U");

        System.out.print(gson.toJson(rsm));
    }


}
