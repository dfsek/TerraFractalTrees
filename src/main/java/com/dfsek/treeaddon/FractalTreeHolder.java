package com.dfsek.treeaddon;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.math.vector.Location;
import com.dfsek.terra.api.platform.block.BlockFace;
import com.dfsek.terra.api.platform.world.Tree;
import com.dfsek.terra.api.util.collections.MaterialSet;
import com.dfsek.treeaddon.trees.FractalTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class FractalTreeHolder implements Tree {
    private final FractalTree tree;
    private final TerraPlugin main;

    public FractalTreeHolder(Class<? extends FractalTree> clazz, TerraPlugin main) throws NoSuchMethodException {
        this.main = main;
        Constructor<? extends FractalTree> constructor = clazz.getConstructor(TerraPlugin.class);
        try {
            tree = constructor.newInstance(this.main);
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unable to load tree: " + clazz);
        }
    }

    @Override
    public boolean plant(Location l, Random r) {
        if(!getSpawnable().contains(l.getBlock().getType())) return false;
        if(!l.getBlock().getRelative(BlockFace.UP).isEmpty()) return false;
        tree.grow(l.add(0, 1, 0), r);
        return true;
    }

    @Override
    public MaterialSet getSpawnable() {
        return tree.getSpawnable();
    }
}
