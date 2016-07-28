package lh.family.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.dao.ILeavewordDao;
import lh.family.model.Leaveword;
import lh.family.service.ILeavewordService;
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
