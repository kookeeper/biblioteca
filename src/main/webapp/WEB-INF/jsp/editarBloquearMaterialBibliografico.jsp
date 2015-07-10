<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarBloquearMaterialBibliografico.heading"/></h1>
<form:form method="post" commandName="bloquearMaterialBibliograficoForm">
  <form:errors cssClass="error"/>
  <form:hidden path="idUsuario"/>'
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarBloquearMaterialBibliografico.materialBibliografico"/></td>
    <c:if test="${bloquearMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico == null}">
      <td width="20%"><form:select path="materialBibliografico.idMaterialBibliografico" items="${listaMaterialBibliografico}" itemLabel="titulo" itemValue="idMaterialBibliografico" /></td>
    </c:if>
    <c:if test="${bloquearMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico != null}">
      <td width="20%"><form:input path="materialBibliografico.titulo" readonly="true"/></td>
      <form:hidden path="materialBibliografico.idMaterialBibliografico"/>
    </c:if>
      <td width="60%"><form:errors path="materialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarBloquearMaterialBibliografico.dataBloqueio"/></td>
      <td width="20%"><form:input path="dataBloqueio" readonly="true"/></td>
      <td width="60%"><form:errors path="dataBloqueio" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarBloquearMaterialBibliografico.motivoBloqueio"/></td>
      <td width="20%"><form:input path="motivoBloqueio"/></td>
      <td width="60%"><form:errors path="motivoBloqueio" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarBloquearMaterialBibliografico.statusBloqueio"/></td>
      <td width="20%"><form:checkbox path="statusBloqueio"/></td>
      <td width="60%"><form:errors path="statusBloqueio" cssClass="error"/></td>
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