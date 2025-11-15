package de.thedon.oresandtools.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class ValyrianChestRenderState extends BlockEntityRenderState {
    public ChestType type;
    public float open;
    public float angle;

    public ValyrianChestRenderState() {
        this.type = ChestType.SINGLE;
    }
}
