# Django

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

# 1. Content Based filtering vs Collaborative Filtering

[이수진의 블로그](https://lsjsj92.tistory.com/563)

### 1) Content Based filtering

(1) 정의

- 특정 아이템을 선호할 때, 그 아이템과 비슷한 콘텐츠 추천
- 옛날에 많이 쓰던 방식, 요즘 잘 사용하지 않음

(2)예시

- ex) 김윤지 감독의 액션 영화 봤으면, 김윤지 감독의 다른 액션 영화 추천해줌
- 예시 코드 블로그

[이수진의 블로그](https://lsjsj92.tistory.com/565?category=853217)

### 2) Collaborative Filtering
(1) 정의

- 사용자의 행동양식 기반으로 추천

![KakaoTalk_20210325_155045829](/uploads/836d4e02d5562282f6e50b2d584fbdf6/KakaoTalk_20210325_155045829.png)

(2) 예시

- ex) 심슨이 피자, 도넛, 과자 좋아함 / 퉁퉁이가 피자, 도넛 좋아함

    → 퉁퉁이랑 심슨은 비슷한 취향 → 퉁퉁이에게 과자 추천

(3) 방식

1) 최근접 이웃 기반 필터링

- 사용자 기반 방식 : 비슷한 고객들이 ~한 아이템 소비
- 아이템 기반 방식 : ~~한 아이템을 소비한 고객들은 다음과 같은 상품도 구매

⇒ 아이템 기반이 정확도 더 높음

- 아이템 기반 예시 코드 블로그

[이수진의 블로그](https://lsjsj92.tistory.com/568)

2) 잠재 요인 협업 필터링

- SVD 행렬 분해 사용

[이수진의 블로그](https://lsjsj92.tistory.com/569?category=853217)

- SVD 행렬분해 사용 + 사용자가 이미 본 아이템 리스트에서 제거

[](https://lsjsj92.tistory.com/570?category=853217)

## 2. 프젝 적용 어떻게?

### 1. 메인 추천 레시피

(1) 사용 알고리즘

- Collaborative Filtering의 최근접 이웃 기반 필터링 (아이템 기반)

(2) 사용 데이터 

- 사용자별 리뷰 평점 데이터& 레시피 목록 데이터
- 취향조사(국가별 음식 선호도, 알레르기) 데이터

(3) 알고리즘 설계

- 평점 데이터 이용한 추천 리스트 목록 추출 → 목록 중 선호 국가 해시태그가 존재하는 레시피 우선순위 부여 →  목록 중 알레르기 데이터 포함하는 레시피 제거

### 2. 상세 레시피 하단의 추천 레시피

(1) 사용 알고리즘

- Content Based Filtering

(2) 사용 데이터

- 재료
- 해시태그
- 레시피 제목
- 레시피 상세설명

(3) 알고리즘 설계

- 데이터 전처리 후 Content Based Filtering 이용해 추천 리스트 추출

### 3. 메인 인기 레시피

(1) 사용 알고리즘

- 정렬

(2) 사용 데이터

- 레시피별 총 좋아요 개수

(3) 알고리즘 설계

- 레시피 별 좋아요 개수 내림차순 정렬