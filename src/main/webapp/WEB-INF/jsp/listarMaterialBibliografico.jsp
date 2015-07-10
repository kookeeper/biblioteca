<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarMaterialBibliografico.heading"/></h1>

<table class="lista">
	<thead>
		<tr>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.tipoMaterialBibliografico"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.localMaterialBibliografico"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.codigoMaterialBibliografico"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.autor"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.titulo"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.edicao"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.dataAquisicao"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.editora"/></th>
			<th><fmt:message key="listarMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaMaterialBibliografico}" var="registro">
		<tr>
			<td><c:out value="${registro.tipoMaterialBibliografico.descricaoTipoMaterialBibliografico}"/></td>
			<td><c:out value="${registro.localMaterialBibliografico.descricaoLocalMaterialBibliografico}"/></td>
			<td><c:out value="${registro.codigoMaterialBibliografico}"/></td>
			<td><c:out value="${registro.autor}"/></td>
			<td><c:out value="${registro.titulo}"/></td>
			<td><c:out value="${registro.edicao}"/></td>
			<td><c:out value="${registro.dataAquisicao}"/></td>
			<td><c:out value="${registro.editora}"/></td>
			<td><a href="editar.form?idMaterialBibliografico=<c:out value="${registro.idMaterialBibliografico}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idMaterialBibliografico=<c:out value="${registro.idMaterialBibliografico}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idMaterialBibliografico=0">
	<img alt="<fmt:message key="listarMaterialBibliografico.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarMaterialBibliografico.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>