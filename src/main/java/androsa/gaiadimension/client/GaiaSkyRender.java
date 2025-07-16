package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.helpers.GaiaConfig;
import com.mojang.blaze3d.buffers.BufferType;
import com.mojang.blaze3d.buffers.BufferUsage;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * Custom sky render for Gaia:
 * Allowing for manipulation of Star brightness based on config values or biome.
 * Removes the moon as it is not required.
 * TODO: Can we add our own sky effects to Smoldering Bog, Shining Grove, Volcanic Lands, Goldstone Lands, and Static Wasteland? Maybe even the whole of Gaia?
 * TODO: Also fix sky colour blending here?
 * TODO: Could we make our own Sun?
 * TODO: I've decided Corrupt biomes need their own sun as well
 * If possible, add extra renders for other biomes.
 */
public class GaiaSkyRender {

    private static final ResourceLocation SUN_TEXTURES = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");
    private final GpuBuffer starVBO;
    private final RenderSystem.AutoStorageIndexBuffer starIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
    private int starIndexCount;

    public GaiaSkyRender() {
        this.starVBO = this.generateStars();
    }

    public boolean render(float partialTicks, ClientLevel world, Camera camera, Matrix4f matrix, Runnable fog) {
        Minecraft minecraft = Minecraft.getInstance();
        LevelRenderer renderer = minecraft.levelRenderer;

        fog.run();
        RenderStateShard.MAIN_TARGET.setupRenderState();
        PoseStack stack = new PoseStack();
        float star = getStarBrightness(world, partialTicks);
        int skycol = world.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTicks);
        float red = ARGB.redFloat(skycol);
        float grn = ARGB.greenFloat(skycol);
        float blu = ARGB.blueFloat(skycol);

        //renderSkyDisc
        RenderSystem.setShaderColor(red, grn, blu, 1.0F);
        GpuTexture colortex = Minecraft.getInstance().getMainRenderTarget().getColorTexture();
        GpuTexture depthtex = Minecraft.getInstance().getMainRenderTarget().getDepthTexture();

        try (RenderPass pass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(colortex, OptionalInt.empty(), depthtex, OptionalDouble.empty())) {
            pass.setPipeline(RenderPipelines.SKY);
            pass.setVertexBuffer(0, renderer.skyRenderer.topSkyBuffer);
            pass.draw(0, 10);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //

        MultiBufferSource.BufferSource bufferSource = renderer.renderBuffers.bufferSource();

        //renderSunMoonAndStars (without the Moon)
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        stack.mulPose(Axis.XP.rotationDegrees(world.getTimeOfDay(partialTicks) * 360.0F));

        ///renderSun
        float f12 = 30.0F;
        VertexConsumer bufferbuilder = bufferSource.getBuffer(RenderType.celestial(SUN_TEXTURES));
        Matrix4f matrix4f1 = stack.last().pose();
        int color = ARGB.white(1.0F);
        bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, -f12).setUv(0.0F, 0.0F).setColor(color);
        bufferbuilder.addVertex(matrix4f1, f12, 100.0F, -f12).setUv(1.0F, 0.0F).setColor(color);
        bufferbuilder.addVertex(matrix4f1, f12, 100.0F, f12).setUv(1.0F, 1.0F).setColor(color);
        bufferbuilder.addVertex(matrix4f1, -f12, 100.0F, f12).setUv(0.0F, 1.0F).setColor(color);
        ///

        ///renderStars, kind of
        if (star > 0.0F) {
            Matrix4fStack matrixstack = RenderSystem.getModelViewStack();
            matrixstack.pushMatrix();
            matrixstack.mul(stack.last().pose());
            RenderSystem.setShaderColor(star, star, star, star);
            RenderSystem.setShaderFog(FogParameters.NO_FOG);
            RenderPipeline pipeline = RenderPipelines.STARS;
            GpuTexture ct = Minecraft.getInstance().getMainRenderTarget().getColorTexture();
            GpuTexture dt = Minecraft.getInstance().getMainRenderTarget().getDepthTexture();
            GpuBuffer indices = this.starIndices.getBuffer(this.starIndexCount);

            try (RenderPass pass = RenderSystem.getDevice()
                    .createCommandEncoder()
                    .createRenderPass(ct, OptionalInt.empty(), dt, OptionalDouble.empty())) {
                pass.setPipeline(pipeline);
                pass.setVertexBuffer(0, this.starVBO);
                pass.setIndexBuffer(indices, this.starIndices.type());
                pass.drawIndexed(0, this.starIndexCount);
            }

            fog.run();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            matrixstack.popMatrix();
        }
        ///

        stack.popPose();
        //

        //renderDarkDisc
        if (camera.getEntity().getEyePosition(partialTicks).y - world.getLevelData().getHorizonHeight(world) < 0.0D) {
            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
            Matrix4fStack matrixstack = RenderSystem.getModelViewStack();
            matrixstack.pushMatrix();
            matrixstack.translate(0.0F, 12.0F, 0.0F);
            GpuTexture ct = Minecraft.getInstance().getMainRenderTarget().getColorTexture();
            GpuTexture dt = Minecraft.getInstance().getMainRenderTarget().getDepthTexture();

            try (RenderPass renderpass = RenderSystem.getDevice()
                    .createCommandEncoder()
                    .createRenderPass(ct, OptionalInt.empty(), dt, OptionalDouble.empty())) {
                renderpass.setPipeline(RenderPipelines.SKY);
                renderpass.setVertexBuffer(0, renderer.skyRenderer.bottomSkyBuffer);
                renderpass.draw(0, 10);
            }

            matrixstack.popMatrix();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        //

        return true;
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private GpuBuffer generateStars() {
        GpuBuffer vertexBuffer;
        RandomSource random = RandomSource.create(10842L);

        //renderStars
        try (ByteBufferBuilder bytebuilder = new ByteBufferBuilder(DefaultVertexFormat.POSITION.getVertexSize() * 1500 * 4)) {
            BufferBuilder bufferbuilder = new BufferBuilder(bytebuilder, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

            for(int i = 0; i < 1500; ++i) {
                float x = random.nextFloat() * 2.0F - 1.0F;
                float y = random.nextFloat() * 2.0F - 1.0F;
                float z = random.nextFloat() * 2.0F - 1.0F;
                float d3 = 0.15F + random.nextFloat() * 0.1F;
                float area = Mth.lengthSquared(x, y, z);
                if ((area < 1.0F) && area > 0.01F) {
                    Vector3f vec3f = new Vector3f(x, y, z).normalize(100.0F);
                    float rotZ = random.nextFloat() * (float) Math.PI * 2.0F;
                    Matrix3f mat3f = new Matrix3f().rotateTowards(new Vector3f(vec3f).negate(), new Vector3f(0.0F, 1.0F, 0.0F)).rotateZ(-rotZ);
                    bufferbuilder.addVertex(new Vector3f(d3, -d3, 0.0F).mul(mat3f).add(vec3f));
                    bufferbuilder.addVertex(new Vector3f(d3, d3, 0.0F).mul(mat3f).add(vec3f));
                    bufferbuilder.addVertex(new Vector3f(-d3, d3, 0.0F).mul(mat3f).add(vec3f));
                    bufferbuilder.addVertex(new Vector3f(-d3, -d3, 0.0F).mul(mat3f).add(vec3f));
                }
            }

            try (MeshData mesh = bufferbuilder.buildOrThrow()) {
                this.starIndexCount = mesh.drawState().indexCount();
                vertexBuffer = RenderSystem.getDevice()
                        .createBuffer(() -> "Gaia star buffer", BufferType.VERTICES, BufferUsage.STATIC_WRITE, mesh.vertexBuffer());

            }
        }

        return vertexBuffer;
    }

    public static float getStarBrightness(ClientLevel world, float par1) {
        Player player = Minecraft.getInstance().player;
        Optional<ResourceKey<Biome>> biome = world.getBiome(player.blockPosition()).unwrapKey();

        return biome.filter(GaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
