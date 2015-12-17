package com.framework.plugin;

import java.util.Comparator;

public abstract class SystemAppPluginBase implements ISystemAppPlugin {
	//执行顺序  数字设置的越小 越靠前执行
    private int startOrder;

	public int getStartOrder() {
		return startOrder;
	}

	public void setStartOrder(int startOrder) {
		this.startOrder = startOrder;
	}

	static class SystemAppPluginBaseCompara implements Comparator<SystemAppPluginBase> {
		public int compare(SystemAppPluginBase o1, SystemAppPluginBase o2) {
			// TODO Auto-generated method stub
			 return o1.getStartOrder() > o2.getStartOrder() ? 1 : (o1.getStartOrder() == o2.getStartOrder() ? 0 : -1);
		}
	}
}
