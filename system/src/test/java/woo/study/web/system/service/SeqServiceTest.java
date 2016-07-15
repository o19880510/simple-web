package woo.study.web.system.service;

import javax.annotation.Resource;

import org.junit.Test;

import test.tools.BaseTests;
import woo.study.web.common.functions.actionlog.constants.SeqContants;
import woo.study.web.common.functions.actionlog.service.SeqService;
import woo.study.web.common.util.IOSystem;

public class SeqServiceTest extends BaseTests{
	
	@Resource(name="seqService")
	SeqService seqService;

	@Test
	public void testSeq(){
		String table = "T_USER_ACTION_LOG";
		
		String originalSeqNo = seqService.getOriginalSeqNo(table);
		String seqNo = seqService.getSeqNo(table);
		String seqNoWithTime = seqService.getSeqNoWithTime(table);
		
		System.out.println("originalSeqNo:\t" + originalSeqNo);
		System.out.println("seqNo:\t" + seqNo);
		System.out.println("seqNoWithTime:\t" + seqNoWithTime);
	}
	
	@Test
	public void testSeqMuilt() throws InterruptedException{
		GetSeq seq1 = new GetSeq(seqService, "TASK-1 ");
		GetSeq seq2 = new GetSeq(seqService, "TASK-2 ");
		GetSeq seq3 = new GetSeq(seqService, "TASK-3 ");
		seq1.start();
		seq2.start();
		seq3.start();
		
		seq1.join();
		seq2.join();
		seq3.join();
		
		IOSystem.writeTo(seq1.sb.toString(), "d:/req1.txt");
		IOSystem.writeTo(seq2.sb.toString(), "d:/req2.txt");
		IOSystem.writeTo(seq3.sb.toString(), "d:/req3.txt");
	}
	
	
	private class GetSeq extends Thread{
		
		private SeqService seqService;
		private String threadId;
		public StringBuilder sb;
		
		public GetSeq(SeqService seqService, String threadId){
			this.seqService = seqService;
			this.threadId = threadId;
			sb = new StringBuilder();
		}
		public void run(){
			int i = 0;
			while(i < 100){
				String logId = seqService.getSeqNoWithTime(SeqContants.Tables.T_USER_ACTION_LOG);
				System.out.println(threadId+":"+logId);
				
				sb.append(threadId).append("=").append(logId).append("\r\n");
				i++;
			}
		}
	}
	
}
