package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundKeepAlivePacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class KeepAlivePacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientKeepAlivePacket").then(Commands.argument("id", IntegerArgumentType.integer()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundKeepAlivePacket(IntegerArgumentType.getInteger(context,"id")));
            return 0;
        })));
    }
}
