package daveyx0.primitivemobs.configuration;

import java.io.File;

public class PrimitiveMobsConfiguration 
{
	public static File generalConfigFile;
	public static File spawnConfigFile;

	public static void init(String configpath)
	{
		generalConfigFile = new File(configpath + "general.cfg");
		spawnConfigFile = new File(configpath + "spawning.cfg");

		PrimitiveMobsConfigurationGeneral.init(generalConfigFile);
		//PrimitiveMobsConfigurationSpawn.init(spawnConfigFile);
	}
}
