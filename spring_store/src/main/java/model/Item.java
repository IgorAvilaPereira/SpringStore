/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author iapereira
 */
public class Item implements RowMapper<Item> {

    private int id;
    private double quantidade;
    private Produto produto;
    private Venda venda;

//    de repente deslocar isso para o ItemRepository (como metodo, como uma classe privada/interna ou etc.)
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setQuantidade(rs.getDouble("quantidade"));
        Produto itemProduto = new Produto();
        itemProduto.setId(rs.getInt("produto_id"));
        itemProduto.setDescricao(rs.getString("descricao"));
        itemProduto.setEstoque(rs.getDouble("estoque"));
        itemProduto.setPreco(rs.getDouble("preco"));
        item.setProduto(itemProduto);
        Venda itemVenda = new Venda();
        itemVenda.setId(rs.getInt("venda_id"));
        itemVenda.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime().toLocalDate());
        item.setVenda(itemVenda);
        return item;
    }

    public Item() {
        this.produto = new Produto();
        this.venda = new Venda();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", quantidade=" + quantidade + ", produto=" + produto + ", venda=" + venda + '}';
    }

}
