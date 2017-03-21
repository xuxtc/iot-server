package service;

import com.google.gson.Gson;
import model.RoomSource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataPaddingServiceTest {
    Gson gson = new Gson();

    @Test
    public void testGetMeetingRoomDetails() throws Exception {
        List<RoomSource> rs = new ArrayList<>();
       System.out.println(new DataPaddingService(rs).getCalendarDetails());
    }
}
