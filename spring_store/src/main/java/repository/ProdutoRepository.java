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

    public List<Produto> list(int venda_id) {
        return jdbcTemplate.query("SELECT * from produto where produto.id not in (SELECT produto.id from produto inner join item on (produto.id = item.produto_id) where item.venda_id = ?)", BeanPropertyRowMapper.newInstance(Produto.class), venda_id);
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
