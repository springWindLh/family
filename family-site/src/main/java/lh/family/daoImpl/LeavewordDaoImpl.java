package lh.family.daoImpl;

import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.ILeavewordDao;
import lh.family.model.Leaveword;
import lh.family.repository.ILeavewordRepository;
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
