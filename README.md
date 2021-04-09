# RECOOK

> #### Welcome to 👉 [Recook](http://j4a204.p.ssafy.io/) 👈
> size : Responsive(387 x 858)


<br>

## Project Summary 🧭

재료 기반 레시피 추천 서비스

##### 🔸 제안 배경

- 코로나19로 인한 외식 감소, 집에서 요리하는 횟수 증가
- 집에서 요리를 하는 사람들이 증가하면서 레시피의 필요성 증가

##### 🔸 서비스 컨셉

- 선택한 재료로 만들 수 있는 요리 레시피를 추천해주는 서비스
- 재료에 맞는 레시피를 일일이 찾아봐야 하는 불편함 해소

##### 🔸 타겟

- 집에서 남은 재료로 맛있는 요리를 만들어 먹고 싶은 사람

##### 🔸 기간

- Feb 22th 2021 ~ Apr 9th 2021

##### 🔸 결과물

- [Architecture & Usecase_Diagram](./document/Architecture.md)
- [Sequance_Diagram](./document/SequanceDiagram.md)
- [PPT](./document/특화PJT_최종발표_A204_최종.pdf)
- [UCC](https://www.youtube.com/watch?v=tknwLMpBXCE)





<br>

## Project Info :pushpin:

### Usage 🎈

#### 💻 Front-end

- Project Setup

  - ```bash
    $ npm install 
    ```

- Compiles and hot-reloads for development

  - ```bash
    $ npm run serve
    ```

- Compiles and minifies for production

  - ```bash
    $ npm run build
    ```

- Run your tests

  - ```bash
    $ npm run test
    ```

- Lints and fixes files

  - ```bash
    $ npm run lint
    ```

- Customize configuration

  - [Configuration Reference](https://cli.vuejs.org/config/)

#### 💻 Back-end

**Install**

- Java (Open JDK 14)

- Maven

- STS 

- Mariadb

  - create tables

    run dump.sql => [dump.sql](./document/dump.sql)

- Django

  - Project Setup

    ```bash
    $ pip3 install -r requirements.txt
    # 오류나는 모듈은 수동으로 깔아주세요
    ```

  - DB Connection

    ```bash
    $ python manage.py inspectdb
    ```
  
  - migration
  
    ``` bash
    $ python manage.py makemigrations
    ```
  
  - Run
  
    ```bash
    $ python manage.py runserver
    ```

<br>



### Tech Stack 🧩

- Backend : Spring Boot, Django, MariaDB
- Frontend : Vue.js

![image](/uploads/5b788ee36c740c2adbdcbcc78fa569a7/image.png)



### Database Modeling :link:

![image](/uploads/f3b7b3bc1348160f884823b1186bd184/image.png)



<br>



### Features :sparkles:

##### 	🍕 메인 기능

```
- 유저의 취향(음식종류, 알레르기)을 분석하기 위한 설문조사
- 재료를 선택하면 만들 수 있는 레시피 추천
- 사용자의 취향(음식종류, 알레르기)을 분석한 개인 맞춤형 레시피 추천
- 각 레시피 마다 비슷한 재료를 가지고 만들 수 있는 연관 레시피 제공
```

##### 	🍔 부가 기능

```
- 해당 레시피에 포함된 유저의 알레르기 정보 알림
- 레시피를 찜해 놓고 모아볼 수 있는 기능
- 최근에 본 레시피 확인 가능
- 해당 레시피를 보고 유저가 만든 음식 사진 게시 기능
- 타유저들이 게시한 음식 사진 모아보기 기능
- 음식 관련 영상 제공
```

##### 	🎓 사용한 빅데이터 추천 알고리즘
- 협업 필터링 Collaborative Filtering
- 컨텐츠 기반 필터링 Content based Filtering

<br>



### Pages in Detail :mag:

> 각 페이지 별 소개

- ##### Survey

  ![취향조사](/uploads/600193d116666ca0fa3dc337a445d341/취향조사.gif)

  

- ##### Main

  ![메인화면](/uploads/70803c76aac1daa139723fa0ca8dd08f/메인화면.gif)

  

- ##### Recipe Detail

  ![레시피_상세](/uploads/b975ec8ad5da02ede9100bbf5674bf37/레시피_상세.gif)

  

- ##### Review

  ![리뷰_모아보기](/uploads/123659d2da8190885fd68416ed0f0051/리뷰_모아보기.gif)

  

- ##### MyPage(My Review & Like)

  ![리뷰_찜](/uploads/1bc248758e825f52e09be527d7284b0d/리뷰_찜.gif)

  

- ##### Cook Video

  ![유튜브](/uploads/e0e5aa2d4841acd54d59da1a8c96aa01/유튜브.gif)

### Recipe Source 📕

- [해먹남녀](https://haemukja.com/)




