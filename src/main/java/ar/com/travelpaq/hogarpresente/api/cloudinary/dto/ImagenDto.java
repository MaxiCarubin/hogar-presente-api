package ar.com.travelpaq.hogarpresente.api.cloudinary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenDto {
    private String name;
    private String imagenUrl;
    private String imagenId;
}
