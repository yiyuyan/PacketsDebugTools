package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class SetTimePacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientSetTimePacket").then(Commands.argument("gameTime", LongArgumentType.longArg()).then(Commands.argument("dayTime", LongArgumentType.longArg()).then(Commands.argument("light", BoolArgumentType.bool()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundSetTimePacket(LongArgumentType.getLong(context,"gameTime"),LongArgumentType.getLong(context,"dayTime"), BoolArgumentType.getBool(context,"light")));
            return 0;
        })))));
    }
}
