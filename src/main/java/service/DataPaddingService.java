package service;

import java.lang.Exception;

import model.GoogleCal;
import model.RoomSource;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataPaddingService {
    private List<RoomSource> sources = new ArrayList<>();

    public DataPaddingService(List<RoomSource> inputSource) {
        if (inputSource != null) {
            sources = inputSource;
        }
    }

    public List<RoomSource> paddingSensorSource(String roomName, String sensorStatus) throws Exception {
        if (sources == null) {
            throw new Exception("No meeting room resources found");
        }

        int index = 0;

        for (RoomSource source : sources) {
            if (source.getRoomName().equals(roomName)) {
                source.setSensorStatus(sensorStatus);
                sources.set(index, source);
            }

            index++;
        }

        return sources;
    }

    private GoogleCal paddingCalendarSource(String roomName) {
        GoogleCal gcm = new GoogleCal();

        try {
            Events events = new GoogleCalendarService(roomName).getCalenderList();
            List<Event> items = events.getItems();

            if (items.size() != 0) {
                for (Event event : items) {
                    if (event.getSummary() == null) {
                        gcm.setStatus("F");
                    } else {
                        gcm.setStatus("B");
                        gcm.setTitle(event.getSummary());
                        gcm.setBooker(event.getCreator().getEmail());
                        gcm.setDescription(event.getDescription());
                    }
                }
            } else {
                gcm.setStatus("F");
            }
        } catch (IOException e) {
            gcm.setStatus("U");
        }

        return gcm;
    }

    private RoomSource paddingRoomSource(String roomName) {
        RoomSource rsm = new RoomSource();

        /*************************************************************************/
        /**this is for showcase only, remove it if sensor works okay.**/
        sources.stream().filter(source -> source.getRoomName().equals(roomName))
                .forEach(source -> rsm.setSensorStatus(source.getSensor()));
        /*************************************************************************/


        rsm.setRoomName(roomName);
        rsm.setCalendar(paddingCalendarSource(roomName));

        return rsm;
    }

    public List<RoomSource> getCalendarDetails() {
        List<RoomSource> response = new ArrayList<>();
        response.addAll(RoomMapper.roomNameMapper().keySet().stream().map(roomShortName -> paddingRoomSource(roomShortName)).collect(Collectors.toList()));
        return response;
    }
}
