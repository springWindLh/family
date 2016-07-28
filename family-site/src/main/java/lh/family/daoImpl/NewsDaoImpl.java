package lh.family.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.INewsDao;
import lh.family.model.News;
import lh.family.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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
