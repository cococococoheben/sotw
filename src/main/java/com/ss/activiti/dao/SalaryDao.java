package com.ss.activiti.dao;

import com.ss.activiti.domain.SalaryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 */
@Mapper
public interface SalaryDao {

	SalaryDO get(String id);
	
	List<SalaryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalaryDO salary);
	
	int update(SalaryDO salary);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
