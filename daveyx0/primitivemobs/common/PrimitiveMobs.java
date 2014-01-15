package daveyx0.primitivemobs.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import daveyx0.primitivemobs.common.PrimitiveMobsCommonProxy;

@NetworkMod(clientSideRequired=true,serverSideRequired=true, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"PrimitiveMobs"}, packetHandler = PrimitiveMobsClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"Primitivemobs"}, packetHandler = PrimitiveMobsServerPacketHandler.class)) //For serverside packet handling

@Mod(modid="PrimitiveMobs",name="Primitive Mobs",version="version 0.1")

public class PrimitiveMobs {

@Instance("PrimitiveMobs")
public static PrimitiveMobs instance = new PrimitiveMobs();

@SidedProxy(clientSide = "daveyx0.primitivemobs.client.TutorialModClientProxy", serverSide = "daveyx0.primitivemobs.common.TutorialModCommonProxy") //Tells Forge the location of your proxies
public static PrimitiveMobsCommonProxy proxy;

@PreInit
public void PreInit(FMLPreInitializationEvent e){

}

@Init
public void InitTutorialMod(FMLInitializationEvent event){

NetworkRegistry.instance().registerGuiHandler(this, proxy);

}
}