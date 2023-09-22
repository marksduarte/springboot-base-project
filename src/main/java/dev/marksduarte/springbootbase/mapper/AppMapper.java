package dev.marksduarte.springbootbase.mapper;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Objects;

public abstract class AppMapper extends ModelMapper {

    protected AppMapper() {
        super();
        configPersistentCollections();
        configConverters();
    }

    public <S, T> T map(S source, Class<T> target, PropertyMap<S, T> propertyMap) {
        addMappings(propertyMap);
        return Objects.nonNull(source) ? map(source, target) : null;
    }

    public <S, T> Collection<T> mapCollection(Collection<S> list, Class<T> target) {
        return list.stream()
                .map(source -> this.map(source, target))
                .toList();
    }

    public <S, T> Page<T> mapPage(Page<S> page, Class<T> target) {
        return page.map(source -> this.map(source, target));
    }

    private void configPersistentCollections() {
        getConfiguration().setPropertyCondition(context ->
                !(context.getSource() instanceof PersistentCollection<?> source)
                        || source.wasInitialized());
    }

    protected void configConverters() {
        // pode ou não ser implementada.
    }

}
