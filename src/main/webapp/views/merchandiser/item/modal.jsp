<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<cds-layer level="0">
<cds-tile id="editor">
	<header>
		<h1>상품 편집기</h1>
	</header>
	<cds-stack id="form"
	           gap="16px" orientation="vertical" use-custom-gap-value>
		<cds-text-input label="상품 이름" invalid-text="Error message" name="label"
						placeholder="상품 이름을 입력해주세요"
						class="field" data="label"></cds-text-input>
		<cds-text-input label="상품 설명" invalid-text="Error message" name="desc"
						placeholder="상품 설명을 입력해주세요"
						class="field" data="desc"></cds-text-input>
		<cds-stack gap="8px" orientation="horizontal" use-custom-gap-value>
			<cds-number-input step="1000" min="0"
							  label="가격" name="price"
							  class="field" data="price"></cds-number-input>
			<cds-number-input min="0"
							  label="재고 수량" name="stock"
							  class="field" data="stock"></cds-number-input>
		</cds-stack>

		<cds-select label-text="분류" placeholder="상품 분류를 선택해주세요"
					class="field" data="category">
		<c:forEach items="${categoryList}" var="category">
			<cds-select-item value="${category}">${category}</cds-select-item>
		</c:forEach>
		</cds-select>
		<cds-multi-select title-text="태그" direction="top" label="태그 선택" filterable
						  class="field" data="tags">
			<c:forEach items="${tagList}" var="tag">
				<cds-multi-select-item value="${tag}">${tag}</cds-multi-select-item>
			</c:forEach>
		</cds-multi-select>

		<%-- Image Grid ------------------------------------------------------%>
		<h1>상품 이미지 관리</h1>
		<p>썸네일로 설정하려면 사진을 클릭하세요
			<br>제품 사진은 최대 8개 업로드만 가능합니다. PNG 형식만 업로드 가능합니다</p>
		<div id="image-grid"></div>
		<%-- End Image Grid --------------------------------------------------%>

		<div id="button-group">
			<cds-button kind="primary" id="editor-submit">완료하기</cds-button>
			<cds-button kind="secondary" id="desc-image-upload">상품 설명 업로드</cds-button>
		</div>

		<div id="desc">
			<h1>상품 설명 이미지</h1>
			<img id="desc-image">
		</div>
	</cds-stack>
</cds-tile>
</cds-layer>
