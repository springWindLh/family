package lh.family.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.dao.IPhotoDao;
import lh.family.model.Photo;
import lh.family.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2016/4/8.
 */
@Service
@Transactional
public class PhotoServiceImpl extends BaseServiceImpl<Photo> implements IPhotoService {
    @Autowired
    private IPhotoDao photoDao;

    @Override
    public IBaseDao getDao() {
        return photoDao;
    }
}
