package woo.study.web.common.util.parameter;

public class ParameterFactory {

	public static final Parameter	DATE_START		= new ParameterValue("开始时间", "dateStart", Parameter.Type.DATE);

	public static final Parameter	DATE_END		= new ParameterValue("结束时间", "dateEnd", Parameter.Type.DATE);

	public static final Parameter	DATETIME_START	= new ParameterValue("开始时间", "datetimeStart", Parameter.Type.DATETIME);

	public static final Parameter	DATETIME_END	= new ParameterValue("结束时间", "datetimeEnd", Parameter.Type.DATETIME);

	public static final Parameter	TIME_START		= new ParameterValue("开始时间", "timeStart", Parameter.Type.TIME);

	public static final Parameter	TIME_END		= new ParameterValue("结束时间", "timeEnd", Parameter.Type.TIME);
	
	
	public static Parameter get(String chiName, String engName, Parameter.Type type) {
		return new ParameterValue(chiName, engName, type);
	}

	public static Parameter get(String chiName, String engName, Object defaultValue, Parameter.Type type) {
		return new ParameterValue(chiName, engName, type, defaultValue);
	}
}
