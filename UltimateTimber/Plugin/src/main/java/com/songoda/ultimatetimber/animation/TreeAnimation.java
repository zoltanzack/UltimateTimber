package com.songoda.ultimatetimber.animation;

import com.songoda.ultimatetimber.UltimateTimber;
import com.songoda.ultimatetimber.manager.ConfigurationManager;
import com.songoda.ultimatetimber.manager.SaplingManager;
import com.songoda.ultimatetimber.tree.DetectedTree;
import com.songoda.ultimatetimber.tree.TreeBlockSet;
import com.songoda.ultimatetimber.tree.TreeDefinition;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class TreeAnimation {

    protected final TreeAnimationType treeAnimationType;
    protected final DetectedTree detectedTree;
    protected final Player player;
    protected final boolean hasSilkTouch;

    TreeAnimation(TreeAnimationType treeAnimationType, DetectedTree detectedTree, Player player) {
        this.treeAnimationType = treeAnimationType;
        this.detectedTree = detectedTree;
        this.player = player;

        ItemStack itemInHand = UltimateTimber.getInstance().getVersionAdapter().getItemInHand(player);
        this.hasSilkTouch = itemInHand != null && itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH);
    }

    /**
     * Plays this tree topple animation
     *
     * @param whenFinished The runnable to run when the animation is done
     */
    public abstract void playAnimation(Runnable whenFinished);

    /**
     * Replaces a given block with a new one
     *
     * @param block The block to replace
     */
    protected void replaceBlock(Block block) {
        block.setType(Material.AIR);
        UltimateTimber.getInstance().getSaplingManager().replantSapling(this.detectedTree.getTreeDefinition(), block.getLocation());
    }

}
