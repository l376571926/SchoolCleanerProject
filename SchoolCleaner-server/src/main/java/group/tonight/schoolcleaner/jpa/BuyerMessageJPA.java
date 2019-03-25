package group.tonight.schoolcleaner.jpa;

import group.tonight.schoolcleaner.model.BuyerMessageBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface BuyerMessageJPA extends JpaRepository<BuyerMessageBean, Long>,
        JpaSpecificationExecutor<BuyerMessageBean>,
        Serializable {
    List<BuyerMessageBean> findByGoodId(Long goodId);
}
