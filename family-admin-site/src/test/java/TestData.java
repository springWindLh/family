import lh.family.admin.model.Leaveword;
import lh.family.admin.service.ILeavewordService;
import lh.family.admin.service.INewsService;
import lh.family.admin.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lh on 2016/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class TestData {
    @Autowired
    private INewsService newsService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILeavewordService leavewordService;

    @Test
    public void insertData() {
        for (int i = 0; i < 35; i++) {
//            News news = new News();
//            news.setTitle("新闻标题"+i);
//            news.setContent("新闻内容"+i);
//            news.setStatus(News.Status.ONLINE);
//            newsService.add(news);
            Leaveword leaveword = new Leaveword();
            leaveword.setContent("留言内容" + i);
            leaveword.setUser(userService.findOne(1L).get());
            leavewordService.add(leaveword);
        }
    }
}
