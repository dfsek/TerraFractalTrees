package com.dfsek.treeaddon.trees;

import com.dfsek.terra.api.math.vector.Location;
import com.dfsek.terra.api.math.vector.Vector3;
import com.dfsek.terra.api.platform.block.BlockData;
import com.dfsek.terra.api.util.collections.ProbabilityCollection;

import java.util.Random;

public class TreeGeometry {
    private final FractalTree tree;

    public TreeGeometry(FractalTree tree) {
        this.tree = tree;
    }

    public static Vector3 getPerpendicular(Vector3 v) {
        return v.getZ() < v.getX() ? new Vector3(v.getY(), -v.getX(), 0) : new Vector3(0, -v.getZ(), v.getY());
    }

    public FractalTree getTree() {
        return tree;
    }

    public void generateSphere(Location l, BlockData m, int radius, boolean overwrite, Random r) {
        generateSphere(l, new ProbabilityCollection<BlockData>().add(m, 1), radius, overwrite, r);
    }

    public void generateCylinder(Location l, BlockData m, int radius, int height, boolean overwrite, Random r) {
        generateCylinder(l, new ProbabilityCollection<BlockData>().add(m, 1), radius, height, overwrite, r);
    }

    public void generateSphere(Location l, ProbabilityCollection<BlockData> m, int radius, boolean overwrite, Random r) {
        for(int x = -radius; x <= radius; x++) {
            for(int y = -radius; y <= radius; y++) {
                for(int z = -radius; z <= radius; z++) {
                    Vector3 position = l.toVector().clone().add(new Vector3(x, y, z));
                    if(l.toVector().distance(position) <= radius + 0.5 && (overwrite || position.toLocation(l.getWorld()).getBlock().isEmpty()))
                        tree.setBlock(position.toLocation(l.getWorld()), m.get(r));
                }
            }
        }
    }

    public void generateSponge(Location l, ProbabilityCollection<BlockData> m, int radius, boolean overwrite, int sponginess, Random r) {
        for(int x = -radius; x <= radius; x++) {
            for(int y = -radius; y <= radius; y++) {
                for(int z = -radius; z <= radius; z++) {
                    Vector3 position = l.toVector().clone().add(new Vector3(x, y, z));
                    if(r.nextInt(100) < sponginess && l.toVector().distance(position) <= radius + 0.5 && (overwrite || position.toLocation(l.getWorld()).getBlock().isEmpty()))
                        tree.setBlock(position.toLocation(l.getWorld()), m.get(r));
                }
            }
        }
    }

    public void generateCylinder(Location l, ProbabilityCollection<BlockData> m, int radius, int height, boolean overwrite, Random r) {
        for(int x = -radius; x <= radius; x++) {
            for(int y = 0; y <= height; y++) {
                for(int z = -radius; z <= radius; z++) {
                    Vector3 position = l.toVector().clone().add(new Vector3(x, 0, z));
                    if(l.toVector().distance(position) <= radius + 0.5 && (overwrite || position.toLocation(l.getWorld()).getBlock().isEmpty()))
                        tree.setBlock(position.toLocation(l.getWorld()), m.get(r));
                }
            }
        }
    }
}
