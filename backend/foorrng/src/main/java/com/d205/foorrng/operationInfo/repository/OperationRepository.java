package com.d205.foorrng.operationInfo.repository;

import com.d205.foorrng.operationInfo.OperationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<OperationInfo, Long> {

}
