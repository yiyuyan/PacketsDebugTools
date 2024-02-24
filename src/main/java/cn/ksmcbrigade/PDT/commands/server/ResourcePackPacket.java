package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundResourcePackPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class ResourcePackPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerResourcePackPacket").then(Commands.argument("action", IntegerArgumentType.integer(0,3)).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundResourcePackPacket(ServerboundResourcePackPacket.Action.values()[IntegerArgumentType.getInteger(context, "type")]));
            return 0;
        })));
    }
}
