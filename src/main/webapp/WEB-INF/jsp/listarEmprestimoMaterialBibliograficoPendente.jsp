<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarEmprestarMaterialBibliograficoPendente.heading"/></h1>

<c:if test="${fn:length(listaEmprestimoPendente) > 0}">
<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.dataEmprestimo"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.materialBibliografico"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.usuario"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.dataDevolucaoPrevista"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaEmprestimoPendente}" var="registro">
		<tr>
			<td><c:out value="${registro.dataEmprestimo}"/></td>
			<td><c:out value="${registro.materialBibliografico.codigoMaterialBibliografico}"/> - <c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td><c:out value="${registro.dataDevolucaoPrevista}"/></td>
			<td>
				<a href="devolver.form?idEmprestarMaterialBibliografico=<c:out value="${registro.idEmprestarMaterialBibliografico}"/>"><img alt="Devolver" src="<c:url value="/images/editar.gif"/>" title="Devolver"></a>
			</td>
		</tr>
</c:forEach>
	</tbody>
</table>
</c:if>

<c:if test="${fn:length(listaEmprestimoPendente) <= 0}">
	<fmt:message key="listarEmprestarMaterialBibliograficoPendente.semRegistro"/>
</c:if>

<br><br>

<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>