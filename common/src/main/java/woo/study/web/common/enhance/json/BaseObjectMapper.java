package woo.study.web.common.enhance.json;

import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class BaseObjectMapper extends ObjectMapper{

	public BaseObjectMapper() {
		super();
		
		registerModule(new JodaModule());
		setTimeZone(DateTimeZone.getDefault().toTimeZone());
		
	}

	public BaseObjectMapper(JsonFactory jf, DefaultSerializerProvider sp, DefaultDeserializationContext dc) {
		super(jf, sp, dc);
	}

	public BaseObjectMapper(JsonFactory jf) {
		super(jf);
	}

	public BaseObjectMapper(ObjectMapper src) {
		super(src);
	}

}
