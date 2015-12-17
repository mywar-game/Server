package com.fandingame.game.version.dao;

import com.fandingame.game.version.model.PackageExtinfo;


public interface PackageExtinfoDao {
	public PackageExtinfo getByVersion(String version, String partnerId);
}
