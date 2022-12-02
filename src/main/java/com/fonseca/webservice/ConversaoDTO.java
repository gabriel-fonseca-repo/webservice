package com.fonseca.webservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversaoDTO {

    private String moeda;

    private double valor;

    private double valorConvertido;

}
