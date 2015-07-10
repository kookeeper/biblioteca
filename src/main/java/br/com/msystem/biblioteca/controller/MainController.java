package br.com.msystem.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = "main.form")
	public ModelAndView get() {

		return new ModelAndView("main");
	}

  @RequestMapping(value = "administrativo.form")
  public ModelAndView getAdministrativo() {
    
    return new ModelAndView("administrativo");
  }

  @RequestMapping(value = "logoff.form")
  public ModelAndView getLogoff(HttpServletRequest request) {
    request.getSession().invalidate();

    return new ModelAndView("redirect:/index.jsp");
  }
}
