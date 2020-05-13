package com.sist.ehr.board;

import static com.sist.ehr.member.service.imple.UserServiceImple.MIN_LOGINCOUNT_FOR_SILVER;
import static com.sist.ehr.member.service.imple.UserServiceImple.MIN_RECCOMENDCOUNT_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
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
public class TestBoardControllerWeb {

	private final Logger  LOG = LoggerFactory.getLogger(TestBoardControllerWeb.class);
	
	@Autowired
	WebApplicationContext  webApplicationContext;
	
	private List<BoardVO> boardList;
	
	@Autowired
	BoardService  boardService;
	
	@Autowired
	BoardDaoImpl  dao;
	
	 
	//브라우저 대신 Mock
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("*********************");
		LOG.debug("=setUp()=");
		LOG.debug("*********************");
		
		boardList = Arrays.asList(
				 new BoardVO(1,"J01_제목_124",0,"J01_내용","jamesol01@paran.com","NO_DATE")
				,new BoardVO(2,"J02_제목_124",0,"J02_내용","jamesol02@paran.com","NO_DATE")					
				,new BoardVO(3,"J03_제목_124",0,"J03_내용","jamesol03@paran.com","NO_DATE")
				);
				
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=====================");
		LOG.debug("=webApplicationContext="+webApplicationContext);
		LOG.debug("=mockMvc="+mockMvc);
		
		LOG.debug("=====================");		

	}
	
	@Test
	public void doRetrieve()  throws Exception {
		//1.전체삭제
		//2.3건입력
		//3.조회
		dao.doDeleteAll();
		int flag = dao.doInsert(boardList.get(0));
		flag += dao.doInsert(boardList.get(1));
		flag += dao.doInsert(boardList.get(2));
		
		assertThat(flag, is(3));
		
		//url+param
		MockHttpServletRequestBuilder  createMesage 
		           = MockMvcRequestBuilders.get("/board/do_retrieve.do")
		             .param("pageNum", "1")
					 .param("pageSize", "10")
					 .param("searchDiv", "")
					 .param("searchWord", " ");
					;
					
		ResultActions  resultActions = mockMvc.perform(createMesage)
				.andExpect(status().is2xxSuccessful())	
				.andExpect(model().attributeExists("searchList"))
				.andExpect(model().attributeExists("list"))
				.andExpect(model().attributeExists("vo"))
				.andExpect(model().attributeExists("totalCnt"))
				;
		
			String result = resultActions.andDo(print())
					.andReturn()
					.getResponse().getContentAsString();
			LOG.debug("=====================");
			LOG.debug("=result="+result);
			LOG.debug("=====================");  				
		
		
		
	}
	
	@Test
	@Ignore
	public void doUpdate() throws Exception {
		//1.전체 삭제
		//2.단건입력
		//3.단건조회:board_id조회
		//3.1.단건수정
		//4.수정
		//5.수정데이터 조회
		//6.비교 
		dao.doDeleteAll();
		
		int flag = dao.doInsert(boardList.get(0));
		assertThat(flag, is(1));
		
		BoardVO boardId = (BoardVO) dao.doSelectOneTitle(boardList.get(0));
		LOG.debug("=====================");
		LOG.debug("=BoardVO="+boardId);
		LOG.debug("=====================");  
		
		boardId.setTitle(boardId.getTitle()+"_U");
		boardId.setContents(boardId.getContents()+"_U");
		boardId.setRegId("admin_U");
		
		
		
		//url+param
		MockHttpServletRequestBuilder  createMesage 
		           = MockMvcRequestBuilders.post ("/board/do_update.do")
		             .param("boardId", boardId.getBoardId()+"")
					 .param("title", boardId.getTitle())
					 .param("contents", boardId.getContents())
					 .param("regId", boardId.getRegId())
					;
		
		
		ResultActions  resultActions = mockMvc.perform(createMesage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		    .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
			;
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("=====================");
		LOG.debug("=result="+result);
		LOG.debug("=====================");  		
				
		//5.수정데이터 조회
		BoardVO vsVO = (BoardVO) dao.doSelectOne(boardId);
		//6.비교 
		checkSameUser(vsVO,boardId);
	}
	
	@Test
	@Ignore
	public void doDelete() throws Exception {
		//1.전체 삭제
		//2.단건입력
		//3.단건조회:board_id조회
		//4.삭제
		dao.doDeleteAll();
		
		int flag = dao.doInsert(boardList.get(0));
		assertThat(flag, is(1));
		
		BoardVO boardId = (BoardVO) dao.doSelectOneTitle(boardList.get(0));
		LOG.debug("=====================");
		LOG.debug("=BoardVO="+boardId);
		LOG.debug("=====================");  
		
		//url+param
		MockHttpServletRequestBuilder  createMesage 
		           = MockMvcRequestBuilders.post ("/board/do_delete.do")
		             .param("boardId", boardId.getBoardId()+"");		
		
		ResultActions  resultActions = mockMvc.perform(createMesage)
			.andExpect(status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		    .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
			;
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("=====================");
		LOG.debug("=result="+result);
		LOG.debug("=====================");  		
				
	}
	
	//단건조회
	@Test
	@Ignore
	public void doSelectOne() throws Exception {
		dao.doDeleteAll();
		
		int flag = dao.doInsert(boardList.get(0));
		assertThat(flag, is(1));
		//board_id
		BoardVO boardId = (BoardVO) dao.doSelectOneTitle(boardList.get(0));
		LOG.debug("=====================");
		LOG.debug("=BoardVO="+boardId);
		LOG.debug("=====================");  
		
		//url+param
		MockHttpServletRequestBuilder  createMesage 
		           = MockMvcRequestBuilders.get("/board/do_selectone.do")
		             .param("boardId", boardId.getBoardId()+"");		
		
		ResultActions  resultActions = mockMvc.perform(createMesage)
			.andExpect(status().is2xxSuccessful())	
			;
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		LOG.debug("=====================");
		LOG.debug("=result="+result);
		LOG.debug("=====================");  		

	}
	
	@Test
	@Ignore
	public void add() throws Exception {
		dao.doDeleteAll();
		
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/board/do_insert.do")
				.param("title", boardList.get(0).getTitle())
				.param("contents", boardList.get(0).getContents())
				.param("regId", boardList.get(0).getRegId())
				;
		
		ResultActions  resultActions =mockMvc.perform(createMessage)
				       .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				       .andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")))
				;
		String result = resultActions.andDo(print())
						.andReturn()
						.getResponse().getContentAsString();
		LOG.debug("=====================");
		LOG.debug("=result="+result);
		LOG.debug("=====================");  
		
		BoardVO titleVO = (BoardVO) dao.doSelectOneTitle(boardList.get(0));
		BoardVO vsVO = (BoardVO) dao.doSelectOne(titleVO);
		checkSameUser(vsVO,boardList.get(0));
	}
	
	public void checkSameUser(BoardVO orgVO, BoardVO vsVO) {
		//assertTrue(orgVO.equals(vsVO));
		assertThat(orgVO.getTitle(), is(vsVO.getTitle()));
		assertThat(orgVO.getContents(), is(vsVO.getContents()));
		assertThat(orgVO.getRegId(), is(vsVO.getRegId()));
	}
	
	@Test
	public void test() {
		LOG.debug("=====================");
		LOG.debug("=test()=");
		LOG.debug("=====================");
		
		LOG.debug("=====================");
		LOG.debug("=boardService="+boardService);
		LOG.debug("=dao="+dao);
		LOG.debug("=====================");		
		assertNotNull(boardService);
		assertNotNull(dao);
		assertThat(1, is(1));
	
	}
	
	
	

	

}
