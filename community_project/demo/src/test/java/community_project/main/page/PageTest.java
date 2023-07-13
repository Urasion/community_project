package community_project.main.page;

import community_project.main.repository.BoardRepository;
import community_project.main.service.BoardService;
import community_project.main.util.Criteria;
import community_project.main.util.PageCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PageTest {

    @Test
    void pageTest(){
        Criteria criteria = new Criteria(1, 10);
        PageCreate pageCreate = new PageCreate();
        pageCreate.setBoardAmount(criteria, 1);
        long beginPage = pageCreate.getBeginPage();
        long endPage = pageCreate.getEndPage();
        System.out.println("beginPage = " + beginPage);
        System.out.println("endPage = " + endPage);


    }
}
