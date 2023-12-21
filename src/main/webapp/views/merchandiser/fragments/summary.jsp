<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<cds-tile id="summary">
	<h1>${summary.label}</h1>
	<cds-stack gap="16px" orientation="horizontal" use-custom-gap-value class="main">
		<c:forEach var="item" items="${summary.itemList}">
			<div class="value">
				<p class="label">${item.label}</p>
				<h1>${item.value}</h1>
			</div>
		</c:forEach>
	</cds-stack>
</cds-tile>
