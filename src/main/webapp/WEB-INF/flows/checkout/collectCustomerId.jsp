<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	
	<title>Customer ID</title>
</head>
<body>
	
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Customer ID</h1>
				<p></p>
			</div>
		</div>
	</section>

	<section class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<fieldset>
			
				<legend>Customer ID</legend>
				
				<div class="form-group">
					<label class="control-label col-lg-2" for="customerId">
						Customer ID
					</label>	
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" type="text" class="form:input-large" />
					</div> 
				</div>
				
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						
						<input type="submit" id="btnAdd" class="btn btn-primary" 
							value="<spring:message code="btn.add.label" />"
							name="_eventId_customerIdCollected" />
						
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