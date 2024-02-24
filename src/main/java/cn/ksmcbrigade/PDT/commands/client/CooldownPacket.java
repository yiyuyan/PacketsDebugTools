package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class CooldownPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientCooldownPacket").then(Commands.argument("main",BoolArgumentType.bool()).then(Commands.argument("cooldown",IntegerArgumentType.integer(0)).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundCooldownPacket(((Player) Objects.requireNonNull(context.getSource().getEntity())).getItemInHand(BoolArgumentType.getBool(context,"main")? InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND).getItem(),IntegerArgumentType.getInteger(context,"cooldown")));
            return 0;
        }))));
    }
}
