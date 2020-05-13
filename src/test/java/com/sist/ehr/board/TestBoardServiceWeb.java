package com.sist.ehr.board;

import static com.sist.ehr.member.service.imple.UserServiceImple.MIN_LOGINCOUNT_FOR_SILVER;
import static com.sist.ehr.member.service.imple.UserServiceImple.MIN_RECCOMENDCOUNT_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.sist.ehr.board.service.BoardService;
import com.sist.ehr.board.service.BoardVO;
import com.sist.ehr.board.service.imple.BoardDaoImpl;
import com.sist.ehr.cmn.DTO;
import com.sist.ehr.cmn.SearchVO;
import com.sist.ehr.member.service.Level;
import com.sist.ehr.member.service.UserService;
import com.sist.ehr.member.service.UserVO;
import com.sist.ehr.member.service.imple.UserDaoImple;



@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
                                   })
public class TestBoardServiceWeb {

	private final Logger  LOG = LoggerFactory.getLogger(TestBoardServiceWeb.class);
	
	@Autowired
	WebApplicationContext  webApplicationContext;
	
	BoardVO board01;
	
	@Autowired
	BoardService  boardService;
	
	@Autowired
	BoardDaoImpl  dao;

	
	@Before
	public void setUp() {
		LOG.debug("*********************");
		LOG.debug("=setUp()="); 
		LOG.debug("*********************");
		board01=new BoardVO(1,"J01_제목_124",0,"J01_내용","jamesol01@paran.com","NO_DATE");
	}
	
	@Test
	public void doSelectOne() {
		//1.전체삭제
		//2.등록
		//2.1.등록데이터 조회
		//3.
		dao.doDeleteAll();
		
		int flag = boardService.doInsert(board01);
		assertThat(flag, is(1));
		
		BoardVO oneVO =(BoardVO) dao.doSelectOneTitle(board01);
		boardService.doSelectOne(oneVO);
		
		
		
	}
	
	
	
	@Test
	public void test() {
		LOG.debug("=====================");
		LOG.debug("=test()=");
		LOG.debug("=====================");
		
		
		LOG.debug("=====================");
		LOG.debug("=boardService="+boardService);
		
		LOG.debug("=====================");		
		
		assertNotNull(boardService);
		assertThat(1, is(1));
		
	}
	

}
