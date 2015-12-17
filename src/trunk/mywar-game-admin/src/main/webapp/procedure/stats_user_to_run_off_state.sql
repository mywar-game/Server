DELIMITER $$

USE `sns_cn_log`$$

DROP PROCEDURE IF EXISTS `stats_user_to_run_off_state`$$

CREATE DEFINER=`programmer`@`%` PROCEDURE `stats_user_to_run_off_state`(IN s_time DATE)
BEGIN
	##声明变量
	
	##用户ID
	DECLARE USER_ID INT(11);
	##用户名
	DECLARE USERNAME VARCHAR(32);
	##总活动时间
	DECLARE LIVETIMESUM DOUBLE DEFAULT 0.0;
	##流失等级 默认最低等级1
	DECLARE GRADE INT DEFAULT 1;
	##是否完成新手向导 0表示没有，1表示有 默认是0没有
	DECLARE IS_COMPLETE INT DEFAULT 0;
	##是否进行一次战斗 0表示没有，1表示有 默认是0没有
	DECLARE IS_BATTLE INT DEFAULT 0;
	##是否建造五个建筑以上 0表示没有，1表示有 默认是0没有
	DECLARE IS_CREATE_BULDING INT DEFAULT 0;
	##临时变量
	DECLARE TEMP VARCHAR(36) DEFAULT NULL;
	
	##控制游标循环 变量
	DECLARE is_found INT DEFAULT 0;
	
	##声明游标
	DECLARE  find_cursor CURSOR FOR SELECT p.USER_ID,p.USERNAME,SUM(p.LIVETIME) FROM p_logout_log p WHERE CONVERT(p.TIME,DATE)=s_time AND NOT EXISTS(SELECT p1.ID FROM p_logout_log p1 WHERE CONVERT(p1.TIME,DATE)>s_time AND p.USER_ID=p1.USER_ID GROUP BY p1.USER_ID) GROUP BY p.USER_ID ; 
	##声明游标结束条件,当没有数据的时候is_found = 1;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET is_found = 1;
	
	##打开游标
	OPEN find_cursor;
	
	
	
	##迭代游标
	REPEAT
		##抓取数据
		FETCH find_cursor INTO USER_ID,USERNAME,LIVETIMESUM;
		
		IF USER_ID IS NOT NULL THEN 
		   ##查询流失玩家的最高等级
		   SELECT MAX(p.GRADE) INTO GRADE FROM p_upgrade_log p WHERE p.USER_ID = USER_ID;
			IF  GRADE IS NULL THEN 
				SET GRADE = 1;
			END IF;
			
		   ##查询流失玩家是否完成新手向导
		   SELECT COUNT(p.USER_ID) INTO IS_COMPLETE FROM p_cng_log p WHERE p.USER_ID = USER_ID;
			##判断是否完成新手向导
			IF IS_COMPLETE IS NOT NULL THEN 
				SET IS_COMPLETE = 1;
			END IF;
			
		   ##查询流失玩家是否进行一次战斗
		   SELECT COUNT(p.ID) INTO IS_BATTLE FROM p_battle_Log p WHERE p.USER_ID = USER_ID;
			IF IS_BATTLE IS NOT NULL  THEN
				SET IS_BATTLE = 1;
			END IF;
		   
		   ##查询流失玩家是否建造五个建筑以上
		   SELECT COUNT(p.ID) INTO IS_CREATE_BULDING FROM p_creatbuilding_log p WHERE p.USER_ID =USER_ID;
			IF IS_CREATE_BULDING>5 THEN
				SET IS_CREATE_BULDING = 1;
			END IF;
		   
		   ##插入数据
		   INSERT INTO sns_admin.p_user_stats(sns_admin.p_user_stats.TIME,sns_admin.p_user_stats.USERNAME,sns_admin.p_user_stats.LIVE_SUM_TIME,sns_admin.p_user_stats.GRADE,sns_admin.p_user_stats.IS_NEWHAND_TASK,sns_admin.p_user_stats.IS_ONE_BATTLE,sns_admin.p_user_stats.IS_BUILDING_FIVE)
		    VALUES(CURDATE(),USERNAME,LIVETIMESUM,GRADE,IS_COMPLETE,IS_BATTLE,IS_CREATE_BULDING);
		END IF;
		
	   ## 循环结束
	   UNTIL is_found
	   
	END REPEAT;
	## 关闭游标
	CLOSE find_cursor;
    END$$

DELIMITER ;