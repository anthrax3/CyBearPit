package is.stma.judgebean.data;

import is.stma.judgebean.model.Resource;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Repository(forEntity = Resource.class)
public interface ResourceRepo extends AbstractRepo<Resource> {

    @Query("SELECT r FROM Resource r WHERE r.contest_id IS NULL")
    List<Resource> findUnassigned();

}
