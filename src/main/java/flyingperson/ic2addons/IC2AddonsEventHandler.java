package flyingperson.ic2addons;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class IC2AddonsEventHandler {
    @SubscribeEvent
    public void onEvent(BlockEvent.PlaceEvent event) {
        World world = event.getWorld();
        if (event.getPlacedBlock().getBlock().hasTileEntity(event.getPlacedBlock())) {
            TileEntity te = world.getTileEntity(event.getPos());
            if (Utils.hasCapability(te, CapabilityEnergy.ENERGY)) {
                world.addTileEntity(new FEConsumerWrapper(te));
            }
        }
    }
    @SubscribeEvent
    public void onEvent(BlockEvent.BreakEvent event) {
        World world = event.getWorld();
        if (event.getState().getBlock().hasTileEntity(event.getState())) {
            TileEntity te = world.getTileEntity(event.getPos());
            if (Utils.hasCapability(te, CapabilityEnergy.ENERGY)) {
                world.removeTileEntity(te.getPos());
            }
        }
    }
    @SubscribeEvent
    public void onEvent(ChunkEvent.Load event) {
        for (Map.Entry<BlockPos, TileEntity> entry : event.getChunk().getTileEntityMap().entrySet()) {
            if (Utils.hasCapability(entry.getValue(), CapabilityEnergy.ENERGY)) {
                entry.getValue().getWorld().addTileEntity(new FEConsumerWrapper(entry.getValue()));
            }
        }
    }
    @SubscribeEvent
    public void onEvent(ChunkEvent.Unload event) {
        for (Map.Entry<BlockPos, TileEntity> entry : event.getChunk().getTileEntityMap().entrySet()) {
            if (Utils.hasCapability(entry.getValue(), CapabilityEnergy.ENERGY)) {
                //entry.getValue().getWorld().addTileEntity(new FEConsumerWrapper(entry.getValue()));
            }
        }
    }
}
