package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**世界Boss信息**/
public class WorldBossInfoBO implements ICodeAble {

		/**boss最大生命值**/
	private Long maxLife=0l;
	/**boss当前生命值**/
	private Long currentLife=0l;
	/**开启时间**/
	private Long openTime=0l;
	/**持续时间**/
	private Long continueTimes=0l;
	/**boss等级**/
	private Integer bossLevel=0;
	/**地图id**/
	private Integer mapId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeLong(maxLife);

		outputStream.writeLong(currentLife);

		outputStream.writeLong(openTime);

		outputStream.writeLong(continueTimes);

		outputStream.writeInt(bossLevel);

		outputStream.writeInt(mapId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		maxLife = inputStream.readLong();

		currentLife = inputStream.readLong();

		openTime = inputStream.readLong();

		continueTimes = inputStream.readLong();

		bossLevel = inputStream.readInt();

		mapId = inputStream.readInt();


	}
	
		/**boss最大生命值**/
    public Long getMaxLife() {
		return maxLife;
	}
	/**boss最大生命值**/
    public void setMaxLife(Long maxLife) {
		this.maxLife = maxLife;
	}
	/**boss当前生命值**/
    public Long getCurrentLife() {
		return currentLife;
	}
	/**boss当前生命值**/
    public void setCurrentLife(Long currentLife) {
		this.currentLife = currentLife;
	}
	/**开启时间**/
    public Long getOpenTime() {
		return openTime;
	}
	/**开启时间**/
    public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}
	/**持续时间**/
    public Long getContinueTimes() {
		return continueTimes;
	}
	/**持续时间**/
    public void setContinueTimes(Long continueTimes) {
		this.continueTimes = continueTimes;
	}
	/**boss等级**/
    public Integer getBossLevel() {
		return bossLevel;
	}
	/**boss等级**/
    public void setBossLevel(Integer bossLevel) {
		this.bossLevel = bossLevel;
	}
	/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	
	
}
