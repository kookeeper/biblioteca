package br.com.msystem.biblioteca.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.msystem.biblioteca.vo.Usuario;

public class LoginFilter implements Filter {

  private FilterConfig filterConfig;

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.Filter#destroy()
   */
  @Override
  public void destroy() {

    this.filterConfig = null;

  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
   * javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    
    String url = ((HttpServletRequest) request).getServletPath();

    System.out.println("Acessando url: " + url);

    if (!url.equals("/usuario/logar.form")) {
      HttpSession session = ((HttpServletRequest) request).getSession();

      Usuario usuario = (Usuario) session.getAttribute("usuario");

      if (usuario == null) {
        RequestDispatcher request_Dispatcher = request
            .getRequestDispatcher("/index.jsp");
        request_Dispatcher.forward(request, response);
      } else {
        chain.doFilter(request, response);
      }
    } else {
      chain.doFilter(request, response);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

}