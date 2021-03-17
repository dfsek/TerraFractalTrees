package com.dfsek.treeaddon;

import com.dfsek.terra.api.TerraPlugin;
import com.dfsek.terra.api.addons.TerraAddon;
import com.dfsek.terra.api.addons.annotations.Addon;
import com.dfsek.terra.api.addons.annotations.Author;
import com.dfsek.terra.api.addons.annotations.Version;
import com.dfsek.terra.api.event.EventListener;
import com.dfsek.terra.api.event.events.config.ConfigPackPreLoadEvent;
import com.dfsek.terra.api.injection.annotations.Inject;
import com.dfsek.terra.api.platform.world.Tree;
import com.dfsek.terra.api.registry.CheckedRegistry;
import com.dfsek.treeaddon.trees.FractalTree;
import com.dfsek.treeaddon.trees.trees.Cactus;
import com.dfsek.treeaddon.trees.trees.IceSpike;
import com.dfsek.treeaddon.trees.trees.OakTree;
import com.dfsek.treeaddon.trees.trees.ShatteredPillar;
import com.dfsek.treeaddon.trees.trees.ShatteredTree;
import com.dfsek.treeaddon.trees.trees.SmallShatteredPillar;
import com.dfsek.treeaddon.trees.trees.SmallShatteredTree;
import com.dfsek.treeaddon.trees.trees.SpruceTree;

import java.util.logging.Logger;

@Addon("FractalTrees")
@Author("dfsek")
@Version("1.0.0")
public class TreeAddon extends TerraAddon implements EventListener {
    @Inject
    private TerraPlugin main;

    @Inject
    private Logger logger;

    @Override
    public void initialize() {
        main.getEventManager().registerListener(this, this);
    }

    public void onPackLoad(ConfigPackPreLoadEvent event) {
        CheckedRegistry<Tree> registry = event.getPack().getTreeRegistry();
        tryAdd("GIANT_OAK", OakTree.class, registry);
        tryAdd("GIANT_SPRUCE", SpruceTree.class, registry);
        tryAdd("LARGE_SHATTERED_PILLAR", ShatteredPillar.class, registry);
        tryAdd("SHATTERED_LARGE", ShatteredTree.class, registry);
        tryAdd("SHATTERED_SMALL", SmallShatteredTree.class, registry);
        tryAdd("SMALL_SHATTERED_PILLAR", SmallShatteredPillar.class, registry);
        tryAdd("ICE_SPIKE", IceSpike.class, registry);
    }

    private void tryAdd(String id, Class<? extends FractalTree> value, CheckedRegistry<Tree> registry) {
        try {
            registry.add(id, new FractalTreeHolder(value, main));
        } catch(Exception e) {
            logger.warning("Unable to load tree " + id + ": " + e.getMessage());
        }
    }
}
