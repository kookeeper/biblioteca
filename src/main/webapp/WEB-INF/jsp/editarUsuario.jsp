<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarUsuario.heading"/></h1>
<form:form method="post" commandName="editarUsuarioForm">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.grupoUsuario"/></td>
      <td width="20%"><form:select path="grupoUsuario" items="${listaGrupoUsuario}"/></td>
      <td width="60%"><form:errors path="grupoUsuario" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.nomeUsuario"/></td>
      <td width="20%"><form:input path="nomeUsuario"/></td>
      <td width="60%"><form:errors path="nomeUsuario" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.nomeUsuarioChines"/></td>
      <td width="20%"><form:input path="nomeUsuarioChines"/></td>
      <td width="60%"><form:errors path="nomeUsuarioChines" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.login"/></td>
      <td width="20%"><form:input path="login"/></td>
      <td width="60%"><form:errors path="login" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.senha"/></td>
      <td width="20%"><form:input path="senha"/></td>
      <td width="60%"><form:errors path="senha" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.dataCadastro"/></td>
      <td width="20%"><form:input path="dataCadastro" readonly="true"/></td>
      <td width="60%"><form:errors path="dataCadastro" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.email"/></td>
      <td width="20%"><form:input path="email" /></td>
      <td width="60%"><form:errors path="email" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.endereco"/></td>
      <td width="20%"><form:input path="endereco" /></td>
      <td width="60%"><form:errors path="endereco" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.telefone"/></td>
      <td width="20%"><form:input path="telefone" /></td>
      <td width="60%"><form:errors path="telefone" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.bloqueado"/></td>
      <td width="20%"><form:checkbox path="bloqueado"/></td>
      <td width="60%"><form:errors path="bloqueado" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarUsuario.motivoBloqueio"/></td>
      <td width="20%"><form:textarea path="motivoBloqueio"/></td>
      <td width="60%"><form:errors path="motivoBloqueio" cssClass="error"/></td>
    </tr>
  </table>
  <br>
  <input type="image" src="<c:url value="/images/salvar.gif"/>">

  <a href="<c:url value="listar.form"/>">
    <img alt="<fmt:message key="voltar.lista"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.lista"/>">
  </a>

</form:form>
</body>
</html>