<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarReservarMaterialBibliografico.heading"/></h1>
<form:form method="post" commandName="reservarMaterialBibliograficoForm">
  <form:errors cssClass="error"/>
  <form:hidden path="idUsuario"/>'
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarReservarMaterialBibliografico.materialBibliografico"/></td>
    <c:if test="${reservarMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico == null}">
      <td width="20%"><form:select path="materialBibliografico.idMaterialBibliografico" items="${listaMaterialBibliograficoDisponivel}" itemLabel="titulo" itemValue="idMaterialBibliografico" /></td>
    </c:if>
    <c:if test="${reservarMaterialBibliograficoForm.materialBibliografico.idMaterialBibliografico != null}">
      <td width="20%"><form:input path="materialBibliografico.titulo" readonly="true"/></td>
      <form:hidden path="materialBibliografico.idMaterialBibliografico"/>
    </c:if>
      <td width="60%"><form:errors path="materialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarReservarMaterialBibliografico.dataInicioReserva"/></td>
      <td width="20%"><form:input path="dataInicioReserva" readonly="true"/></td>
      <td width="60%"><form:errors path="dataInicioReserva" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarReservarMaterialBibliografico.periodoReserva"/></td>
      <td width="20%"><form:input path="periodoReserva"/></td>
      <td width="60%"><form:errors path="periodoReserva" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarReservarMaterialBibliografico.statusReserva"/></td>
      <td width="20%"><form:checkbox path="statusReserva"/></td>
      <td width="60%"><form:errors path="statusReserva" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarReservarMaterialBibliografico.observacao"/></td>
      <td width="20%"><form:textarea path="observacao"/></td>
      <td width="60%"><form:errors path="observacao" cssClass="error"/></td>
    </tr>
  </table>
  <br>
  <input type="image" src="<c:url value="/images/salvar.gif"/>">
<a href="<c:url value="listar.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.lista"/>">
</a>
  <a href="<c:url value="/main/main.form"/>">
    <img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
  </a>
</form:form>
</body>
</html>