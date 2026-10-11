# 🚀 Spring Boot Backend Framework 과제

Author : Tae Hyun Kim 
DATE : 2026-10-11

본 프로젝트는 전북대학교 백엔드 프레임워크 실습 과제 제출용 리포지토리입니다

## 📌 과제 구현 내용

**1. HTTP 메소드별 API 구현 (총 8개)**
* **GET (2개):** 전체 유저 조회(`/api/users`), 단건 유저 조회(`/api/users/{id}`)
* **POST (2개):** 신규 유저 생성(`/api/users`), 서버 에러 시뮬레이션(`/api/users/process`)
* **PUT (2개):** 유저 정보 수정(`/api/users/{id}`), 외부 서비스 동기화 에러 시뮬레이션(`/api/users/{id}/sync`)
* **DELETE (2개):** 특정 유저 삭제(`/api/users/{id}`), 전체 데이터 초기화(`/api/users/clear`)

**2. 미들웨어(Middleware) 구현**
* `HandlerInterceptor`를 상속받은 `LoggingInterceptor`를 구현하여, 모든 API 요청 시 HTTP 메서드와 URI를 콘솔에 로그로 출력하도록 설정했습니다.

**3. 다양한 HTTP 상태 코드 사용**
* **2xx:** `200 OK`, `201 Created`
* **4xx:** `400 Bad Request` (필수 값 누락 시), `404 Not Found` (존재하지 않는 유저 조회/수정/삭제 시)
* **5xx:** `500 Internal Server Error`, `503 Service Unavailable`

**4. 공통 응답 포맷 통일**
* `UserApiResponse` 클래스를 생성하여 모든 API의 반환값을 `{"status": "...", "data": ...}` 형태로 통일했습니다.
