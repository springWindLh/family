package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.admin.dao.IPhotoDao;
import lh.family.admin.model.Photo;
import lh.family.admin.service.IPhotoService;
import lh.family.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
