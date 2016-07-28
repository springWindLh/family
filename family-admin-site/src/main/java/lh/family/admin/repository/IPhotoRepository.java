package lh.family.admin.repository;

import lh.family.admin.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by lh on 2016/4/16.
 */
public interface IPhotoRepository extends JpaRepository<Photo, Long>, JpaSpecificationExecutor<Photo> {
}
