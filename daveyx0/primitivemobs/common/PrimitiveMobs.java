package daveyx0.primitivemobs.common;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import daveyx0.primitivemobs.common.PrimitiveMobsCommonProxy;
import daveyx0.primitivemobs.common.handlers.PrimitiveMobsClientPacketHandler;
import daveyx0.primitivemobs.common.handlers.PrimitiveMobsServerPacketHandler;
import daveyx0.primitivemobs.configuration.PrimitiveMobsConfiguration;
import daveyx0.primitivemobs.core.PrimitiveMobsEntities;
import daveyx0.primitivemobs.entity.EntityTravellingMerchant;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"PrimitiveMobs"}, packetHandler = PrimitiveMobsClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Primitivemobs"}, packetHandler = PrimitiveMobsServerPacketHandler.class)) //For serverside packet handling

@Mod(modid="PrimitiveMobs",name="Primitive Mobs",version="version 0.1")

public class PrimitiveMobs {

@Instance("PrimitiveMobs")
public static PrimitiveMobs instance = new PrimitiveMobs();

@SidedProxy(clientSide = "daveyx0.primitivemobs.client.PrimitiveMobsClientProxy", serverSide = "daveyx0.primitivemobs.common.PrimitiveMobsCommonProxy") //Tells Forge the location of your proxies
public static PrimitiveMobsCommonProxy proxy;

public static CreativeTabs tabPrimitiveMobs;
public static String configPath;

@EventHandler
public void PreInit(FMLPreInitializationEvent event){
	
	MinecraftForge.EVENT_BUS.register(new PrimitiveMobs());
    PMlogger = Logger.getLogger("PrimitiveMobs");
    PMlogger.setParent(FMLLog.getLogger());
    
    configPath = event.getModConfigurationDirectory() + "/primitivemobs/";
	PrimitiveMobsConfiguration.init(configPath);

	tabPrimitiveMobs = new CreativeTabs(CreativeTabs.getNextID(),"tabPrimitiveMobs");
	
	PrimitiveMobsEntities.init();
	
}

@EventHandler
public void InitTutorialMod(FMLInitializationEvent event){

	NetworkRegistry.instance().registerGuiHandler(this, proxy);
	proxy.registerRenderers();

}

@EventHandler
public void postInit(FMLPostInitializationEvent event)
{
	//TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	//TickRegistry.registerTickHandler(new TickHandlerServer(), Side.SERVER);
}

public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
int id = EntityRegistry.findGlobalUniqueEntityId();

	EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
	EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
}

public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
if (spawnProb > 0) 
{
	EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
}
}

public static Logger PMlogger;
}
