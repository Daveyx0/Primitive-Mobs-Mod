package daveyx0.primitivemobs.client.villagers.renderer;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import daveyx0.primitivemobs.entity.EntityTravellingMerchant;

@SideOnly(Side.CLIENT)
public class RenderTravellingMerchant extends RenderLiving
{

    private static final ResourceLocation skin = new ResourceLocation("primitivemobs","/textures/entities/villager/traveller.png");

    public RenderTravellingMerchant(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
    }

    public void func_177_a(EntityTravellingMerchant entityTravellingMerchant, double d, double d1,
    double d2, float f, float f1) {
    super.doRenderLiving(entityTravellingMerchant, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLivingBase entityliving, double d, double d1,
    double d2, float f, float f1) {
    func_177_a((EntityTravellingMerchant) entityliving, d, d1, d2, f, f1);
    }

            @Override
    public void doRender(Entity entity, double d, double d1, double d2,
    float f, float f1) {
    func_177_a((EntityTravellingMerchant) entity, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
    return skin;
    }
    }