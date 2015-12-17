package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.bo.TArena;
import com.example.dao.TArenaDao;

public class ArenaService {
private TArenaDao arenaDao;	

public TArenaDao getArenaDao() {
	return arenaDao;
}

public void setArenaDao(TArenaDao arenaDao) {
	this.arenaDao = arenaDao;
}

public TArena getArenaByIdService(Integer id) {
	return arenaDao.load(id, null);
}

public List<TArena> findArenaListService() {
	return arenaDao.find("from TArena", 0);
}

public void updateArena(Integer areanId) {
	TArena arena = arenaDao.load(areanId, -1);
	arena.setName("252410");
	arena.setCreateTime(new Date());
	arenaDao.unSynUpdate(arena, -1);
}

public void saveArena() {
	TArena arena = new TArena("dogdog", 2365, 1, 2, 
			1, new Date());
	arenaDao.saveUnsyn(arena, -1);
	
}

public void deleteArena(Integer areanId) {
	TArena arena = arenaDao.load(areanId, -1);

	arenaDao.unSynRemove(arena, -1);
}


}
