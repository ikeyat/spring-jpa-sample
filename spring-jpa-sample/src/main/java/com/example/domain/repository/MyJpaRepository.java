package com.example.domain.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Repositoryプロキシクラスが生成されるのを防ぐ
public interface MyJpaRepository<T, ID extends Serializable> extends
        JpaRepository<T, ID> {
    // 今回追加実装するカスタムメソッド
    Class<?> getEntityClass();
}
