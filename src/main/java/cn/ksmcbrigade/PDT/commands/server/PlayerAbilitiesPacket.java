package cn.ksmcbrigade.PDT.commands.server;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class PlayerAbilitiesPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ServerPlayerAbilitiesPacket").executes(context -> {
            PacketsDebugTools.connection.send(new ServerboundPlayerAbilitiesPacket(((Player) Objects.requireNonNull(context.getSource().getEntity())).getAbilities()));
            return 0;
        }));
    }
}
