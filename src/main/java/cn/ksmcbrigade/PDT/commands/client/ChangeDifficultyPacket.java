package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.world.Difficulty;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class ChangeDifficultyPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientChangeDifficultyPacket").then(Commands.argument("locked", BoolArgumentType.bool()).then(Commands.argument("type", IntegerArgumentType.integer(0,3)).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundChangeDifficultyPacket(Difficulty.values()[IntegerArgumentType.getInteger(context, "type")], BoolArgumentType.getBool(context, "locked")));
            return 0;
        }))));
    }
}
