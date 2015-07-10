<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarReservaMaterialBibliograficoPendente.heading"/></h1>

<c:if test="${fn:length(listaReservaPendente) > 0}">
<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="listarReservarMaterialBibliografico.tituloTabela.dataInicioReserva"/></th>
			<th><fmt:message key="listarReservarMaterialBibliografico.tituloTabela.materialBibliografico"/></th>
			<th><fmt:message key="listarReservarMaterialBibliografico.tituloTabela.usuario"/></th>
			<th><fmt:message key="listarReservarMaterialBibliografico.tituloTabela.observacao"/></th>
			<th><fmt:message key="listarReservarMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaReservaPendente}" var="registro">
		<tr>
			<td><c:out value="${registro.dataInicioReserva}"/></td>
			<td><c:out value="${registro.materialBibliografico.codigoMaterialBibliografico}"/> - <c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td><c:out value="${registro.observacao}"/></td>
			<td>
				<a href="converter.form?idReservarMaterialBibliografico=<c:out value="${registro.idReservarMaterialBibliografico}"/>"><img alt="Emprestar" src="<c:url value="/images/emprestar.jpg"/>" title="Emprestar"></a>
			</td>
		</tr>
</c:forEach>
	</tbody>
</table>
</c:if>

<c:if test="${fn:length(listaReservaPendente) <= 0}">
	<fmt:message key="listarReservaMaterialBibliograficoPendente.semRegistro"/>
</c:if>

<c:if test="${param.error != null}">
<br>
<span id="errors" class="error"><fmt:message key="${param.error}"/></span>
<br>
</c:if>
<br><br>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>