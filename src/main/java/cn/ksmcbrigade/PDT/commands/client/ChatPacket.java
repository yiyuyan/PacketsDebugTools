package cn.ksmcbrigade.PDT.commands.client;

import cn.ksmcbrigade.PDT.PacketsDebugTools;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundChatPacket;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "pdt")
public class ChatPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ClientChatPacket").then(Commands.argument("message", StringArgumentType.string()).then(Commands.argument("type", IntegerArgumentType.integer(0,2)).executes(context -> {
            PacketsDebugTools.connection.send(new ClientboundChatPacket(Component.nullToEmpty(I18n.get(StringArgumentType.getString(context, "message"))), ChatType.values()[IntegerArgumentType.getInteger(context, "type")], Objects.requireNonNull(context.getSource().getEntity()).getUUID()));
            return 0;
        }))));
    }
}
