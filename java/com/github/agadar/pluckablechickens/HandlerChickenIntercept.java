package com.github.agadar.pluckablechickens;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Mod event handler for canceling normal chicken spawning and substituting our pluckable chickens.
 */
public class HandlerChickenIntercept 
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{		
		// Intercept chickens spawning and substitute the chicken with our pluckable chicken.
		if (event.entity.getClass().equals(EntityChicken.class))
		{
			EntityChicken chicken = (EntityChicken) event.entity;
			EntityPluckableChicken pluckable = new EntityPluckableChicken(chicken);
	        event.world.spawnEntityInWorld(pluckable);
			event.setCanceled(true);
		}
	}
}
