package ar.com.travelpaq.hogarpresente.api.mercadopago.service;

import ar.com.travelpaq.hogarpresente.api.mercadopago.dto.NewPreferenceDto;
import ar.com.travelpaq.hogarpresente.api.models.entity.CursoEntity;
import ar.com.travelpaq.hogarpresente.api.models.repository.ICursoRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferenceService {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ICursoRepository cursoRepository;


    public ResponseEntity create(NewPreferenceDto preferenceDTO) throws MPException {
        if (StringUtils.isEmpty(preferenceDTO.getAccessToken())) {
            return ResponseEntity.badRequest().body("Access token is mandatory");
        }
        if (preferenceDTO.getCursosId().isEmpty()) {
            return ResponseEntity.badRequest().body("Items empty");
        }

        MercadoPago.SDK.setAccessToken(preferenceDTO.getAccessToken());
        String notificationUrl = "http://localhost:8080/generic";

        Preference p = new Preference();
        p.setBackUrls(
                new BackUrls()
                        .setSuccess(notificationUrl)
                        .setPending(notificationUrl)
                        .setFailure(notificationUrl)
        );
        List<CursoEntity> cursos = new ArrayList<>();
        for (long cursoId : preferenceDTO.getCursosId()){
            CursoEntity cursoEntity = cursoRepository.getOne(cursoId);
            cursos.add(cursoEntity);
        }

        p.setItems(cursos.stream()
                .map(i -> {
                    Item item = new Item();
                    item.setUnitPrice(i.getPrecio());
                    item.setTitle(i.getNombre());
                    item.setDescription(i.getDescripcion());
                    item.setId(String.valueOf(i.getId()));
                    item.setPictureUrl(i.getImagen());
                    item.setQuantity(1);
                    return item;
                })
                .collect(Collectors.toCollection(ArrayList::new)));
        p.save();

        if (StringUtils.isEmpty(p.getId())) {
            return ResponseEntity.status(404).body(
                    Collections.singletonMap("Message",
                            "Preference was not created. Check if Access Token is valid")
            );
        }
        return ResponseEntity.ok(gson.toJson(p));
    }
}
