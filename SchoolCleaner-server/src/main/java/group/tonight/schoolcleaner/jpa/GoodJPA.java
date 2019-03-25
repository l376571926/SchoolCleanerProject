package group.tonight.schoolcleaner.jpa;

import group.tonight.schoolcleaner.model.GoodBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface GoodJPA extends JpaRepository<GoodBean, Long>,
        JpaSpecificationExecutor<GoodBean>,
        Serializable {
    List<GoodBean> findByGoodNameLike(String searchKey);
}
