# 회원

# 1. Swagger
 * [Swagger UI 바로가기](http://localhost:8080/swagger-ui/index.html)

# 2. 요구사항 구현 여부
   - 로그인 시 회원가입이 되어 있는 지 검색하여 토큰 발급하도록 구현 
   - JWT Token (Bearer *) 토큰(인증)으로 할 수 있는 서비스 구현(본인의 정보 검색)
   - 주민번호 및 패스워드 암호화
   - 일단 AES256으로 했습니다.
   
# 3. 구현 방법
   - Java11, Spring Boot 2.7.9, gradle, H2, JPA, Swagger 3.0.0 이용하여 구현
   
### 3-1. 패키지
   - 최초 요청을 받는 Controller
   - 비즈니스 로직을 수행하는 Service
   - DB와 연결 되는 Repository,
   - 실제 DB의 엔티티를 나타내는 Entity
   - DB엔티티와 매핑하기 위한 Model
   - Spring security 및 Swagger 설정을 위한 config
   - Spring security 필터 설정을 위한 filter
   - Exception 처리를 위한 exception
   - 자주 사용하는 함수를 모아두기 위한 util

# 4. 검증 결과
   - Postman(회원.postman_collection.json 해당 파일 import) 을 이용한 검증
     - 회원가입, 로그인 진행 후 발급되는 토큰을 복사하여 인증이 필요한 서비스에 header탭 Authorization -> (Bearer 토큰) 입력 후 진행
   - Swagger를 통한 검증
     - Swagger-UI페이지 내에서 로그인, 회원가입은 (try it out) 선택 후 json 형식데이터 넣어서 진행
     - 토큰이 필요한 서비스의 경우 오른쪽 상단에 있는 Authorize를 선택하여 로그인 이후 발급받은 토큰을 가지고 (Bearer 토큰) 형식으로 만들어 넣어준 뒤 Authorize 선택
     - 이후 테스트할 서비스 진행하면 완료.
     
     