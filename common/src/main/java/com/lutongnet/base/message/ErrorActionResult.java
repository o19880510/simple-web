package com.lutongnet.base.message;

public class ErrorActionResult extends ActionResult {

	public ErrorActionResult ( String message ) {
		super ( ActionResult.Type.ERROR.name() , message );
	}

	public ErrorActionResult ( ) {
		super ( ActionResult.Type.ERROR.name() , "action.result.error" );
	}
}
