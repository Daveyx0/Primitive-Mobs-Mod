package daveyx0.primitivemobs.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import daveyx0.primitivemobs.client.models.ModelTravellingMerchant;
import daveyx0.primitivemobs.client.renderer.entity.RenderTravellingMerchant;
import daveyx0.primitivemobs.common.PrimitiveMobsCommonProxy;
import daveyx0.primitivemobs.entity.EntityTravellingMerchant;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;

public class PrimitiveMobsClientProxy extends PrimitiveMobsCommonProxy {

public void registerRenderInformation(){
}


public void registerRenderers()
{
float villagerShadowSize = 0.5F;

RenderingRegistry.registerEntityRenderingHandler(EntityTravellingMerchant.class, new RenderTravellingMerchant(new ModelTravellingMerchant(), villagerShadowSize));

}
}