package com.playground.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import com.playground.constants.DataSourceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class RoutingDataSourceConfigTest {

  private static final String DETERMINE_CURRENT_LOOKUP_KEY = "determineCurrentLookupKey";

  @Transactional(readOnly = false)
  @DisplayName("MasterDataSource Replication 설정 테스트")
  @Test
  void testMasterDataSourceReplication() throws Exception {

    // Given
    RoutingDataSource routingDataSource = new RoutingDataSource();

    // When
    Method declaredMethod = ReflectionUtils.findMethod(RoutingDataSource.class, DETERMINE_CURRENT_LOOKUP_KEY);
    ReflectionUtils.makeAccessible(declaredMethod);

    Object object = ReflectionUtils.invokeMethod(declaredMethod, routingDataSource);

    // Then
    log.debug(">>> invokeMethod : [{}]", object);
    assertEquals(DataSourceType.MASTER.toString(), object.toString());
  }

  @Transactional(readOnly = true)
  @DisplayName("SlaveDataSource Replication 설정 테스트")
  @Test
  void testSlaveDataSourceReplication() throws Exception {

    // Given
    RoutingDataSource routingDataSource = new RoutingDataSource();

    // When
    Method declaredMethod = ReflectionUtils.findMethod(RoutingDataSource.class, DETERMINE_CURRENT_LOOKUP_KEY);
    ReflectionUtils.makeAccessible(declaredMethod);

    Object object = ReflectionUtils.invokeMethod(declaredMethod, routingDataSource);

    // Then
    log.debug(">>> invokeMethod : [{}]", object);
    assertEquals(DataSourceType.SLAVE.toString(), object.toString());
  }
}
