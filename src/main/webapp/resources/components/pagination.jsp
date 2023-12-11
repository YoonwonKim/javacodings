<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="pagination"
     totalItems="${totalItems}" totalPages="${totalPages}"
     page="${currentPage}" page-size="15">
	<cds-stack class="page-size" orientation="horizontal">
		<p>페이지 당 상품 수 :</p>
		<cds-select id="row" name="page-sizes-select" inline value="${row}">
			<cds-select-item value="15">15</cds-select-item>
			<cds-select-item value="20">20</cds-select-item>
			<cds-select-item value="30">30</cds-select-item>
			<cds-select-item value="40">40</cds-select-item>
			<cds-select-item value="50">50</cds-select-item>
		</cds-select>
	</cds-stack>
	<cds-stack class="item-size">
		<p>${start+1} - ${(end <=totalItems)?end:totalItems} 상품</p>
	</cds-stack>
	<cds-stack class="page" orientation="horizontal">
		<p>페이지</p>
		<cds-select id="page" inline value="${currentPage}">
			<c:forEach var="page" begin="1" end="${totalPages}">
				<cds-select-item value="${page}">${page}</cds-select-item>
			</c:forEach>
		</cds-select>
	</cds-stack>
	<cds-stack class="page-button" orientation="horizontal">
		<cds-button id="prev" onclick="prev_page()" kind="ghost" size="md" ${(currentPage == 1)?"disabled":""}>
			<slot name="icon"><%@ include file="/resources/icons/caret--left.svg"%></slot></cds-button>
		<cds-button id="next" onclick="next_page()" kind="ghost" size="md" ${(currentPage == totalPages)?"disabled":""}>
			<slot name="icon"><%@ include file="/resources/icons/caret--right.svg"%></slot></cds-button>
	</cds-stack>
</div>
