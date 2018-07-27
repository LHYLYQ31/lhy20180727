package com.sinosoft.hms.modules.common.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.ConfigProperties;
import com.sinosoft.hms.common.util.GenericsUtils;
import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;

/**
 * 
 * @author kjx123
 *         Dao基础类实现
 * @param <T>
 */
@Component
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private HibernateTemplate hibernateTemplate;

    protected Class<T> clazz; // 这是一个问题！

    @SuppressWarnings({ "unchecked" })
    public BaseDaoImpl() {
        // 通过反射得到T的真实类型
        this.clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
    }

    // 添加
    @Override
    public void add(Object o) {
        hibernateTemplate.save(o);
    }

    // 修改
    @Override
    public void update(Object o) {
        hibernateTemplate.update(o);
    }

    // 修改(在session中已存在这个对象的修改)
    @Override
    public void merge(Object o) {
        hibernateTemplate.merge(o);
    }

    // 根据ID获取对象
    @Override
    public Object getById(Class<?> c, Serializable id) {
        return hibernateTemplate.get(c, id);
    }

    // 删除对象
    @Override
    public void delete(Object o) {
        hibernateTemplate.delete(o);
    }

    // 根据ID删除对象
    @Override
    public void deleteById(Class<?> c, Serializable id) {
        delete(getById(c, id));
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Integer delete(final String hql, final Object... objects) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objects != null)
                    for (int i = 0; i < objects.length; i++)
                        query.setParameter(i, objects[i]);
                return query.executeUpdate();
            }
        });
    }

    // 获取所有的记录
    @Override
    public List<?> getAll(Class<?> c) {
        return hibernateTemplate.find("from " + c.getName());
    }

    // 批量修改
    @Override
    public void bulkUpdate(String hql, Object... objects) {
        hibernateTemplate.bulkUpdate(hql, objects);
    }

    // 得到唯一值
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object getUnique(final String hql, final Object... objects) {
        return hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objects != null)
                    for (int i = 0; i < objects.length; i++)
                        query.setParameter(i, objects[i]);
                return query.uniqueResult();
            }
        });
    }

    // 分页查询
    @Override
    @SuppressWarnings("rawtypes")
    public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects) {
        return (List<?>) hibernateTemplate.executeWithNativeSession(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objects != null)
                    for (int i = 0; i < objects.length; i++) {
                        query.setParameter(i, objects[i]);
                    }
                if (page != null && size != null)
                    query.setFirstResult((page - 1) * size).setMaxResults(size);
                return query.list();
            }
        });
    }

    // 不分页查询
    @Override
    public List<?> pageQuery(String hql, Object... objects) {
        return pageQuery(hql, null, null, objects);
    }

    // 不分页查询,参数传递List
    @Override
    public List<?> pageQuery(String hql, List<Object> objList) {
        return pageQuery(hql, null, null, objList);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List<?> pageQuery(final String hql, final Integer page, final Integer size, final List<Object> objList) {
        return (List<?>) hibernateTemplate.executeWithNativeSession(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objList != null) {
                    for (int i = 0; i < objList.size(); i++) {
                        query.setParameter(i, objList.get(i));
                    }
                }
                if (page != null && size != null) {
                    query.setFirstResult((page - 1) * size).setMaxResults(size);
                }
                return query.list();
            }
        });
    }

    @Override
    public void save(Object o) {
        if (o != null)
            hibernateTemplate.saveOrUpdate(o);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Integer update(final String hql, final Object... objects) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objects != null)
                    for (int i = 0; i < objects.length; i++)
                        query.setParameter(i, objects[i]);
                return query.executeUpdate();
            }
        });
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Integer update(final String hql, final List<Object> objList) {
        return (Integer) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (objList != null) {
                    for (int i = 0; i < objList.size(); i++) {
                        query.setParameter(i, objList.get(i));
                    }
                }
                return query.executeUpdate();
            }
        });
    }

    /**
     * whereParameter where 语句拼接
     * parameters 参数
     */
    public List<?> queryList(String[] whereParameter, final Object[] parameters) {
        final StringBuffer hql = new StringBuffer();
        hql.append("FROM " + clazz.getSimpleName() + " o ");
        if (whereParameter.length > 0) {
            hql.append(" where 1 = 1");
        }
        for (String str : whereParameter) {
            hql.append(" and o." + str + " = ? ");
        }

        //hibernateTemplate.executeWithNativeSession(action)
        return (List<?>) hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query listQuery = session.createQuery(hql.toString());
                for (int i = 0; i < parameters.length; i++) {
                    listQuery.setParameter(i, parameters[i]);
                }
                return listQuery.list();

            }
        });
    }

    /**
     * 查询记录条数
     * 
     * @param pageNum 当前页
     * @param queryListHQL 查询HQL
     * @param parameters 传递的参数
     * @return
     */
    @Override
    public Long getCount(String queryListHQL, List<Object> parameters) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        // 查询总记录数
        Query countQuery = session.createQuery("SELECT COUNT(*) " + queryListHQL);
        if (parameters != null) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        return (Long) countQuery.uniqueResult(); // 执行查询
    }

    /**
     * 获取匹配记录数
     * 
     * @param hqlHelper hql生成工具
     * @return
     */
    @Override
    public List<?> queryList(HqlHelper hqlHelper) {
        List<Object> parameters = hqlHelper.getParameters();
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        // 查询本页的数据列表
        Query listQuery = session.createQuery(hqlHelper.getQueryListHql());
        if (parameters != null && parameters.size() > 0) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                listQuery.setParameter(i, parameters.get(i));
            }
        }
        return listQuery.list(); // 返回查询结果
    }

    /**
     * 查询记录条数
     * 
     * @param hqlHelper hql生成工具
     * @return
     */
    @Override
    public Long getCount(HqlHelper hqlHelper) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        // 查询总记录数
        List<Object> parameters = hqlHelper.getParameters();
        //"SELECT COUNT(*) " +
        Query countQuery = session.createQuery(hqlHelper.getQueryCountHql());
        if (parameters != null) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        return (Long) countQuery.uniqueResult(); // 执行查询
    }

    /**
     * 公共的查询分页信息的方法
     * 
     * @param pageNum 当前页
     * @param queryListHQL 查询HQL
     * @param parameters 传递的参数
     * @return
     */
    @Override
    public Page getPageBean(int pageNum, String queryListHQL, List<Object> parameters) {
        Integer pageSize = ConfigProperties.getIntPropertyValue(GlobalNames.PAGE_SIZE, GlobalNames.DEFAULT_PAGE_SIZE);
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        // 查询本页的数据列表
        Query listQuery = session.createQuery(queryListHQL);
        if (parameters != null) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                listQuery.setParameter(i, parameters.get(i));
            }
        }
        listQuery.setFirstResult((pageNum - 1) * pageSize);
        listQuery.setMaxResults(pageSize);
        List list = listQuery.list(); // 执行查询
        // 查询总记录数
        Query countQuery = session.createQuery("SELECT COUNT(*) " + queryListHQL);
        if (parameters != null) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        Long count = (Long) countQuery.uniqueResult(); // 执行查询
        return new Page(pageNum, pageSize, count.intValue(), list);
    }

    /**
     * 分页查询
     * 
     * @param pageNum 当前页
     * @param pageSize 每页个数
     * @param hqlHelper hql工具
     * @return
     */
    @Override
    public Page getPageBean(Integer pageNum, Integer pageSize, HqlHelper hqlHelper) {
        if (pageSize == null) {
            pageSize = ConfigProperties.getIntPropertyValue(GlobalNames.PAGE_SIZE, GlobalNames.DEFAULT_PAGE_SIZE);
        }
        List<Object> parameters = hqlHelper.getParameters();
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        // 查询本页的数据列表
        Query listQuery = session.createQuery(hqlHelper.getQueryListHql());
        if (parameters != null && parameters.size() > 0) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                listQuery.setParameter(i, parameters.get(i));
            }
        }
        listQuery.setFirstResult((pageNum - 1) * pageSize);
        listQuery.setMaxResults(pageSize);
        List list = listQuery.list(); // 执行查询
        // 查询总记录数
        Query countQuery = session.createQuery(hqlHelper.getQueryCountHql());
        if (parameters != null && parameters.size() > 0) { // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        Long count = (Long) countQuery.uniqueResult(); // 执行查询
        return new Page(pageNum, pageSize, count.intValue(), list);
    }

    /**
     * 获取ids 的业务数据
     * 
     * @param ids
     * @return
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> getByIds(final Long[] ids) {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return (List<T>) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createQuery("FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)").setParameterList(
                        "ids", ids)//
                        .list();
            }
        });
    }

    @Override
    public Session getCurrentSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Override
    public Object getById(Serializable id) {
        return getById(clazz, id);
    }

    @Override
    public void deleteById(Serializable id) {
        deleteById(clazz, id);
    }

    @Override
    public List<?> getAll() {
        return hibernateTemplate.find("from " + clazz.getSimpleName());
    }

    @Override
    public void saveOrUpdate(T entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }

    /**
     * 批量删除文件
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see cn.com.sinosoft.nfwjs.common.dao.BaseDao#delBatch(java.lang.String[], java.lang.String)
     */
    @Override
    public int delBatch(String selectFlag, String table) {
        //数组中封装的是ID的集合;
        String hql = "";
        /*
         * for (int i = 0; i < selectFlag.length; i++) {
         * if (i == 0) {
         * hql = "id=" + Integer.parseInt(selectFlag[i]);
         * }
         * else {
         * hql = hql + " or id=" + Integer.parseInt(selectFlag[i]);
         * }
         * }
         */
        hql = "id=" + Integer.parseInt(selectFlag);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query q = session.createQuery("delete from " + table + " where " + hql);
        return q.executeUpdate();
    }

}
