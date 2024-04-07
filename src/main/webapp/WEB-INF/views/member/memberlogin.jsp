<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style type="text/css">

	div{
		display: block;
	}

	.header{
		top: 0;
		left: 50%;
		transform: translate(-50%, 0);
		padding-bottom: 50px;
	}
	
	.header-logo{
		margin-top: 50px;
	}
	
	.grid{
		display: grid;
		grid-template-columns: 120px 1fr;
		grid-template-rows: 35px 35px 1fr;
		row-gap: 20px;
	}


	.footer{
		position: absolute;
		bottom: 0;
		left: 50%;
		transform: translate(-50%, 0);
	}
</style>
</head>
<body>
	<div class="">
		<div class="header">
			<h1 class="header-logo">홈페이지 로고</h1>
		</div>

		<div class="contents">
			<div class="grid">
				<label for="employeeid">사원번호</label>
				<input id="employeeid" type="number" name="employeeid"/>
				
				<label for="password">비밀번호</label>
				<input id="password" type="password" name="password"/>

				<div>
					<button>로그인</button>
				</div>
				<div>menagement는 회사 임직원 관리, 비품 관리 및 예정 프로젝트를 관리하는 프로그램입니다. 로그인후 이용하실 수 있으며 임/직원 신규 등록은 경영지원부(tel:123-1234)로 문의 부탁드립니다.</div>
			</div>
		</div>

		<div class="footer">
			<div>회원 가입 문의: 경영지원부 (전화번호):123-1234</div>
			<div>회사 정보(이름, 사업자 번호, 대표명, 전호번호, 이메일)</div>
			<div>회사 주소</div>
			<div>COPYRIGHT</div>
		</div>
	</div>
</body>
</html>