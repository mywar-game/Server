package com.framework.scheduler;

import org.quartz.Job;

import com.framework.plugin.IAppPlugin;
/**
 * 调度接口
 * @author mengchao
 *
 */
public interface IScheduler extends Job, IAppPlugin {
 
}
