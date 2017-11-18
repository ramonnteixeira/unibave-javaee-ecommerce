package net.unibave.ecommerce.aluno;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import net.unibave.ecommerce.base.jaxrs.CrudResource;

@Stateless
@Path("alunos")
public class AlunoResource extends CrudResource<Aluno, Long> {
}
