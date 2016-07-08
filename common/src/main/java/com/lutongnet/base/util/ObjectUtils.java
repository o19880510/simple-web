package com.lutongnet.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.SerializationException;

/**
 * java 对象帮助工具
 * 
 * 术语解释 浅克隆 被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。
 * 换言之，浅复制仅仅复制所考虑的对象，而不复制它所引用的对象。
 * 
 * 深克隆 被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象的变量将指向被复制过的新对象，
 * 而不再是原有的那些被引用的对象。 换言之，深复制把要复制的对象所引用的对象都复制了一遍。
 * 
 * @author tianjp
 *
 */
public class ObjectUtils {

	/**
	 * 深度克隆对象
	 * 
	 * @param obj
	 *            可序列化对象
	 * @param clazz
	 *            对象类型
	 * @return 克隆对象
	 */
	public static <T> T clone(Serializable obj, Class<T> clazz) {
		try {
			return (T) clone(obj);
		} catch (Exception e) {
			throw new RuntimeException("ObjectUtils clone fail! ", e);
		}
	}

	/**
	 * 深度克隆对象
	 * 
	 * @param obj
	 *            可序列化对象
	 * @return 克隆对象
	 */
	public static Object clone(Serializable obj) {
		try {

			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();

			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteIn);
			Object cloneObj = objectInputStream.readObject();

			return cloneObj;
		} catch (Exception e) {
			throw new RuntimeException("ObjectUtils clone fail! ", e);
		}

	}

	public static byte[] getBytes(Serializable obj) {
		try {

			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();

			return byteOut.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("ObjectUtils getBytes fail! ", e);
		}
	}

	public static byte[] getBytes(Object obj) {
		if (obj != null && obj instanceof Serializable) {

			return getBytes((Serializable) obj);
		}

		throw new SerializationException(obj + " can not Serializable.");
	}

	public static Object toObject(byte[] objBytes) {
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(objBytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteIn);
			return objectInputStream.readObject();
		} catch (Exception e) {
			throw new RuntimeException("ObjectUtils toObject fail! ", e);
		}
	}
}
