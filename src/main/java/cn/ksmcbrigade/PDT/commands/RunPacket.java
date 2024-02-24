package cn.ksmcbrigade.PDT.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = "pdt")
public class RunPacket {
    @SubscribeEvent
    public static void command(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("RunPacket").then(Commands.argument("count", IntegerArgumentType.integer()).then(Commands.argument("sleep", LongArgumentType.longArg()).then(Commands.literal("packet").redirect(event.getDispatcher().getRoot(), context -> {
            int count = IntegerArgumentType.getInteger(context,"count");
            if(count!=1){
                execute(count,LongArgumentType.getLong(context,"sleep"),context,event);
            }
            return context.getSource();
        })))));
    }

    public static void execute(int count, long sleep, CommandContext<CommandSourceStack> context, RegisterClientCommandsEvent event){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String command = context.getInput().replace("/RunPacket "+count+" "+sleep+" packet ","");
                try {
                    event.getDispatcher().execute(command,context.getSource());
                    Thread.sleep(sleep);
                } catch (CommandSyntaxException | InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<count-2;i++){
                    try {
                        event.getDispatcher().execute(command,context.getSource());
                        Thread.sleep(sleep);
                        if(i>=(count-2)){
                            break;
                        }
                    } catch (CommandSyntaxException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timer.cancel();
            }
        }, sleep, TimeUnit.MILLISECONDS.ordinal());
    }
}
