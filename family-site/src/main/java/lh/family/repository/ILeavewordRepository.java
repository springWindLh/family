package lh.family.repository;

import lh.family.model.Leaveword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lh on 2016/4/27.
 */
public interface ILeavewordRepository extends JpaRepository<Leaveword, Long>, JpaSpecificationExecutor<Leaveword> {
}
