package rtg.world.biome.realistic.morechinesemc;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.morechinesemc.config.BiomeConfigMCMLoessPlateau;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.morechinesemc.SurfaceMCMLoessPlateau;
import rtg.world.gen.terrain.morechinesemc.TerrainMCMLoessPlateau;

public class RealisticBiomeMCMLoessPlateau extends RealisticBiomeMCMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMCMLoessPlateau(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMCMLoessPlateau(true, 35f, 160f, 60f, 40f, 69f),
            new SurfaceMCMLoessPlateau(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = BlockUtil.getStateLog(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigMCMLoessPlateau.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.logBlock = BlockUtil.getStateLog(1);
        decoShrub.leavesBlock = BlockUtil.getStateLeaf(1);
        decoShrub.maxY = 90;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 12;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 30;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
