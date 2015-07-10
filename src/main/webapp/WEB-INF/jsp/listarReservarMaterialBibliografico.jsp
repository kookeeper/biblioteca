<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarReservarMaterialBibliografico.heading"/></h1>

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
<c:forEach items="${listaReservarMaterialBibliografico}" var="registro">
		<tr>
			<td><c:out value="${registro.dataInicioReserva}"/></td>
			<td><c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td><c:out value="${registro.observacao}"/></td>
			<td>
				<a href="editar.form?idReservarMaterialBibliografico=<c:out value="${registro.idReservarMaterialBibliografico}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
				<a href="apagar.form?idReservarMaterialBibliografico=<c:out value="${registro.idReservarMaterialBibliografico}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a>
			<c:if test="${registro.statusReservaBoolean }">
				<a href="converter.form?idReservarMaterialBibliografico=<c:out value="${registro.idReservarMaterialBibliografico}"/>"><img alt="Emprestar" src="<c:url value="/images/emprestar.jpg"/>" title="Apagar"></a>
			</c:if>
			</td>
		</tr>
</c:forEach>
	</tbody>
</table>
<c:if test="${param.error != null}">
<br>
<span id="errors" class="error"><fmt:message key="${param.error}"/></span>
<br>
</c:if>
<br><br>
<a href="editar.form?idReservarMaterialBibliografico=0">
	<img alt="<fmt:message key="listarReservarMaterialBibliografico.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarReservarMaterialBibliografico.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>