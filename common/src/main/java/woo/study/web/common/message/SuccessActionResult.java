package woo.study.web.common.message;


public class SuccessActionResult extends ActionResult {

	public SuccessActionResult ( String message ) {
		super ( ActionResult.Type.SUCCESS.name() , message );
	}

	public SuccessActionResult ( ) {
		super ( ActionResult.Type.SUCCESS.name() , "action.result.success" );
	}
}
