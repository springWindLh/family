package lh.family.admin.daoImpl;

import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.admin.dao.ISingleDetailDao;
import lh.family.admin.model.SingleDetail;
import lh.family.admin.repository.ISingleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/23.
 */
@Repository
public class SingleDetailDaoImpl extends BaseDaoImpl<SingleDetail> implements ISingleDetailDao {
    @Autowired
    private ISingleDetailRepository singleDetailRepository;

    @Override
    public JpaRepository getRepository() {
        return singleDetailRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return singleDetailRepository;
    }
}
