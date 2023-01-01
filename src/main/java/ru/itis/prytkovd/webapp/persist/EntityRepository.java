package ru.itis.prytkovd.webapp.persist;

import ru.itis.prytkovd.persist.ColumnInfo;
import ru.itis.prytkovd.persist.Persistor;
import ru.itis.prytkovd.persist.TableInfo;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

class EntityRepository<Entity, Id> {
    protected final Persistor persistor;
    protected final Class<Entity> entityType;
    protected final Supplier<Entity> entitySupplier;
    protected final TableInfo table;
    protected final ColumnInfo primaryKey;

    private final String ID_EQUALS;

    public EntityRepository(Persistor persistor, Class<Entity> entityType, Supplier<Entity> entitySupplier) {
        this.persistor = persistor;
        this.entityType = entityType;
        this.entitySupplier = entitySupplier;
        table = persistor.getTableInfo(entityType);
        primaryKey = table.getPrimaryKey();
        ID_EQUALS = primaryKey.getName() + " = ?";
    }

    public List<Entity> findAll() {
        return persistor.select(entityType)
            .queryForList(entitySupplier);
    }

    public Optional<Entity> findById(Id id) {
        return persistor.select(entityType)
            .where(ID_EQUALS, id)
            .queryForObject(entitySupplier);
    }

    public void save(Entity... entities) {
        persistor.insert(entityType)
            .updateOnConflict()
            .entities(entities)
            .execute();
    }

    public void delete(Entity... entities) {
        for (Entity entity : entities) {
            persistor.delete(entityType)
                .entity(entity)
                .execute();
        }
    }

    @SuppressWarnings("unchecked")
    public void deleteAll() {
        delete((Entity[])findAll().toArray());
    }

    public void deleteById(Id id) {
        persistor.delete(entityType)
            .where(ID_EQUALS, id)
            .execute();
    }
}
