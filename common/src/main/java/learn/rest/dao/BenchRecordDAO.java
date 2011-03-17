package learn.rest.dao;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import learn.rest.api.BenchResource;
import learn.rest.model.BenchRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author octo
 */
@Component
public class BenchRecordDAO {
    private static List<BenchRecord> records = Lists.newArrayList(
            new BenchRecord("name1", 100500, "tag1", "tag2"),
            new BenchRecord("name2", 100100, "tag1", "tag2"),
            new BenchRecord("name3", 500, "tag1"),
            new BenchRecord("name4", 100, "tag2", "tag3"),
            new BenchRecord("name5", 9, "tag1", "tag3"),
            new BenchRecord("name6", 9000, "tag3")
    );

    private static Multimap<String, String> tagIndex;
    private static Map<String, BenchRecord> nameIndex;

    {
        tagIndex = HashMultimap.create();
        for (BenchRecord record : records) {
            final String name = record.getName();
            for (String tag : record.getTags()) {
                tagIndex.put(tag, name);
            }
        }
        nameIndex = Maps.uniqueIndex(records, new Function<BenchRecord, String>() {
            @Override
            public String apply(BenchRecord from) {
                return from.getName();
            }
        });
    }

    public List<BenchRecord> listRecords() {
        return Lists.newArrayList(records);
    }

    public List<BenchRecord> findRecords(BenchResource.Filter filter) {
        return Lists.newArrayList(Iterables.filter(records, new FilterPredicate(filter)));

    }

    public BenchRecord getByName(String name) {
        return nameIndex.get(name);
    }

    static class FilterPredicate implements Predicate<BenchRecord> {
        final Pattern pattern;
        final BenchResource.Filter filter;

        FilterPredicate(BenchResource.Filter filter) {
            this.filter = filter;
            if (filter.getNameRegex() != null)
                pattern = Pattern.compile(filter.getNameRegex());
            else
                pattern = null;
        }

        @Override
        public boolean apply(BenchRecord benchRecord) {
            if (filter.getValueFilter() != null && benchRecord.getValue() != null && !filter.getValueFilter().inRange(benchRecord.getValue()))
                return false;
            if (pattern != null && !pattern.matcher(benchRecord.getName()).matches())
                return false;
            // octo47: inefficient, but it is toy project
            if (filter.getTags() != null) {
                for (String tag : filter.getTags()) {
                    if (tagIndex.containsEntry(tag, benchRecord.getName()))
                        return true;
                }
            } else {
                return true;
            }
            return false;
        }
    }

}
