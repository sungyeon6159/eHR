package com.sist.ehr.member.web;

import java.util.List;

import javax.inject.Qualifier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.ehr.cmn.DTO;
import com.sist.ehr.cmn.MessageVO;
import com.sist.ehr.cmn.SearchVO;
import com.sist.ehr.cmn.StringUtil;
import com.sist.ehr.member.service.UserService;
import com.sist.ehr.member.service.UserVO;

@Controller
public class MemberController {

	private final Logger  LOG = LoggerFactory.getLogger(MemberController.class);

	//@Qualifier("dummyMailSender") : root-context.xml bean id
	@Autowired
	UserService userService;

	/**
	 * Login
	 * @param user
	 * @param session
	 * @return json
	 */
	@RequestMapping(value = "login/login.do",method = RequestMethod.POST
			,produces ="application/json; charset=UTF-8" )
	@ResponseBody
	public String doLogin(UserVO user, HttpSession session) {
		//1.idPassCheck call
		//1.1. return 10 -> "ID를 확인하세요."
		//1.2. return 20 -> "비번을 확인하세요."
		//1.3. return 30 -> "로그인 성공"
		//    1.3.1. 단건조회 -> session Write
		LOG.debug("1===================");
		LOG.debug("1=user="+user);
		LOG.debug("1===================");
		String message = "";
		if(null == user.getU_id() || "".equals(user.getU_id().trim())) {
			message="ID를 입력 하세요.";
			throw new IllegalArgumentException(message);
		}

		if(null == user.getPasswd() || "".equals(user.getPasswd().trim())) {
			message="비번을 입력 하세요.";
			throw new IllegalArgumentException(message);
		}

		int flag = this.userService.idPassCheck(user);
		MessageVO  messageVO=new MessageVO();
		messageVO.setMsgId(String.valueOf(flag));

		if(10==flag) {//ID CHECK
			messageVO.setMsgMsg("아이디를 확인 하세요.");
		}else if(20==flag) {// pass CHECK
			messageVO.setMsgMsg("비번를 확인 하세요.");
		}else if(30==flag) {// 성공
			messageVO.setMsgMsg("로그인 성공.");
			//사용자 정보 조회
			UserVO userInfo = (UserVO) this.userService.doSelectOne(user);
			LOG.debug("2===================");
			LOG.debug("2=userInfo="+userInfo);
			LOG.debug("2===================");
			session.setAttribute("user", userInfo);
		}

		Gson gson=new Gson();
		String json = gson.toJson(messageVO);
		LOG.debug("2===================");
		LOG.debug("2=json="+json);
		LOG.debug("2===================");

		return json;
	}


//	@RequestMapping(value="member/do_retrieve.do",method = RequestMethod.GET)
//	public String doRetrieve(HttpServletRequest req,SearchVO search,Model model) {
//		LOG.debug("1===================");
//		LOG.debug("1=search="+search);
//		LOG.debug("1===================");
//		//페이지 사이즈
//		if(search.getPageSize()==0)
//		{
//			search.setPageSize(10);
//		}
//
//		//페이지 num
//		if(search.getPageNum()==0) {
//			search.setPageNum(1);
//		}
//
//		//검색구분
//		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
//		//검색어
//		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
//		model.addAttribute("param", search);
//
//		LOG.debug("1.2===================");
//		LOG.debug("1.2=search="+search);
//		LOG.debug("1.2===================");
//
//		List<UserVO> list =(List<UserVO>) userService.doRetrieve(search);
//		LOG.debug("1.3===================");
//		for(UserVO vo :list) {
//			LOG.debug("vo="+vo);
//		}
//		LOG.debug("1.3===================");
//		model.addAttribute("list", list);
//
//		//총글수
//		int totalCnt = 0;
//		if(null != list && list.size()>0) {
//			totalCnt = list.get(0).getTotalCnt();
//		}
//		model.addAttribute("totalCnt", totalCnt);
//
//		int maxPageNo = ((totalCnt - 1) / search.getPageSize()) + 1;
//
//		model.addAttribute("maxPageNo", maxPageNo);
//		model.addAttribute("pageNum", search.getPageNum());
//		//
//
//		//member/member_mng  -> /+member/member_mng+.jsp
//		return "member/member_mng";
//	}
	//api -> JSON(list) -> 화면 -> json to html
	@RequestMapping(value="member/do_retrieve.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(HttpServletRequest req,SearchVO search,Model model) {
		LOG.debug("1===================");
		LOG.debug("1=search="+search);
		LOG.debug("1===================");
		//페이지 사이즈
		if(search.getPageSize()==0)
		{
			search.setPageSize(10);
		}

		//페이지 num
		if(search.getPageNum()==0) {
			search.setPageNum(1);
		}

		//검색구분
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		//검색어
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		model.addAttribute("param", search);

		LOG.debug("1.2===================");
		LOG.debug("1.2=search="+search);
		LOG.debug("1.2===================");

		List<UserVO> list =(List<UserVO>) userService.doRetrieve(search);
		LOG.debug("1.3===================");
		for(UserVO vo :list) {
			LOG.debug("vo="+vo);
		}
		LOG.debug("1.3===================");
		model.addAttribute("list", list);

		//총글수
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt);

		int maxPageNo = ((totalCnt - 1) / search.getPageSize()) + 1;

		model.addAttribute("maxPageNo", maxPageNo);
		model.addAttribute("pageNum", search.getPageNum());
		//

		//list to json
		Gson gson=new Gson();
		String json = gson.toJson(list);
		LOG.debug("====================");
		LOG.debug("=json="+json);
		LOG.debug("====================");


		//member/member_mng  -> /+member/member_mng+.jsp
		return json;
	}

	@RequestMapping(value="member/do_select_one.do",method = RequestMethod.POST
		       ,produces = "application/json;charset=UTF-8")
    @ResponseBody
	public String doSelectOne(UserVO user) {
		LOG.debug("1===================");
		LOG.debug("1=user="+user);
		LOG.debug("1===================");

		UserVO outVO = (UserVO) userService.doSelectOne(user);
		//outVO.setLevel(outVO.getLevel().intValue());

		LOG.debug("1.2===================");
		LOG.debug("1.2=outVO="+outVO);
		LOG.debug("1.2===================");

		Gson gson=new Gson();
		String json = gson.toJson(outVO);

		LOG.debug("1.3===================");
		LOG.debug("1.3=json="+json);
		LOG.debug("1.3===================");

		return json;
	}


	@RequestMapping(value="member/do_delete.do",method = RequestMethod.POST
			       ,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(UserVO user) {
		LOG.debug("1===================");
		LOG.debug("1=user="+user);
		LOG.debug("1===================");

		int flag = 0;
		flag = userService.doDelete(user);

		MessageVO message=new MessageVO();
		message.setMsgId(String.valueOf(flag));
		LOG.debug("1.2===================");
		LOG.debug("1.2=flag="+flag);
		LOG.debug("1.2===================");

		//성공
		if(flag ==1) {
			message.setMsgMsg(user.getU_id()+"님이 삭제 되었습니다.");
		//실패
		}else {
			message.setMsgMsg(user.getU_id()+"삭제 실패.");
		}


		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(message);

		LOG.debug("1.3===================");
		LOG.debug("1.3=json="+json);
		LOG.debug("1.3===================");

		return json;
	}





	@RequestMapping(value = "member/do_update.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(UserVO user) {
		LOG.debug("1===================");
		LOG.debug("1=user="+user);
		LOG.debug("1===================");

		int  flag = userService.doUpdate(user);
		LOG.debug("1.2===================");
		LOG.debug("1.2=flag="+flag);
		LOG.debug("1.2===================");

		//메시지 처리
		MessageVO message=new MessageVO();

		message.setMsgId(flag+"");
		//성공
		if(flag ==1) {
			message.setMsgMsg(user.getName()+"님이 수정 되었습니다.");
		//실패
		}else {
			message.setMsgMsg(user.getName()+"님 등록 실패.");
		}

		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(message);

		LOG.debug("1.3===================");
		LOG.debug("1.3=json="+json);
		LOG.debug("1.3===================");

		return json;

	}


	@RequestMapping(value = "member/add.do",method = RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(UserVO user) {

		LOG.debug("1===================");
		LOG.debug("1=user="+user);
		LOG.debug("1===================");

		int  flag = userService.add(user);

		LOG.debug("1.2===================");
		LOG.debug("1.2=flag="+flag);
		LOG.debug("1.2===================");

		//메시지 처리
		MessageVO message=new MessageVO();

		message.setMsgId(flag+"");
		//성공
		if(flag ==1) {
			message.setMsgMsg(user.getName()+"님이 등록 되었습니다.");
		//실패
		}else {
			message.setMsgMsg(user.getName()+"님 등록 실패.");
		}

		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(message);

		LOG.debug("1.3===================");
		LOG.debug("1.3=json="+json);
		LOG.debug("1.3===================");

		return json;
	}


}
