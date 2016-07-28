package lh.family.admin.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.admin.dao.INewsDao;
import lh.family.admin.model.News;
import lh.family.admin.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by lh on 2016/4/8.
 */
@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements INewsDao {
    @Autowired
    private INewsRepository newsRepository;
    @Override
    public JpaRepository getRepository() {
        return newsRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return newsRepository;
    }
}
