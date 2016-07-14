package com.github.agadar.pluckablechickens;

import net.minecraftforge.fml.common.registry.EntityRegistry;

/** The proxy that is used server-side. */
public class CommonProxy 
{
	/** Used by most mobs and animals; most projectiles use 64. */
	private static final int tracking_range = 80;
	
	/** Used by most mobs and animals; most projectiles use 10, except arrows. */
	private static final int update_frequency = 3;
	
	/**
	 * Registers this mod's entities.
	 */
	public void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityPluckableChicken.class, "pluckable_chicken", 0, 
				PluckableChickens.instance, tracking_range, update_frequency, true);
	}
	
	/**
	 * Registers this mod's entity rendering handlers.
	 */
	public void registerEntityRenderingHandlers()
	{
		// Empty, because server side doesn't do any rendering.
	}
}
