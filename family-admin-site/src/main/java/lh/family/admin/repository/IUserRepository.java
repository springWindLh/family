package lh.family.admin.repository;

import lh.family.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lh on 2016/4/16.
 */
public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
