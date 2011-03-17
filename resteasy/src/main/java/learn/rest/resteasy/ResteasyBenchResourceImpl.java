package learn.rest.resteasy;

import learn.rest.api.BenchResource;
import learn.rest.dao.BenchRecordDAO;
import learn.rest.model.BenchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author octo
 */
@Component
public class ResteasyBenchResourceImpl implements BenchResource {

    @Autowired
    private BenchRecordDAO dao;

    @Override
    public List<BenchRecord> listAll() {
        return dao.listRecords();
    }

    @Override
    public List<BenchRecord> list(Filter filter) {
        return dao.findRecords(filter);
    }

    @Override
    public BenchRecord getRecord(String name) {
        return dao.getByName(name);
    }
}
