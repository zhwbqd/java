package com.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.model.Boss;

public class QueryBOSSTest
{
    public static void main(String[] args)
    {
        QueryBOSSTest.Query();
        QueryBOSSTest.delete();
        QueryBOSSTest.Query();
        QueryBOSSTest.insert();

    }

    private static void Query()
    {
        String hql = "from Boss boss where boss.name like ?";
        SessionFactory sf = TestInitial.getSf();
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter(0, "ja%");
        List<?> list = query.list();
        java.util.Iterator it = list.iterator();
        while (it.hasNext())
        {
            Boss boss = (Boss)it.next();
            System.out.println("ID: " + boss.getId() + "\n" + "Name: " + boss.getName());
        }
        session.close();
    }

    private static void delete()
    {
        String hql = "delete Boss boss where boss.id=?";
        SessionFactory sf = TestInitial.getSf();
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter(0, 250);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    private static void insert()
    {
        String hql = "from Boss boss where boss.name like ?";
        SessionFactory sf = TestInitial.getSf();
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter(0, "ja%");
        List<?> list = query.list();
        java.util.Iterator it = list.iterator();
        while (it.hasNext())
        {
            Boss boss = (Boss)it.next();
            System.out.println("ID: " + boss.getId() + "\n" + "Name: " + boss.getName());
        }
        session.close();
    }
}
