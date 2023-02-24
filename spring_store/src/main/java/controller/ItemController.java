/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.ItemRepository;
import repository.ProdutoRepository;

/**
 *
 * @author iapereira
 */
@Controller
@RequestMapping(value = "/itens")
@ComponentScan("repository.")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping( "/tela_adicionar/{venda_id}")
    public ModelAndView tela_adicionar(@PathVariable("venda_id") int venda_id) {
        Map<String, Object> template = new HashMap();
        template.put("venda_id", venda_id);
        template.put("vetProduto", this.produtoRepository.list(venda_id));
        return new ModelAndView("itens/tela_adicionar", template);        
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(Item item) {
        this.itemRepository.save(item);
        return this.listar(item.getVenda().getId());
    }

    @GetMapping({"/{venda_id}", "/listar/{venda_id}"})
    public ModelAndView listar(@PathVariable("venda_id") int venda_id) {
        Map<String, Object> template = new HashMap();
        template.put("venda_id", venda_id);
        template.put("vetItem", this.itemRepository.list(venda_id));
        return new ModelAndView("itens/listar", template);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") int id) {
        Item item = this.itemRepository.load(id);
        this.itemRepository.delete(id);
        return this.listar(item.getVenda().getId());
    }

    @GetMapping("/tela_editar/{id}")
    public ModelAndView tela_editar(@PathVariable("id") int id) {
        Map<String, Object> template = new HashMap();
        template.put("item", this.itemRepository.load(id));
        return new ModelAndView("itens/tela_editar", template);
    }

    @PostMapping("/editar")
    public ModelAndView editar(Item item) {
        this.itemRepository.update(item);
        return this.listar(item.getVenda().getId());
    }
}
