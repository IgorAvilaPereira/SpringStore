
package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Venda;


import java.util.List;

import org.springframework.stereotype.Repository;


/**
 *
 * @author iapereira
 */
@Repository
public class VendaRepository implements IRepository<Venda> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM venda where id = ?";
        jdbcTemplate.update(sqlDelete, id);
    }

    @Override
    public void update(Venda t) {
        String sqlUpdate = "UPDATE venda SET data_hora = ? id = ?";
        jdbcTemplate.update(sqlUpdate, t.getDataHora(), t.getId());
    }

    @Override
    public List<Venda> list() {
        return jdbcTemplate.query("SELECT * from venda", BeanPropertyRowMapper.newInstance(Venda.class));
    }

    @Override
    public void save(Venda t) {
        String sqlInsert = "INSERT INTO venda (data_hora) VALUES (?)";
        jdbcTemplate.update(sqlInsert, t.getDataHora());
    }

    @Override
    public Venda load(int id) {
        String sqlSelect = "SELECT * FROM venda WHERE id = ?;";
        return jdbcTemplate.queryForObject(sqlSelect, BeanPropertyRowMapper.newInstance(Venda.class), id);
    }
}
