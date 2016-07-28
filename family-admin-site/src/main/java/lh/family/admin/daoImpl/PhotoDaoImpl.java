package lh.family.admin.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.admin.dao.IPhotoDao;
import lh.family.admin.model.Photo;
import lh.family.admin.repository.IPhotoRepository;
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
public class PhotoDaoImpl extends BaseDaoImpl<Photo> implements IPhotoDao {
    @Autowired
    private IPhotoRepository photoRepository;
    @Override
    public JpaRepository getRepository() {
        return photoRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return photoRepository;
    }
}
