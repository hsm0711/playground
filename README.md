# 1. Swagger
 * [Swagger UI 바로가기](http://localhost:8080/swagger-ui/index.html)

# 2. Database Replication DataSource
   - Database Replication은 실시간 복제본 데이터베이스 서버를 운용하는 것을 의미함
   - 기준이 되는 서버가 Master서버 복제된 서버를 Replica서버라 칭함
   - Replica 서버는 읽기 전용 속성으로 운영되며 이를 이용해 readonly 여부에 따라 DataSource를 변경하여 DB Connection을 변경함
   - @Transactional annotaion의 readonly 속성을 사용하여 read/write인 경우 Master로 readonly인 경우 Slave로 DataSource를 결정해서 connection함

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
   - yaml, properties에 적용 되야 될 중요정보를 별도의 영역인 cloud에서 관리하고 application runtime 시점에 해당 정보를 조회함
   - Google Cloud Platform (GCP), Amazon Web Services (AWS)의 Secert Manager, Vault 등이 있음
   - GCP의 Secret Manager를 적용 함
   - application.yml의 spring.cloud.gcp.secretmanager.credentials.location 설정이 적용되지 안항서 추후 확인 및 테스트 필요
   - 임시로 GOOGLE_APPLICATION_CREDENTIALS 시스템 변수 설정이 필요함
     git repository의 Settings - Secrets and variables - Actions 메뉴에서 Variables탭에 GOOGLE_APPLICATION_CREDENTIALS 항목 복사해서
     PC의 특정 영역에 json파일로 저장한 후 해당 파일을 GOOGLE_APPLICATION_CREDENTIALS 환경변수로 등록

> 관련 소스
   - [application.yml](https://github.com/hsm0711/member/blob/master/src/main/resources/application.yml#L21)
   - [build.gradle](https://github.com/hsm0711/member/blob/master/build.gradle#L40)
   : spring-cloud-gcp-starter, spring-cloud-gcp-starter-secretmanager

