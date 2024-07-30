package br.com.niquemoura.cep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cep")
public class BuscaCepController {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    @GetMapping("/{cep}")
    public String buscaCep(@PathVariable String cep) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = VIACEP_URL.replace("{cep}", cep);
        String response = restTemplate.getForObject(url, String.class);

        // Error handling (optional): Check for null or empty response
        if (response == null || response.isEmpty()) {
            throw new Exception("CEP not found");
        }

        return response;
    }
}
