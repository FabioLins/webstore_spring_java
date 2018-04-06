<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<section class="container" ng-app="cartApp">
		
		<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
			<div>
				<a class="btn btn-danger pull-left" ng-click="clearCart()">
					<span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="cart.btn.clear" />
				</a>
				<a href="<spring:url value='/checkout?cartId=${cartId}' />" class="btn btn-success pull-right">
					<span class="glyphicon-shopping-cart glyphicon"></span> <spring:message code="cart.btn.checkout" />
				</a>
			</div>
			<table class="table table-hover">
				<tr>
					<th><spring:message code="cart.product.label" /></th>
					<th><spring:message code="cart.unitPrice.label" /></th>
					<th><spring:message code="cart.quantity.label" /></th>
					<th><spring:message code="cart.price.label" /></th>
					<th><spring:message code="cart.action.label" /></th>
				</tr>
				<tr ng-repeat="item in cart.cartItems">
					<td>{{item.product.productId}} - {{item.product.name}}</td>
					<td>{{item.product.unitPrice}}</td>
					<td>{{item.quantity}}</td>
					<td>{{item.totalPrice}}</td>
					<td>
						<a href="#" class="label label-danger" 
							ng-click="removeFromCart(item.product.productId)">
							<span class="glyphicon glyphicon-remove"></span> <spring:message code="cart.remove.label" />
						</a>
					</td>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th><spring:message code="cart.grandtotal.label" /></th>
					<th>{{cart.grandTotal}}</th>
					<th></th>					
				</tr>
			</table>
			<a href="<spring:url value='/market/products' />" class="btn btn-default">
				<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="cart.continue.shopping.label" />
			</a>
		</div>
	</section>
