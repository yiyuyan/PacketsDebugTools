package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class PlayerAbilitiesPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientPlayerAbilitiesPacket").executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundPlayerAbilitiesPacket(((Player) Objects.requireNonNull(context.getSource().getEntity())).getAbilities()));
            return 0;
        }));
    }
}
