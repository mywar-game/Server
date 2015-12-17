package com.dataconfig.bean;


public class MissionPrize {
	/**奖励id**/
	private int id;
	/**1资源 2装备 3道具**/
	private int category;
	/**category为1时1代表金钱2矿石3粮食4钻石5声望  2时是装备常量id 3时是道具常量id**/
	private int targetId;
	/**奖励物品类型1固定奖励2可选奖励**/
	private int type;
	/**奖励数量 **/
	private int num;
	
	public MissionPrize(int category, int targetId, int type, int num) {
		super();
		this.category = category;
		this.targetId = targetId;
		this.type = type;
		this.num = num;
	}

	public MissionPrize() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {
//		List<Goods> list = new ArrayList<Goods>();
//		Goods goods1 = new Goods(1,2,3);
//		Goods goods2 = new Goods(4,5,6);
//		list.add(goods1);
//		list.add(goods2);
//		String str1 = "{'money':100,'wood':10}";
//		
//		Prize prize1 = getPrize(str1);
//		Prize prize = new Prize(1,2,3,list,5);
//		//javabean----->json
//		JSONObject js = JSONObject.fromObject(prize);
//		
//		System.out.println(js.toString());
//		//json--->string
//		String str = js.toString();
//		
//		//string-->json
//		JSONObject jsBean = JSONObject.fromObject(str);
//		
//		
//		//json--->javaBean
//		
//		Map<String, Class> m = new HashMap<String, Class>();
//		m.put("list", Goods.class);
//		Prize my = (Prize)JSONObject.toBean(jsBean,Prize.class,m);
//		
//		System.out.println(my.getList().get(0).getGoodsID());
//		
//		System.out.println("cc");
//		
//		//-----------------LIST example---------------------//
//		List<Condition> listCondition = new ArrayList<Condition>();
//		String string = "[{'id':1,'num':2}]";
//		Condition condition1 = new Condition(1, 2, 3, 4, 5, "1");
//		Condition condition2 = new Condition(6, 7, 8, 9, 10, "1");
//		listCondition.add(condition1);
//		listCondition.add(condition2);
//		JSONArray jsList = JSONArray.fromObject(listCondition);
//		JSONArray jsList1 = JSONArray.fromObject(string);
//		System.out.println("adsfafd"+jsList.toString());
//		System.out.println(jsList1.toString());
//		
//		String jsArrayStr = jsList.toString();
//		
//		List<Condition> c = JSONArray.toList(jsList, Condition.class);
		
//		System.out.println(c.size());
		
	}
	
}
