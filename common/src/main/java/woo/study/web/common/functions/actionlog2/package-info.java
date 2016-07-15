/**
 * user-action-log 2.0.0版本<br>
 * 
 * 工具可记录用户请求。包括输入参数、业务状态、异常和页面停留时间。<br>
 * <br>
 * 
 * 如何使用：<br>
 * 1、 config.properties。在此文件中配置工具参数<br>
 * 2、user-action-log.xml。配置请求action，url、param等参数<br>
 * 3、通过浏览器测试配置中的url。之后查看三张表：t_ual_base, t_ual_business, t_ual_stay_time<br>
 * 
 * 程序员须知：<br>
 * 1、日志有记录开关：整体记录开关、记录页面停留时间开关、单个URI记录开关（通过请求参数）<br>
 * 2、日志异步记录<br>
 * 3、action-code必须保证唯一性。一个URL对应唯一的action-code<br>
 * 
 * <br>
 *  测试要点：<br>
 * 	1、配置文件开关测试：整体、停留时间<br>
 * <br>
 * 	2、url映射测试<br>
 * 	3、单条记录 record-flag测试<br>
 * 	<br>
 *  4、参数测试<br>
 * 
 */
/**
 * @author tianjp
 * @version 2.0.0
 * @since 1.0.4
 *
 */
package woo.study.web.common.functions.actionlog2;