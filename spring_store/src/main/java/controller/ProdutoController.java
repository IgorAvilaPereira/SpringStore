/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.ProdutoRepository;

/**
 *
 * @author iapereira
 */
@Controller
@RequestMapping(value = "/produtos")
@ComponentScan("repository.")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/tela_adicionar")
    public ModelAndView tela_adicionar() {
        return new ModelAndView("produtos/tela_adicionar");
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(Produto produto) {
        this.produtoRepository.save(produto);
        return this.listar();
    }

    @GetMapping({"/", "/listar"})
    public ModelAndView listar() {
        Map<String, Object> template = new HashMap();
        template.put("vetProduto", this.produtoRepository.list());
        return new ModelAndView("produtos/listar", template);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") int id) {
        this.produtoRepository.delete(id);
        return this.listar();
    }

    @GetMapping("/tela_editar/{id}")
    public ModelAndView tela_editar(@PathVariable("id") int id) {
        Map<String, Object> template = new HashMap();
        template.put("produto", this.produtoRepository.load(id));
        return new ModelAndView("produtos/tela_editar", template);
    }

    @PostMapping("/editar")
    public ModelAndView editar(Produto produto) {
        this.produtoRepository.update(produto);
        return this.listar();
    }
}
