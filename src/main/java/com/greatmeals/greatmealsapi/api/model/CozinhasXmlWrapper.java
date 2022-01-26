package com.greatmeals.greatmealsapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {

    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cozinha> cozinhas;

    public CozinhasXmlWrapper(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }

    public List<Cozinha> getCozinhas() {
        return cozinhas;
    }

    public void setCozinhas(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }
}
