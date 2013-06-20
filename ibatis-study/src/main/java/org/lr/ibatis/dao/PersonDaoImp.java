package org.lr.ibatis.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lr.ibatis.bean.Person;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

public class PersonDaoImp extends SqlMapClientDaoSupport implements PersonDao
{

    private static Log log = LogFactory.getLog(PersonDaoImp.class);

    @SuppressWarnings("unchecked")
    public List<Person> getAllPerson()
    {
        return getSqlMapClientTemplate().queryForList("queryAll");
    }

    public Person getPersonById(final String id)
    {
        return (Person)getSqlMapClientTemplate().queryForObject("queryById", id);
    }

    public void updatePerson(final Person person)
    {
        getSqlMapClientTemplate().update("updatePerson", person);
    }

    public void insertPerson(final Person person)
    {
        getSqlMapClientTemplate().insert("insertPerson", person);
    }

    public void batchUpdate(final List<Person> updateList)
    {
        if (!updateList.isEmpty())
        {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback()
            {
                public Object doInSqlMapClient(final SqlMapExecutor executor)
                    throws SQLException
                {
                    executor.startBatch();
                    for (Person person : updateList)
                    {
                        executor.update("updatePerson", person);
                    }
                    executor.executeBatch();

                    return null;
                }
            });
        }
    }

    public void batchInsert(final List<Person> insertList)
    {
        if (!insertList.isEmpty())
        {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback()
            {
                public Object doInSqlMapClient(final SqlMapExecutor executor)
                    throws SQLException
                {
                    executor.startBatch();
                    for (Person person : insertList)
                    {
                        executor.insert("insertPerson", person);
                    }
                    executor.executeBatch();

                    return null;
                }
            });
        }
    }

}
