package lh.family.admin.repository;

import lh.family.admin.model.SingleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lh on 2016/4/23.
 */
public interface ISingleMenuRepository extends JpaRepository<SingleMenu, Long>, JpaSpecificationExecutor<SingleMenu> {
}
