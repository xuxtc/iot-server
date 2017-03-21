package basic;

import service.GoogleCalendarService;
import com.google.api.services.calendar.model.FreeBusyResponse;
import com.google.gson.Gson;
import model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class RequestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Gson gson = new Gson();

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public Greeting sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/busyFree")
    public FreeBusyResponse getRoomBusyfree(@RequestParam(value = "name", required = false, defaultValue = "Melbourne") String name) throws IOException {
        return new GoogleCalendarService(name).getFreeBusy();
    }
}