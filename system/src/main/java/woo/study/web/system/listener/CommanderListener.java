package woo.study.web.system.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import woo.study.web.system.listener.commander.Commander;

public class CommanderListener implements ServletContextListener {
	
	private static Logger log = LoggerFactory.getLogger(CommanderListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		new Thread(new Commander()).start();
		log.info("Commander start...");
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
	

}
