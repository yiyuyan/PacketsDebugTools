package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pdt")
public class SetActionBarTextPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientSetActionBarTextPacket").then(Commands.argument("text", StringArgumentType.string()).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundSetActionBarTextPacket(Component.nullToEmpty(I18n.get(StringArgumentType.getString(context, "text")))));
            return 0;
        })));
    }
}