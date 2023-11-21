<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/modal.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/number-input.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.rtl.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/select.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/multi-select.min.js"></script>
<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/file-uploader.min.js"></script>


<cds-layer>
<cds-modal id="item-modal">
	<cds-modal-header>
		<cds-modal-close-button></cds-modal-close-button>
		<cds-modal-label value="item_id">Item ID</cds-modal-label>
		<cds-modal-heading>상품 정보 수정</cds-modal-heading>
	</cds-modal-header>

	<cds-modal-body class="cds--layout">
		<div id="modal-div">
			<div>
				<img class="input">
			</div>
			<cds-stack gap="16px" orientation="vertical" use-custom-gap-value>
				<cds-text-input label="상품 이름" invalid-text="Error message"
				                name="label"
				                class="input"></cds-text-input>
				<cds-text-input label="상품 설명" invalid-text="Error message"
				                name="desc"
				                class="input"></cds-text-input>
				<cds-stack gap="8px" orientation="horizontal" use-custom-gap-value>
					<cds-number-input value="0" step="1000" min="0"
					                  label="가격" name="price"
					                  class="input"></cds-number-input>
					<cds-number-input value="0" min="0"
					                  label="재고 수량" name="stock"
					                  class="input"></cds-number-input>
				</cds-stack>
				<cds-stack gap="8px" orientation="horizontal" use-custom-gap-value>
					<cds-select label-text="분류" placeholder="Optional placeholder text"
					            class="input" name="category">
					<c:forEach items="${categoryList}" var="category">
						<cds-select-item value="${category}">${category}</cds-select-item>
					</c:forEach>
					</cds-select>

					<cds-multi-select title-text="태그" direction="top" label="태그 선택" filterable
					                  class="input" name="tags">
						<c:forEach items="${tagList}" var="tag">
							<cds-multi-select-item value="${tag}">${tag}</cds-multi-select-item>
						</c:forEach>
					</cds-multi-select>
				</cds-stack>
				<div id="uploader" role="button">
					<p>이미지 업로드</p>
					<p style="color: #525252; padding-bottom: 16px;">PNG 형식 만 업로드 가능합니다</p>
					<div id="upload-label">
						<label for="file" class="button">업로드</label>
						<p id="uploaded-file"></p>
					</div>
					<input id="file" name="file" type="file"
					       accept="image/png" hidden="true"/>
				</div>
			</cds-stack>
		</div>
	</cds-modal-body>

	<cds-modal-footer>
		<cds-modal-footer-button kind="secondary" data-modal-close>그만두기</cds-modal-footer-button>
		<cds-modal-footer-button kind="primary" id="submit">수정하기</cds-modal-footer-button>
	</cds-modal-footer>
</cds-modal>
</cds-layer>