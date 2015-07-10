<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="usuarioListar.heading"/></h1>

<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="usuarioListar.tituloTabela.nomeUsuario"/></th>
			<th><fmt:message key="usuarioListar.tituloTabela.nomeUsuarioChines"/></th>
			<th><fmt:message key="usuarioListar.tituloTabela.login"/></th>
			<th><fmt:message key="usuarioListar.tituloTabela.bloqueado"/></th>
			<th><fmt:message key="usuarioListar.tituloTabela.grupoUsuario"/></th>
			<th><fmt:message key="usuarioListar.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaUsuario}" var="usuario">
		<tr>
			<td><c:out value="${usuario.nomeUsuario}"/></td>
			<td><c:out value="${usuario.nomeUsuarioChines}"/></td>
			<td><c:out value="${usuario.login}"/></td>
			<td><c:out value="${usuario.bloqueado}"/></td>
			<td><c:out value="${usuario.grupoUsuario.descricaoGrupoUsuario}"/></td>
			<td><a href="editar.form?idUsuario=<c:out value="${usuario.idUsuario}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idUsuario=<c:out value="${usuario.idUsuario}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idUsuario=0">
	<img alt="<fmt:message key="usuarioListar.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="usuarioListar.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>