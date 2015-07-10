<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

<html>
<head>
  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarLocalMaterialBibliografico.heading"/></h1>

<table class="lista">
	<thead>
		<tr>
			<th><fmt:message key="listarLocalMaterialBibliografico.tituloTabela.descricao"/></th>
			<th><fmt:message key="listarLocalMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaLocalMaterialBibliografico}" var="registro">
		<tr>
			<td><c:out value="${registro.descricaoLocalMaterialBibliografico}"/></td>
			<td><a href="editar.form?idLocalMaterialBibliografico=<c:out value="${registro.idLocalMaterialBibliografico}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idLocalMaterialBibliografico=<c:out value="${registro.idLocalMaterialBibliografico}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idLocalMaterialBibliografico=0">
	<img alt="<fmt:message key="listarLocalMaterialBibliografico.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarLocalMaterialBibliografico.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>