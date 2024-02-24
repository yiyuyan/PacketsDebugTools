package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;

@Mod.EventBusSubscriber(modid = "pdt")
public class PlayerInputPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerPlayerInputPacket").then(Commands.argument("jumping", BoolArgumentType.bool()).then(Commands.argument("shiftDown",BoolArgumentType.bool()).executes(context -> {
            if (MC.player != null) {
                PacketsDebugTools.connection.send(new ServerboundPlayerInputPacket(MC.player.xxa,MC.player.zza,BoolArgumentType.getBool(context, "jumping"),BoolArgumentType.getBool(context, "shiftDown")));
            }
            return 0;
        }))));
    }
}
