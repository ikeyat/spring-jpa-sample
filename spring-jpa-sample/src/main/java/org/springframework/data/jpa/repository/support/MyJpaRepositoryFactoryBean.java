package org.springframework.data.jpa.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.example.domain.repository.MyJpaRepository;
import com.example.domain.repository.MyJpaRepositoryImpl;

// 拡張したインタフェースを返却するようFactoryやそのFactoryBeanも修正する
public class MyJpaRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(
            final EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {
            protected SimpleJpaRepository<T, ID> getTargetRepository(
                    RepositoryMetadata metadata, EntityManager entityManager) {

                @SuppressWarnings("unchecked")
                JpaEntityInformation<T, ID> entityInformation = getEntityInformation((Class<T>) metadata
                        .getDomainType());

                MyJpaRepositoryImpl<T, ID> repositoryImpl = new MyJpaRepositoryImpl<T, ID>(
                        entityInformation, entityManager);
                repositoryImpl
                        .setRepositoryMethodMetadata(CrudMethodMetadataPostProcessor.INSTANCE
                                .getLockMetadataProvider());

                return repositoryImpl;
            }

            @Override
            protected Class<?> getRepositoryBaseClass(
                    RepositoryMetadata metadata) {
                // 拡張したJpaRepsoitoryの型を返却する
                return MyJpaRepository.class;
            }
        };
    }
}
