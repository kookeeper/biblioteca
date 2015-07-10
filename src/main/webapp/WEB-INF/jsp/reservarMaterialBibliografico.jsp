<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="reservarMaterialBibliografico.heading"/></h1>
<form:form method="post" commandName="reservarMaterialBibliograficoForm">

  <table class="cadastro">
    <tr>
      <td align="right" width="20%"><fmt:message key="reservarMaterialBibliografico.materialBibliografico"/></td>
      <td width="20%"><form:select size="10" path="materialBibliografico.idMaterialBibliografico" items="${listaMaterialBibliograficoDisponivel}" itemLabel="codigoMaterialBibliografico" itemValue="idMaterialBibliografico" /></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="reservarMaterialBibliografico.dataInicioReserva"/></td>
      <td width="20%"><form:input path="dataInicioReserva" readonly="true"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="reservarMaterialBibliografico.periodoReserva"/></td>
      <td width="20%"><form:input path="periodoReserva" readonly="true"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="reservarMaterialBibliografico.observacao"/></td>
      <td width="20%"><form:textarea path="observacao"/></td>
    </tr>
  </table>
  <br>
  <input type="image" src="<c:url value="/images/salvar.gif"/>">
  <a href="<c:url value="/main/main.form"/>">
    <img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
  </a>
  <form:errors cssClass="error"/>
</form:form>
</body>
</html>