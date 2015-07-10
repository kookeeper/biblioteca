<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarGrupoUsuario.heading"/></h1>

<c:if test="${param.error != null}">
<br>
<span id="senha.errors" class="error"><c:out value="${param.error}"/></span>
<br>
</c:if>
<table class="lista">
	<thead>
		<tr>
			<th><fmt:message key="listarGrupoUsuario.tituloTabela.descricao"/></th>
			<th><fmt:message key="listarGrupoUsuario.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaGrupoUsuario}" var="grupoUsuario">
		<tr>
			<td><c:out value="${grupoUsuario.descricaoGrupoUsuario}"/></td>
			<td><a href="editar.form?idGrupoUsuario=<c:out value="${grupoUsuario.idGrupoUsuario}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idGrupoUsuario=<c:out value="${grupoUsuario.idGrupoUsuario}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idGrupoUsuario=0">
	<img alt="<fmt:message key="listarGrupoUsuario.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarGrupoUsuario.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>