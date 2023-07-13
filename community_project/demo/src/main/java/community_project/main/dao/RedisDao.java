package community_project.main.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisDao {
    private final RedisTemplate<String, String> redisTemplate;
    public void setValues(String key, String data){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }
}
