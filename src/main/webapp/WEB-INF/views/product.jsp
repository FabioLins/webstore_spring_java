<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular.min.js"></script>
	<script src="/webstore/resources/js/controllers.js"></script>
	
	<title><spring:message code="product.title.label" /></title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=en">English</a> | <a href="?language=nl">Dutch</a>
			<a href="<c:url value='/logout' />"><spring:message code="addProduct.logout.label" /></a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="product.title.label" /></h1>
			</div>
		</div>
	</section>
	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value='/img/${product.productId}.png'></c:url>" 
					alt="image" style="width:100%" />
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong><spring:message code="product.itemcode.label" /> : </strong>
					<span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong><spring:message code="product.manufacturer.label" /></strong> : 
					${product.manufacturer}
				</p>
				<p>
					<strong><spring:message code="product.category.label" /></strong> : 
					${product.category}
				</p>
				<p>
					<strong><spring:message code="product.available.unitsinstock.label" /> </strong> : ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice} <spring:message code="product.currency.symbol.label" /></h4>
				<p ng-controller="cartCtrl">
					<a href="<spring:url value='/market/products' />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="product.btn.back.label" />
					</a>
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
						<span class="glyphicon-shopping-cart glyphicon"></span> <spring:message code="product.btn.order.now.label" />
					</a>
					<a href="<spring:url value='/cart' />" class="btn btn-warning btn-large">
						<span class="glyphicon-hand-right glyphicon"></span> <spring:message code="product.btn.view.cart.label" />
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>