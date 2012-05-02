package ru.habr.zrd.hls.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.habr.zrd.hls.domain.Company;

@Repository
public class CompanyDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Company getCompany(String fetchProfile) {
		Session session = sessionFactory.getCurrentSession();
		session.enableFetchProfile(fetchProfile);
		return (Company) session.get(Company.class, 0);
	}
}