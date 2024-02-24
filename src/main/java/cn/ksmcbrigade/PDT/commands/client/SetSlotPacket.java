package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class SetSlotPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientSetSlotPacket").then(Commands.argument("-1", BoolArgumentType.bool()).then(Commands.argument("stateId", IntegerArgumentType.integer()).then(Commands.argument("slot",IntegerArgumentType.integer()).then(Commands.argument("main",BoolArgumentType.bool()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundContainerSetSlotPacket(BoolArgumentType.getBool(context,"-1")?-1:-2,IntegerArgumentType.getInteger(context,"stateId"),IntegerArgumentType.getInteger(context,"slot"),((Player) Objects.requireNonNull(context.getSource().getEntity())).getItemInHand(BoolArgumentType.getBool(context,"main")? InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND)));
            return 0;
        }))))));
    }
}
