/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.VendaRepository;

/**
 *
 * @author iapereira
 */
@Controller
@RequestMapping(value = "/vendas")
@ComponentScan("repository.")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping("/tela_adicionar")
    public ModelAndView tela_adicionar() {
        return new ModelAndView("vendas/tela_adicionar");
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(Venda produto) {
        this.vendaRepository.save(produto);
        return this.listar();
    }

    @GetMapping({"/", "/listar"})
    public ModelAndView listar() {
        Map<String, Object> template = new HashMap();
        template.put("vetVenda", this.vendaRepository.list());
        return new ModelAndView("vendas/listar", template);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") int id) {
        this.vendaRepository.delete(id);
        return this.listar();
    }

    @GetMapping("/tela_editar/{id}")
    public ModelAndView tela_editar(@PathVariable("id") int id) {
        Map<String, Object> template = new HashMap();
        template.put("produto", this.vendaRepository.load(id));
        return new ModelAndView("vendas/tela_editar", template);
    }

    @PostMapping("/editar")
    public ModelAndView editar(Venda produto) {
        this.vendaRepository.update(produto);
        return this.listar();
    }
}
