/**
 *
 */
package com.sist.ehr.member.service.imple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.ehr.board.service.BoardVO;
import com.sist.ehr.cmn.DTO;
import com.sist.ehr.cmn.SearchVO;
import com.sist.ehr.member.service.Level;
import com.sist.ehr.member.service.UserDao;
import com.sist.ehr.member.service.UserVO;

/**
 * @author sist
 *
 */
@Repository("userDao")
public class UserDaoImple implements UserDao {
	//Logger
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private final String NAMESPACE= "com.sist.ehr.user";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;


	public UserDaoImple() {}

	public int passCheck(DTO dto) {
		int cnt = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doUpdate
		String statement = NAMESPACE+".passCheck";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		cnt = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=cnt="+cnt);
		LOG.debug("3==============================");

		LOG.debug("=cnt= "+cnt);
		return cnt;
	}
	/**
	 * ID 체크: 성공>0
	 * @param dto
	 * @return int
	 */
	public int idCheck(DTO dto) {
		int cnt = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doUpdate
		String statement = NAMESPACE+".idCheck";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		cnt = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=cnt="+cnt);
		LOG.debug("3==============================");

		LOG.debug("=cnt= "+cnt);
		return cnt;
	}



















	public int doInsert(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doInsert
		String statement = NAMESPACE+".doInsert";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=flag="+flag);
		LOG.debug("3==============================");

		return flag;
	}

	public int doUpdate(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doUpdate
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		flag = this.sqlSessionTemplate.update(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=flag="+flag);
		LOG.debug("3==============================");

		return flag;
	}

	public int count(DTO dto) {
		int cnt = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doUpdate
		String statement = NAMESPACE+".count";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		cnt = this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=cnt="+cnt);
		LOG.debug("3==============================");

		LOG.debug("=cnt= "+cnt);
		return cnt;
	}


	public DTO doSelectOne(DTO dto) {
		UserVO outVO = null;        //return UserVO
		UserVO inVO  = (UserVO) dto;//Param UserVO

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doInsert
		String statement = NAMESPACE+".doSelectOne";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		//hLevel -> Level전환
		outVO.setLevel(Level.valueOf(outVO.gethLevel()));

		LOG.debug("3==============================");
		LOG.debug("3=outVO="+outVO);
		LOG.debug("3==============================");

		return outVO;
	}

	public int doDelete(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doInsert
		String statement = NAMESPACE+".doDelete";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("3==============================");
		LOG.debug("3=flag="+flag);
		LOG.debug("3==============================");

		return flag;
	}

	/**
	 *
	 *Method Name:getAll
	 *작성일: 2020. 4. 8.
	 *작성자: sist
	 *설명: 전체 조회
	 *@param dto
	 *@return
	 */
	public List<UserVO> getAll(DTO dto) {
		UserVO outVO = null;        //return UserVO
		UserVO inVO  = (UserVO) dto;//Param UserVO

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.getAll
		String statement = NAMESPACE+".getAll";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		List<UserVO> list = this.sqlSessionTemplate.selectList(statement, inVO);

		//hLevel -> Level전환
		List<UserVO> outList=new ArrayList<UserVO>();
		for(UserVO vo: list) {
			vo.setLevel(Level.valueOf(vo.gethLevel()));
			outList.add(vo);
			LOG.debug("3=vo="+vo);

		}

		return outList;
	}

	public List<?> doRetrieve(DTO dto) {
		SearchVO  inVO= (SearchVO) dto;
		//검색구분
		  //ID : 10
		  //이름: 20

		LOG.debug("1==============================");
		LOG.debug("1=inVO="+inVO);
		LOG.debug("1==============================");

		// namespace+id = com.sist.ehr.user.doInsert
		String statement = NAMESPACE+".doRetrieve";
		LOG.debug("2==============================");
		LOG.debug("2=statement="+statement);
		LOG.debug("2==============================");

		List<UserVO> list = this.sqlSessionTemplate.selectList(statement, inVO);

		//hLevel -> Level전환
		List<UserVO> outList=new ArrayList<UserVO>();
		for(UserVO vo: list) {
			vo.setLevel(Level.valueOf(vo.gethLevel()));
			outList.add(vo);
			LOG.debug("3=vo="+vo);

		}

		LOG.debug("3==============================");
		LOG.debug("3=outList="+outList);
		LOG.debug("3==============================");

		return outList;
	}

}















