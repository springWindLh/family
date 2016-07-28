package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.admin.dao.ISingleMenuDao;
import lh.family.admin.model.SingleMenu;
import lh.family.admin.service.ISingleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2016/4/25.
 */
@Service
@Transactional
public class SingleMenuServiceImpl extends BaseServiceImpl<SingleMenu> implements ISingleMenuService {
    @Autowired
    private ISingleMenuDao singleMenuDao;

    @Override
    public IBaseDao getDao() {
        return singleMenuDao;
    }
}
