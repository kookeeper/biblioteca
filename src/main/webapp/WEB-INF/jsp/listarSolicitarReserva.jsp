<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarSolicitarReserva.heading"/></h1>

<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="listarSolicitarReserva.tituloTabela.dataSolicitacao"/></th>
			<th><fmt:message key="listarSolicitarReserva.tituloTabela.materialBibliografico"/></th>
			<th><fmt:message key="listarSolicitarReserva.tituloTabela.usuario"/></th>
			<th><fmt:message key="listarSolicitarReserva.tituloTabela.observacao"/></th>
			<th><fmt:message key="listarSolicitarReserva.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaSolicitarReserva}" var="registro">
		<tr>
			<td><c:out value="${registro.dataSolicitacao}"/></td>
			<td><c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td><c:out value="${registro.observacao}"/></td>
			<td><a href="editar.form?idSolicitarReserva=<c:out value="${registro.idSolicitarReserva}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idSolicitarReserva=<c:out value="${registro.idSolicitarReserva}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idSolicitarReserva=0">
	<img alt="<fmt:message key="listarSolicitarReserva.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarSolicitarReserva.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>