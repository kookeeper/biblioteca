<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarGrupoUsuario.heading"/></h1>
<form:form method="post" commandName="editarForm">
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarGrupoUsuario.descricao"/></td>
      <td width="20%">
        <form:input path="descricao"/>
      </td>
      <td width="60%">
        <form:errors path="descricao" cssClass="error"/>
      </td>
    </tr>
  </table>

<br>
<input type="image" src="<c:url value="/images/salvar.gif"/>">
</form:form>
<br>
<br>

<c:if test="${idGrupoUsuario != null}">
	<form:form method="post" commandName="editarPermissaoForm" action="../grupousuariopermissao/editar.form">
	<table class="lista">
		<thead>
			<tr>
				<th><fmt:message key="listarGrupoUsuarioPermissao.tituloTabela.funcionalidade"/></th>
				<th><fmt:message key="listarGrupoUsuarioPermissao.tituloTabela.tipoPermissao"/></th>
				<th><fmt:message key="listarGrupoUsuarioPermissao.tituloTabela.acao"/></th>
			</tr>
		</thead>
		<tbody>
	<c:forEach items="${listaGrupoUsuarioPermissao}" var="grupoUsuarioPermissao">
			<tr>
				<td><c:out value="${grupoUsuarioPermissao.permissao.funcionalidade}"/></td>
				<td><c:out value="${grupoUsuarioPermissao.tipoPermissao}"/></td>
				<td align="center"><a href="../grupousuariopermissao/apagar.form?idGrupoUsuario=<c:out value="${grupoUsuarioPermissao.grupoUsuario.idGrupoUsuario}"/>&idGrupoUsuarioPermissao=<c:out value="${grupoUsuarioPermissao.idGrupoUsuarioPermissao}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
			</tr>
	</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td>
					<form:hidden path="fkGrupoUsuario"/>
					<form:select path="idPermissao" items="${listaPermissao}" itemLabel="funcionalidade" itemValue="idPermissao"/>
		        	<form:errors path="idPermissao" cssClass="error"/>
				</td>
				<td>
					<form:checkbox path="consultar"/>Consultar<br>
					<form:checkbox path="alterar"/>Alterar<br>
					<form:checkbox path="excluir"/>Excluir<br>
		        	<form:errors path="consultar" cssClass="error"/>
				</td>
				<td>
					<input type="image" src="<c:url value="/images/salvar.gif"/>">
				</td>
			</tr>
		</tfoot>
	</table>
	</form:form>
</c:if>
<br><br>
<a href="<c:url value="listar.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.lista"/>">
</a>
</body>
</html>