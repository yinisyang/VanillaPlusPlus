package vanillaplusplus.items;

import vanillaplusplus.entities.LitDynamiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DynamiteItem extends Item {
    public DynamiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        //Vec3d pos = user.getPos();

        if (!world.isClient) {
            LitDynamiteEntity dynamiteEntity = new LitDynamiteEntity(world, user);
            dynamiteEntity.setItem(itemStack);
            //dynamiteEntity.setBoundingBox(new Box(user.getPos(), user.getPos().add(1, 1, 1)));
            dynamiteEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
            //dynamiteEntity.setVelocity(1.0f, 1.0f, 1.0f);
            //dynamiteEntity.setPos(user.getX(), user.getY(), user.getZ());
            //dynamiteEntity.updatePosition(user.getX(), user.getY(), user.getZ());


            world.spawnEntity(dynamiteEntity);
        }

        if (!user.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.method_29237(itemStack, world.isClient());
    }
}
