package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundLockDifficultyPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class LockDifficultyPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerLockDifficultyPacket").then(Commands.argument("lock", BoolArgumentType.bool()).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundLockDifficultyPacket(BoolArgumentType.getBool(context,"lock")));
            return 0;
        })));
    }
}
