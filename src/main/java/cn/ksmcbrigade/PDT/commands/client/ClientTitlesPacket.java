package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundClearTitlesPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class ClientTitlesPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientClearTitlesPacket").then(Commands.argument("resetTimes", BoolArgumentType.bool()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundClearTitlesPacket(BoolArgumentType.getBool(context, "resetTimes")));
            return 0;
        })));
    }
}
