package com.Util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.entity.Account;
import com.entity.Customer;
import com.entity.Transactions;

public class HibernateUtil {

	public static SessionFactory sf = null;

	public static StandardServiceRegistry registry = null;

	public static SessionFactory getSessionFactory() {

		if (sf == null) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			map.put(Environment.URL, "jdbc:mysql://localhost:3306/BankManagement");
			map.put(Environment.USER, "root");
			map.put(Environment.PASS, "root");

			map.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			map.put(Environment.HBM2DDL_AUTO, "update");
			map.put(Environment.SHOW_SQL, true);

			registry = new StandardServiceRegistryBuilder().applySettings(map).build();

			MetadataSources mds = new MetadataSources(registry).addAnnotatedClass(Customer.class)
					.addAnnotatedClass(Account.class)
					.addAnnotatedClass(Transactions.class);

			Metadata md = mds.buildMetadata();

			sf = md.buildSessionFactory();

		}

		return sf;
	}
}
