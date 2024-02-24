package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class ClientCommandPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("Server-ClientCommandPacket").then(Commands.argument("type", IntegerArgumentType.integer(0,1)).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.values()[IntegerArgumentType.getInteger(context, "type")]));
            return 0;
        })));
    }
}
