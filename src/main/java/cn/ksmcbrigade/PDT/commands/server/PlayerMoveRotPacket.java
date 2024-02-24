package cn.ksmcbrigade.PDT.commands.server;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;
import static cn.ksmcbrigade.PDT.PacketsDebugTools.connection;

@Mod.EventBusSubscriber(modid = "pdt")
public class PlayerMoveRotPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerPlayerMoveRotPacket").then(Commands.argument("yRot", FloatArgumentType.floatArg()).then(Commands.argument("xRot",FloatArgumentType.floatArg()).then(Commands.argument("onGround", BoolArgumentType.bool()).executes(context -> {
            if (MC.player != null) {
                    connection.send(new ServerboundMovePlayerPacket.Rot(FloatArgumentType.getFloat(context,"yRot"),FloatArgumentType.getFloat(context,"xRot"),BoolArgumentType.getBool(context,"onGround")));
            }
            return 0;
        })))));
    }
}
