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

	<label class="menuItem" onclick="redirect('../reservarmaterialbibliografico/reservar.form')"><fmt:message key="menu.reservarMaterialBibliografico" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../reservarmaterialbibliografico/listarEfetivarReserva.form')"><fmt:message key="menu.efetivarReservaMaterialBibliografico" /></label>
	<p>
	<label class="menuItem" onclick="redirect('../emprestarmaterialbibliografico/listarPendente.form')"><fmt:message key="menu.emprestarMaterialBibliografico.listarPendente" /></label>
	<p>
	<label class="menuItem" onclick="redirect('administrativo.form')"><fmt:message key="menu.administrativo" /></label>
	<p>
<a href="<c:url value="logoff.form"/>">
	<img alt="<fmt:message key="logoff"/>" src="<c:url value="/images/logoff.jpg"/>" title="<fmt:message key="logoff"/>">
</a>

</body>
</html>