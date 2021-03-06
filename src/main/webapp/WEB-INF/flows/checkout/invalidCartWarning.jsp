<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	
	<title><spring:message code="orderConfirmation.subtitle.label" /></title>
</head>
<body>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1 class="alert alert-danger"> <spring:message code="invalidCart.emptyCart.label" /></h1>
			</div>
		</div>
	</section>

	<section>
		<div class="container">
			<p>
				<a href="<spring:url value='/market/products/' />" class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="invalidCart.products.label" />
				</a>  
			</p>
		</div>
	</section>
	
</body>
</html>