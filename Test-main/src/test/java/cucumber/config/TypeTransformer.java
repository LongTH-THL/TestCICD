package cucumber.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;

import java.util.Map;

public class TypeTransformer implements TableEntryByTypeTransformer {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T transform(Map<String, String> map, Class<T> aClass, TableCellByTypeTransformer typeTransformer) {
        return objectMapper.convertValue(map, aClass);
    }
}
