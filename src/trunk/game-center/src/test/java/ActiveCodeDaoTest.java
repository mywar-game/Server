import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.testcore.DBTest;
import com.fantingame.game.gamecenter.dao.NoticeDao;
import com.fantingame.game.gamecenter.model.Notice;


public class ActiveCodeDaoTest extends DBTest {
     public void testWay(){
    	 NoticeDao dao = ServiceCacheFactory.getServiceCache().getService(NoticeDao.class) ;
    	 Notice notice = dao.getNotice("s1");
    	 LogSystem.info(notice.getContent());
     }
}
