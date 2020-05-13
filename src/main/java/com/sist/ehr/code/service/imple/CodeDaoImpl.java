package com.sist.ehr.code.service.imple;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.ehr.cmn.DTO;
import com.sist.ehr.code.service.CodeDao;
import com.sist.ehr.code.service.CodeVO;

@Repository
public class CodeDaoImpl implements CodeDao {
	Logger  LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAME_SPACE="com.sist.ehr.code";
	
	@Override
	public List<?> doRetrieve(DTO dto) {
		//1.PARAM
		CodeVO inVO = (CodeVO) dto;
		LOG.debug("===================");
		LOG.debug("1.param="+inVO);
		LOG.debug("===================");
		//1.1.
		String statement = NAME_SPACE+".doRetrieve";
		LOG.debug("===================");
		LOG.debug("1.1. statement="+statement);
		LOG.debug("===================");		
		
		//2.sqlSessionTemplate
		List<CodeVO> list = this.sqlSessionTemplate.selectList(statement, inVO);
		//3.RETURN
		LOG.debug("===================");
		LOG.debug("1.2. list="+list);
		LOG.debug("===================");			
		
		return list;
	}

}








