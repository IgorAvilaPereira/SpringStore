/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author iapereira
 */
@Repository
public class ProdutoRepository implements IRepository<Produto> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM produto where id = ?";
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public void update(Produto t) {
        String sqlUpdate = "UPDATE produto SET descricao = ?, preco = ?, estoque = ? where id = ?";
        jdbcTemplate.update(sqlUpdate, t.getDescricao(), t.getPreco(), t.getEstoque(), t.getId());
    }

    @Override
    public List<Produto> list() {
        return jdbcTemplate.query("SELECT * from produto", BeanPropertyRowMapper.newInstance(Produto.class));
    }

    @Override
    public void save(Produto t) {
        String sqlInsert = "INSERT INTO produto (descricao, preco, estoque) VALUES (?,?,?)";
        jdbcTemplate.update(sqlInsert, t.getDescricao(), t.getPreco(), t.getEstoque());
    }

    @Override
    public Produto load(int id) {
        String sqlSelect = "SELECT * FROM produto WHERE id = ?;";
        return jdbcTemplate.queryForObject(sqlSelect, BeanPropertyRowMapper.newInstance(Produto.class), id);
    }
}
