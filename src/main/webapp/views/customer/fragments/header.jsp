<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="global">
<script src="/views/customer/fragments/header.js"></script>
<span>
	<a class="logo" href="/">
		<h2>Java</h2>
		<h1>Codings</h1>
	</a>
	<ul id="sub-menu">
		<c:choose>
			<c:when test="${ssKey == null}">
				<li><a href="/account/login">로그인</a></li>
				<li><a href="/account/register">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a id="logout">로그아웃</a></li>
				<li><a href="/account">마이페이지</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="#">장바구니</a></li>
		<li><a href="#">고객센터</a></li>
	</ul>
</span>
<div id="global-menu">
	<ul>
		<li><a href="/product/c/memory">기념상품</a></li>
		<li><a href="/product/c/plan">기획전</a></li>
		<li><a href="/product/c/uniform">유니폼</a></li>
		<li><a href="/product/c/hats">모자</a></li>
		<li><a href="/product/c/clothes">의류</a></li>
		<li><a href="/product/c/cheer">응원용품</a></li>
		<li><a href="/product/c/baseball">야구용품</a></li>
	</ul>
</div>
</header>