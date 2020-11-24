package ar.com.travelpaq.hogarpresente.api.mercadopago.controller;

import ar.com.travelpaq.hogarpresente.api.mercadopago.dto.NewPreferenceDto;
import ar.com.travelpaq.hogarpresente.api.mercadopago.service.PreferenceService;
import com.mercadopago.exceptions.MPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MercadoPagoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PreferenceService preferenceService;

    @PostMapping("/create")
    public ResponseEntity createPreference(
            @RequestBody NewPreferenceDto preferenceDTO
    ) throws MPException {
        return preferenceService.create(preferenceDTO);
    }
}
