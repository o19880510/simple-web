package woo.study.web.system.listener.commander;

public class ExitAction implements Action{

	@Override
	public void dos(String... commanders) {
		System.exit(0);
		
	}
}