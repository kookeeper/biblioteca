<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

  <title><fmt:message key="title"/></title>
</head>
<body>
<h1><fmt:message key="editarMaterialBibliografico.heading"/></h1>


<form:form method="post" commandName="editarMaterialBibliograficoForm">
<br><form:errors cssClass="error"/><br>
  <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.tipoMaterialBibliografico"/></td>
      <td width="20%"><form:select path="idTipoMaterialBibliografico" items="${listaTipoMaterialBibliografico}" itemLabel="descricaoTipoMaterialBibliografico" itemValue="idTipoMaterialBibliografico"/></td>
      <td width="60%"><form:errors path="idTipoMaterialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.localMaterialBibliografico"/></td>
      <td width="20%"><form:select path="idLocalMaterialBibliografico" items="${listaLocalMaterialBibliografico}" itemLabel="descricaoLocalMaterialBibliografico" itemValue="idLocalMaterialBibliografico"/></td>
      <td width="60%"><form:errors path="idLocalMaterialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.codigoMaterialBibliografico"/></td>
      <td width="20%"><form:input path="codigoMaterialBibliografico"/></td>
      <td width="60%"><form:errors path="codigoMaterialBibliografico" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.autor"/></td>
      <td width="20%"><form:input path="autor"/></td>
      <td width="60%"><form:errors path="autor" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.titulo"/></td>
      <td width="20%"><form:input path="titulo"/></td>
      <td width="60%"><form:errors path="titulo" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.edicao"/></td>
      <td width="20%"><form:input path="edicao"/></td>
      <td width="60%"><form:errors path="edicao" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.dataAquisicao"/></td>
      <td width="20%"><form:input path="dataAquisicao"/></td>
      <td width="60%"><form:errors path="dataAquisicao" cssClass="error"/></td>
    </tr>
    <tr>
      <td align="right" width="20%"><fmt:message key="editarMaterialBibliografico.editora"/></td>
      <td width="20%"><form:input path="editora"/></td>
      <td width="60%"><form:errors path="editora" cssClass="error"/></td>
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