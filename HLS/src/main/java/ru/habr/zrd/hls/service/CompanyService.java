package ru.habr.zrd.hls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.habr.zrd.hls.dao.CompanyDAO;
import ru.habr.zrd.hls.domain.Company;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Transactional(readOnly = true)
	public Company getCompany(String fetchProfile) {
		return companyDAO.getCompany(fetchProfile);
	}
}