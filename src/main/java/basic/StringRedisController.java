package basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@RestController
public class StringRedisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringRedisController.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name="stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;

    @RequestMapping("/temp/set")
    public String setKeyAndValue(String key,String value){
        LOGGER.debug("访问set:key={},value={}",key,value);
        valOpsStr.set(key, value);
        return "Set Ok";
    }

    @RequestMapping("/temp/get")
    public String getKey(String key){
        LOGGER.debug("访问get:key={}",key);
        return valOpsStr.get(key);
    }
}
