package repository;

import java.util.List;

/**
 *
 * @author iapereira
 * @param <Model>
 */
public interface IRepository<Model> {
    public Model load(int id);
    public void delete(int id);
    public void update(Model t);
    public void save(Model t);
    public List<Model> list();    
}