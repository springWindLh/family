package lh.family.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.dao.INewsDao;
import lh.family.model.News;
import lh.family.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2016/4/8.
 */
@Service
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements INewsService {
    @Autowired
    private INewsDao newsDao;

    @Override
    public IBaseDao getDao() {
        return newsDao;
    }
}
