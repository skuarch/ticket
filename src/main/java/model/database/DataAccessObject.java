package model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;

/**
 * Connection with Hibernate.
 *
 * @author skuarch
 */
class DataAccessObject {

    private static SessionFactory sessionFactory = null;
    //private static ServiceRegistry serviceRegistry = null;
    //private static StandardServiceRegistryBuilder serviceRegistryBuilder;
    private static Session session = null;
    private static Transaction transaction = null;

    static {

        try {

            Configuration configuration = new Configuration();
            configuration.configure();

            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());

            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (HibernateException e) {
            try {
                throw e;
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }

    } // end static

    //==========================================================================
    private DataAccessObject() {
    } // end DataAccessObject

    //==========================================================================
    /**
     * open a session with the database.
     */
    private static void openSession() {

        session = sessionFactory.openSession();

    } // end openSession

    //==========================================================================
    /**
     * close session.
     */
    private static void closeSession() {

        if (session != null) {

            if (session.isOpen()) {
                session.close();
            }
        }

    } // end closeSession

    //==========================================================================
    private static void beginTransaction() {
        transaction = session.beginTransaction();
    }

    //==========================================================================
    private static void commitTransaction() {
        transaction.commit();
    }

    //==========================================================================
    /**
     * save the object in the database.
     *
     * @param object Object
     * @return long (id)
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    protected static long create(final Object object) throws HibernateException {

        if (object == null) {
            throw new IllegalArgumentException("the parameter object is null");
        }

        long id = 0;

        try {

            openSession();
            beginTransaction();
            id = (long) session.save(object);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return id;

    } // end create

    //==========================================================================
    /**
     * remove the object from table.
     *
     * @param object Object (Bean)
     * @throws HibernateException
     */
    protected static void delete(final Object object) throws HibernateException {

        if (object == null) {
            throw new IllegalArgumentException("the parameter object is null");
        }

        try {

            openSession();
            beginTransaction();
            session.delete(object);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

    } // end delete

    //==========================================================================
    /**
     * return a Object from table.
     *
     * @param <T> type
     * @param id long
     * @param type type
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static <T> T get(final long id, final T type) {

        if (type == null) {
            throw new IllegalArgumentException("the parameter type is null");
        }

        if (id < 1) {
            throw new IllegalArgumentException("the parameter id is less than 1");
        }

        T t = null;

        try {

            openSession();
            beginTransaction();
            t = (T) session.get(type.getClass(), id);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return t;

    } // end get

    //==========================================================================
    /**
     * return a List of specific objects.
     *
     * @param <T> type
     * @param type type
     * @return List<T>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> getList(final T type) throws HibernateException {

        if (type == null) {
            throw new IllegalArgumentException("the parameter type is null");
        }

        List<T> list = null;

        try {

            openSession();
            beginTransaction();
            
            list =  session.createQuery("from " + type.getClass().getCanonicalName()).list();
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end getList

    //==========================================================================
    /**
     * execute a <code>hql</code> query in the database,.
     *
     * @param <T> type
     * @param hql String
     * @param type type
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> hql(final String hql, final T type) {

        if (hql == null || hql.length() < 1) {
            throw new IllegalArgumentException("the parameter hql is null");
        }

        if (type == null) {
            throw new IllegalArgumentException("the parameter type is null");
        }

        List<T> list = null;
        Query query = null;

        try {

            openSession();
            query = session.createQuery(hql);
            query.setProperties(type);
            list = query.list();

        } catch (HibernateException e) {
            throw e;
        } finally {
            closeSession();
        }

        return list;

    } // end hql

    //==========================================================================
    /**
     * execute a <code>hql</code> sentence.
     *
     * @param <T> type
     * @param hql String
     * @param type type
     * @param maxResults int
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> hql(final String hql, final T type, final int maxResults) {

        if (hql == null || hql.length() < 1) {
            throw new IllegalArgumentException("the parameter hql is null");
        }

        if (type == null) {
            throw new IllegalArgumentException("the parameter type is null");
        }

        if (maxResults < 1) {
            throw new IllegalArgumentException("the parameter maxResults is incorrect");
        }

        List<T> list = null;
        Query query;

        try {

            openSession();
            query = session.createQuery(hql);
            query.setMaxResults(maxResults);
            query.setProperties(type);
            list = query.list();

        } catch (HibernateException e) {
            throw e;
        } finally {
            closeSession();
        }

        return list;

    } // end hql

    //==========================================================================
    /**
     * retrieve a query from bean and execute the sentence.
     *
     * @param <T> type
     * @param queryName String
     * @param type type
     * @return List<T>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> query(final String queryName, final T type) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("queryName is null or empty");
        }

        if (type == null) {
            throw new NullPointerException("type is null");
        }

        Query query;
        List<T> list = null;

        try {

            openSession();
            query = session.getNamedQuery(queryName);
            query.setProperties(type);
            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end query

    //==========================================================================
    /**
     * retrieve a query from bean and execute the sentence with parameters.
     *
     * @param <T> type
     * @param queryName String
     * @param parameters HashMap
     * @param type type
     * @return List<T>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> query(final String queryName, final HashMap<String, String> parameters, final T type) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("queryName is null or empty");
        }

        if (parameters == null || parameters.size() < 1) {
            throw new NullPointerException("parameters are null or empty");
        }

        if (type == null) {
            throw new NullPointerException("type is null");
        }

        Query query;
        List<T> list = null;

        try {

            openSession();
            query = session.getNamedQuery(queryName);
            query.setProperties(type);

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                query.setString(entry.getKey(), entry.getValue());
            }

            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end query

    //==========================================================================
    /**
     * retrieve a query from bean and execute the sentence with parameters and
     * max result.
     *
     * @param <T> type
     * @param queryName String
     * @param parameters HashMap
     * @param type type
     * @param maxResults int
     * @return List<T>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    protected static <T> List<T> query(final String queryName, final HashMap<String, String> parameters, final T type, final int maxResults) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("queryName is null or empty");
        }

        if (parameters == null || parameters.size() < 1) {
            throw new NullPointerException("parameters are null or empty");
        }

        if (type == null) {
            throw new NullPointerException("type is null");
        }

        if (maxResults < 1) {
            throw new IllegalArgumentException("the parameter maxResults is incorrect");
        }

        String key;
        String value;
        Query query;
        List<T> list = null;

        try {

            openSession();
            query = session.getNamedQuery(queryName);
            query.setProperties(type);
            query.setMaxResults(maxResults);

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                query.setString(key, value);
            }

            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end query

    //==========================================================================
    /**
     * @param <T> type
     * @param queryName String
     * @return List or null
     * @throws HibernateException
     */ 
    @SuppressWarnings("unchecked")
    protected static <T> ArrayList<T> query(T type, String queryName, boolean ignoreCache) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("queryName is null or empty");
        }

        if (type == null) {
            throw new NullPointerException("type is null");
        }

        Query query = null;
        ArrayList<T> arrayList = null;

        try {

            query = session.getNamedQuery(queryName);

            if (ignoreCache) {
                query.setCacheMode(CacheMode.IGNORE);
            }

            query.setProperties(type);
            arrayList = new ArrayList(query.list());

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return arrayList;

    } // end query

    //==========================================================================
    @SuppressWarnings("unchecked")
    protected static <T> List<T> queryLimit(String queryName, T type, int start, int maxResults) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("parameters are null or empty");
        }

        if (start < 0 || maxResults < 0) {
            throw new NullPointerException("parameters are less than 0");
        }
        
        List<T> list = null;
        Query query;

        try {

            query = session.getNamedQuery(queryName);
            query.setFirstResult(start);
            query.setMaxResults(maxResults);
            query.setProperties(type);
            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    }

    //==========================================================================
    @SuppressWarnings("unchecked")
    protected static <T> List<T> queryLimit(T type, String queryName, int start, int maxResults) throws HibernateException {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("parameters are null or empty");
        }

        if (start < 0 || maxResults < 0) {
            throw new NullPointerException("parameters are less than 0");
        }

        List<T> list = null;
        Query query;

        try {

            query = session.getNamedQuery(queryName);
            query.setFirstResult(start);
            query.setMaxResults(maxResults);
            query.setProperties(type);
            list = query.list();
            
        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end queryLimit

    //==========================================================================
    /**
     * update a object in database.
     *
     * @param object Object
     * @throws HibernateException
     */
    protected static void update(final Object object) throws HibernateException {

        if (object == null) {
            throw new IllegalArgumentException("the parameter object is null");
        }

        try {

            openSession();
            beginTransaction();
            session.update(object);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

    } // end update

    //==========================================================================
    @SuppressWarnings("unchecked")
    protected static <T> ArrayList<T> orderCriteria(T type, int maxResults, String propertyName, String order) {

        ArrayList<T> list = null;

        Criteria criteria = session.createCriteria(type.getClass());

        if (order.equalsIgnoreCase("asc")) {
            criteria.addOrder(Order.asc(propertyName));
        } else {
            criteria.addOrder(Order.desc(propertyName));
        }

        criteria.setMaxResults(maxResults);
        list = new ArrayList(criteria.list());

        return list;
    }

} // end class DataAccessObject
