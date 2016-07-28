package lh.family.admin.daoImpl;

import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.admin.dao.ILeavewordDao;
import lh.family.admin.model.Leaveword;
import lh.family.admin.repository.ILeavewordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/27.
 */
@Repository
public class LeavewordDaoImpl extends BaseDaoImpl<Leaveword> implements ILeavewordDao {
    @Autowired
    private ILeavewordRepository leavewordRepository;

    @Override
    public JpaRepository getRepository() {
        return leavewordRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return leavewordRepository;
    }
}
