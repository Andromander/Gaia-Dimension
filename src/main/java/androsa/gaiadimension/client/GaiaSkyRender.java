package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.helpers.GaiaConfig;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.state.level.SkyRenderState;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import org.joml.*;

import java.lang.Math;
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

    private final GpuBuffer starVBO;
    private final RenderSystem.AutoStorageIndexBuffer quadIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
    private final RenderSystem.AutoStorageIndexBuffer starIndices = RenderSystem.getSequentialBuffer(VertexFormat.Mode.QUADS);
    private int starIndexCount;

    public GaiaSkyRender() {
        this.starVBO = this.generateStars();
    }

    public boolean render(LevelRenderState levelState, SkyRenderState skyState, Matrix4fc modelMatrix, Runnable fog) {
        Minecraft minecraft = Minecraft.getInstance();
        LevelRenderer renderer = minecraft.levelRenderer;
        SkyRenderer skyRenderer = renderer.skyRenderer;

        fog.run();
        PoseStack stack = new PoseStack();
        float star = getStarBrightness(minecraft.level, skyState.starBrightness);
        skyRenderer.renderSkyDisc(skyState.skyColor);

        //renderSunMoonAndStars (without the Moon)
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        stack.mulPose(Axis.XP.rotationDegrees(skyState.sunAngle));

        ///renderSun
        Matrix4fStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.pushMatrix();
        matrixStack.mul(stack.last().pose());
        matrixStack.translate(0.0F, 100.0F, 0.0F);
        matrixStack.scale(30.0F, 1.0F, 30.0F);
        GpuBufferSlice sSlice = RenderSystem.getDynamicUniforms()
                .writeTransform(matrixStack, new Vector4f(1.0F, 1.0F, 1.0F, skyState.rainBrightness), new Vector3f(), new Matrix4f());
        GpuTextureView sct = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
        GpuTextureView sdt = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
        GpuBuffer buffer = this.quadIndices.getBuffer(6);

        try (RenderPass pass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(() -> "Sun", sct, OptionalInt.empty(), sdt, OptionalDouble.empty())) {
            pass.setPipeline(RenderPipelines.CELESTIAL);
            RenderSystem.bindDefaultUniforms(pass);
            pass.setUniform("DynamicTransforms", sSlice);
            pass.bindTexture("Sampler0", skyRenderer.celestialsAtlas.getTextureView(), skyRenderer.celestialsAtlas.getSampler());
            pass.setVertexBuffer(0, skyRenderer.sunBuffer);
            pass.setIndexBuffer(buffer, this.quadIndices.type());
            pass.drawIndexed(0, 0, 6, 1);
        }

        matrixStack.popMatrix();
        ///

        ///renderStars, kind of
        if (star > 0.0F) {
            Matrix4fStack matrixstack = RenderSystem.getModelViewStack();
            matrixstack.pushMatrix();
            matrixstack.mul(stack.last().pose());
            RenderPipeline pipeline = RenderPipelines.STARS;
            GpuTextureView tct = Minecraft.getInstance().getMainRenderTarget().getColorTextureView();
            GpuTextureView tdt = Minecraft.getInstance().getMainRenderTarget().getDepthTextureView();
            GpuBuffer indices = this.starIndices.getBuffer(this.starIndexCount);
            GpuBufferSlice starslice = RenderSystem.getDynamicUniforms()
                    .writeTransform(matrixstack, new Vector4f(star, star, star, star), new Vector3f(), new Matrix4f());

            try (RenderPass pass = RenderSystem.getDevice()
                    .createCommandEncoder()
                    .createRenderPass(() -> "Stars", tct, OptionalInt.empty(), tdt, OptionalDouble.empty())) {
                pass.setPipeline(pipeline);
                RenderSystem.bindDefaultUniforms(pass);
                pass.setUniform("DynamicTransforms", starslice);
                pass.setVertexBuffer(0, this.starVBO);
                pass.setIndexBuffer(indices, this.starIndices.type());
                pass.drawIndexed(0, 0, this.starIndexCount, 1);
            }

            matrixstack.popMatrix();
        }
        ///

        stack.popPose();
        //

        //renderDarkDisc
        if (skyState.shouldRenderDarkDisc) {
            skyRenderer.renderDarkDisc();
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
                        .createBuffer(() -> "Gaia star buffer", 40, mesh.vertexBuffer());

            }
        }

        return vertexBuffer;
    }

    public static float getStarBrightness(ClientLevel world, float par1) {
        Player player = Minecraft.getInstance().player;
        Optional<ResourceKey<Biome>> biome = world.getBiome(player.blockPosition()).unwrapKey();

        return biome.filter(GaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElse(par1);
    }
}
