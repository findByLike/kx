package com.example.demo.controller;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Basic;
import com.example.demo.domain.Basicsplit;
import com.example.demo.repository.BasicMapper;

@Controller
public class BasicController {
	@Autowired
	private BasicMapper basicMapper;

	@RequestMapping("index")
	public String helloFtl(Map<String, Object> map) {

		map.put("hello", "Hello FreeMarker");
		return "index";
	}

	@ResponseBody
	@RequestMapping("getkx")
	public Basicsplit getkx(@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2) throws ParseException {
		List<Basic> findByDate = basicMapper.findByDate(date1, date2);
		Basicsplit bsc = new Basicsplit();
		List<String> dateLists = new ArrayList<String>();
		List<String> openLists = new ArrayList<String>();
		List<String> closeLists = new ArrayList<String>();
		List<String> highLists = new ArrayList<String>();
		List<String> lowLists = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for (Basic basic : findByDate) {
			dateLists.add(basic.getDate());
			openLists.add(basic.getOpen());
			closeLists.add(basic.getClose());
			highLists.add(basic.getHigh());
			lowLists.add(basic.getLow());
			if(Strings.isNullOrEmpty(bsc.getMin())
					||Double.parseDouble(basic.getLow())<Double.parseDouble(bsc.getMin())){
				bsc.setMin(basic.getLow());
			}
			
		}
		bsc.setClose(closeLists);
		bsc.setDate(dateLists);
		bsc.setHigh(highLists);
		bsc.setLow(lowLists);
		bsc.setOpen(openLists);
		return bsc;
	}
}
