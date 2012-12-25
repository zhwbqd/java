package zhwb.batch.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@Repository("hibernateDAO")
public class HibernateDAO<T extends Serializable> {

	private static final Logger log = Logger.getLogger(HibernateDAO.class);

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate template;

	public Serializable save(T entity) {
		try {
			return template.save(entity);
		} catch (Exception e) {
			log.info("save exception : " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void flush() {
		try {
			template.flush();
		} catch (Exception e) {
			log.error("flush exception : " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public int executeByHql(String hql, Object... values)
			throws HibernateException {
		try {
			return template.bulkUpdate(hql, values);
		} catch (Exception e) {
			log.error("executeByHql exception : " + e.getMessage());
			throw new HibernateException(e);
		}
	}

	public void clear() {
		try {
			template.clear();
		} catch (Exception e) {
			log.error("clear exception : " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public void execute(final String sql, final Object... values) {
		template.execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql);
				
				int i = 0;
				for (Object obj : values) {
					query.setParameter(i++, obj);
				}
				return query.executeUpdate();
			}
		});
	}

}
