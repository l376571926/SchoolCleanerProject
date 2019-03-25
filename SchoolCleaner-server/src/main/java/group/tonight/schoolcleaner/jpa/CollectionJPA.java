package group.tonight.schoolcleaner.jpa;

import group.tonight.schoolcleaner.model.CollectionBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface CollectionJPA extends JpaRepository<CollectionBean, Long>,
        JpaSpecificationExecutor<CollectionBean>,
        Serializable {
    List<CollectionBean> findByUserId(Long userId);

    boolean existsByUserIdAndGoodId(Long userId, Long goodId);
}
