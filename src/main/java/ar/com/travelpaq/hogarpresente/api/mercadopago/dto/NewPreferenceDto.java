package ar.com.travelpaq.hogarpresente.api.mercadopago.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPreferenceDto implements Serializable {
    private String accessToken;
    private List<Long> cursosId;
}
