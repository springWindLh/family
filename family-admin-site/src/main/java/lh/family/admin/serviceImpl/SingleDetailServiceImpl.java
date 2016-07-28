package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.admin.dao.ISingleDetailDao;
import lh.family.admin.model.SingleDetail;
import lh.family.admin.service.ISingleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2016/4/25.
 */
@Service
@Transactional
public class SingleDetailServiceImpl extends BaseServiceImpl<SingleDetail> implements ISingleDetailService {
    @Autowired
    private ISingleDetailDao singleDetailDao;

    @Override
    public IBaseDao getDao() {
        return singleDetailDao;
    }
}
