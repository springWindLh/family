package lh.family.daoImpl;

import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.ISingleMenuDao;
import lh.family.model.SingleMenu;
import lh.family.repository.ISingleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/23.
 */
@Repository
public class SingleMenuDaoImpl extends BaseDaoImpl<SingleMenu> implements ISingleMenuDao {
    @Autowired
    private ISingleMenuRepository singleMenuRepository;

    @Override
    public JpaRepository getRepository() {
        return singleMenuRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return singleMenuRepository;
    }
}
