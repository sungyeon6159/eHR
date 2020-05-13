package com.sist.ehr.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.sist.ehr.board.service.BoardVO;
import com.sist.ehr.board.service.imple.BoardDaoImpl;
import com.sist.ehr.cmn.DTO;
import com.sist.ehr.cmn.SearchVO;
import com.sist.ehr.member.service.Level;

import com.sist.ehr.member.service.imple.UserDaoImple;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
                                   })
public class TestBoardDao {

	private final Logger  LOG = LoggerFactory.getLogger(TestBoardDao.class);
	
	@Autowired
	WebApplicationContext  webApplicationContext;

	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	BoardVO board04;	
	
	@Autowired
	BoardDaoImpl  dao;
	
	@Test
	public void doRetrieve() {
		//1.전체 삭제
		//2.추가:3건
		//3.목록조회:3건
		
		dao.doDeleteAll();
		
		int flag = dao.doInsert(board01);
		flag += dao.doInsert(board02);
		flag += dao.doInsert(board03);
		assertThat(flag, is(3));
		
		SearchVO  searchVO=new SearchVO(10,1,"10","_124");
		List<BoardVO> list =   (List<BoardVO>) dao.doRetrieve(searchVO);
		
		assertThat(list.size(), is(3));
		for(BoardVO vo: list) {
			LOG.debug(vo.toString());
		}
				
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//1.전체 삭제		
		//2.추가		
		//3.단건조회		
		//4.수정		
		//5.비교
		
		//1.전체 삭제		
		dao.doDeleteAll();
		
		//2.추가:3건		
		int flag = dao.doInsert(board01);
		flag += dao.doInsert(board02);
		flag += dao.doInsert(board03);
		
		assertThat(flag, is(3));
		//3.단건조회:board01		
		BoardVO vsVO = (BoardVO) dao.doSelectOneTitle(board01);
		//4.수정		
		vsVO.setTitle(vsVO.getTitle()+"_U");
		vsVO.setContents(vsVO.getContents()+"_U");
		vsVO.setRegId("update_user");
		//4.1 수정
		flag = dao.doUpdate(vsVO);
		assertThat(flag, is(1));
		//4.2.단건조회
		BoardVO orgVO = (BoardVO) dao.doSelectOne(vsVO);
		
		//5.비교
		this.checkSameUser(orgVO, vsVO);
	}
	
	@Test
	@Ignore
	public void doInsert() {
		
		//1. 삭제	
		dao.doDeleteAll();
		
		//2. 입력
		int flag = dao.doInsert(board01);
		flag += dao.doInsert(board02);
		flag += dao.doInsert(board03);
		LOG.debug("--------------");
		LOG.debug("flag:"+flag);
		LOG.debug("--------------");
		assertThat(flag, is(3));
		
		//3.조회
		BoardVO vsVO = (BoardVO) dao.doSelectOneTitle(board01);
		LOG.debug("--------------");
		LOG.debug("vsVO:"+vsVO);
		LOG.debug("--------------");		
		
		
	}
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("^^^^^^^^^^^");
		LOG.debug("^WebApplicationContext^"+webApplicationContext);
		LOG.debug("^^^^^^^^^^^");
		board01=new BoardVO(1,"J01_제목_124",0,"J01_내용","jamesol01@paran.com","NO_DATE");
		board02=new BoardVO(2,"J02_제목_124",0,"J02_내용","jamesol02@paran.com","NO_DATE");
		board03=new BoardVO(3,"J03_제목_124",0,"J03_내용","jamesol03@paran.com","NO_DATE");
		
		board04=new BoardVO(4,"J04_제목_124",0,"J04_내용","jamesol04@paran.com","NO_DATE");
		
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("^^^^^^^^^^^");
		LOG.debug("^tearDown^");
		LOG.debug("^^^^^^^^^^^");		
	}
	
	public void checkSameUser(BoardVO orgVO, BoardVO vsVO) {
		//assertTrue(orgVO.equals(vsVO));
		assertThat(orgVO.getTitle(), is(vsVO.getTitle()));
		assertThat(orgVO.getContents(), is(vsVO.getContents()));
		assertThat(orgVO.getRegId(), is(vsVO.getRegId()));
	}
	
	@Test
	@Ignore
	public void test() {
		LOG.debug("=====================");
		LOG.debug("=test()=");
		LOG.debug("=====================");
		
		
		LOG.debug("=====================");
		LOG.debug("=dao()="+dao);
		LOG.debug("=====================");		
		
		assertNotNull(dao);
		assertThat(1, is(1));
		
	}
	
	
	
	

}
