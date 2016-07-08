package com.lutongnet.base.cookies;

public interface Store<K,V> {
	
	public static enum Time {
		SECOND(1),
		MINUTE(60),
		HOUR(60 * 60),
		DAY(HOUR.getValue() * 24),
		WEEK(DAY.getValue() * 7),
		MONTH(DAY.getValue() * 30),
		QUARTER(MONTH.getValue() * 3),
		YEAR(DAY.getValue() * 365);
		
		private int value;
		private Time(int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 永久保存
	 * @param key
	 * @param value
	 */
	void set(K key, V value);
	
	/**
	 * 非永久保存，指定保存时长，单位为秒
	 * @param key
	 * @param value
	 * @param duration
	 */
	void set(K key, V value, int duration);
	
	/**
	 * 非永久保存，指定保存时长和单位
	 * @param key
	 * @param value
	 * @param duration
	 * @param unit
	 */
	void set(K key, V value, int duration, Time unit);
	
	/**
	 * 获取保存的值
	 * @param key
	 * @return
	 */
	V get(K key);
	
	/**
	 * 删除保存的值
	 * @param key
	 */
	void delete(K key);
}
