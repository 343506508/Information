package com.project.repository.base;

import com.project.component.base.BaseComponent;
import com.project.tools.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2017/12/28.
 * Base : 是Dao类的基类
 */

@Repository
public class BaseRepository {
    Log log = LogFactory.getLog(BaseRepository.class);
    @Autowired
    //@Qualifier("primaryNamedParameterJdbcTemplate")//在多数据源的时候用
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    //@Qualifier("primaryJdbcTemplate")//在多数据源的时候用
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 新增
     * Param : sql , t=>实体对象
     * Success : 返回id
     * Error : 返回-1
     */
    public <T extends BaseComponent> Integer insert(String sql, T t){
        try {
            KeyHolder keyholder = new GeneratedKeyHolder();
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(t);
            namedParameterJdbcTemplate.update(sql, namedParameters,keyholder);
            Integer pk = keyholder.getKey().intValue();
            return pk;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    /**
     * 新增2
     * Param : t=>实体对象
     * Success : 返回id
     * Error : 返回-1
     */
    public <T extends BaseComponent> Integer insert2(T t){
        try {
            String sql = makeSql4Insert(t.getClass().getSimpleName());
            KeyHolder keyholder = new GeneratedKeyHolder();
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(t);
            namedParameterJdbcTemplate.update(sql, namedParameters,keyholder);
            Integer pk = keyholder.getKey().intValue();
            return pk;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    /**
     * 新增3
     * Param : t=>实体对象
     * Success : 返回id
     * Error : 返回-1
     * 注意 : 返回类型不同
     */
    public <T extends BaseComponent> Integer insert3(T t){
        try {
            String sql = makeSql4Insert("t_"+t.getClass().getSimpleName());
            KeyHolder keyholder = new GeneratedKeyHolder();
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(t);
            namedParameterJdbcTemplate.update(sql, namedParameters,keyholder);
            Integer pk = keyholder.getKey().intValue();
            return pk;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    //根据表名生成insert2 sql
    public String makeSql4Insert(String table){
        List<String> cols = getTableColumns(table);//共用方法
        final StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(table);
        sql.append("(");
        StringBuilder colStr = new StringBuilder();
        for(int i=1;i<cols.size();i++){
            colStr.append(cols.get(i)).append(",");
        }
        sql.append(colStr.substring(0,colStr.length()-1));
        sql.append(")");
        sql.append(" values( ");
        StringBuilder colStr2 = new StringBuilder();
        for(int i=1;i<cols.size();i++){
            String colVal = cols.get(i);
            if(colVal != null && colVal.equalsIgnoreCase("cdate")){//处理插入时间，直接获取系统时间
                colStr2.append("now()").append(",");
            }else{
                colStr2.append(":").append(cols.get(i)).append(",");
            }
        }
        sql.append(colStr2.substring(0,colStr2.length()-1));
        sql.append(" ) ");
        return sql.toString();
    }

    /**
     * 修改
     * Param : sql , params => 数组对象
     * Success : 返回0
     * Error : 返回-1
     */
    public int update(String sql,Object[] params){
        try {
            jdbcTemplate.update(sql, params);
            return 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    /**
     * 修改2
     * Param : sql , t=>实体对象
     * Success : 返回0
     * Error : 返回-1
     */
    public <T extends BaseComponent> int update2(String sql,T t){
        try {
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(t);
            namedParameterJdbcTemplate.update(sql, namedParameters);
            return 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    /**
     * 修改3
     * Param : t=>实体对象
     * Success : 返回0
     * Error : 返回-1
     * 注意 : 返回类型不同
     */
    public <T extends BaseComponent> int update3(T t){
        try {
            String sql = makeSql4Update(t.getClass().getSimpleName());
            SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(t);
            namedParameterJdbcTemplate.update(sql, namedParameters);
            return 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    //根据表名生成update2 sql
    public String makeSql4Update(String table){
        List<String> cols = getTableColumns(table);//共用方法
        final StringBuilder sql = new StringBuilder();
        sql.append("update ");
        sql.append(table);
        sql.append(" set ");
        StringBuilder s = new StringBuilder();
        for(int i=1;i<cols.size();i++){
            String colName = cols.get(i);
            s.append(colName).append(" = ").append(":").append(colName).append(",");
        }
        sql.append(s.substring(0,s.length()-1));
        sql.append(" where ").append(cols.get(0)).append(" = ").append(":").append(cols.get(0));
        return sql.toString();
    }

    /**
     * 删除2
     * Param : sql , ids => 多个参数
     * Success : 返回执行条数
     * Error : 返回-1
     */
    public int delete2(String sql,Object...ids){
        try {
            return jdbcTemplate.update(sql,ids);
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    /**
     * 删除
     * Param : table =>表名 , id
     * Success : 返回执行条数
     * Error : 返回-1
     */
    public int delete(String table,Object id){
        final StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        sql.append(table);
        sql.append(" where ").append(getPKColNameByTableName(table)).append(" = ?");
        try {
            return jdbcTemplate.update(sql.toString(),id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    //根据表名获取主键字段名称  删除用的
    public String getPKColNameByTableName(String tableName){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from "+tableName);
            ResultSetMetaData rss = rs.getMetaData();
            return rss.getColumnName(1);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }finally {
            jdbcClose(conn, stat, rs);
        }
    }

    //批量写入
    public <T extends BaseComponent> void batch(final String sql,List<T> t){
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(t.toArray());
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
    }

    //查询
    public <T extends BaseComponent> List<T> query(String sql, Class<T> beanClass, Object[] params) {
        return jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<T>(beanClass));
    }
    //查询
    public <T extends BaseComponent> List<T> query(String sql, Class<T> beanClass) {
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<T>(beanClass));
    }

    //查询byID
    public <T extends BaseComponent> T queryById(String sql, Class<T> beanClass, Object... ids) {
        try {
            return jdbcTemplate.queryForObject(sql,ids,new BeanPropertyRowMapper<T>(beanClass));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //查询返回Object
    public <T extends Object> T queryForObject(String sql, Class<T> beanClass, Object[] params) {
        try {
            return jdbcTemplate.queryForObject(sql,params,beanClass);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //校验值是否存在
    public int checkIsExist(String table,String col,String colVal){
        final StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) as c from ");
        sql.append(table);
        sql.append(" where ").append(col).append(" = ").append("'" + colVal + "'");
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    //分页查询
    public <T extends BaseComponent> Page<T> queryByPage(String sql, Class<T> beanClass, Object[] params, Integer pagesize, Integer count) {
        Page<T> pageList = new Page<T>();
        int from = pagesize;
        count = (count > 0) ? count : Integer.MAX_VALUE;
        Pattern p = Pattern.compile("order\\s*by.*");
        Matcher m = p.matcher(sql);
        String sqlpart="";
        if(m.find()){
            sqlpart="("+ sql.substring(0,sql.indexOf(m.group(0)))+") xx";
        }else{
            sqlpart="("+ sql.substring(0,sql.length())+") xx";
        }
        String sqlcount="select count(*) total  from ";
        sqlcount=sqlcount+sqlpart;
        int total=this.queryForObject(sqlcount, Integer.class, params);
        if(total<1)
            return new Page<T>();
        List<T> result = new ArrayList<T>();
        List<T> list=query(sql + " LIMIT ?,?",beanClass, ArrayUtils.addAll(params, new Object[]{from, count}));
        for(T t:list){
            t.setCount(pagesize);
            result.add(t);
        }
        pageList.setResult(result);
        pageList.setTotalCount(total);
        pageList.setPageNumber(pagesize);

        return pageList;

    }

    //根据数据库名及表名获得所有字段
    public List<String> getTableColumns(String tableName){
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from "+tableName);
            ResultSetMetaData rss = rs.getMetaData();
            int columnCount = rss.getColumnCount();
            for(int i = 1;i <= columnCount; i++){
                list.add(rss.getColumnName(i));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }finally {
            jdbcClose(conn, stat, rs);
        }
        return list;
    }

    //关闭数据库连接
    private void jdbcClose(Connection conn, Statement stat, ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
