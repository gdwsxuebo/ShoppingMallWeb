package com.gws.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gws.util.StringUtil;
import com.gws.util.commonUtil;
/**
 * 
 * @author Administrator
 * V61 同步数据到POS_SERVER 定时任务
 */
public class TimerSynchV61 {
@Autowired synchv61Service synchv61;
private Logger logger = LoggerFactory.getLogger(TimerSynchV61.class);
public void synchV61Data(){
	ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    long initialDelay = 1;
    long delay = Long.parseLong(StringUtil.getNsyhInfo("synchTime"));
    service.scheduleWithFixedDelay(
            new Runnable() {
				@Override
				public void run() {
					logger.info("自动同步中");
					try {
						synchv61.savesynchStore();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					try {
						synchv61.savesynchStaff();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					try {
						synchv61.savesynchItem();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					try {
						synchv61.savesynchTender();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

				}
			}, initialDelay,
            delay, TimeUnit.HOURS);

}
}
