package daveyx0.primitivemobs.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import daveyx0.primitivemobs.common.PrimitiveMobs;

public class PrimitiveMobsConfigurationGeneral
{
	public static Configuration config;

	// Spawn Frequencies
	public static int TravelMerchantFreq;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			config.addCustomCategoryComment("Mob Frequencies", "Change mob spawn frequencies here.");
	        TravelMerchantFreq = config.get("Mob Frequencies", "Travel Merchant Spawn Frequency ", 4).getInt();

			PrimitiveMobs.PMlogger.info("Loaded General configuration file");
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Primitive Mobs cannot load the configuration file: General");
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}