package com.kh.spring.demo.model.dao;

import java.util.List;

import com.kh.spring.demo.model.vo.Dev;

public interface DemoDao {

	int insertDev(Dev dev);

	List<Dev> selectDevList();

	int deleteDev(int no);

	Dev selectDevOne(int no);

	int updateDev(Dev dev);

}
