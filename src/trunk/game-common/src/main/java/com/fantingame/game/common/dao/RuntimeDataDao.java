package com.fantingame.game.common.dao;

import com.fantingame.game.common.model.RuntimeData;


public interface RuntimeDataDao {

	public boolean add(RuntimeData rumtimeData);

	public RuntimeData get(String key);

	public boolean inc(String key);

	public boolean set(String key, long value);
}
