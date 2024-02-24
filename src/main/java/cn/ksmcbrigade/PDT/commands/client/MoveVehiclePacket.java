package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;
import static cn.ksmcbrigade.PDT.commands.client.SetCameraPacket.getEntity;

@Mod.EventBusSubscriber(modid = "pdt")
public class MoveVehiclePacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientMoveVehiclePacket").then(Commands.argument("id", IntegerArgumentType.integer()).executes(context -> {
            Entity entity = getEntity(MC.level,IntegerArgumentType.getInteger(context,"id"));
            if(entity!=null){
                PacketsDebugTools.connection.send(new ClientboundMoveVehiclePacket(entity));
            }
            return 0;
        })));
    }
}
