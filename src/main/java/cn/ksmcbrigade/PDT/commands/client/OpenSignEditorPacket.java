package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class OpenSignEditorPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientOpenSignEditorPacket").then(Commands.argument("x", DoubleArgumentType.doubleArg()).then(Commands.argument("y", DoubleArgumentType.doubleArg()).then(Commands.argument("z",DoubleArgumentType.doubleArg()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundOpenSignEditorPacket(new BlockPos(DoubleArgumentType.getDouble(context,"x"),DoubleArgumentType.getDouble(context,"y"),DoubleArgumentType.getDouble(context,"z"))));
            return 0;
        })))));
    }
}
