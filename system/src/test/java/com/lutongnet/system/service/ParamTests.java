package com.lutongnet.system.service;

import java.io.IOException;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.lutongnet.system.model.entity.Param;
import com.lutongnet.system.model.entity.ParamItem;
import com.lutongnet.system.service.ParamService;

import test.tools.BaseTests;
import test.tools.TestBindingResult;
import test.tools.TestModel;
import test.tools.TestRequest;
import test.tools.TestServletContext;
import test.tools.TestSession;

public class ParamTests extends BaseTests {

	@Autowired
	private ParamService	paramService;

	// @Test
	public void add ( ) {
		ParamItem item = new ParamItem ( );
		item.setName ( "red" );
		item.setValue ( "红色" );

		ParamItem item2 = new ParamItem ( );
		item2.setName ( "white" );
		item2.setValue ( "白色" );

		Param param = new Param ( );
		param.setName ( "COLOR" );
		param.setDescription ( "各种颜色" );

		param.addItem ( item );
		param.addItem ( item2 );
		
		BindingResult bindingResult = TestBindingResult.getInstance();
		
		paramService.add(param, bindingResult , new TestModel(), new TestRequest(new TestSession(new TestServletContext())));
		
		System.out.println(bindingResult.hasErrors());
	}
}
