<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarSolicitarReserva.heading"/></h1>
<form:form method="post" commandName="editarSolicitarReservaForm">
  <form:errors cssClass="error"/>
  <form:hidden path="idUsuario"/>
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarSolicitarReserva.materialBibliografico"/></td>
      <td width="20%"><form:select path="idMaterialBibliografico" items="${listaMaterialBibliografico}" itemLabel="titulo" itemValue="idMaterialBibliografico"/></td>
      <td width="60%"><form:errors path="idMaterialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarSolicitarReserva.dataSolicitacao"/></td>
      <td width="20%"><form:input path="dataSolicitacao" readonly="true"/></td>
      <td width="60%"><form:errors path="dataSolicitacao" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarSolicitarReserva.observacao"/></td>
      <td width="20%"><form:input path="observacao"/></td>
      <td width="60%"><form:errors path="observacao" cssClass="error"/></td>
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