package com.github.agadar.pluckablechickens;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Renderer for our plucked chicken.
 */
@SideOnly(Side.CLIENT)
public class RenderPluckableChicken extends RenderChicken
{
	/** Textures for the plucked chicken. */
    private static final ResourceLocation pluckedChickenTextures = new ResourceLocation(PluckableChickens.MODID + ":textures/entities/plucked_chicken.png");

    public RenderPluckableChicken(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn)
    {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityChicken entity)
    {
    	EntityPluckableChicken chicken = (EntityPluckableChicken) entity;
    	return chicken.getSheared() ? pluckedChickenTextures : super.getEntityTexture(entity);
    }
}
