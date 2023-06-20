package com.playground.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.playground.constants.DataSourceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    log.debug(">>> RoutingDataSource : {}",
        TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.SLAVE : DataSourceType.MASTER);
    return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? DataSourceType.SLAVE : DataSourceType.MASTER;
  }
}
