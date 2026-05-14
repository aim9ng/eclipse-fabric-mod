package com.eclipse.mod.visual;

import com.eclipse.mod.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;

public class TargetESP {
    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void renderTargetESP(MatrixStack matrices, VertexConsumerProvider vertexConsumers, 
                                       float tickDelta) {
        var config = ConfigManager.getConfig();
        if (!config.targetESPEnabled) return;

        Entity targetEntity = mc.targetedEntity;
        if (targetEntity instanceof LivingEntity && targetEntity != mc.player) {
            LivingEntity entity = (LivingEntity) targetEntity;
            Box box = entity.getBoundingBox();
            
            if (mc.gameRenderer.getCamera() != null) {
                Box relativeBox = box.offset(
                    -mc.gameRenderer.getCamera().getPos().getX(),
                    -mc.gameRenderer.getCamera().getPos().getY(),
                    -mc.gameRenderer.getCamera().getPos().getZ()
                );
                
                renderBox(matrices, relativeBox, config.targetESPRed, config.targetESPGreen, 
                         config.targetESPBlue, config.targetESPThickness, 
                         config.targetESPFilled, config.targetESPFilledAlpha);
            }
        }
    }

    private static void renderBox(MatrixStack matrices, Box box, int red, int green, int blue, 
                                   float thickness, boolean filled, int fillAlpha) {
        matrices.push();
        
        if (filled) {
            drawFilledBox(matrices, box, red, green, blue, fillAlpha);
        }
        
        drawOutlineBox(matrices, box, red, green, blue, thickness);
        
        matrices.pop();
    }

    private static void drawOutlineBox(MatrixStack matrices, Box box, int r, int g, int b, float thickness) {
        // Edge rendering implementation
    }

    private static void drawFilledBox(MatrixStack matrices, Box box, int r, int g, int b, int alpha) {
        // Filled box rendering with transparency
    }
}
