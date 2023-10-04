
**목차**
* [1. 개요](#1-개요)
  * [1.1 소개](#11-소개)
  * [1.2 배경](#12-배경)
  * [1.3 개발 기간](#13-개발-기간)
  * [1.4 개발 환경](#14-개발-환경)
  * [1.5 기술 스택](#15-기술-스택)
* [2. 베이스 프로젝트 화면 VS RICU 화면](#2-베이스-프로젝트-화면-vs-ricu-화면)
  * [2.1. 변경 없음](#21-변경-없음)
      * [2.1.1. 게시글 등록](#211-게시글-등록)
      * [2.1.2. 로그인](#212-로그인)
  * [2.2. 변경 된 것](#22-변경-된-것)
      * [2.2.1. 회원가입](#221-회원가입)
          * [2.2.1.1. 베이스 프로젝트](#2211-베이스-프로젝트)
          * [2.2.1.2. RICU](#2212-ricu)
      * [2.2.2. 게시글 리스트](#222-게시글-리스트)
         * [2.2.2.1. 베이스 프로젝트](#2221-베이스-프로젝트)
         * [2.2.2.2. RICU](#2222-ricu)
      * [2.2.3. 게시글 화면](#223-게시글-화면)
         * [2.2.3.1. 베이스 프로젝트](#2231-베이스-프로젝트)
         * [2.2.3.2. RICU](#2232-ricu)
  * [2.3 추가된 화면](#23-추가된-화면)
     * [2.3.1 갤러리 생성](#231-갤러리-생성)
     * [2.3.2 갤러리 리스트](#232-갤러리-리스트)
     * [2.3.3 갤러리 수정](#233-갤러리-수정)
     * [2.3.4 유저 정보 화면](#234-유저-정보-화면)
* [3. 프로젝트를 진행하며 기억에 남는 것들](#3-프로젝트를-진행하며-기억에-남는-것들)
   * [3.1 HTTP 비동기의 활용](#31-http-비동기의-활용)
      * [3.1.1 문제 직면과 해결](#311-문제-직면과-해결)
      * [3.1.2 다른 곳으로의 확장](#312-다른-곳으로의-확장)
         * [3.1.2.1 값 유효 표시](#3121-값-유효-표시)
         * [3.1.2.2 자동 완성](#3122-자동-완성)
    * [3.2 첫 JavaScript](#32-첫-javascript)
       * [3.2.1 중복 코드 제거](#321-중복-코드-제거)
       * [3.2.2 업로드 이미지 관리](#322-업로드-이미지-관리)
* [4. 이 프로젝트를 진행하며 배운 것](4-이-프로젝트를-진행하며-배운-것)
* [5. 계획했지만 여러 이유로 보류되거나 취소된 것들](5-계획했지만-여러-이유로-보류되거나-취소된-것들)
* [6. 앞으로 할 것들](6-앞으로-할-것들)
# 1. 개요
## 1.1 소개
공통된 관심사를 이야기 할 수 있는 갤러리를 생성하여, 갤러리 마다의 게시글을 작성할 수 있는 커뮤니티 게시판
## 1.2 배경
스프링부트를 책을 통해 배우고, 활용하고 싶어서, 책에 수록된 코드를 바탕으로 나만의 커뮤니티 게시판을 작성하였다.
## 1.3 개발 기간
2023.08 ~ 2023.09
## 1.4 개발 환경
* OS: Window 10
* IDE: Intellij IDEA 2023.1.3
* 실행 브라우저: Chrome
## 1.5 기술 스택
* 언어: Java(JDK 20), JavaScript
* Front-end: Thymeleaf, Axios
* Back-end: SpringBoot(3.1.2), Spring Security(6.1.2), QueryDSL, JPA, Lombok
* Test: JUnit5
* Database: MySQL(8.0.33)
# 2. 베이스 프로젝트 화면 VS RICU 화면
## 2.1 변경 없음
### 2.1.1 게시글 등록
<br><img width=1000 height=400 src="https://github.com/tlsgkdns/ricu/assets/24753709/83d51982-593c-4679-b069-d8b38a1fc07e">
### 2.1.2 로그인
<br><img width=200 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/d0f67679-df4d-41d7-a51f-fc24b11fc4ef">
>  게시글 등록 화면과 로그인 화면은 특별히 바뀐 점이 없다
## 2.2 변경 된 것
### 2.2.1 회원가입
#### 2.2.1.1 베이스 프로젝트
<br><img width=1000 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/5c32b4ea-5b93-45d1-a0a7-2410b6aa498d">
#### 2.2.1.2 RICU
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/eaf209b0-ea37-4f68-8788-8ee26a9b27d3">
<br> 
>  멤버 프로필사진을 추가하였고, 비밀번호 확인란을 추가로 두어 비밀번호를 다시 타이핑하게 하였다.
### 2.2.2 게시글 리스트
#### 2.2.2.1 베이스 프로젝트
<br><img width=1000 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/ea541b6d-250c-48ca-ac20-b7e5fc0f547b">
#### 2.2.2.2 RICU
<br><img width=1000 height=500 src="https://github.com/tlsgkdns/ricu/assets/24753709/e051ccf4-c49b-4532-9b9f-c8f29dc70fb0">
>  윗 부분에 현재 갤러리 정보를 볼 수 있도록 추가하였다. 갤러리 매니저의 경우 정보를 수정 할 수 있도록, Edit 버튼이 보이게 했다.
>  게시글마다의 조회수와 추천을 추가 했고, 인기 있는 게시글만 볼 수 있도록 Popular 모드를 도입했다.
### 2.2.3 게시글 화면
#### 2.2.3.1 베이스 프로젝트
<br><img width=1000 height=600 src="https://github.com/tlsgkdns/ricu/assets/24753709/db873d03-18ad-450f-8055-ca17ffefb4c2">
#### 2.2.3.2 RICU
<br><img width=1000 height=800 src="https://github.com/tlsgkdns/ricu/assets/24753709/44948148-d333-4889-b1ce-18d22da619f9">
>  게시글 밑에 해당 갤러리의 게시글 리스트를 삽입하였고, 댓글 작성자의 프로필 사진을 볼 수 있게 했다. 클릭하면 해당 댓글 작성자의 정보를 볼 수 있다.
>  댓글은 작성자만이 삭제 할 수 있다. 또한 게시글의 추천을 할 수 있도록 추천 버튼 또한 도입했다. 추천한 게시글을 다시 추천하면 추천이 취소된다.
>  해당 갤러리 매니저는 어떠한 게시글이든 삭제할 수 있는 권한을 가진다.
## 2.3 추가된 화면
### 2.3.1 갤러리 생성
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/f1526fd8-1fb1-4bcd-b5a8-af9737d17b52">
>  공통된 관심사를 이야기 할 수 있는 갤러리를 생성한다. 생성자는 매니저가 된다.
### 2.3.2 갤러리 리스트
<br><img width=1000 height=1300 src="https://github.com/tlsgkdns/ricu/assets/24753709/b6d6e43d-71ef-428b-aa95-49f12cf241ac">
>  갤러리 리스트들을 대문과 함께 보여준다, 게시글과 마찬가지로 페이징, 검색을 지원한다.
### 2.3.3 갤러리 수정
<br><img width=1000 height=450 src="https://github.com/tlsgkdns/ricu/assets/24753709/02a57a3d-b52a-48fa-900d-51177d5d752a">
>  매니저만이 접근할 수 있다.  해당 갤러리의 설명, 대문 그리고 인기글 기준을 설정할 수 있다.
### 2.3.4 유저 정보 화면
<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/00657292-efff-4160-bc14-aaf0ca2b7368">
>  유저 정보를 보여준다.  유저 편집에선 닉네임과 프로필 사진을 바꿀 수 있다. 갤러리를 검색해서 해당 유저가 그 갤러리에서 어떤 글을 썼는지 검색할 수 있다.
# 3. 프로젝트를 진행하며 기억에 남는 것들
이 프로젝트를 진행하며 여러 문제를 직면했다. 첫 웹 프로젝트인 만큼 여러 문제를 직면 및 해결했지만, 밑의 내용은 그 중 기억에 남는 것들 몇 개만 추려내었다.
## 3.1 HTTP 비동기의 활용
### 3.1.1 문제 직면과 해결
베이스가 된 프로젝트에서 이해가 힘들었던 부분 중 하나는 RestController를 활용해 댓글을 만들었단 점이였다. 나는 그것보단  @OneToMany를 사용하는 게 더 직관적이라 생각해서 그렇게 코딩을 했었다.

하지만 게시물 밑에 게시물 리스트를 추가하다가, 댓글과 게시물 리스트의 페이징이 중복되어 parameter를 어떻게 해야 할 지에 대한 문제에 직면한다. 생각 끝에, 페이지를 갱신하지 않아도 되는 비동기를 떠올리고, 비동기 통신을 통해 댓글을 다시 코딩하였다. 이로써 페이징의 중복 문제를 해결하였고, 비동기에 대해 이해하는 좋은 기회가 되었다.

<br><img width=1000 height=700 src="https://github.com/tlsgkdns/ricu/assets/24753709/73f9cee2-0be2-4e1c-81c4-54b578042fd7">
> 댓글과 게시물의 페이징을 동시에
### 3.1.2 다른 곳으로의 확장
비동기를 통해 문제를 해결한 나는 다른 곳에서도 이 비동기를 활용할 수 없을지 고민한다.
#### 3.1.2.1 값 유효 표시
<br><img width=800 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/0980b422-45a4-4579-943e-4e8d3d8acf43">
<br><img width=800 height=200 src="https://github.com/tlsgkdns/ricu/assets/24753709/b239ac51-a7d2-48f2-9d79-6361327a47bd">
> 회원가입 시 유효한 입력인지 입력을 받을 때 마다 확인해준다.

<br><img width=700 height=100 src="https://github.com/tlsgkdns/ricu/assets/24753709/0b13e483-7c0b-4df3-b5bf-4e85d6666e8d"><br>
> 갤러리 생성시에도 확인

초기엔 버튼을 눌러서 값을 확인하는 방식을 채택했지만, 사용자 입장에선 일일히 버튼을 눌러 사용가능한 값을 확인하는 것 보단 사용가능여부를 직관적으로 바로 알려주는 것이 더 편할 것이라 생각해 구현하였다.

#### 3.1.2.2 자동 완성
<br><img width=1000 height=390 src="https://github.com/tlsgkdns/ricu/assets/24753709/e6d93555-d55d-41f7-bc27-8f76c25efaf5">
> 유저의 게시글들을 보기위해 검색창에 포함된 단어가 있는 갤러리들을 보여준다.

유저 정보에서 특정 갤러리에서의 게시글 목록을 검색할 때, 자동완성이 있으면 편리할 것이란 생각과 함께 마침 비동기의 활용이 도움이 될 거라 생각이 들어서 구현하였다.

## 3.2 첫 JavaScript
JavaScript를 이 책을 통해 처음 접하였다. 처음에는 많이 혼란스러웠지만, 코드를 짜며 익숙해지고  JavaScript 사용법을 코드를 분석하고 유추하며 배워나갔다.
### 3.2.1 중복 코드 제거
이미지 업로드 코드는 책에 실려있는 코드를 그대로 가져다 사용했다.
```javascript
document.querySelector(".uploadBtn").addEventListener("click", function (e){
        const formObj = new FormData();

        const fileInput = document.querySelector("input[name='file']")
        console.log(fileInput.files)
        const files = fileInput.files
        for(let i=0; i < files.length; i++)
        {
            formObj.append("files", files[i]);
        }
        const uploadFiles = galleryImage.querySelector("img")
        console.log(uploadFiles);
        if(uploadFiles.getAttribute("data-src") != null)
            removeFileLinkToServer(uploadFiles.getAttribute("data-src"))
        uploadToServer(formObj).then(result => {
            for(const uploadResult of result)
            {
                showUploadFile(uploadResult)
            }
            uploadModal.hide()
        }).catch(e => {
            uploadModal.hide()
        })
    })
```
``` javascript
document.querySelector(".submitBtn").addEventListener("click", function (e){
        e.preventDefault()
        e.stopPropagation()

        const target = document.querySelector(".uploadHidden")

        const uploadFiles = galleryImage.querySelectorAll("img")

        let str = ''

        console.log(uploadFiles.length)
        for(let i = 0; i < uploadFiles.length; i++)
        {
            const uploadFile = uploadFiles[i]
            const imgLink = uploadFile.getAttribute("data-src")
            console.log(imgLink)
            str += `<input type='hidden' name='galleryImageName' value="${imgLink}">`
        }
        target.innerHTML = str;

        document.querySelector("#f1").submit();
        document.querySelector("#f1").reset();
    }, false)
```
> 위 - 갤러리 이미지를 업로드하고 보여주는 코드 <br>
> 아래- 갤러리를 생성하기 위한 제출 버튼을 클릭할때, 이미지 업로드 함수이다.

하지만 일일히 이미지 업로드가 필요할 때마다 해당 코드를 복사하고 사용처에 맞게 수정하는 것은 관리가 쉽지 않다고 느껴졌다.<br>
갤러리 생성,수정,회원가입,유저정보 수정 등 총 4군데에서 이것과 비슷한 코드를 사용하였고, 단순히 JavaScript에 익숙하지 않다는 이유로 중복 코드를 내버려두기 힘들었다.<br>
그래서 JavaScript의 코드를 분석하고, 유추하는 과정을 거쳐 중복코드를 획기적으로 줄일 수 있는 함수로 변환하는데 성공하였다.

```javascript
function uploadImage(fileInput, showImage)
{
    const formObj = new FormData();

    console.log(fileInput.files)

    const files = fileInput.files

    for(let i=0; i < files.length; i++)
    {
        if(!isImage(files[i].name))
        {
            alert("You have to insert Image File!")
            fileInput.innerHTML = ''
            continue
        }
        formObj.append("files", files[i]);
        console.log(files[i] + " ADDED!")
    }
    console.log("Upload Btn Clicked!")
    uploadToServer(formObj).then(result => {
        for(const uploadResult of result)
        {
            console.log("Show UpLoad!!!!")
            showImage(uploadResult)
        }
        uploadModal.hide()
    }).catch(e => {
        console.log("ERROR!")
        uploadModal.hide()
    })
}
```
```javascript
function submitUploadedImage(target, uploadFile, submitForm, imageName)
{
    if(uploadFile != null && uploadFile.getAttribute("id") != "altImage")
    {
        console.log("Uploading File...")
        let str = ''
        const imgLink = uploadFile.getAttribute("data-src")
        str += `<input type='hidden' name=${imageName} value="${imgLink}">`
        target.innerHTML = str;
    }
    submitForm.submit();
    submitForm.reset();
}
```
> 위 - 이미지를 업로드하고 보여주는 코드 <br>
> 아래 - 이미지와 함께 입력된 정보들을 제출하는 코드
### 3.2.2 업로드 이미지 관리
베이스 프로젝트는 단순 실습에 지나지 않아서 생긴 문제점이 있다.<br>
일단 업로드만 해도 저장되고 페이지를 닫거나, 생성을 취소하여도 이미지는 그대로 남아있다는 점이다.<br>
하지만, 코드를 구성하고 테스트를 진행하면서 공간을 쓸모없이 차지 하는 것은 비효율적이다고 느꼈다.<br>
JavaScript에 익숙해지고 자신감을 얻은 나는 구글링을 활용한다.
```javascript
    window.onbeforeunload = function ()
    {
        if(!submitting && tmpFile != null) 
            removeFileLinkToServer(tmpFile)
    }
```
> 제출을 제외한 페이지를 벗어나는 이벤트 때, 저장된 이미지 파일을 삭제한다.

이것이 내가 JavaScript를 만지며 처음으로 구글링을 활용해서 문제를 해결한 사례가 되어서 기억에 남았다.
# 4. 이 프로젝트를 진행하며 배운 것
* 첫 웹 프로젝트로써 Spring framework를 처음 만져보며 경험을 쌓을 수 있었다.
* HTTP 비동기에 대해서 이해하고, 활용을 할 수 있게 되었다.
* 자바스크립트를 처음 접해보고, 코드를 작성할 수 있었다.
* 책에서 처음 봤을 때 와닿지 않았던 QueryDSL의 코드를 이해할 수 있었다.
# 5. 계획했지만 여러 이유로 보류되거나 취소된 것들
* 매니저 위임, 추가
* 게시글 이미지 업로드 및 글 사이사이에 이미지 업로드 기능.
# 6. 앞으로 할 것들
* JavaScript를 책을 통해 배운다.
* 웹의 보안에 대한 정보를 찾고, 관련 책을 읽는다.
* SQL/Database에 대한 지식을 복기한다.
* 웹에서 사용할 수 있는 다른 기술들을 검색하고, 공부한다.
* 새로운 프로젝트를 구상한다.
