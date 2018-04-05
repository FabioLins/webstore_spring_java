<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	
	<title><spring:message code="thankCustomer.title.label" /></title>
</head>
<body>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1 class="alert alert-danger"><spring:message code="thankCustomer.title.label" /></h1>
				<p><spring:message code="thankCustomer.success.purchase.label" /> 
					<fmt:formatDate type="date" value="${order.shippingDetail.shippingDate}" />!
				</p>
				<p>
					<spring:message code="thankCustomer.order.number.label" /> ${order.orderId}
				</p>
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