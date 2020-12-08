package ar.com.travelpaq.hogarpresente.api.cloudinary.repository;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImagenRepository extends JpaRepository<ImagenEntity, Integer> {
    List<ImagenEntity> findByOrderById();
}
