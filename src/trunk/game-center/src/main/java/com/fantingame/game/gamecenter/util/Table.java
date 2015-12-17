package com.fantingame.game.gamecenter.util;

public class Table {

	public static void main(String[] args) {

		for (int i = 0; i < 128; i++) {

			/*
			 * String table1 = "CREATE TABLE `user_copper_use_log_{index}` (" +
			 * "`log_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID'," +
			 * "`user_id` CHAR(32) NOT NULL COMMENT '用户ID'," +
			 * "`use_type` INT(11) NOT NULL COMMENT '使用类型'," +
			 * "`amount` INT(11) NOT NULL COMMENT '数量'," +
			 * "`flag` TINYINT(4) NOT NULL COMMENT '标志(-1 消耗  1 收入)'," +
			 * "`created_time` DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',"
			 * + "PRIMARY KEY (`log_id`)" + ")" + "COLLATE='utf8_general_ci'" +
			 * "ENGINE=InnoDB" + " AUTO_INCREMENT=0;";
			 */
			/*
			 * String table1 = "CREATE TABLE `user_online_log_{index}` (" +
			 * "`log_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID'," +
			 * "`login_time` DATETIME NOT NULL COMMENT '登录入时间'," +
			 * "`logout_time` DATETIME NOT NULL COMMENT '登出时间'," +
			 * "`user_id` CHAR(32) NOT NULL COMMENT '用户ID'," +
			 * "`user_ip` VARCHAR(50) NULL DEFAULT NULL," +
			 * "PRIMARY KEY (`log_id`)" + ")" + "COLLATE='utf8_general_ci'" +
			 * "ENGINE=InnoDB" + " AUTO_INCREMENT=1;";
			 */;

//			String table1 = "drop table if exists user_tower_floor_{index};create table user_tower_floor_{index} ("
//						  + "user_id              varchar(40) not null comment '用户ID',"
//						  + "floor                int not null comment '楼层',"
//						  + "pos                  int not null comment '位置',"
//			 			  + "map_id               int not null comment '地图ID',"
//						  + "obj_type             int not null comment '对象类型，0怪物、1宝箱',"
//						  + "passed               int not null comment '是否通过',"
//						  + "obj_id               int not null comment '对象ID',"
//						  + "created_time         datetime not null,"
//						  + "updated_time         datetime,"
//						  + "primary key (user_id, floor, pos)"
//						  + ");";

//			 String table1 = "drop table if exists user_tower_{index};create table user_tower_{index} ("
//				 		   + "user_id              varchar(40) not null comment '用户ID',"
//				 		   + "stage                int not null comment '关卡ID',"
//				 		   + "floor                int not null comment '楼层ID',"
//				 		   + "status               int not null comment '状态,-1失败,0正在打,1通关',"
//				 		   + "times                int not null comment '次数',"
//				 		   + "created_time         datetime not null,"
//				 		   + "updated_time         datetime not null comment '更新时间',"
//				 		   + "primary key (user_id, stage));";		
			String table = "alter table user_tool_use_log_{index} add index created_time_idx(`created_time`);";
			System.out.println(table.replace("{index}", String.valueOf(i)));

		}

	}
}
