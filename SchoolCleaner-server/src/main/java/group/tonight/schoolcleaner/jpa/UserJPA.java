package group.tonight.schoolcleaner.jpa;

import group.tonight.schoolcleaner.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserJPA extends JpaRepository<UserBean, Long>,
        JpaSpecificationExecutor<UserBean>,
        Serializable {

    UserBean findUserBeanByUserPhone(String userPhone);
}
