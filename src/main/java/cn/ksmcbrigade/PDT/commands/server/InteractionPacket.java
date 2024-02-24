package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;

@Mod.EventBusSubscriber(modid = "pdt")
public class InteractionPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerInteractionPacket").then(Commands.argument("id", IntegerArgumentType.integer()).then(Commands.argument("usingSecondaryAction", BoolArgumentType.bool()).then(Commands.argument("main",BoolArgumentType.bool()).executes(context -> {
            Entity entity = getEntity(MC.level,IntegerArgumentType.getInteger(context,"id"));
            if(entity!=null){
                PacketsDebugTools.connection.send(ServerboundInteractPacket.createInteractionPacket(entity,BoolArgumentType.getBool(context,"usingSecondaryAction"),BoolArgumentType.getBool(context,"main")? InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND));
            }
            return 0;
        })))));
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
