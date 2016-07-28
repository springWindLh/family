package lh.family.admin.repository;

import lh.family.admin.model.SingleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/23.
 */
public interface ISingleDetailRepository extends JpaRepository<SingleDetail, Long>, JpaSpecificationExecutor<SingleDetail> {
}
