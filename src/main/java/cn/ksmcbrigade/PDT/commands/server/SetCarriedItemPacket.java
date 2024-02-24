package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class SetCarriedItemPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerSetCarriedItemPacket").then(Commands.argument("slot", IntegerArgumentType.integer()).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundSetCarriedItemPacket(IntegerArgumentType.getInteger(context,"slot")));
            return 0;
        })));
    }
}
