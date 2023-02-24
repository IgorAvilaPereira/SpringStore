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
        String sqlUpdate = "UPDATE item SET quantidade = ? WHERE produto_id = ?  AND venda_id = ?";
        jdbcTemplate.update(sqlUpdate, t.getQuantidade(), t.getProduto().getId(), t.getVenda().getId());
    }

    @Override
    public List<Item> list() {
        return jdbcTemplate.query("SELECT * from item", BeanPropertyRowMapper.newInstance(Item.class));
    }

    @Override
    public void save(Item t) {
        String sqlInsert = "INSERT INTO item (produto_id, venda_id, quantidade) VALUES (?,?,?)";
        jdbcTemplate.update(sqlInsert, t.getProduto().getId(), t.getVenda().getId(), t.getQuantidade());
    }

    @Override
    public Item load(int id) {
        String sqlSelect = "SELECT * FROM item WHERE id = ?;";
        return jdbcTemplate.queryForObject(sqlSelect, BeanPropertyRowMapper.newInstance(Item.class), id);
    }
}
