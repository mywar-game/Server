package com.dataconfig.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataconfig.dao.SystemArtifactDao;
import com.framework.common.DBSource;

/**
 * 神器Service
 * 
 * @author Administrator
 */
public class SystemArtifactService {

	private SystemArtifactDao systemArtifactDao;

	public Map<Integer, String> findArtifactIDNameMap() {

		Map<Integer, String> artifactIdNameMap = new LinkedHashMap<Integer, String>();
		systemArtifactDao.closeSession(DBSource.CFG);
		List<Object> list = systemArtifactDao
				.findSQL_("SELECT artifact_id, name FROM system_artifact");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int artifactId = Integer.parseInt(((Object[]) list.get(i))[0].toString());
				String name = ((Object[]) list.get(i))[1].toString();
				artifactIdNameMap.put(artifactId, name);
			}
		}

		return artifactIdNameMap;
	}

	public SystemArtifactDao getSystemArtifactDao() {
		return systemArtifactDao;
	}

	public void setSystemArtifactDao(SystemArtifactDao systemArtifactDao) {
		this.systemArtifactDao = systemArtifactDao;
	}

}
