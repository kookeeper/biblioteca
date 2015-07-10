<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="listarEmprestarMaterialBibliografico.heading"/></h1>

<table border="1" >
	<thead>
		<tr>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.materialBibliografico"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.dataEmprestimo"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.dataDevolucaoPrevista"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.usuario"/></th>
			<th><fmt:message key="listarEmprestarMaterialBibliografico.tituloTabela.acao"/></th>
		</tr>
	</thead>
	<tbody>
<c:forEach items="${listaEmprestarMaterialBibliografico}" var="registro">
		<tr>
			<td><c:out value="${registro.materialBibliografico.titulo}"/></td>
			<td><c:out value="${registro.dataEmprestimo}"/></td>
			<td><c:out value="${registro.dataDevolucaoPrevista}"/></td>
			<td><c:out value="${registro.usuario.nomeUsuario}"/></td>
			<td>
				<a href="editar.form?idEmprestarMaterialBibliografico=<c:out value="${registro.idEmprestarMaterialBibliografico}"/>"><img alt="Editar" src="<c:url value="/images/editar.gif"/>" title="Editar"></a>
				<a href="apagar.form?idEmprestarMaterialBibliografico=<c:out value="${registro.idEmprestarMaterialBibliografico}"/>"><img alt="Apagar" src="<c:url value="/images/excluir.gif"/>" title="Apagar"></a>
			</td>
		</tr>
</c:forEach>
	</tbody>
</table>

<br><br>
<a href="editar.form?idEmprestarMaterialBibliografico=0">
	<img alt="<fmt:message key="listarEmprestarMaterialBibliografico.novo"/>" src="<c:url value="/images/incluir.png"/>" title="<fmt:message key="listarEmprestarMaterialBibliografico.novo"/>">
</a>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
</body>
</html>