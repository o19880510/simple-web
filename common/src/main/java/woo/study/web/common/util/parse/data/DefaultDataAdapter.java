package woo.study.web.common.util.parse.data;


public class DefaultDataAdapter extends CommonDataAdapter {

	public DefaultDataAdapter() {
		super();
	}
	
	public DefaultDataAdapter(Object inData) {
		super(inData);
	}

	protected void transfer() {
		outData = inData;
	}

}
