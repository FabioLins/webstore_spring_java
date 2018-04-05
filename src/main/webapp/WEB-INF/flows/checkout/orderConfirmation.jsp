<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<h1><spring:message code="orderConfirmation.title.label" /></h1>
				<p><spring:message code="orderConfirmation.subtitle.label" /></p>
			</div>
		</div>
	</section>

	<div class="container">
		<div class="row">
	
			<form:form modelAttribute="order" class="form-horizontal">
			
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				
				<div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<spring:message code="orderConfirmation.form.receipt.label" />
					</div>	
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>
									<spring:message code="orderConfirmation.form.shippingAddress.label" />
								</strong>
								<br> ${order.shippingDetail.name}<br>
								${order.shippingDetail.shippingAddress.doorNo},
								${order.shippingDetail.shippingAddress.streetName},
								<br>
								${order.shippingDetail.shippingAddress.areaName},
								${order.shippingDetail.shippingAddress.state},
								<br>
								${order.shippingDetail.shippingAddress.country},
								${order.shippingDetail.shippingAddress.zipCode},
								<br>
							</address>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 text-right">
							<p>
								<em>
									<spring:message code="orderConfirmation.form.shippingDate.label" />
									<fmt:formatDate type="date" value="${order.shippingDetail.shippingDate}" />
								</em>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong><spring:message code="orderConfirmation.form.billingAddress.label" /></strong>
								<br>
								${order.customer.name}
								<br>
								${order.customer.billingAddress.doorNo}
								${order.customer.billingAddress.streetName}
								<br>
								${order.customer.billingAddress.areaName}
								${order.customer.billingAddress.state}
								<br>
								${order.customer.billingAddress.country}
								${order.customer.billingAddress.zipCode}
								<br><abbr>P:</abbr>
								${order.customer.phoneNumber}
							</address>
						</div>
					</div>
					<div class="row">
						<table class="table table-hover">
							<thead>
								<tr>
									<th><spring:message code="orderConfirmation.form.product.label" /></th>
									<th>#</th>
									<th class="text-center"><spring:message code="orderConfirmation.form.price.label" /></th>
									<th class="text-center"><spring:message code="orderConfirmation.form.total.label" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartItem" items="${order.cart.cartItems}">
									<tr>
										<td class="col-md-9"><em>${cartItem.product.name}</em></td>
										<td class="col-md-1" style="text-align: center">${cartItem.quantity}</td>
										<td class="col-md-1 text-center">$${cartItem.product.unitPrice}</td>
										<td class="col-md-1 text-center">$${cartItem.totalPrice}</td>
									</tr>
								</c:forEach>
								
								<tr>
									<td></td>
									<td></td>
									<td class="text-right">
										<h4><strong><spring:message code="orderConfirmation.form.grandTotal.label" /></strong></h4>
									</td>
									<td class="text-center text-danger">
										<h4><strong>$${order.cart.grandTotal}</strong></h4>
									</td>
								</tr>
							</tbody>
						</table>
						
						<button id="back" class="btn btn-default" 
							name="_eventId_backToCollectShippingDetail">
							<spring:message code="btn.back.label" />
						</button>
						
						<button type="submit" class="btn btn-success" 
							name="_eventId_orderConfirmed">
							<spring:message code="btn.confirm.label" /> 
							<span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						
						<button id="btnCancel" class="btn btn-default" 
							name="_eventId_cancel">
							<spring:message code="btn.cancel.label" /> 
						</button>
					</div>
				</div> 
			</form:form>
		</div>
	</div>
</body>
</html>