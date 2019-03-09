package com.scrap.avatar.api.external.swapi.dto;

import java.util.List;

/**
 * PlanetResultDTO.
 *
 * @author Luis Alonso Ballena Garcia
 */

public class PlanetResultDTO {

    private String next;
    private List<PlanetDTO> results;

    public PlanetResultDTO() {
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<PlanetDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetDTO> results) {
        this.results = results;
    }
}
