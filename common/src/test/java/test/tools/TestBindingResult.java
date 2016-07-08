package test.tools;

import java.util.HashMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

public class TestBindingResult{
	
	public static BindingResult getInstance(){
		MapBindingResult bindingResult = new MapBindingResult(new HashMap(),"TestBindingResult");
		return bindingResult;
	}

}
