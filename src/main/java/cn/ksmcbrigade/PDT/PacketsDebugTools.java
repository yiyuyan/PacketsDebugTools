package cn.ksmcbrigade.PDT;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


@Mod("pdt")
@Mod.EventBusSubscriber
public class PacketsDebugTools {

    public static Minecraft MC = Minecraft.getInstance();
    public static Connection connection;
    public static boolean test = false;

    public PacketsDebugTools() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void getConnection(TickEvent.PlayerTickEvent event){
        if(MC.getConnection()!=null){
            connection = Objects.requireNonNull(MC.getConnection()).getConnection();
        }
    }

    @SubscribeEvent
    public static void RegisterCommands(@NotNull RegisterClientCommandsEvent event){
        //other

        //abilities
        event.getDispatcher().register(Commands.literal("PlayerAbilities").then(Commands.argument("mayFly", BoolArgumentType.bool()).then(Commands.argument("flying",BoolArgumentType.bool()).executes(context -> {
            if (MC.player != null) {
                MC.player.getAbilities().mayfly = BoolArgumentType.getBool(context,"mayFly");
                MC.player.getAbilities().flying = BoolArgumentType.getBool(context,"flying");
            }
            return 0;
        }))));

        //camera
        event.getDispatcher().register(Commands.literal("Camera").then(Commands.argument("noGravity",BoolArgumentType.bool()).executes(context -> {
            if(MC.cameraEntity!=null){
                MC.cameraEntity.setNoGravity(BoolArgumentType.getBool(context,"noGravity"));
            }
            return 0;
        })));

        //jump
        event.getDispatcher().register(Commands.literal("jump").executes(context -> {
            if(MC.player!=null){
                MC.player.jumpFromGround();
            }
            return 0;
        }));

        //moveVehicle
        event.getDispatcher().register(Commands.literal("moveVehicle").then(Commands.argument("x", DoubleArgumentType.doubleArg()).then(Commands.argument("y",DoubleArgumentType.doubleArg()).then(Commands.argument("z",DoubleArgumentType.doubleArg()).executes(context -> {
            Entity entity = context.getSource().getEntity();
            if (entity != null && entity.getVehicle() != null) {
                entity.getVehicle().setDeltaMovement(new Vec3(DoubleArgumentType.getDouble(context,"x"),DoubleArgumentType.getDouble(context,"y"),DoubleArgumentType.getDouble(context,"z")));
            }
            return 0;
        })))));


        //listEntities
        event.getDispatcher().register(Commands.literal("listEntityIDs").executes(context -> {
            if(MC.player!=null){
                for(Entity entity:MC.player.level.getEntitiesOfClass(Entity.class,new AABB(MC.player.position(),MC.player.position()).inflate(10000))){
                    MC.player.sendMessage(Component.nullToEmpty(entity.getDisplayName().getString()+": "+ entity.getId()),MC.player.getUUID());
                }
            }
            return 0;
        }));

        //test
        event.getDispatcher().register(Commands.literal("testP").executes(context -> {
            if(MC.player!=null){
                MC.player.sendMessage(Component.nullToEmpty(String.valueOf(test)),MC.player.getUUID());
                test=!test;
            }
            return 0;
        }));
    }
}
