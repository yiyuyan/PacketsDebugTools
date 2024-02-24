package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class SetCreativeModeSlotPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerSetCreativeItemPacket").then(Commands.argument("slot", IntegerArgumentType.integer()).then(Commands.argument("main", BoolArgumentType.bool())).executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundSetCreativeModeSlotPacket(IntegerArgumentType.getInteger(context,"slot"),((Player) Objects.requireNonNull(context.getSource().getEntity())).getItemInHand(BoolArgumentType.getBool(context,"main")? InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND)));
            return 0;
        })));
    }
}
