package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;

@Mod.EventBusSubscriber(modid = "pdt")
public class TpEntityPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientTpEntityPacket").then(Commands.argument("id", IntegerArgumentType.integer()).executes(context -> {
            Entity entity = getEntity(MC.level,IntegerArgumentType.getInteger(context,"id"));
            if(entity!=null){
                PacketsDebugTools.connection.send(new ClientboundTeleportEntityPacket(entity));
            }
            return 0;
        })));
    }

    public static Entity getEntity(Level level,int id){
        if (MC.player != null) {
            for(Entity entity:level.getEntitiesOfClass(Entity.class,new AABB(MC.player.position(),MC.player.position()).inflate(10000))){
                if(entity.getId()==id){
                    return entity;
                }
            }
        }
        return null;
    }
}
