package learn.rest.api;

import learn.rest.model.BenchRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author octo
 */
@Path("/bench")
public interface BenchResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<BenchRecord> listAll();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<BenchRecord> list(Filter filter);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    BenchRecord getRecord(@PathParam("name") String name);

    public static class MinMax<T extends Comparable<T>> {
        private T min;
        private T max;

        public MinMax() {
        }

        public MinMax(T min, T max) {
            this.min = min;
            this.max = max;
        }

        public boolean inRange(T value) {
            if (min != null && value.compareTo(min) < 0)
                return false;
            if (max != null && value.compareTo(max) > 0)
                return false;
            return true;
        }
    }

    public static class Filter {
        private String nameRegex;
        private MinMax<BigDecimal> valueFilter;
        private List<String> tags;

        public Filter() {
        }

        public String getNameRegex() {
            return nameRegex;
        }

        public void setNameRegex(String nameRegex) {
            this.nameRegex = nameRegex;
        }

        public MinMax<BigDecimal> getValueFilter() {
            return valueFilter;
        }

        public void setValueFilter(MinMax<BigDecimal> valueFilter) {
            this.valueFilter = valueFilter;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }

}
