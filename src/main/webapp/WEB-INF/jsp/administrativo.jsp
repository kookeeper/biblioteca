<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" pageEncoding="Big5"%> 

	<title><fmt:message key="title.main"/></title>
<style type="text/css">
	label.menuItem {
		font-family: cursive;
		font-style: italic; 
		font-size: 40px;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
<!--
	function redirect(path) {
		window.location.href = path;
	}
//-->
</script>
</head>
<body>

	<label class="menuItem" onclick="redirect('../grupousuario/listar.form')"><fmt:message key="menu.grupoUsuario.listar" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../usuario/listar.form')"><fmt:message key="menu.usuario.listar" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../tipomaterialbibliografico/listar.form')"><fmt:message key="menu.tipoMaterialBibliografico.listar" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../localmaterialbibliografico/listar.form')"><fmt:message key="menu.localMaterialBibliografico.listar" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../materialbibliografico/listar.form')"><fmt:message key="menu.materialBibliografico.listar"/></label>
	<p>
	<label class="menuItem" onclick="redirect('../solicitarreserva/listar.form')"><fmt:message key="menu.solicitarReserva.listar"/></label>
	<p>
	<label class="menuItem" onclick="redirect('../bloquearmaterialbibliografico/listar.form')"><fmt:message key="menu.bloquearMaterialBibliografico.listar"/></label>
	<p>
	<label class="menuItem" onclick="redirect('../reservarmaterialbibliografico/listar.form')"><fmt:message key="menu.reservarMaterialBibliografico.listar"/></label>
	<p>
	<label class="menuItem" onclick="redirect('../emprestarmaterialbibliografico/listar.form')"><fmt:message key="menu.emprestarMaterialBibliografico.listar"/></label>
	<p>
<a href="<c:url value="/main/main.form"/>">
	<img alt="<fmt:message key="voltar.main"/>" src="<c:url value="/images/voltar.jpg"/>" title="<fmt:message key="voltar.main"/>">
</a>
	

</body>
</html>