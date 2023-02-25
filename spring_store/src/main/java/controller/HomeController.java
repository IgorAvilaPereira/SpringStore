/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.VendaRepository;

/**
 *
 * @author iapereira
 */
@Controller
@ComponentScan("repository.")
public class HomeController {

    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping("/")
    public ModelAndView index() {
        Map<String, Object> template = new HashMap();
//      pendente => mostrar a tb a hora da venda (pedido). No BD eh gravado mas aqui nao esta aparecendo.
        template.put("vetVenda", this.vendaRepository.list());
        return new ModelAndView("index", template);
    }
}
