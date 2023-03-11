package net.david85258.nms.customMobs;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

public class PigCustom extends Animal {

    public PigCustom(Location loc) {
        super(EntityType.PIG, ((CraftWorld) loc.getWorld()).getHandle());

        this.teleportTo(loc.getX(), loc.getY(), loc.getZ());

        try {
            registerGenericAttribute(this.getBukkitEntity(), org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(10);
        /*this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));*/

        this.level.addFreshEntity(this);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    private static Field attributeField;


    //Credit to @ysl3000
    //We need this to register the new attribute to the pig
    static {
        try {
            attributeField = AttributeMap.class.getDeclaredField("b");
            attributeField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void registerGenericAttribute(org.bukkit.entity.Entity entity, org.bukkit.attribute.Attribute attribute) throws IllegalAccessException {
        AttributeMap attributeMapBase = ((org.bukkit.craftbukkit.v1_19_R2.entity.CraftLivingEntity)entity).getHandle().getAttributes();
        Map<Attribute, AttributeModifier> map = (Map<Attribute, AttributeModifier>) attributeField.get(attributeMapBase);
        Attribute attributeBase = org.bukkit.craftbukkit.v1_19_R2.attribute.CraftAttributeMap.toMinecraft(attribute);
        AttributeModifier attributeModifiable = new AttributeModifier(attributeBase.getDescriptionId(), attributeBase.getDefaultValue(), AttributeModifier.Operation.ADDITION);
        map.put(attributeBase, attributeModifiable);
    }

}
