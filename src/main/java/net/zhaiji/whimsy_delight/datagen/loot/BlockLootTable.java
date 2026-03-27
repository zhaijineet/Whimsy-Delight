package net.zhaiji.whimsy_delight.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.block.MeatRafflesiaCropBlock;
import net.zhaiji.whimsy_delight.init.WDBlocks;
import net.zhaiji.whimsy_delight.init.WDItems;

import java.util.Set;

public class BlockLootTable extends BlockLootSubProvider {
    public BlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // 大五花作物：按年龄分掉落
        // age 0 → 种子, age 1 → 种子, age 2 → 花苞, age 3 → 果实
        Block cropBlock = WDBlocks.MEAT_RAFFLESIA.get();
        LootItemBlockStatePropertyCondition.Builder age0 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock)
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MeatRafflesiaCropBlock.AGE, 0));
        LootItemBlockStatePropertyCondition.Builder age1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock)
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MeatRafflesiaCropBlock.AGE, 1));
        LootItemBlockStatePropertyCondition.Builder age2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock)
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MeatRafflesiaCropBlock.AGE, 2));

        LootTable.Builder lootTable = LootTable.lootTable()
            .withPool(
                applyExplosionCondition(
                    cropBlock,
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(
                            LootItem.lootTableItem(WDItems.MEAT_RAFFLESIA_SEEDS.get()).when(age0)
                                .otherwise(LootItem.lootTableItem(WDItems.MEAT_RAFFLESIA_SEEDS.get()).when(age1))
                                .otherwise(LootItem.lootTableItem(WDItems.MEAT_RAFFLESIA_BUD.get()).when(age2))
                                .otherwise(LootItem.lootTableItem(WDItems.MEAT_RAFFLESIA.get()))
                        )
                )
            );

        add(cropBlock, lootTable);

        // 其他方块默认掉落自身
        WDBlocks.BLOCK.getEntries().stream()
            .map(RegistryObject::get)
            .filter(block -> block != WDBlocks.MEAT_RAFFLESIA.get())
            .forEach(this::dropSelf);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return WDBlocks.BLOCK.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
