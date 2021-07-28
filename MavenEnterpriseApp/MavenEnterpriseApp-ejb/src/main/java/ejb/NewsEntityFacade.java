package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hamfree
 */
@Stateless
public class NewsEntityFacade extends AbstractFacade<NewsEntity> implements NewsEntityFacadeLocal {
    @PersistenceContext(unitName = "name.ruiz.juanfco_MavenEnterpriseApp-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsEntityFacade() {
        super(NewsEntity.class);
    }
    
}
