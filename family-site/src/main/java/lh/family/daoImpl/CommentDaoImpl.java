package lh.family.daoImpl;


import lh.base.support.daoImpl.BaseDaoImpl;
import lh.family.dao.ICommentDao;
import lh.family.model.Comment;
import lh.family.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lh on 2016/4/8.
 */
@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements ICommentDao {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public JpaRepository getRepository() {
        return commentRepository;
    }

    @Override
    public JpaSpecificationExecutor getSpecificationExecutor() {
        return commentRepository;
    }
}
