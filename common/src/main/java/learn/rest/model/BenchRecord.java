package learn.rest.model;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author octo
 */
public class BenchRecord {
    private String name;
    private BigDecimal value;
    private List<String> tags;

    public BenchRecord(String name, BigDecimal value, List<String> tags) {
        this.name = name;
        this.value = value;
        this.tags = tags;
    }

    public BenchRecord(String name, BigDecimal value, String ... tags) {
        this.name = name;
        this.value = value;
        this.tags = Lists.newArrayList(tags);
    }

    public BenchRecord(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public BenchRecord(String name, long value, List<String> tags) {
        this.name = name;
        this.value = new BigDecimal(value);
        this.tags = tags;
    }

    public BenchRecord(String name, long value, String ... tags) {
        this.name = name;
        this.value = new BigDecimal(value);
        this.tags = Lists.newArrayList(tags);
    }

    public BenchRecord(String name, long value) {
        this.name = name;
        this.value = new BigDecimal(value);
    }
    
    public BenchRecord() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
