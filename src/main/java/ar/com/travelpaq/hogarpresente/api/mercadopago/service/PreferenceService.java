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
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferenceService {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private ICursoRepository cursoRepository;


    public String create(
    //        NewPreferenceDto preferenceDTO
    ) throws MPException {
//        if (StringUtils.isEmpty(preferenceDTO.getAccessToken())) {
//            return ResponseEntity.badRequest().body("Access token is mandatory");
//        }
//        if (preferenceDTO.getCursosId().isEmpty()) {
//            return ResponseEntity.badRequest().body("Items empty");
//        }

        MercadoPago.SDK.setAccessToken("TEST-3319053569301118-081923-3c1a0eff6d205cf50cd2a2ab5fba3890-137809924");
//        String notificationUrl = "http://localhost:8080/generic";

        Preference p = new Preference();
        p.setBackUrls(
                new BackUrls()
                        .setSuccess("http://localhost:8080/succes")
                        .setPending("http://localhost:8080/pending")
                        .setFailure("http://localhost:8080/failure")
        );

//        List<CursoEntity> cursos = new ArrayList<>();
//        for (long cursoId : preferenceDTO.getCursosId()){
//            CursoEntity cursoEntity = cursoRepository.getOne(cursoId);
//            cursos.add(cursoEntity);
//        }

        Item item = new Item();
        item.setTitle("test item")
                .setQuantity(1)
                .setUnitPrice((float) 12);
        p.appendItem(item);

//        p.setItems(cursos.stream()
//                .map(i -> {
//                    Item item = new Item();
//                    item.setUnitPrice(i.getPrecio());
//                    item.setTitle(i.getNombre());
//                    item.setDescription(i.getDescripcion());
//                    item.setId(String.valueOf(i.getId()));
//                    item.setPictureUrl(i.getImagen());
//                    item.setQuantity(1);
//                    return item;
//                })
//                .collect(Collectors.toCollection(ArrayList::new)));
        var result =p.save();

//        if (StringUtils.isEmpty(p.getId())) {
//            return ResponseEntity.status(404).body(
//                    Collections.singletonMap("Message",
//                            "Preference was not created. Check if Access Token is valid")
//            );
//        }

        return "redirect:" + result.getSandboxInitPoint();
    }
}
