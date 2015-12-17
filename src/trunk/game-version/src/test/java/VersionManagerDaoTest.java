import com.fandingame.game.framework.context.ServiceCacheFactory;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.testcore.DBTest;
import com.fandingame.game.version.dao.VersionManagerDao;
import com.fandingame.game.version.model.VersionManagerApk;


public class VersionManagerDaoTest extends DBTest {
     public void testGetApkVersion(){
    	 VersionManagerDao dao = ServiceCacheFactory.getServiceCache().getService(VersionManagerDao.class);
    	 VersionManagerApk apk1 = dao.getLastApkVersion("2001", "0");
    	 VersionManagerApk apk2 = dao.getOfficialApkVersion("2001", "0");
    	 LogSystem.info(apk1.getDescription());
    	 LogSystem.info(apk2.getDescription());
     }
}
