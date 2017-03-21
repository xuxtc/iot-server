package service;


import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.Calendar;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleCalendarService {
    private DateTime now;
    private DateTime tmr;
    private DateTime maxtime;
    private final String id;
    private static final int MAX_COUNT = 10;
    private static final String EVENT_ORDER = "startTime";
    private static final String TIME_ZONE = "UTC+08:00";

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "Google GoogleCalendarService API Java Quickstart";

    /**
     * Directory to store user credentials for this application.
     */
    private static final String DATA_STORE_PATH = ".credentials/calendar-java-quickstart";

    private Calendar service() throws IOException {
        GoogleAuthorizationService auth = new GoogleAuthorizationService();
        auth.setDataDir(DATA_STORE_PATH);
        return auth.getCalendarService(APPLICATION_NAME);
    }

    public GoogleCalendarService(String room) {
        this.id = RoomMapper.getRoomID(room);
        this.now = new DateTime(System.currentTimeMillis());
        this.tmr = new DateTime(System.currentTimeMillis() + 86400000);
        this.maxtime = new DateTime(System.currentTimeMillis() + 2000);
    }

    public FreeBusyResponse getFreeBusy() throws IOException {
        List<FreeBusyRequestItem> items = new ArrayList<>();
        FreeBusyRequest freeBusyRequest = new FreeBusyRequest();

        items.add(new FreeBusyRequestItem().setId(id));

        freeBusyRequest.setCalendarExpansionMax(MAX_COUNT);
        freeBusyRequest.setGroupExpansionMax(MAX_COUNT);
        freeBusyRequest.setTimeMin(now);
        freeBusyRequest.setTimeMax(tmr);
        freeBusyRequest.setTimeZone(TIME_ZONE);
        freeBusyRequest.setItems(items);

        return service().freebusy().query(freeBusyRequest).execute();
    }

    public Events getCalenderList() throws IOException {
        return service().events().list(id)
                .setTimeMin(now)
                .setTimeMax(maxtime)
                .setSingleEvents(true)
                .setOrderBy(EVENT_ORDER)
                .setTimeZone(TIME_ZONE)
                .execute();
    }

}
