package com.dfsek.treeaddon.trees.trees;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.math.vector.Location;
import com.dfsek.terra.api.platform.block.BlockData;
import com.dfsek.terra.api.util.collections.MaterialSet;
import com.dfsek.treeaddon.trees.FractalTree;

import java.util.Random;

public class SmallShatteredPillar extends FractalTree {

    @Override
    public MaterialSet getSpawnable() {
        return MaterialSet.get(main.getWorldHandle().createBlockData("minecraft:end_stone"));
    }

    /**
     * Instantiates a TreeGrower at an origin location.
     */
    public SmallShatteredPillar(TerraPlugin main) {
        super(main);
    }

    /**
     * Grows the tree in memory. Intended to be invoked from an async thread.
     * @param origin
     * @param random
     */
    @Override
    public void grow(Location origin, Random random) {
        int h = random.nextInt(5) + 5;
        BlockData obsidian = getMain().getWorldHandle().createBlockData("minecraft:obsidian");
        for(int i = -h; i < h; i++) setBlock(origin.clone().add(0, i, 0), obsidian);
    }
}
