package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundChangeDifficultyPacket;
import net.minecraft.world.Difficulty;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class ChangeDifficultyPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerChangeDifficultyPacket").then(Commands.argument("type", IntegerArgumentType.integer(0,3)).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundChangeDifficultyPacket(Difficulty.values()[IntegerArgumentType.getInteger(context, "type")]));
            return 0;
        })));
    }
}
