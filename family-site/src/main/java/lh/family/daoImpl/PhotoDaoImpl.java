package lh.family.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.IPhotoDao;
import lh.family.model.Photo;
import lh.family.repository.IPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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
