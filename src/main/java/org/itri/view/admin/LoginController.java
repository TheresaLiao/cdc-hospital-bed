package org.itri.view.admin;

import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;

import org.itri.view.login.AuthenticationService;
import org.itri.view.login.AuthenticationServiceImpl;
import org.itri.view.login.dao.UserCredential;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class LoginController extends SelectorComposer<Component> {

	AuthenticationService authService = new AuthenticationServiceImpl();

	@Wire
	Textbox account;
	@Wire
	Textbox password;

	@Listen("onClick=#login; onOK=#loginWin")
	public void doLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("doLogin");

		String username = account.getValue();
		String pd = password.getValue();

		if (!authService.login(username, pd)) {
			System.out.println("account or password are not correct.");
			return;
		}

//		UserCredential cre = authService.getUserCredential();
//		System.out.println(cre.getPatientId());

		Executions.sendRedirect("/humanCare.zul");
	}
}
