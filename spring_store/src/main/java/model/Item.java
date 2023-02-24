/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setQuantidade(rs.getDouble("quantidade"));
        Produto produto = new Produto();
        produto.setId(rs.getInt("produto_id"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setEstoque(rs.getDouble("estoque"));
        item.setProduto(produto);
        Venda venda = new Venda();
        venda.setId(rs.getInt("venda_id"));
//        venda.setDataHora(rs.getD("data_hora"));
        item.setVenda(venda);
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
