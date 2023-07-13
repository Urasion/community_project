package community_project.main.dto.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;

@Getter
@RedisHash(value = "view")
public class ViewEntity {
    @Id
    private Long id;

    private Long viewCount;

    public ViewEntity(Long id, Long viewCount) {
        this.id = id;

        this.viewCount = viewCount;
    }
}
