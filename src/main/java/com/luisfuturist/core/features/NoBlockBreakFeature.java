package com.luisfuturist.core.features;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import com.luisfuturist.core.models.Feature;

import lombok.Getter;
import lombok.Setter;

public class NoBlockBreakFeature extends Feature {

    @Getter @Setter
    private Set<Material> whitelist = new HashSet<Material>();

    public void whitelistMaterial(@Nonnull Material material) {
        whitelist.add(material);
    }

    public void whitelistMaterials(@Nonnull Material... materials) {
        for(var material : materials) {
            whitelist.add(material);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!isPlaying(event.getPlayer())) {
            return;
        }

        if(!whitelist.contains(event.getBlock().getType())) {
            event.setCancelled(true);
        }
    }
}
