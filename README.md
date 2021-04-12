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

- Vue.js

  - Project Setup

    ```bash
    $ npm install 
    ```

  - Compiles and hot-reloads for development

    ```bash
    $ npm run serve
    ```

  - Compiles and minifies for production

    ```bash
    $ npm run build
    ```

  - Run your tests

    ```bash
    $ npm run test
    ```

  - Lints and fixes files

    ```bash
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

![기술스택](https://user-images.githubusercontent.com/40655666/114400390-deeb3900-9bdc-11eb-8913-7719bd7beb6c.png)



### Database Modeling :link:

![erd](https://user-images.githubusercontent.com/40655666/114400394-e0b4fc80-9bdc-11eb-9483-1c6f6db300b4.png)



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

> 각 페이지 별 스크린샷 (GIF)

- ##### Survey

![취향조사](https://user-images.githubusercontent.com/40655666/114399700-2f15cb80-9bdc-11eb-996e-b978cfb64b7a.gif)

- ##### Main
- ![메인_리뷰장려팝업](https://user-images.githubusercontent.com/40655666/114400961-72246e80-9bdd-11eb-9392-549aa1697d6d.png)![메인_로그인](https://user-images.githubusercontent.com/40655666/114400945-6fc21480-9bdd-11eb-95aa-43a5676f21fc.png)![메인_최근본레시피](https://user-images.githubusercontent.com/40655666/114400965-73559b80-9bdd-11eb-963b-e4b0fb54598c.png)


  

- ##### Recipe Detail

![레시피_상세_썸네일2](https://user-images.githubusercontent.com/40655666/114401040-82d4e480-9bdd-11eb-9b63-9b9225d9aaa1.png)![레시피_상세_정보](https://user-images.githubusercontent.com/40655666/114401061-87010200-9bdd-11eb-9366-a560fd578714.png)![레시피_상세_정보2](https://user-images.githubusercontent.com/40655666/114401068-87999880-9bdd-11eb-9586-7ec211b948eb.png)![레시피_상세_요리순서](https://user-images.githubusercontent.com/40655666/114401054-85cfd500-9bdd-11eb-810e-df953ab57e5d.png)![레피시_상세_마지막](https://user-images.githubusercontent.com/40655666/114401072-88322f00-9bdd-11eb-80c9-dac8e3285c5f.png)

- ##### Review
![리뷰모아보기](https://user-images.githubusercontent.com/40655666/114399862-5a001f80-9bdc-11eb-9b41-d1e73b1e7633.gif)


  

- ##### MyPage(My Review & Like)
![리뷰찜](https://user-images.githubusercontent.com/40655666/114399877-5ec4d380-9bdc-11eb-91e2-0ea6ff1e74fa.gif)



  

- ##### Cook Video
![유튜브](https://user-images.githubusercontent.com/40655666/114399903-63898780-9bdc-11eb-97aa-ce6ce053f159.gif)



### Recipe Source 📕

- [해먹남녀](https://haemukja.com/)




