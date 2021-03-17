package com.dfsek.treeaddon.trees;

import com.dfsek.terra.api.math.vector.Location;
import com.dfsek.terra.api.platform.entity.Entity;

import java.util.function.Consumer;


public class EntitySpawnHolder {
    private final Location l;
    private final Class<? extends Entity> e;
    private final Consumer<Entity> c;

    public EntitySpawnHolder(Location l, Class<? extends Entity> e, Consumer<Entity> c) {
        this.l = l;
        this.e = e;
        this.c = c;
    }

    @SuppressWarnings("rawtypes")
    public Class getEntity() {
        return e;
    }

    public Consumer<Entity> getConsumer() {
        return c;
    }

    public Location getLocation() {
        return l;
    }
}
