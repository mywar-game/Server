package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.util.DateUtil;
import com.system.bo.Command;
import com.system.dao.CommandDao;

/**
 * 通知Service
 * 
 * @author yezp
 */
public class CommandService {

	private CommandDao commandDao;
	
	public List<Command> getCommandList(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.game_web_id,a.server_id,a.current_cmd,a.current_cmd_status,a.exec_info,a.created_time FROM command a right join (select server_id as serverId,max(created_time) as maxTime from command group by serverId) b on a.server_id=b.serverId and a.created_time=b.maxTime");
		commandDao.closeSession(DBSource.ADMIN);
		List<Object> list = commandDao.findSQL_(sql.toString());
		List<Command> res = new ArrayList<Command>();
		if(list!=null && list.size()>0){
			for(Object obj : list){
				Object[] totalArr = (Object[])obj;
				Command cmd = new Command();
				cmd.setGameWebId(Integer.parseInt(totalArr[0].toString()));
				cmd.setServerId(totalArr[1].toString());
				cmd.setCurrentCmd(Integer.parseInt(totalArr[2].toString()));
				cmd.setCurrentCmdStatus(Integer.parseInt(totalArr[3].toString()));
				cmd.setExecInfo(totalArr[4].toString()==null?"":totalArr[4].toString());
				cmd.setCreatedTime(DateUtil.stringtoDate(totalArr[5].toString(), DateUtil.FORMAT_ONE));
				res.add(cmd);
			}
		}
		return res;
	}
	
	public List<Command> getCommandDetails(String serverId){
		return commandDao.find("from Command as a where a.serverId = '"+serverId+"' order by a.createdTime desc", DBSource.ADMIN);
	}

	/**
	 * 添加命令
	 */
	public void addCommand(Command command) {
		commandDao.save(command, DBSource.ADMIN);
	}
	
	
	/**
	 * 更新命令
	 */
	public void update(Command command) {
		commandDao.closeSession(DBSource.ADMIN);
		commandDao.saveOrUpdate(command);
	}


	public CommandDao getCommandDao() {
		return commandDao;
	}

	public void setCommandDao(CommandDao commandDao) {
		this.commandDao = commandDao;
	}


}
