package com.sinosoft.hms.modules.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;

/**
 * @author kjx123
 *         基础操作Dao接口
 */
public interface BaseDao<T> {

    // 添加
    public void add(T entity);

    // 修改
    public void update(T entity);

    // 修改(在session中已存在这个对象的修改)
    public void merge(T entity);

    // 更新或修改
    public void saveOrUpdate(T entity);

    // 根据ID获取对象，实体为传递的泛型
    public Object getById(Serializable id);

    // 根据ID获取对象
    public Object getById(Class<?> c, Serializable id);

    // 删除对象
    public void delete(T entity);

    // 根据ID删除对象
    public void deleteById(Serializable id);

    //自己写hql删除
    public Integer delete(final String hql, final Object... objects);

    // 根据ID删除对象
    public void deleteById(Class<?> c, Serializable id);

    // 获取所有的记录，实体为传递的泛型
    public List<?> getAll();

    // 获取所有的记录
    public List<?> getAll(Class<?> c);

    // 批量修改
    public void bulkUpdate(String hql, Object... objects);

    // 得到唯一值
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object getUnique(final String hql, final Object... objects);

    // 分页查询
    @SuppressWarnings("rawtypes")
    public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects);

    // 不分页查询
    public List<?> pageQuery(String hql, Object... objects);

    public void save(T entity);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Integer update(final String hql, final Object... objects);

    public Integer update(final String hql, final List<Object> objList);

    // 不分页查询,参数传递List
    public List<?> pageQuery(String hql, List<Object> objList);

    /**
     * 获取匹配记录数
     * 
     * @param hqlHelper hql生成工具
     * @return
     */
    public List<?> queryList(HqlHelper hqlHelper);

    // 分页查询,参数传递List
    @SuppressWarnings("rawtypes")
    public List<?> pageQuery(final String hql, final Integer page, final Integer size, final List<Object> objList);

    /**
     * 查询记录条数
     * 
     * @param pageNum 当前页
     * @param queryListHQL 查询HQL前面加上“select count(*) ”变成查询总数量的HQL语句
     * @param parameters 传递的参数 参数列表，顺序与HQL中的'?'的顺序一一对应。
     * @return
     */
    public Long getCount(String queryListHQL, List<Object> parameters);

    /**
     * 查询记录条数
     * 
     * @param hqlHelper hql生成工具
     * @return
     */
    public Long getCount(HqlHelper hqlHelper);

    /**
     * 公共的查询分页信息的方法
     * 
     * @param pageNum 当前页
     * @param queryListHQL 查询HQL 查询数据列表的HQL语句，如果在前面加上“select count(*) ”就变成了查询总数量的HQL语句了
     * @param parameters 传递的参数 参数列表，顺序与HQL中的'?'的顺序一一对应。
     * @return
     */
    public Page getPageBean(int pageNum, String queryListHQL, List<Object> parameters);

    /**
     * 分页查询
     * 
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @param hqlHelper hql工具
     * @return
     */
    public Page getPageBean(Integer pageNum, Integer pageSize, HqlHelper hqlHelper);

    /**
     * 获取ids 的业务数据
     * 
     * @param ids
     * @return
     */
    public List<T> getByIds(final Long[] ids);

    public Session getCurrentSession();

    /**
     * 批量删除
     * 
     * @param selectFlag
     * @param table
     */
    public int delBatch(String selectFlag, String table);
}
