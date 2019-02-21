package com.iss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.db.dao.AgencyDAO;
import com.iss.db.entity.Agency;

@Service
public class AgencyService {
	@Autowired
	private AgencyDAO agencyDao;

	public List<Agency> selectAllAgency(String agencyName) {
		return agencyDao.selectAllAgency(agencyName);
	}

	public int updateByPrimaryKeySelective(Agency agency) {
		return agencyDao.updateByPrimaryKeySelective(agency);
	}

	public int insertSelective(Agency agency) {
		return agencyDao.insertSelective(agency);
	}

	public Agency selectByPrimaryKey(String agencyId) {
		return agencyDao.selectByPrimaryKey(agencyId);
	}

	public int deleteByPrimaryKey(String agencyId) {
		return agencyDao.deleteByPrimaryKey(agencyId);
	}

	public List<Agency> getAllAgency() {
		return agencyDao.getAllAgency();
	}
}
