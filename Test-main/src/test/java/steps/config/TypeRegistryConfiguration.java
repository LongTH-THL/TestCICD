package steps.config;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import cucumber.models.web.SearchVendor;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        TypeTransformer transformer = new TypeTransformer();
        typeRegistry.setDefaultDataTableEntryTransformer(transformer);
        typeRegistry.defineDataTableType(DataTableType.entry(SearchVendor.class));
    }
}
