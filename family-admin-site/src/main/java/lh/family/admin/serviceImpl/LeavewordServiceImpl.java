package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.admin.dao.ILeavewordDao;
import lh.family.admin.model.Leaveword;
import lh.family.admin.service.ILeavewordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/27.
 */
@Repository
public class LeavewordServiceImpl extends BaseServiceImpl<Leaveword> implements ILeavewordService {
    @Autowired
    private ILeavewordDao leavewordDao;

    @Override
    public IBaseDao getDao() {
        return leavewordDao;
    }
}
