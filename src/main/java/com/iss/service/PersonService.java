package com.iss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iss.db.dao.PersonDAO;
import com.iss.db.entity.Person;
import com.iss.db.entity.PersonAtt;
import com.iss.serviceifs.IPersonService;

@Service
public class PersonService implements IPersonService {

	@Resource
	private PersonDAO personDAO;

	@Override
	public PersonAtt selectByUserId(String userId) {
		return personDAO.selectByUserId(userId);
	}

	@Override
	public int insertSelective(Person person) {
		return personDAO.insertSelective(person);
	}

	@Override
	public int updateByPrimaryKeySelective(Person person) {
		return personDAO.updateByPrimaryKeySelective(person);
	}
}
