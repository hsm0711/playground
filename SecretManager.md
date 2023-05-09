# secret manager
   - git, svn 등등 형상 관리 되는 프로젝트의 yaml, properties에 서버의 중요 정보가 노출되어있음
   - yaml, properties에 적용 되야 될 중요정보를 별도의 영역인 cloud에서 관리하고 application runtime 시점에 해당 정보를 조회함
   - Google Cloud Platform (GCP), Amazon Web Services (AWS)의 Secert Manager, Vault 등이 있음
   - GCP의 Secret Manager를 적용 함
   - GOOGLE_APPLICATION_CREDENTIALS 시스템 변수 설정 프로젝트의 /member/src/main/resources/gcp/service.json 경로 지정
     application.yml의 spring.cloud.gcp.secretmanager.credentials.location 설정이 적용이 안되서 추후 테스트 필요

