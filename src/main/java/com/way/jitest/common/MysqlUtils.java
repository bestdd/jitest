package com.way.jitest.common;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.sql.SqlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 * @author xkm
 * @date 2020/2/24
 */
public class MysqlUtils {
   static Logger log = LoggerFactory.getLogger(MysqlUtils.class);

    /**
     * 查询数据库
     *
     * @param  sql sql语句
     * @return  查询结果集合 key:value
     */
    public static List<Entity> query(String sql){
        Connection conn = null;
        List<Entity> entityList = new ArrayList<Entity>();
        try {
            //获取默认数据源
            DataSource ds = DSFactory.get();
            conn = ds.getConnection();
            /* 执行查询语句，返回实体列表，一个Entity对象表示一行的数据，Entity对象是一个继承自HashMap的对象，存储的key为字段名，value为字段值 */
            entityList = SqlExecutor.query(conn, sql, new EntityListHandler());

        } catch (SQLException e) {
            log.error("sql语句：{}，异常 ",sql,e);
        } finally {
            DbUtil.close(conn);
        }
        return entityList;
    }

    /**
     * 更新数据库
     *
     * @param  sql sql语句
     * @return  影响行数
     */
    public static int update(String sql){
        Connection conn = null;
        int count = 0;
        try {
            //获取默认数据源
            DataSource ds = DSFactory.get();
            conn = ds.getConnection();
            // 执行非查询语句，返回影响的行数
            count = SqlExecutor.execute(conn, sql);
            log.info("sql语句：{}，影响行数：{}",sql, count);

        } catch (SQLException e) {
            log.error("sql语句：{}，异常 ",sql,e);
        } finally {
            DbUtil.close(conn);
        }
        return count;
    }

}
