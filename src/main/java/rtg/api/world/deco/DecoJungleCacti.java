package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenJungleCacti;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;


/**
 * @author WhichOnesPink
 */
public class DecoJungleCacti extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private boolean sandOnly;
    private int extraHeight;

    public DecoJungleCacti() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setStrengthFactor(8f);
        this.setMaxY(255); // No height limit by default.
        this.setSandOnly(false);
        this.setExtraHeight(7);

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(IRealisticBiome biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), CACTUS)) {

                WorldGenerator worldGenerator = new WorldGenJungleCacti(this.sandOnly, rand.nextInt(this.extraHeight));

                for (int i = 0; i < this.strengthFactor * strength; i++) {
                    int intX = worldX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(160);
                    int intZ = worldZ + rand.nextInt(16) + 8;

                    if (intY < this.maxY) {
                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoJungleCacti setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoJungleCacti setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public boolean isSandOnly() {

        return sandOnly;
    }

    public DecoJungleCacti setSandOnly(boolean sandOnly) {

        this.sandOnly = sandOnly;
        return this;
    }

    public int getExtraHeight() {

        return extraHeight;
    }

    public DecoJungleCacti setExtraHeight(int extraHeight) {

        this.extraHeight = extraHeight;
        return this;
    }
}
