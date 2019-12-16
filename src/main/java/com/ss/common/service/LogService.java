package com.ss.common.service;

import org.springframework.stereotype.Service;

import com.ss.common.domain.LogDO;
import com.ss.common.domain.PageDO;
import com.ss.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
