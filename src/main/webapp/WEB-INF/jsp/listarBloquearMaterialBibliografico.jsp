<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarBloquearMaterialBibliografico.heading"/></h1>

<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="listarBloquearMaterialBibliografico.tituloTabela.dataBloqueio"/></th>
			<th><fmt:message key="listarBloquearMaterialBibliografico.tituloTabela.materialBibliografico"/></th>
			<th><fmt:message key="listarBloquearMaterialBibliografico.tituloTabela.usuario"/></th>
			<th><fmt:message key="listarBloquearMaterialBibliografico.tituloTabela.motivoBloqueio"/></th>
			<th><fmt:message key="listarBloquearMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaBloquearMaterialBibliografico}" var="registro">
		<tr>
			<td><c:out value="${registro.dataBloqueio}"/></td>
			<td><c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td><c:out value="${registro.motivoBloqueio}"/></td>
			<td><a href="editar.form?idBloquearMaterialBibliografico=<c:out value="${registro.idBloquearMaterialBibliografico}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
			<a href="apagar.form?idBloquearMaterialBibliografico=<c:out value="${registro.idBloquearMaterialBibliografico}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a></td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idBloquearMaterialBibliografico=0">
	<img alt="<fmt:message key="listarBloquearMaterialBibliografico.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarBloquearMaterialBibliografico.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>