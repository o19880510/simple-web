package woo.study.web.common.util.filesync;

import org.junit.Test;

import woo.study.web.common.util.filesync.FileSyncRmiClient;
import woo.study.web.common.util.filesync.FileSyncRmiClientMultilImpl;
import woo.study.web.common.util.filesync.FileSyncRmiServerStartup;


public class FileSyncRmiClientTest {
	
	int port = 6000;
	String ip = "172.16.4.159";
	String serverName = "fileSync";
	String workDir = "C:/Users/tianjp/Desktop/新建文件夹/main";
	String workDir01 = "C:/Users/tianjp/Desktop/新建文件夹/brach01";
	String workDir02 = "C:/Users/tianjp/Desktop/新建文件夹/brach02";
	String workDir03 = "C:/Users/tianjp/Desktop/新建文件夹/brach03";
	
	@Test
	public void test() throws InterruptedException{

		
		new FileSyncRmiServerStartup(port, ip, serverName, workDir01).startup();
//		new FileSyncRmiServerStartup(port+1, ip, serverName, workDir02).startup();
		
		
		Thread.sleep(3 * 1000);
		
		FileSyncRmiClient fileSyncClient = new FileSyncRmiClientMultilImpl(workDir, serverName, ip + ":" + port ); //    , ip + ":" + (port + 1 )
		
		fileSyncClient.sync("/2.jpg");
	}
	
	
	@Test
	public void testFromView() throws InterruptedException{
		
		FileSyncRmiClient fileSyncClient = new FileSyncRmiClientMultilImpl(workDir, "fileSyncServer", "127.0.0.1:10000");
		fileSyncClient.sync("/");
		
	}
	
	@Test
	public void test4Cmd() throws InterruptedException{
		
		// FileSyncRmiServerConfigStartup.main(null);
		
		FileSyncRmiClient fileSyncClient = new FileSyncRmiClientMultilImpl(workDir, "fileSyncServer", "127.0.0.1:10000");
		fileSyncClient.sync("/");
		
	}
	
	public static void main(String[] args) {
		
//		FileSyncRmiServerConfigStartup.main(null);
	}
}
