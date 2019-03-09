package com.scrap.avatar.api.external.swapi;

import com.scrap.avatar.api.external.swapi.dto.PlanetResultDTO;
import com.scrap.avatar.exception.AvatarException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * PlanetApi.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Component
public class PlanetApi {

    private Logger logger = LoggerFactory.getLogger(PlanetApi.class);
    private String userAgent = "headers.add(\"user-agent\", \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36\");";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.external.swapi.url}")
    private String url;

    public boolean evaluatePlanetName(String name) {
        Boolean exist = true;
        String next = new String(this.url);
        do {
            PlanetResultDTO planetResultDTO = findAll(next);
            next = planetResultDTO.getNext();
            exist = planetResultDTO.getResults().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().isPresent();
        } while (!exist && !StringUtils.isEmpty(next));
        return exist;
    }


    public PlanetResultDTO findAll(String url) {
        logger.debug("PlanetApi-findAll() -> url : {}", url);
        PlanetResultDTO planetResultDTO = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            //al consultar por el navegador no muestra la data normalmente, se transforma.
            headers.add("user-agent", userAgent);
            HttpEntity<String> entity = new HttpEntity("parameters", headers);
            ResponseEntity<PlanetResultDTO> response =
                    restTemplate.exchange(url, HttpMethod.GET, entity, PlanetResultDTO.class);
            planetResultDTO = response.getBody();
        } catch (HttpClientErrorException e) {
            logger.error("Ocurrio un error al consumir el servicio externo : {}", e.getMessage(), e);
            throw new AvatarException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrio un error al consumir el servicio externo");
        }
        return planetResultDTO;
    }

}
