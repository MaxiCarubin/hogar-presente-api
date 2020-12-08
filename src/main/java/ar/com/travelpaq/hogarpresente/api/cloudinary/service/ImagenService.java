package ar.com.travelpaq.hogarpresente.api.cloudinary.service;

import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import ar.com.travelpaq.hogarpresente.api.cloudinary.repository.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenService {
    @Autowired
    IImagenRepository imagenRepository;

    public List<ImagenEntity> list(){
        return imagenRepository.findByOrderById();
    }

    public Optional<ImagenEntity> getOne(int id){
        return imagenRepository.findById(id);
    }

    public void save(ImagenEntity imagen){
        imagenRepository.save(imagen);
    }

    public void delete(int id){
        imagenRepository.deleteById(id);
    }

    public boolean exists(int id){
        return imagenRepository.existsById(id);
    }
}
