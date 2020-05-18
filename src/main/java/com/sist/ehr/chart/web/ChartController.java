package com.sist.ehr.chart.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.sist.ehr.chart.service.PieVO;

@Controller
public class ChartController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "chart/pie_chart_view.do",method = RequestMethod.GET)
	public String doPieView() {
		LOG.debug("====================");
		LOG.debug("=doPieView()=");
		LOG.debug("====================");
		return "chart/pie_chart";
	}
	//PieVO
	@RequestMapping(value = "chart/pie_chart.do",method = RequestMethod.GET
			,produces = "application/json; charset=UTF-8")
	@ResponseBody   
	public String pieChart() {
		LOG.debug("====================");
		LOG.debug("=pieChart()=");
		LOG.debug("====================");
//        ['Work',     11],
//        ['Eat',      2],
//        ['Commute',  2],
//        ['Watch TV', 2],
//        ['Sleep',    7]
        		
		PieVO out01=new PieVO("공부",11);
		PieVO out02=new PieVO("식사",2);
		PieVO out03=new PieVO("Commute",2);
		PieVO out04=new PieVO("컴퓨터",2);
		PieVO out05=new PieVO("잠",7);
		
		List<PieVO> list=new ArrayList<>();
		list.add(out01);
		list.add(out02);
		list.add(out03);
		list.add(out04);
		list.add(out05);
		
		JsonArray  jArray=new JsonArray();
		for(PieVO vo:list) {
			JsonArray  sArray=new JsonArray();
			sArray.add(vo.getTask());
			sArray.add(vo.getHoursPerDay());
			
			jArray.add(sArray);
		}
		
		LOG.debug("=========================");
		LOG.debug("=jArray=="+jArray.toString());
		LOG.debug("=========================");
		
		
		return jArray.toString();
	}
	
	
}



