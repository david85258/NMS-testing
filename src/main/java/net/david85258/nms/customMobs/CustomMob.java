package net.david85258.nms.customMobs;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class CustomMob extends Monster {

    public CustomMob(Location loc){
        super(EntityType.ZOMBIE, ((CraftWorld) loc.getWorld()).getHandle());

        //Un Zombie facil de modificar de toda la vida de bukkit
        org.bukkit.entity.Monster craftZombie = (org.bukkit.entity.Monster) this.getBukkitEntity();


        this.teleportTo(loc.getX(), loc.getY(), loc.getZ());

        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100);

        this.setHealth(100);
        this.setCustomName(Component.literal(ChatColor.RED+"A"));
        this.setCustomNameVisible(true);


        //Añade que ataque a su misma especie.
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, CustomMob.class, 8.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, CustomMob.class, true));

        //Añade la entidad al mundo(level)
        this.level.addFreshEntity(this);
    }
}
