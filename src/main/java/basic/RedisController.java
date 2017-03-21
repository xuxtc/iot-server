package basic;

import model.RoomSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.DataPaddingService;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class RedisController {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name="redisTemplate")
    ValueOperations<Object,Object> valOps;


    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<RoomSource> GetCalendarEventDetails() {
        return (List<RoomSource>) valOps.get("meetingRoom") ;
    }

    @RequestMapping(value = "/get")
    public List<RoomSource> GetSource() {
        List<RoomSource> res = (List<RoomSource>)valOps.get("meetingRoom");
        res = new DataPaddingService(res).getCalendarDetails();
        valOps.set("meetingRoom",res);

        return  res;
    }

    @RequestMapping(value = "/sensor",method = RequestMethod.POST)
    public List<RoomSource> updateSensorStatus(@RequestParam(value = "room", defaultValue = "Melbourne") String room,
                                   @RequestParam(value = "status", defaultValue = "B") String status ) throws Exception {
        List<RoomSource> res = (List<RoomSource>)valOps.get("meetingRoom");
        res = new DataPaddingService(res).paddingSensorSource(room,status);
        valOps.set("meetingRoom",res);

        return res;
    }
}
