package ar.com.travelpaq.hogarpresente.api.auth;

import ar.com.travelpaq.hogarpresente.api.models.entity.AlumnoEntity;
import ar.com.travelpaq.hogarpresente.api.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IAlumnoService alumnoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        AlumnoEntity alumnoEntity = alumnoService.findByCorreo(oAuth2Authentication.getName());
        Map<String, Object> info = new HashMap<>();

        info.put("info_adicional", "Hola que tal " + alumnoEntity.getNombre() + " " + alumnoEntity.getApellido() + "!");

        info.put("correo", alumnoEntity.getCorreo());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
    //hola
}
