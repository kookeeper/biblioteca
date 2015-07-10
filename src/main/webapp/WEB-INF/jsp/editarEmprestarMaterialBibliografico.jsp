<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarEmprestarMaterialBibliografico.heading"/></h1>
<form:form method="post" commandName="emprestarMaterialBibliograficoForm">
  <form:errors cssClass="error"/>
  <form:hidden path="idUsuario"/>'
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarEmprestarMaterialBibliografico.materialBibliografico"/></td>
    <c:if test="${emprestarMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico == null}">
      <td width="20%"><form:select path="materialBibliografico.idMaterialBibliografico" items="${listaMaterialBibliografico}" itemLabel="titulo" itemValue="idMaterialBibliografico" /></td>
    </c:if>
    <c:if test="${emprestarMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico != null}">
      <td width="20%"><form:input path="materialBibliografico.titulo" readonly="true"/></td>
      <form:hidden path="materialBibliografico.idMaterialBibliografico"/>
    </c:if>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarEmprestarMaterialBibliografico.dataEmprestimo"/></td>
      <td width="20%"><form:input path="dataEmprestimo"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarEmprestarMaterialBibliografico.dataDevolucaoPrevista"/></td>
      <td width="20%"><form:input path="dataDevolucaoPrevista" readonly="true"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarEmprestarMaterialBibliografico.dataDevolucaoEfetiva"/></td>
      <td width="20%"><form:input path="dataDevolucaoEfetiva"/></td>
      <td width="60%"><form:errors path="dataDevolucaoEfetiva" cssClass="error"/></td>
    </tr>
  </table>
  <br>
  <input type="image" src="<c:url value="/images/salvar.gif"/>">
<a href="<c:url value="listar.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.lista"/>">
</a>
</form:form>
</body>
</html>