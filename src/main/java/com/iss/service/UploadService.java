package com.iss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.db.dao.AttenchmentDAO;
import com.iss.db.entity.Attenchment;

@Service
public class UploadService {
	@Autowired
	private AttenchmentDAO attDao;

	public List<Attenchment> getAllAtt(String agencyId) {
		return attDao.getAllAttByTid(agencyId);
	}

	public boolean uploadFile(Attenchment att) {
		return attDao.insertSelective(att) > 0 ? true : false;
	}

	public boolean deleteFile(Attenchment attment) {
		return attDao.deleteByPrimaryKey(attment.getAttid()) > 0 ? true : false;
	}

	public boolean checkFile(String deviceId) {
		List<Attenchment> temp = attDao.getAllAttByTid(deviceId);
		return temp.size() > 0 ? true : false;
	}

	public boolean updateFile(Attenchment att) {
		return attDao.updateByPrimaryKeySelective(att) > 0 ? true : false;
	}
}
