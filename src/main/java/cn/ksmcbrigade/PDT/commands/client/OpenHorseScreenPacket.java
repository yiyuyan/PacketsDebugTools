package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundHorseScreenOpenPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class OpenHorseScreenPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientOpenHorseScreenPacket").then(Commands.argument("-1", BoolArgumentType.bool()).then(Commands.argument("size", IntegerArgumentType.integer()).then(Commands.argument("entityId",IntegerArgumentType.integer()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundHorseScreenOpenPacket(BoolArgumentType.getBool(context,"-1")?-1:-2,IntegerArgumentType.getInteger(context,"size"),IntegerArgumentType.getInteger(context,"entityId")));
            return 0;
        })))));
    }
}
