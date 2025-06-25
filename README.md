### easy notes api

---    
쿠버네티스에 배포하여 테스트 용도로 사용할 Application 입니다.   

---
### Tech Spec.     

- JDK 17
- Gradle 8
- Spring Boot 3.5

---
### Library    

- MySQL 8
- JPA
- Swagger 3
- Embedded Tomcat 9
- Lombok

---      
### 주요 기능

| 기능           | 	method | 	url	            | request	      | response	              | status      |
|--------------|---------|------------------|---------------|------------------------|-------------|
| Landing Page | GET     | /api/index       | No Parameter	 | String	                | 200         |
| 데이터 등록       | POST    | /api/bulk-import | No Parameter	 | Successfully imported	 | 200: 정상 등록  |
| 노트 전체 조회     | GET     | /api/notes       | No Parameter  | 	전체 데이터 응답             | 	200: 정상 조회 |
| 노트 단건 조회     | GET     | /api/notes/{id}  | {id}	         | 데이터 응답	                | 200: 정상 조회  |
| 노트 단건 등록     | POST    | /api/notes       | Body	         | 데이터 응답	                | 201: 정상 등록  |
| 노트 단건 수정     | PUT     | /api/notes/{id}  | {id}	         | 데이터 응답	                | 200: 정상 수정  |
| 노트 단건 삭제     | DELETE  | /api/notes/{id}  | {id}	         | 데이터 응답	                | 200: 정상 삭제  |


