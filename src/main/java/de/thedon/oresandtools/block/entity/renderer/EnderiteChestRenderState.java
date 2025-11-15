package de.thedon.oresandtools.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class EnderiteChestRenderState extends BlockEntityRenderState {
    public ChestType type;
    public float open;
    public float angle;

    public EnderiteChestRenderState() {
        this.type = ChestType.SINGLE;
    }
}
