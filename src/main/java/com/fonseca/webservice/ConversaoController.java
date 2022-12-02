package com.fonseca.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin
public class ConversaoController {

    private static HashMap<String, Double> valoresConversao = new HashMap<>();

    static {
        valoresConversao.put("USD", 5.43);
        valoresConversao.put("EUR", 3.46);
    }

    @RequestMapping(
            path = "/converter",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.TEXT_PLAIN_VALUE
            })
    public ResponseEntity<Object> converter(ConversaoDTO conversaoDTO) {
        System.out.println("Requisição recebida!");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Convertendo valor...");
        double valorConvertido = valoresConversao.get(conversaoDTO.getMoeda()) * conversaoDTO.getValor();
        conversaoDTO.setValorConvertido(valorConvertido);
        try {
            String resposta = objectMapper.writeValueAsString(conversaoDTO);
            System.out.println("Respondendo a requisição!");
            System.out.println("Resposta: ");
            System.out.println(resposta);
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (JsonProcessingException jsonPE) {
            return new ResponseEntity<>("Erro!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
