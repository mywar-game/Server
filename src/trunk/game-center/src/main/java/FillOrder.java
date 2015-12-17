import java.util.HashMap;
import java.util.Map;

import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;


public class FillOrder {
//    private static int i= 0;
	public static void main(String[] args) {
            String url = "http://192.168.1.158:8089/webApi/fillOrder.do";
    		String timestamp = System.currentTimeMillis()+"";
    		
    		String orderNo ="0120042014021800000004";
    		String partnerOrderId = "111";
    		String key = "098!@#&^%asdfg*&^%589l";
    		String sign = EncryptUtil.getMD5(timestamp + orderNo + partnerOrderId + key);
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("timestamp", timestamp);
			paraMap.put("orderNo", orderNo);
			paraMap.put("partnerOrderId", partnerOrderId);
			paraMap.put("sign", sign);
			String jsonStr = UrlRequestUtils.execute(url, paraMap, UrlRequestUtils.Mode.GET);
	        System.out.println("结果:="+jsonStr);
//		final String url = "http://wapi.andr.hw.fantingame.com/webApi/getServerList.do?fr=356555050810558&mac=5C:F8:A1:34:A9:B7&version=1.0.1.28&partnerId=1001&qn=0";
//		for(int i=0;i<1;i++){
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for(int i=0;i<100000;i++){
//					String jsonStr = UrlRequestUtils.execute(url, new HashMap<String, String>(), UrlRequestUtils.Mode.POST);
//						if(jsonStr==null){
//							System.out.println("结果:=fail"+(i++));
//						}
//					}
//				}
//			}).start();
//		}
//		
//		try {
//			Thread.sleep(1000000000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

}
