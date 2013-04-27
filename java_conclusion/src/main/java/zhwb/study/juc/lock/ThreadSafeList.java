package zhwb.study.juc.lock;

import java.util.AbstractList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeList<E> extends AbstractList<E>
{
    private List<E> list;

    private Lock lock = new ReentrantLock();

    @Override
    public E get(final int index)
    {
        if (lock.tryLock())
        {
            try
            {
                return list.get(index);
            }
            finally
            {
                lock.unlock();
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public boolean add(final E e)
    {
        if (lock.tryLock())
        {
            try
            {
            return list.add(e);
            }
            finally
            {
                lock.unlock();
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean remove(final Object o)
    {
        if (lock.tryLock())
        {
            try
            {
                return list.remove(o);
            }
            finally
            {
                lock.unlock();
            }
        }
        else
        {
            return false;
        }

    }

    /**
     * @param list
     */
    public ThreadSafeList(final List<E> list)
    {
        super();
        this.list = list;
    }
}