package basic;

import com.google.gson.Gson;
import model.Greeting;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import service.GoogleCalendarService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RequestControllerTest extends TestBase {

    @Test
    public void testSayHello() throws Exception {
        Greeting greeting = new Greeting(1, String.format("Hello, %s!", "Stranger"));
        Gson gson = new Gson();

        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(greeting)));
    }

    @Test
    public void testGetFreebusy() throws Exception {
        String room = "Sydney";

        mockMvc.perform(MockMvcRequestBuilders.get("/busyFree?name=" + room))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Test
    public void testGetCalenderList() throws Exception {
        GoogleCalendarService gc = new GoogleCalendarService("Sydney");
        System.out.println(gc.getCalenderList());
    }


}