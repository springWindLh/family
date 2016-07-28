package lh.family.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.IUserDao;
import lh.family.model.User;
import lh.family.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/8.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public JpaRepository getRepository() {
        return userRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return userRepository;
    }
}
