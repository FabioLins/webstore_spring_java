<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

	<section class="container">
		<form:form method="POST" modelAttribute="newCustomer" class="form-horizontal">
			<fieldset>
				<legend><spring:message code="addCustomer.form.title.label" /></legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="customerId">
						<spring:message code="addCustomer.form.customerId.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" type="text" class="form:input-large" />
					</div> 
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="addCustomer.form.name.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large" />
					</div> 
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="address">
						<spring:message code="addCustomer.form.address.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="address" path="address" type="text" class="form:input-large" />
					</div> 
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="noOfOrdersMade">
						<spring:message code="addCustomer.form.noOfOrdersMade.label" />
					</label>	
					<div class="col-lg-10">
						<form:input id="noOfOrdersMade" path="noOfOrdersMade" type="text" class="form:input-large" />
					</div> 
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="btn.add.label" />" />
					</div>
				</div>
			</fieldset>	
		</form:form>
	</section>
