package woo.study.web.common.util.value;


public class EmptyGettable implements ValueGettable {

	@Override
	public Object get(String key) {
		return null;
	}

}
