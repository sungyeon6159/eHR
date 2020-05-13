package com.sist.ehr.code.service;

import java.util.List;

import com.sist.ehr.cmn.DTO;

public interface CodeDao {
	/**
	 * 목록조회
	 * @param dto
	 * @return
	 */
	public List<?> doRetrieve(DTO dto);
}
