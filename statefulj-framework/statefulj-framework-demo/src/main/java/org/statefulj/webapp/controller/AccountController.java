package org.statefulj.webapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;
import org.statefulj.framework.core.annotations.StatefulController;
import org.statefulj.framework.core.annotations.Transition;
import org.statefulj.webapp.model.Account;

@StatefulController(
	clazz=Account.class,
	startState=Account.NON_EXISTENT
)
public class AccountController {
	
	// Make sure that only owner's can access the particular account
	//
	@Transition(event="springmvc:/accounts/{id}")
	@PreAuthorize("#account.owner.email == principal.username")
	public ModelAndView displayAccount(Account account, String event) {
		ModelAndView mv = new ModelAndView("account");
		mv.addObject("account", account);
		return mv;
	}
}