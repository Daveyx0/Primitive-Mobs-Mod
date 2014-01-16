package daveyx0.primitivemobs.common.handlers;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.PlayStreamingEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler 
{
	static String[] mobSoundFiles = { "primitivemobs:mob/phantom/say.ogg"};
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) 
	{
		for (String soundFile : mobSoundFiles) 
		{
			try 
			{
				event.manager.soundPoolSounds.addSound(soundFile);
			}

			catch (Exception e) 
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[Primitive Mobs] Failed loading sound file: " + soundFile);
			}
		}
	}
}
