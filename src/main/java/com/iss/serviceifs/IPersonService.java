package com.iss.serviceifs;

import com.iss.db.entity.Person;
import com.iss.db.entity.PersonAtt;

public interface IPersonService {

	PersonAtt selectByUserId(String userId);

	int insertSelective(Person person);

	int updateByPrimaryKeySelective(Person person);
}
