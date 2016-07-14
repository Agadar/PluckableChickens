package com.github.agadar.pluckablechickens;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/** The proxy that is used client-side. */
public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerEntityRenderingHandlers()
	{
		// Register chicken rendering handler.
		RenderingRegistry.registerEntityRenderingHandler(EntityPluckableChicken.class, new IRenderFactory<EntityPluckableChicken>()
		{
			@Override
			public Render<? super EntityPluckableChicken> createRenderFor(RenderManager manager)
			{
				return new RenderPluckableChicken(manager, new ModelChicken(), 0.3F);
			}
		});
	}
}
