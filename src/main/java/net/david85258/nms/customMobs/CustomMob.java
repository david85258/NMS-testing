package net.david85258.nms.customMobs;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Zombie;

public class CustomMob {

    public CustomMob(Location loc){
        EntityZombie entityZombie = new EntityZombie(EntityTypes.bj, ((CraftWorld) loc.getWorld()).getHandle());
        Zombie craftZombie = (org.bukkit.entity.Zombie) entityZombie.getBukkitEntity();
        craftZombie.teleport(loc);


        craftZombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);

        craftZombie.setHealth(100);
        craftZombie.setCustomName(ChatColor.RED+"A");
        craftZombie.setCustomNameVisible(true);

        entityZombie.bS.d().forEach(wrappedGoal -> entityZombie.bS.a(wrappedGoal));
        entityZombie.bS.a(0, new PathfinderGoalNearestAttackableTarget<>(entityZombie, EntityZombie.class, true));


        entityZombie.s.b(entityZombie);
    }
}
