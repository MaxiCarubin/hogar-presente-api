package ar.com.travelpaq.hogarpresente.api.cloudinary.mapper;

import ar.com.travelpaq.hogarpresente.api.cloudinary.dto.ImagenDto;
import ar.com.travelpaq.hogarpresente.api.cloudinary.entity.ImagenEntity;
import org.springframework.stereotype.Component;

@Component
public class ImagenMapper {

    public ImagenDto mapImagenEntityToImagenDto(ImagenEntity imagenEntity) {
        ImagenDto imagenDto = new ImagenDto();
        imagenDto.setImagenId(imagenEntity.getImagenId());
        imagenDto.setName(imagenEntity.getName());
        imagenDto.setImagenUrl(imagenEntity.getImagenUrl());
        return imagenDto;
    }
}
