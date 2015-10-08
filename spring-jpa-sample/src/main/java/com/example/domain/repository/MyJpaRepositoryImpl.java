package com.example.domain.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

// SimpleJpaRepositoryが提供する機能にカスタムメソッドを追加する場合
public class MyJpaRepositoryImpl<T, ID extends Serializable> extends
        SimpleJpaRepository<T, ID> implements MyJpaRepository<T, ID> {

    public MyJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    // 今回追加実装するカスタムメソッド
    public Class<T> getEntityClass() {
        return super.getDomainClass();
    }
}
