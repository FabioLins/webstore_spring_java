<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	
	<title><spring:message code="customerinfo.title.label" /></title>
</head>
<body>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="customerinfo.title.label" /></h1>
				<p><spring:message code="customerinfo.subtitle.label" /></p>
			</div>
		</div>
	</section>

	<section class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<fieldset>
			
				<legend><spring:message code="customerinfo.subtitle.label" /></legend>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">
						<spring:message code="customerinfo.form.name.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="doorNo">
						<spring:message code="customerinfo.form.doorNo.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="doorNo" path="billingAddress.doorNo" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="streetName">
						<spring:message code="customerinfo.form.streetName.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="streetName" path="billingAddress.streetName" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="areaName">
						<spring:message code="customerinfo.form.areaName.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="areaName" path="billingAddress.areaName" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="state">
						<spring:message code="customerinfo.form.state.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="state" path="billingAddress.state" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="country">
						<spring:message code="customerinfo.form.country.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="country" path="billingAddress.country" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="zipCode">
						<spring:message code="customerinfo.form.zipCode.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="zipCode" path="billingAddress.zipCode" type="text" 
							class="form:input-large" />
					</div> 
				</div>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="phoneNumber">
						<spring:message code="customerinfo.form.phoneNumber.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="phoneNumber" path="phoneNumber" type="text" 
							class="form:input-large" />
					</div>
				</div>
				
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						
						<input type="submit" id="btnAdd" class="btn btn-primary" 
							value="<spring:message code="btn.add.label" />"
							name="_eventId_customerInfoCollected" />
						
						<button id="btnCancel" class="btn btn-default" 
							name="_eventId_cancel">
							<spring:message code="btn.cancel.label" />
						</button>
							
					</div>
				</div>
				
			</fieldset>	
		</form:form>
	</section>
</body>
</html>