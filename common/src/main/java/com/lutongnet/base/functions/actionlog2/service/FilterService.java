package com.lutongnet.base.functions.actionlog2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FilterService {

	public boolean beforeCheck(HttpServletRequest request, HttpServletResponse response);

	public void beforeDoFilter(HttpServletRequest request, HttpServletResponse response);

	public boolean afterCheck(HttpServletRequest request, HttpServletResponse response);

	public void afterDoFilter(HttpServletRequest request, HttpServletResponse response);
	
}
