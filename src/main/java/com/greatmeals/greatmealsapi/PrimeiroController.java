package com.greatmeals.greatmealsapi;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import com.greatmeals.greatmealsapi.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public PrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente joao = new Cliente("João", "joao@email.com", "5511118888");
        ativacaoClienteService.ativar(joao);
        return "Hello!";
    }
}
