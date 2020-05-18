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


	RowMapper<UserVO> rowMapper = new RowMapper<UserVO>() {

		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO outData=new UserVO();
			outData.setU_id(rs.getString("u_id"));
			outData.setName(rs.getString("name"));
			outData.setPasswd(rs.getString("passwd"));

			//-----------------------------------------
			//-2020/04/09 등업 요건 추가
			//-----------------------------------------
			outData.setLevel(Level.valueOf(rs.getInt("u_level")));
			outData.setLogin(rs.getInt("login"));
			outData.setRecommend(rs.getInt("recommend"));
			outData.setEmail(rs.getString("mail"));
			outData.setRegDt(rs.getString("reg_dt"));
			outData.setNum(rs.getInt("rnum"));
			outData.setTotalCnt(rs.getInt("total_cnt"));
			return outData;
		}

	};


	//JDBCTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserDaoImple() {}






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
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt \n");
		sb.append(" FROM  HR_MEMBER     \n");
		sb.append(" WHERE u_id like ?   \n");


		//Query수행
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param= "+inVO.toString());
		cnt = this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {"%"+inVO.getU_id()+"%"}
		        , Integer.class);

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
		UserVO inVO = (UserVO) dto;
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT                                               \n");
		sb.append("     u_id,                                            \n");
		sb.append("     name,                                            \n");
		sb.append("     passwd,                                          \n");
		sb.append("     u_level,                                         \n");
		sb.append("     login,                                           \n");
		sb.append("     recommend,                                       \n");
		sb.append("     mail,                                            \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') AS reg_dt, \n");
		sb.append("     1 rnum,       \n");
		sb.append("     1 total_cnt   \n");
		sb.append(" FROM                                                 \n");
		sb.append("     hr_member                      \n");
		sb.append(" WHERE  u_id like ?                 \n");
		sb.append(" ORDER BY u_id                      \n");
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param="+inVO);
		//new Object[] {"%"+inVO.getU_id()+"%"}
		List<UserVO> list = this.jdbcTemplate.query(sb.toString()
				               , new Object[] {"%"+inVO.getU_id()+"%"}
				               , rowMapper);
		LOG.debug("=list="+list);
		LOG.debug("==============================");
		return list;
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















