package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class RenNamePacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerRenNamePacket").then(Commands.argument("name", StringArgumentType.string()).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundRenameItemPacket(StringArgumentType.getString(context, "name")));
            return 0;
        })));
    }
}
