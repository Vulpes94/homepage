<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${root}/css/manager/header.css">
<title>Document</title>
</head>
<body>
	<div id="wrap">
		<div class="adminLogo">
			<a href="${root}/">NOVELREADER</a>
			<a href="${root}/manager/menu.do">ADMINMODE</a>
		</div>
		<div class="adminmenu">
			<details id="details">
				<summary class="summary">회원정보</summary>
				<ul class="summary_ul" style="margin-top: 10px;">
					<a href="${root}/manager/memberView.do">회원조회</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/memberModify.do">회원수정</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/memberDelete.do">회원삭제</a>
				</ul>

			</details>
			<details id="details">
				<summary class="summary">신고관리</summary>
				<ul class="summary_ul" style="margin-top: 10px;">
					<a href="${root}/manager/reportPost.do">신고된 게시글</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/reportComment.do">신고된 댓글</a>
				</ul>

			</details>
			<details id="details">
				<summary class="summary">게시물관리</summary>
				<ul class="summary_ul" style="margin-top: 10px;">
					<a href="${root}/manager/memberPost.do">회원 게시물</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/notice.do">공지사항</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/faq.do">자주 묻는 질문</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/question.do">문의정보</a>
				</ul>


			</details>
			<details id="details">
				<summary class="summary">소설카테고리관리</summary>
				<ul class="summary_ul" style="margin-top: 10px;">
					<a href="${root}/manager/categoryView.do">카테고리 조회</a>
				</ul>
				<ul class="summary_ul">
					<a href="${root}/manager/categoryDelete.do">카테고리 삭제</a>
				</ul>

			</details>
		</div>
	</div>
</body>
</html>