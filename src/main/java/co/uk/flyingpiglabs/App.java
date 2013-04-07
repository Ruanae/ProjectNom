package co.uk.flyingpiglabs;

import org.hibernate.Session;

import co.uk.flyingpiglabs.projectnom.util.HibernateUtil;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();

	}
}
