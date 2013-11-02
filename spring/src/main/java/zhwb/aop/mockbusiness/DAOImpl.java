
package zhwb.aop.mockbusiness;


public class DAOImpl<T> implements IDAO<T>
{

    public boolean insert(final Object obj)
    {
        System.out.println("insert into database");
        return false;
    }

    public boolean update(final Object obj)
    {
        System.out.println("update into database");
        return false;
    }

    public T find(final T obj)
    {
        if (obj == null)
        {
            System.out.println("Exception throws");
            throw new IllegalArgumentException("input can not be null");
        }
        System.out.println("find from database");
        return obj;
    }

    public boolean delete(final Object obj)
    {
        System.out.println("delete from database");
        return false;
    }

}
