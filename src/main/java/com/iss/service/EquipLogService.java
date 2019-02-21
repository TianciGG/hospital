package com.iss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.db.dao.EquipLogDAO;
import com.iss.db.entity.EquipLog;

@Service
public class EquipLogService {
	@Autowired
	private EquipLogDAO equipLogDao;

	public List<EquipLog> selectAllEquipLog(String equipName, String presonId) {
		return equipLogDao.selectAllEquipLog(equipName, presonId);
	}

	public EquipLog selectByPrimaryKey(String logId) {
		return equipLogDao.selectByPrimaryKey(logId);
	}

	public int updateByPrimaryKeySelective(EquipLog equipLog) {
		return equipLogDao.updateByPrimaryKeySelective(equipLog);
	}

	public int insertSelective(EquipLog equipLog) {
		return equipLogDao.insertSelective(equipLog);
	}

	public int deleteByPrimaryKey(String logId) {
		return equipLogDao.deleteByPrimaryKey(logId);
	}
}
