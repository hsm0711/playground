# Database Replication DataSource
   - Database Replication은 실시간 복제본 데이터베이스 서버를 운용하는 것을 의미함
   - 기준이 되는 서버가 Master서버 복제된 서버를 Replica서버라 칭함
   - Replica 서버는 읽기 전용 속성으로 운영되며 이를 이용해 readonly 여부에 따라 DataSource를 변경하여 DB Connection을 변경함
   - @Transactional annotaion의 readonly 속성을 사용하여 read/write인 경우 Master로 readonly인 경우 Slave로 DataSource를 결정해서 connection함

