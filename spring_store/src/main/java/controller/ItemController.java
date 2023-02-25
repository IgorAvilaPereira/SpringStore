/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Item;
import model.Produto;
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

    @GetMapping("/tela_adicionar/{venda_id}")
    public ModelAndView tela_adicionar(@PathVariable("venda_id") int venda_id) {
        Map<String, Object> template = new HashMap();
        List<Produto> vetProduto = this.produtoRepository.list(venda_id);
//         pendente => verificar se todos os produtos ja foram anteriormente adicionados ao pedido de venda
//        if (!vetProduto.isEmpty()) {
        template.put("venda_id", venda_id);
        template.put("vetProduto", vetProduto);
        return new ModelAndView("itens/tela_adicionar", template);
//        } else {
//            return "There are no more products to add";
//        }
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(Item item) {
        this.itemRepository.save(item);
        return new ModelAndView("redirect:/itens/listar/" + item.getVenda().getId());
    }

    @GetMapping({"/{venda_id}", "/listar/{venda_id}"})
    public ModelAndView listar(@PathVariable("venda_id") int venda_id) {
        Map<String, Object> template = new HashMap();
        template.put("venda_id", venda_id);
        List<Item> vetItem = this.itemRepository.list(venda_id);
//      pendente => colocar o calculo do total ou na classe de model, ou na propria consulta sql (no repositorio)
        double total = 0;
        for (int i = 0; i < vetItem.size(); i++) {
            Item item = vetItem.get(i);
            total += item.getQuantidade() * item.getProduto().getPreco();
        }
        template.put("vetItem", vetItem);
        template.put("total", total);
        return new ModelAndView("itens/listar", template);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") int id) {
        Item item = this.itemRepository.load(id);
        int venda_id = item.getVenda().getId();
        this.itemRepository.delete(id);
        return new ModelAndView("redirect:/itens/listar/" + venda_id);
    }

    @GetMapping("/tela_editar/{id}")
    public ModelAndView tela_editar(@PathVariable("id") int id) {
        Map<String, Object> template = new HashMap();
        Item item = this.itemRepository.load(id);
        template.put("item_id", item.getId());
        template.put("produto_descricao", item.getProduto().getDescricao());
        template.put("quantidade", item.getQuantidade());
        return new ModelAndView("itens/tela_editar", template);
    }

    @PostMapping("/editar")
    public ModelAndView editar(int item_id, double quantidade) {
        Item item = this.itemRepository.load(item_id);
        item.setQuantidade(quantidade);
        this.itemRepository.update(item);
        return new ModelAndView("redirect:/itens/listar/" + item.getVenda().getId());
    }
}
