package com.sist.ehr;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.sist.ehr.cmn.StringUtil;

@Controller
public class LocaleController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	LocaleResolver localeResolver;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest req, HttpServletResponse res,
			Locale locale, Model model ) {
		String lang = StringUtil.nvl(req.getParameter("lang"));
		LOG.debug("========================");
		LOG.debug("=lang="+lang);
		LOG.debug("========================");
		
		Locale  paramLocale=new Locale(lang);
		localeResolver.setLocale(req, res, paramLocale);
		
		
		return "login/login";
	}
}












