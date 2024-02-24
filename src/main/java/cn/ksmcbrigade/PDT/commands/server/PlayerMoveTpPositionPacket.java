package cn.ksmcbrigade.PDT.commands.server;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.PDT.PacketsDebugTools.MC;
import static cn.ksmcbrigade.PDT.PacketsDebugTools.connection;

@Mod.EventBusSubscriber(modid = "pdt")
public class PlayerMoveTpPositionPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerPlayerMoveTpPositionPacket").then(Commands.argument("x", DoubleArgumentType.doubleArg()).then(Commands.argument("y",DoubleArgumentType.doubleArg()).then(Commands.argument("z",DoubleArgumentType.doubleArg()).executes(context -> {
            if (MC.player != null) {
                connection.send(new ServerboundMovePlayerPacket.Pos(DoubleArgumentType.getDouble(context,"x"),DoubleArgumentType.getDouble(context,"y"),DoubleArgumentType.getDouble(context,"z"),MC.player.isOnGround()));
            }
            return 0;
        })))));
    }
}
