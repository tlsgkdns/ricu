
목차<br>
* [1. 개요](#개요)
  * [1-1. 배경](#배경)
  * [1-2. 개발 기간](#개발-기간)
  * [1-3. 개발 환경](#개발-환경)
  * [1-4. 기술 스택](#기술-스택)
* [2. 베이스 프로젝트 VS RICU](베이스-프로젝트-화면-vs-ricu-화면)
  * [2-1. 변경 없음](#변경-없음)
      * [2-1-1. 게시글 등록](#게시글-등록)
      * [2-1-2. 로그인](#로그인)
  * [2-2. 변경 된 것](#변경-된-것)
      * [2-2-1. 회원가입](#회원가입)
          * [2-2-1-1. 베이스 프로젝트](#베이스-프로젝트)
          * [2-2-1-2. RICU](#ricu)
      * [2-2-2. 게시글 리스트](#게시글-리스트)
         * [2-2-2-1. 베이스 프로젝트](#베이스-프로젝트)
         * [2-2-2-2. RICU](#ricu)

# 개요
## 소개
공통된 관심사를 이야기 할 수 있는 갤러리를 생성하여, 갤러리 마다의 게시글을 작성할 수 있는 커뮤니티 게시판
## 배경
스프링부트를 책을 통해 배우고, 활용하고 싶어서, 책에 수록된 코드를 바탕으로 나만의 커뮤니티 게시판을 작성하였다.
## 개발 기간
2023.08 ~ 2023.09
## 개발 환경
* OS: Window 10
* IDE: Intellij IDEA 2023.1.3
* 실행 브라우저: Chrome
## 기술 스택
* 언어: JAVA(JDK 20), JAVASCRIPT
* Front-end: Thymeleaf, Axios
* Back-end: SpringBoot(3.1.2), Spring Security(6.1.2), QueryDSL, JPA, Lombok
* Test: JUnit5
* Database: MySQL(8.0.33)
# 베이스 프로젝트 화면 vs ricu 화면
## 변경 없음
### 게시글 등록
<br><img width=1000 height=400 src="https://github.com/tlsgkdns/ricu/assets/24753709/83d51982-593c-4679-b069-d8b38a1fc07e">
### 로그인
<br><img width=200 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/d0f67679-df4d-41d7-a51f-fc24b11fc4ef">
>  게시글 등록 화면과 로그인 화면은 특별히 바뀐 점이 없다
## 변경 된 것
### 회원가입
#### 베이스 프로젝트
<br><img width=1000 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/5c32b4ea-5b93-45d1-a0a7-2410b6aa498d">
#### RICU
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/eaf209b0-ea37-4f68-8788-8ee26a9b27d3">
<br> 
>  멤버 프로필사진을 추가하였고, 비밀번호 확인란을 추가로 두어 비밀번호를 다시 타이핑하게 하였다.
### 게시글 리스트
#### 베이스 프로젝트
<br><img width=1000 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/ea541b6d-250c-48ca-ac20-b7e5fc0f547b">
#### RICU
<br><img width=1000 height=500 src="https://github.com/tlsgkdns/ricu/assets/24753709/e051ccf4-c49b-4532-9b9f-c8f29dc70fb0">
>  윗 부분에 현재 갤러리 정보를 볼 수 있도록 추가하였다. 갤러리 매니저의 경우 정보를 수정 할 수 있도록, Edit 버튼이 보이게 했다.
>  게시글마다의 조회수와 추천을 추가 했고, 인기 있는 게시글만 볼 수 있도록 Popular 모드를 도입했다.
### 게시글 화면
#### 베이스 프로젝트
<br><img width=1000 height=600 src="https://github.com/tlsgkdns/ricu/assets/24753709/db873d03-18ad-450f-8055-ca17ffefb4c2">
#### RICU
<br><img width=1000 height=800 src="https://github.com/tlsgkdns/ricu/assets/24753709/44948148-d333-4889-b1ce-18d22da619f9">
>  게시글 밑에 해당 갤러리의 게시글 리스트를 삽입하였고, 댓글 작성자의 프로필 사진을 볼 수 있게 했다. 클릭하면 해당 댓글 작성자의 정보를 볼 수 있다.
>  댓글은 작성자만이 삭제 할 수 있다. 또한 게시글의 추천을 할 수 있도록 추천 버튼 또한 도입했다. 추천한 게시글을 다시 추천하면 추천이 취소된다.
>  해당 갤러리 매니저는 어떠한 게시글이든 삭제할 수 있는 권한을 가진다.
## 추가된 화면
### 갤러리 생성
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/f1526fd8-1fb1-4bcd-b5a8-af9737d17b52">
>  공통된 관심사를 이야기 할 수 있는 갤러리를 생성한다. 생성자는 매니저가 된다.
### 갤러리 리스트
<br><img width=1000 height=1300 src="https://github.com/tlsgkdns/ricu/assets/24753709/b6d6e43d-71ef-428b-aa95-49f12cf241ac">
>  갤러리 리스트들을 대문과 함께 보여준다, 게시글과 마찬가지로 페이징, 검색을 지원한다.
### 갤러리 수정
<br><img width=1000 height=450 src="https://github.com/tlsgkdns/ricu/assets/24753709/02a57a3d-b52a-48fa-900d-51177d5d752a">
>  매니저만이 접근할 수 있다.  해당 갤러리의 설명, 대문 그리고 인기글 기준을 설정할 수 있다.
### 유저정보 화면
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/00657292-efff-4160-bc14-aaf0ca2b7368">
>  유저 정보를 보여준다.  유저 편집에선 닉네임과 프로필 사진을 바꿀 수 있다. 갤러리를 검색해서 해당 유저가 그 갤러리에서 어떤 글을 썼는지 검색할 수 있다.
# 이 프로젝트를 진행하며 배운 것
* 첫 웹 프로젝트로써 스프링부터를 처음 만져보며 경험을 쌓을 수 있었다.
* HTTP 비동기에 대해서 이해하고, 활용을 할 수 있게 되었다.
* 자바스크립트를 처음 접해보고, 코드를 작성할 수 있었다.
* 책에서 처음 봤을 때 와닿지 않았던 QueryDSL의 코드를 이해할 수 있었다.
# 계획했지만 여러 이유로 보류되거나 취소된 것들
* 매니저 위임, 추가
* 게시글 이미지 업로드 및 글 사이사이에 이미지 업로드 기능.
# 앞으로 할 것들
* JavaScript를 책을 통해 배운다.
* 웹의 보안에 대한 정보를 찾고, 관련 책을 읽는다.
* SQL/Database에 대한 지식을 복기한다.
* 웹에서 사용할 수 있는 다른 기술들을 검색하고, 공부한다.
* 새로운 프로젝트를 구상한다.
