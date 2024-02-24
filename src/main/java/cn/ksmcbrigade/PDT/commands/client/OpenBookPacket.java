package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class OpenBookPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientOpenBookPacket").then(Commands.argument("main",BoolArgumentType.bool()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundOpenBookPacket(BoolArgumentType.getBool(context,"main")? InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND));
            return 0;
        })));
    }
}
