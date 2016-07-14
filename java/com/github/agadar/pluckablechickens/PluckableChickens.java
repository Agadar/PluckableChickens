package com.github.agadar.pluckablechickens;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PluckableChickens.MODID, version = PluckableChickens.VERSION, name = PluckableChickens.NAME)
public class PluckableChickens
{
	@Instance(value = PluckableChickens.MODID)
	public static PluckableChickens instance;

	@SidedProxy(clientSide = PluckableChickens.CLIENTSIDE, serverSide = PluckableChickens.SERVERSIDE)
	public static CommonProxy proxy;

	// These are the references we use. These values are the same for our entire mod, so we only have 
	// to make them once here, and we can always access them.
	public static final String MODID = "pluckablechickens";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "PluckableChickens";
	public static final String CLIENTSIDE = "com.github.agadar.pluckablechickens.ClientProxy";
	public static final String SERVERSIDE = "com.github.agadar.pluckablechickens.CommonProxy";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Register entities.
		proxy.registerEntities();
		
		// Register entity rendering handlers.
		proxy.registerEntityRenderingHandlers();
		
		// Register event handlers.
		MinecraftForge.EVENT_BUS.register(new HandlerChickenIntercept());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
}
