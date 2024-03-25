package com.d205.foorrng.jwt.repository;

import com.d205.foorrng.jwt.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long>{

}
