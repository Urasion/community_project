package community_project.main.redis;

import community_project.main.dto.redis.ViewEntity;
import community_project.main.repository.redis.ViewRedisRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ViewRedisRepository viewRedisRepository;

    @Test
    void redisConnectionTest() {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String s = valueOperations.get(key);
        Assertions.assertThat(s).isEqualTo(data);
    }
    @Test
    void redisEntityTest(){
        ViewEntity temp = new ViewEntity(1L, 1L);
        viewRedisRepository.save(temp);



    }
}
