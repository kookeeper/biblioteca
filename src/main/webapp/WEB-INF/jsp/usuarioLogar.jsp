<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="Big5"%> 

	<title><fmt:message key="title.usuarioLogar"/></title>
</head>
<body onload="document.getElementById('login').focus()" style="background-image: url('../images/entrada.jpg'); background-repeat: no-repeat; background-position: center center;">
<div class="login centered">
	<form:form method="post" commandName="usuarioLogarForm">
		<div>
			<fmt:message key="usuarioLogar.login"/>
			<form:input path="login"/>
        </div>
        <div>
			<fmt:message key="usuarioLogar.senha"/>
			<form:password path="senha"/>
        </div>
		<div>
	        <form:errors path="login" cssClass="error"/>
        </div>
        <div>
			<input type="submit" value="<fmt:message key="botao.logar"/>"/>
		</div>
	</form:form>
</div>

Language : <a href="?language=pt">Portugues</a>|<a href="?language=zh_CN">Chinese</a>

</body>
</html>