package daveyx0.primitivemobs.core;

import java.util.LinkedList;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenHell;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import daveyx0.primitivemobs.common.PrimitiveMobs;
import daveyx0.primitivemobs.configuration.PrimitiveMobsConfigurationGeneral;

public class PrimitiveMobsEntities
{
	private static String PM = "PrimitiveMobs";
	
	private static int freq;
	private static int min;
	private static int max;
	
	public BiomeGenBase biome = null;
	
	public static BiomeGenBase ocean = BiomeGenBase.ocean;
	public static BiomeGenBase plains = BiomeGenBase.plains;
	public static BiomeGenBase desert = BiomeGenBase.desert;
	public static BiomeGenBase extremeHills = BiomeGenBase.extremeHills;
	public static BiomeGenBase forest = BiomeGenBase.forest;
	public static BiomeGenBase taiga = BiomeGenBase.taiga;
	public static BiomeGenBase swampland = BiomeGenBase.swampland;
	public static BiomeGenBase river = BiomeGenBase.river;
	public static BiomeGenBase hell = BiomeGenBase.hell;
	public static BiomeGenBase sky = BiomeGenBase.sky;
	public static BiomeGenBase frozenOcean = BiomeGenBase.frozenOcean;
	public static BiomeGenBase frozenRiver = BiomeGenBase.frozenRiver;
	public static BiomeGenBase icePlains = BiomeGenBase.icePlains;
	public static BiomeGenBase iceMountains = BiomeGenBase.iceMountains;
	public static BiomeGenBase mushroomIsland = BiomeGenBase.mushroomIsland;
	public static BiomeGenBase mushroomIslandShore = BiomeGenBase.mushroomIslandShore;
	public static BiomeGenBase beach = BiomeGenBase.beach;
	public static BiomeGenBase desertHills = BiomeGenBase.desertHills;
	public static BiomeGenBase forestHills = BiomeGenBase.forestHills;
	public static BiomeGenBase taigaHills = BiomeGenBase.taigaHills;
	public static BiomeGenBase extremeHillsEdge = BiomeGenBase.extremeHillsEdge;
	public static BiomeGenBase jungle = BiomeGenBase.jungle;
	public static BiomeGenBase jungleHills = BiomeGenBase.jungleHills;
	
    public PrimitiveMobsEntities()
    {      

    }    
    
    public static void init()
    {
    	addTravelMerchant(null, PM, new BiomeGenBase[] 
				{plains, desert, extremeHills, forest, river, beach, desertHills, forestHills, extremeHillsEdge});
    }
    
    public static void addTravelMerchant(BiomeGenBase biome, String modid, BiomeGenBase... biomes)
    {
    	freq = PrimitiveMobsConfigurationGeneral.TravelMerchantFreq;
    	boolean spawnBool = true;
    	if (freq <= 0)
    	{
    		spawnBool = false;
    	}
    	min = 1;
    	max = 1;    	
		addEntities(modid, daveyx0.primitivemobs.entity.EntityTravellingMerchant.class, "Travelling Merchant", "Travelling Merchant", 1 , spawnBool, 
				freq, min, max, 0xFFFFFF, 0xFFFFFF, daveyx0.primitivemobs.entity.EntityTravellingMerchant.class, EnumCreatureType.creature, modid == PM? biomes: new BiomeGenBase[] {biome});
    }
    
    /*private void addEntities(String modid, Class var1, String name1, String name2, int entityid, boolean spawn, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
    {
    	if (modid == PM)
    	{
	        LanguageRegistry.instance().addStringLocalization("entity." + "PrimitiveMobs." + name1 + ".name", "en_US", name2);	    
		    EntityRegistry.registerModEntity(var1, name2, entityid,  PrimitiveMobs.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		    PrimitiveMobs.PMlogger.info("Primitive Mobs: entity " + name2 + " registered");
	        addEntitySpawning(modid, var1, name2, spawn, weightedProb, min, max, typeOfCreature, biomes);
    	}
    	else
    	{
			for (BiomeGenBase biomegenbase : biomes)
			{
	    		if (biomes != null)
	    		{
	    			addEntitySpawning(modid, var1, name2, spawn, weightedProb, min, max, typeOfCreature, biomes); 
	    		}
			}
    	}
    }*/
    
    private static void addEntities(String modid, Class var1, String name1, String name2, int entityid, boolean spawn, int weightedProb, int min, int max, int bkEggColor, int fgEggColor, Class entityClass, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
    {
    	int trackingRange = 80;
    	int updateFrequency = 3;
    	boolean sendsVelocityUpdates = true;

    	if (modid == PM)
    	{
	        LanguageRegistry.instance().addStringLocalization("entity." + "PrimitiveMobs." + name1 + ".name", "en_US", name2);	    	
		    EntityRegistry.registerModEntity(var1, name2, entityid,  PrimitiveMobs.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		    PrimitiveMobs.PMlogger.info("Primitive Mobs: entity " + name2 + " registered");
		    PrimitiveMobs.PMlogger.info("Primitive Mobs: entity " + name2 + " will spawn with " + weightedProb + " frequency");
	        addEntitySpawning(modid, var1, name2, spawn, weightedProb, min, max, typeOfCreature, biomes);
	        int id = entityid + 800;
	        EntityList.IDtoClassMapping.put(id, entityClass);
	        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
    	}
    	else
    	{
			for (BiomeGenBase biomegenbase : biomes)
			{
	    		if (biomes != null)
	    		{
	    			addEntitySpawning(modid, var1, name2, spawn, weightedProb, min, max, typeOfCreature, biomes); 
	    		}
			}
    	}
    }
    
    
    public static void addEntitySpawning(String modid, Class var1, String name2, boolean spawn, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
    {
    	int adjweight = settingsLimiter(weightedProb);
    	int adjmin = settingsLimiter(min);
    	int adjmax = settingsLimiter(max);
    	int weight = adjweight * 4;
    	
        	if (spawn)
        	{
            	EntityRegistry.addSpawn(var1, adjweight, adjmin, adjmax, typeOfCreature, biomes); 
        	}
           	
    }
    
    /*public void addEntitySpawningNonVerbose(Class var1, boolean spawn, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
    {
    	int adjweight = settingsLimiter(weightedProb);
    	int adjmin = settingsLimiter(min);
    	int adjmax = settingsLimiter(max);    	
    	int weight = adjweight * 4;
    	
        	if (spawn)
        	{
        	EntityRegistry.addSpawn(var1, adjweight, adjmin, adjmax, typeOfCreature, biomes); 
        	}  	
    }*/
    	
    
	public static int settingsLimiter(int par1)
	{   
		if (par1 > 8)
		{
			return par1 = 8;
		}
		else if (par1 < 0)
		{
			return par1 = 0;
		}
		else
		{
			return par1;
		}
	}
	 
}
