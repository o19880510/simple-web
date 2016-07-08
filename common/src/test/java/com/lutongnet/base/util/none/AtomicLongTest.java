package com.lutongnet.base.util.none;

import java.util.concurrent.atomic.AtomicLong;
public class AtomicLongTest {

	static AtomicLong	atomicLong	= new AtomicLong();

	private static String getUalId() {
		atomicLong.compareAndSet(99L, 0L);
		long seq = atomicLong.incrementAndGet();
		return String.format("%07d", seq);
	}
	
	
	@org.junit.Test
	public void test(){
		new Thread(new Test("UAL01")).start();
		new Thread(new Test("UAL02")).start();
		new Thread(new Test("UAL03")).start();
		new Thread(new Test("UAL04")).start();
		new Thread(new Test("UAL05")).start();
		new Thread(new Test("UAL06")).start();
		new Thread(new Test("UAL07")).start();
		new Thread(new Test("UAL08")).start();
		new Thread(new Test("UAL09")).start();
	}
	
	public static class Test implements Runnable {

		private String	name;

		public Test(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			int i = 0;
			while (i++ < 30) {
				System.out.println(name + "-" + AtomicLongTest.getUalId());
			}
		}

	}

}
