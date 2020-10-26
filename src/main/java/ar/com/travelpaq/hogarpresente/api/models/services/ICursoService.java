package ar.com.travelpaq.hogarpresente.api.models.services;
import ar.com.travelpaq.hogarpresente.api.models.domain.Curso;
import java.util.List;

public interface ICursoService {

    public List<Curso> findAll();

    public Curso findByNombre(String nombre);

    public Curso create(Curso curso);

    public Curso update(Curso curso, long id);

    public void delete(long id);
}
