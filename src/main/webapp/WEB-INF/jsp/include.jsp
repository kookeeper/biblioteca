<%@ page session="false" language="java" pageEncoding="Big5"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="pragma" content="no-cache" /> 
<link href="<c:url value="/css/padrao.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/js/jquery-1.7.2.min.js"/>" type="text/javascript" />
<link href="<c:url value="/js/jquery-ui-1.8.20.custom.min.js"/>" type="text/javascript" />
<script type="text/javascript">
	function CenterItem(theItem){
		var winWidth=$(window).width();
		var winHeight=$(window).height();
		var windowCenter=winWidth/2;
		var itemCenter=$(theItem).width()/2;
		var theCenter=windowCenter-itemCenter;
		var windowMiddle=winHeight/2;
		var itemMiddle=$(theItem).height()/2;
		var theMiddle=windowMiddle-itemMiddle;
		if(winWidth>$(theItem).width()){ //horizontal
			$(theItem).css('left',theCenter);
		} else {
			$(theItem).css('left','0');
		}
		if(winHeight>$(theItem).height()){ //vertical
			$(theItem).css('top',theMiddle);
		} else {
			$(theItem).css('top','0');
		}
	}

	$(document).ready(function() {
		CenterItem('.centered');
	});

	$(window).resize(function() {
		CenterItem('.centered');
	});
</script>
