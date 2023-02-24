/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author iapereira
 */
@Repository
public class ItemRepository implements IRepository<Item> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM item where id = ?";
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public void update(Item t) {
        String sqlUpdate = "UPDATE item SET quantidade = ? WHERE id = ?";
        jdbcTemplate.update(sqlUpdate, t.getQuantidade(), t.getId());
    }

    @Override
    public List<Item> list() {
        String sqlSelectByVenda = "SELECT item.id as id, item.quantidade as quantidade, item.produto_id, produto.descricao, produto.estoque, item.venda_id, venda.data_hora from item inner join produto on (item.produto_id = produto.id) inner join venda on (venda.id = item.venda_id) order by venda.data desc;";
        return jdbcTemplate.query(sqlSelectByVenda, new Item());                
    }    
    
    public List<Item> list(int venda_id) {
        String sqlSelectByVenda = "SELECT item.id as id, item.quantidade as quantidade, item.produto_id, produto.descricao, produto.estoque, item.venda_id, venda.data_hora from item inner join produto on (item.produto_id = produto.id) inner join venda on (venda.id = item.venda_id) WHERE item.venda_id = ?";
        return jdbcTemplate.query(sqlSelectByVenda, new Item(), venda_id);        
    }

    @Override
    public void save(Item t) {
        String sqlInsert = "INSERT INTO item (produto_id, venda_id, quantidade) VALUES (?,?,?)";
        jdbcTemplate.update(sqlInsert, t.getProduto().getId(), t.getVenda().getId(), t.getQuantidade());
    }

    @Override
    public Item load(int id) {
        String sqlSelectByVenda = "SELECT item.id as id, item.quantidade as quantidade, item.produto_id, produto.descricao, produto.estoque, item.venda_id, venda.data_hora from item inner join produto on (item.produto_id = produto.id) inner join venda on (venda.id = item.venda_id) WHERE item.id = ?";
        return jdbcTemplate.queryForObject(sqlSelectByVenda, new Item(), id);        
    }
}
