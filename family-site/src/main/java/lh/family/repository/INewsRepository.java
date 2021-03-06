package lh.family.repository;

import lh.family.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lh on 2016/4/16.
 */
public interface INewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
}
