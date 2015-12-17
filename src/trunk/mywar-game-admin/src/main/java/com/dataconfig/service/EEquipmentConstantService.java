package com.dataconfig.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.bo.EEquipmentConstant;
import com.dataconfig.dao.EEquipmentConstantDao;
import com.dataconfig.dao.SystemEquipDao;
import com.framework.common.DBSource;
import com.framework.common.IPage;

public class EEquipmentConstantService {
	
	private EEquipmentConstantDao eEquipmentConstantDao;
	private SystemEquipDao systemEquipDao;

	/**
	 * 查询装备常量分页列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<EEquipmentConstant> findEEquipmentConstantPageList(Integer id, String name, Integer currentPage, Integer pageSize) {
		String str = "from SystemEquip";
		if (id != null) {
			str = "from SystemEquip c where c.equipId = " + id;
		}
		if (name != null && !"".equals(name)) {
			str = "from SystemEquip c where c.equipName like '%" + name + "%'";
		}
//		System.out.println(str);
		eEquipmentConstantDao.closeSession(DBSource.CFG);
		return eEquipmentConstantDao.findPage(str, new ArrayList<Object>(), pageSize, currentPage);
//		return eEquipmentConstantDao.findPage("from EEquipmentConstant c where c.equipmentName like '%"+ name+ "%' and c.equipmentId = "+ id, new ArrayList<Object>(), pageSize, currentPage);
		
		
//		eEquipmentConstantDao.closeSession(DBSource.CFG);
//		return eEquipmentConstantDao.findPage("from EEquipmentConstant", new ArrayList<Object>(), pageSize, currentPage);
	}
	
	/**
	 * 查询装备常量列表
	 * @param currentPage
	 * @param pageSize
	 * @return 
	 * @return
	 */
	public  List<EEquipmentConstant> findEEquipmentConstantList() {
		eEquipmentConstantDao.closeSession(DBSource.CFG);
		return eEquipmentConstantDao.findAll();
	}
	
	/**
	 *  查询所有装备常量的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findEquipmentIDNameMap() {

		Map<Integer, String> equipmentIdNameMap = new LinkedHashMap<Integer, String>();
		systemEquipDao.closeSession(DBSource.CFG);
		List<Object> list = systemEquipDao.findSQL_("SELECT equip_id, name, quality FROM system_equip");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int equipmentId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String equipmentName = ((Object[]) list.get(i))[1].toString()+"_"+((Object[]) list.get(i))[2].toString();
				equipmentIdNameMap.put(equipmentId, equipmentName);
			}
		}
		return equipmentIdNameMap;
	
		
		
//		Map<Integer, String> equipmentIdNameMap = new LinkedHashMap<Integer, String>();
//		List<EEquipmentConstant> equipmentList = this.findEEquipmentConstantList();
//		for (EEquipmentConstant equipment : equipmentList) {
//			equipmentIdNameMap.put(equipment.getEquipmentId(), equipment.getEquipmentName());
//		}
//		return equipmentIdNameMap;
	}
	
	public Map<Integer, String> findSysEquipmentIDNameMap() {

		Map<Integer, String> equipmentIdNameMap = new LinkedHashMap<Integer, String>();
		systemEquipDao.closeSession(DBSource.CFG);
		List<Object> list = systemEquipDao.findSQL_("SELECT equip_id, equip_name FROM system_equip");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int equipmentId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String equipmentName = ((Object[]) list.get(i))[1].toString();
				equipmentIdNameMap.put(equipmentId, equipmentName);
			}
		}
		return equipmentIdNameMap;
	}
	/**
	 *  查询所有装备常量的ID和装备对应的map
	 * @return
	 */
	public Map<Integer, EEquipmentConstant> findIdEquipmentMap() {
		Map<Integer, EEquipmentConstant> idEquipmentMap = new LinkedHashMap<Integer, EEquipmentConstant>();
		List<EEquipmentConstant> equipmentList = this.findEEquipmentConstantList();
		for (EEquipmentConstant equipment : equipmentList) {
			idEquipmentMap.put(equipment.getEquipmentId(), equipment);
		}
		return idEquipmentMap;
	}
	
	/**
	 *  根据条件查到的ID和name对应的map
	 * @return
	 */
	public Map<Integer, String> findEquipmentIdNameMapByCondition(Integer id, String name) {
		Map<Integer, String> equipmentIdNameMap = new LinkedHashMap<Integer, String>();
		StringBuffer sb = new StringBuffer();
		if (id != null) {
			sb.append("SELECT EQUIPMENT_ID, EQUIPMENT_NAME FROM e_equipment_constant WHERE EQUIPMENT_ID = " + id);
		}
		if (name != null && !"".equals(name)) {
			sb.append("SELECT EQUIPMENT_ID, EQUIPMENT_NAME FROM e_equipment_constant WHERE EQUIPMENT_NAME LIKE '%" + name + "%'");
		}
		if (sb.length() == 0) {
			sb.append("SELECT EQUIPMENT_ID, EQUIPMENT_NAME FROM e_equipment_constant");
		}
		eEquipmentConstantDao.closeSession(DBSource.CFG);
		List<Object> list = eEquipmentConstantDao.findSQL_(sb.toString());
		for (int i = 0; i < list.size(); i++) {
			Integer equipmentId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
			String equipmentName = ((Object[]) list.get(i))[1].toString();
			equipmentIdNameMap.put(equipmentId, equipmentName);
		}
		return equipmentIdNameMap;
	}
	
	/**
	 * 查询装备常量数据
	 * @param equipmentId
	 * @return
	 */
	public EEquipmentConstant findOneEEquipmentConstant(Integer equipmentId) {
		return eEquipmentConstantDao.loadBy("equipmentId", equipmentId, DBSource.CFG);
	}
	
	/**
	 * 新增装备常量数据
	 * @param eEquipmentConstant
	 */
	public void addOneEEquipmentConstant(EEquipmentConstant eEquipmentConstant) {
		eEquipmentConstantDao.save(eEquipmentConstant, DBSource.CFG);
	}
	
	/**
	 * 删除装备常量数据
	 * @param equipmentId
	 */
	public void delOneEEquipmentConstant(Integer equipmentId) {
		eEquipmentConstantDao.remove(this.findOneEEquipmentConstant(equipmentId), DBSource.CFG);
	}
	
	/**
	 * 更新装备常量数据
	 * @param eEquipmentConstant
	 */
	public void updateOneEEquipmentConstant(EEquipmentConstant eEquipmentConstant) {
		eEquipmentConstantDao.update(eEquipmentConstant, DBSource.CFG);
	}

	public EEquipmentConstantDao geteEquipmentConstantDao() {
		return eEquipmentConstantDao;
	}

	public void seteEquipmentConstantDao(EEquipmentConstantDao eEquipmentConstantDao) {
		this.eEquipmentConstantDao = eEquipmentConstantDao;
	}

	public SystemEquipDao getSystemEquipDao() {
		return systemEquipDao;
	}

	public void setSystemEquipDao(SystemEquipDao systemEquipDao) {
		this.systemEquipDao = systemEquipDao;
	}
}
