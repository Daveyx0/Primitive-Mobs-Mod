package daveyx0.primitivemobs.modintegration;

import cpw.mods.fml.common.Loader;

public class IntegratedModChecker {
	
	public static void preInit()
	{
	}

	public static void init()
	{
		if (Loader.isModLoaded("BiomesOPlenty"))
		{
			try {
				BiomesOPlentyIntegration.init();
			}
			catch (Exception e) {
				System.out.println("[Primitive Mobs] Biomes O Plenty support was not applied due to a problem");
				e.printStackTrace(System.err);
			}
		}
	}
	
	public static void postInit()
	{
	}

}
