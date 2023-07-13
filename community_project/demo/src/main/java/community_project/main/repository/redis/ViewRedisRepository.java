package community_project.main.repository.redis;

import community_project.main.dto.redis.ViewEntity;
import org.springframework.data.repository.CrudRepository;

public interface ViewRedisRepository extends CrudRepository<ViewEntity, Long> {

}
