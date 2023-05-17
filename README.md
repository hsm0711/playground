# 1. Swagger
 * [Swagger UI 바로가기](http://localhost:8080/swagger-ui/index.html)



# 2. Database Replication 설정
   - Database Replication은 실시간 복제본 데이터베이스 서버를 운용하는 것을 의미함
   - 기준이 되는 서버가 Master 서버 복제된 서버를 Replica 서버라 칭함
   - Replica 서버는 읽기 전용 속성으로 운영되며 이를 이용해 readonly 여부에 따라 DataSource 를 변경하여 DB Connection을 변경함
   - @Transactional annotaion의 readonly 속성을 사용하여 read/write이면 Master로 readonly인 경우 Slave로 DataSource를 결정해서 connection 함

> 관련 소스
   - [application.yml](https://github.com/hsm0711/member/blob/master/src/main/resources/application.yml#L25)
   : master/slave jdbc 관련 설정
   - [DataSourceConfig.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/config/DataSourceConfig.java)
   : yaml 파일에 설정한 jdbc 설정 정보로 DataSource를 반환하는 bean 생성
   - [RoutingDataSource.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/config/RoutingDataSource.java)
   : Transaction의 readonly여부에 따라 master/slave 판단
   - [RoutingDataSourceConfig.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/config/RoutingDataSourceConfig.java)
   : LazyConnection으로 RoutingDataSource에서 판단된 DataSource로 connection



# 3. secret manager
   - git, svn 등등 형상 관리 되는 프로젝트의 yaml, properties에 서버의 중요 정보가 노출되어있음
   - yaml, properties에 적용 돼야 할 중요 정보를 별도의 영역인 cloud에서 관리하고 application runtime 시점에 해당 정보를 조회함
   - Google Cloud Platform (GCP), Amazon Web Services (AWS) 의 Secert Manager, Vault 등이 있음
   - GCP의 Secret Manager를 적용함
   - application.yml의 spring.cloud.gcp.secretmanager.credentials.location 설정이 적용되지 않아서 추후 확인 및 테스트 필요
   - 임시로 GOOGLE_APPLICATION_CREDENTIALS 시스템 변수 설정이 필요함
     - git repository의 Settings - Secrets and variables - Actions 메뉴에서 Variables탭에 GOOGLE_APPLICATION_CREDENTIALS 항목 복사
     - PC의 특정 영역에 json파일로 저장한 후 해당 파일을 GOOGLE_APPLICATION_CREDENTIALS 환경변수로 등록

> 관련 소스
   - [application.yml](https://github.com/hsm0711/member/blob/master/src/main/resources/application.yml#L21)
   : ${sm://jdbc-url} 와 같은 형태로 사용
   - [build.gradle](https://github.com/hsm0711/member/blob/master/build.gradle#L40)
   : spring-cloud-gcp-starter, spring-cloud-gcp-starter-secretmanager

> 참고 링크
   - <https://spring-gcp.saturnism.me/app-dev/cloud-services/secret-management>
   - <https://codelabs.developers.google.com/codelabs/cloud-spring-cloud-gcp-secret-manager#1>



# 4. 로그
   - Spring boot에는 logback이 기본으로 설정되어있음
   - 로그 추적을 위해 JwtFilter에서 userId가 있는 경우 MDC에 put하고 application.yml의 log formatter에 userId 찍도록 추가
   - 로그에 표출되지 말아야 할 민감정보를 masking 처리해서 로그에 표출하도록 BaseEntity, BaseDto에서 ToString 구현
   - 모든 Entity나 DTO/VO들은 BaseEntity, BaseDto를 상속받아야 함


> 관련 소스
   - [JwtFilter.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/filter/JwtFilter.java#L67)
   : MDC 셋팅
   - [application.yml](https://github.com/hsm0711/member/blob/master/src/main/resources/application.yml#L59)
   : default pattern에 userId 추가
   - [BaseEntity.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/entity/BaseEntity.java)
   : 민감정보 로그에 표출되지 않도록 마스킹 처리를 위해 ToString Override
   - [BaseDto.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/model/BaseDto.java)
   : 민감정보 로그에 표출되지 않도록 마스킹 처리를 위해 ToString Override



# 5. Redis
   - 내용 작성 중


> 관련 소스
   - [application-local.yml](https://github.com/hsm0711/member/blob/master/src/main/resources/application-local.yml#L18)
   : redis 접속 정보 셋팅
   - [RedisConfig.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/config/RedisConfig.java)
   : LettuceConnectionFactory를 활용해 redis 접속 환경 구성
   - [CacheConfig.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/config/CacheConfig.java)
   : redis를 Cache로 활용하기 위해 CacheManager 설정 구성
   - [RedisController.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/api/sample/RedisController.java)
   : redis 활용 샘플 api
   - [MemberService.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/api/member/service/MemberService.java#L88)
   : redis 활용 캐싱 api




# 6. Excel 다운로드
   - Excel 다운로드를 위해 Apache POI를 사용함
   - Workbook은 SXSSFWorkbook을 사용함
   - SXSSFWorkbook은 스트림 기반으로 동작하며 데이터를 메모리에 모두 로드하지 않고 생성자 생성 시 설정 한 rowAccessWindowSize만 메모리에서 작업을 수행하기 때문에 Out of Memory(OOM)를 방지함
   - rowAccessWindowSize가 넘어가는 경우 임시 파일에 flush 처리를 함
   - Mybatis를 사용 할 경우 Mybatis에서 제공하는 ResultHandler를 활용하면 전체 데이터의 List<VO>를 생성하지 않기 때문에 성능상의 이점이 있음


> 관련 소스
   - [ExcelDown.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/annotation/ExcelDown.java)
   : Excel 다운로드를 위해 해더, width, 순서 등 설정
   - [ExcelDownUtil.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/utils/ExcelDownUtil.java)
   : Excel 다운로드를 처리를 하는 Util
   - [ExcelController.java](https://github.com/hsm0711/member/blob/master/src/main/java/com/member/api/sample/ExcelController.java)
   : Excel 다운로드를 샘플 api






------------------------------------------------------------


> DB접속정보 등 기타 정보는
  Settings - Secrets and variables - Actions에 메뉴에서 Variables탭에 기록함







