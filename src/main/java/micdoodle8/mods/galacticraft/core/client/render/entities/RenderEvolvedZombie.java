package micdoodle8.mods.galacticraft.core.client.render.entities;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlaySensorGlasses;
import micdoodle8.mods.galacticraft.core.client.model.ModelEvolvedZombie;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEvolvedZombie extends BipedRenderer<EntityEvolvedZombie>
{
    private static final ResourceLocation zombieTexture = new ResourceLocation(Constants.MOD_ID_CORE, "textures/model/zombie.png");
    private static final ResourceLocation powerTexture = new ResourceLocation(Constants.MOD_ID_CORE, "textures/model/power.png");

    private final ModelBase model = new ModelEvolvedZombie(0.2F, false, true);
    private boolean texSwitch;

    public RenderEvolvedZombie(EntityRendererManager manager)
    {
        super(manager, new ModelEvolvedZombie(true), 0.5F);
        LayerRenderer layerrenderer = (LayerRenderer) this.layerRenderers.get(0);
        this.addLayer(new HeldItemLayer(this));
        BipedArmorLayer layerbipedarmor = new BipedArmorLayer(this)
        {
            @Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityEvolvedZombie par1Entity)
    {
        return texSwitch ? OverlaySensorGlasses.altTexture : RenderEvolvedZombie.zombieTexture;
    }

    @Override
    protected void preRenderCallback(EntityEvolvedZombie zombie, float par2)
    {
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        if (texSwitch)
        {
            OverlaySensorGlasses.preRenderMobs();
        }
    }

    @Override
    public void doRender(EntityEvolvedZombie entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(entity, par2, par4, par6, par8, par9);
        if (OverlaySensorGlasses.overrideMobTexture())
        {
            texSwitch = true;
            super.doRender(entity, par2, par4, par6, par8, par9);
            texSwitch = false;
            OverlaySensorGlasses.postRenderMobs();
        }
    }

    @Override
    protected void applyRotations(EntityEvolvedZombie zombie, float pitch, float yaw, float partialTicks)
    {
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GL11.glTranslatef(0F, -zombie.height * 0.55F, 0F);
        GL11.glRotatef(zombie.getTumbleAngle(partialTicks), zombie.getTumbleAxisX(), 0F, zombie.getTumbleAxisZ());
        GL11.glTranslatef(0F, zombie.height * 0.55F, 0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        super.applyRotations(zombie, pitch, yaw, partialTicks);
    }
}
