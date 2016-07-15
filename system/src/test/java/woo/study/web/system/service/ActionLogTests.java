package woo.study.web.system.service;

import javax.annotation.Resource;


import org.junit.Test;

import test.tools.BaseTests;
import woo.study.web.system.model.entity.ActionLog;
import woo.study.web.system.service.LogService;


public class ActionLogTests extends BaseTests {

	@Resource(name="logService")
	private LogService	logService;

	@Test
	public void get() {
		ActionLog a = logService.get(1);
		System.out.println(a.getContent());
		System.out.println(a.getDetail());

		ActionLog b = logService.get(2);
		System.out.println(b.getContent());
		System.out.println(b.getDetail());
	}
}
