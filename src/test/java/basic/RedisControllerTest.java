package basic;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RedisControllerTest extends TestBase{

    @Test
    public void testRedisContoller() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/temp/set?key=xiaotest&&value=1001"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetAllMeetingRoomDetails() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

    }

    @Test
    public void testGetAllMeetingRoomDetailsAndStoreToRedis() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

    }

    @Test
    public void sendPostToChangeSensorStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sensor?room=Sydney&status=F"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }
}
