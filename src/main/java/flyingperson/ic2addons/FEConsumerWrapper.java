package flyingperson.ic2addons;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class FEConsumerWrapper extends TileEntity implements IEnergyAcceptor, ITickable {
    public TileEntity te;
    public IEnergyStorage cap;
    public EUSink energySink;

    public FEConsumerWrapper(TileEntity te) {
        if (!Utils.hasCapability(te, CapabilityEnergy.ENERGY)) {
            throw new RuntimeException("Tried to make FEConsumerWrapper out of TileEntity that can't consume FE");
        } else {
            this.te = te;
            this.cap = Utils.getCapability(te, CapabilityEnergy.ENERGY);
            assert cap != null;
            this.energySink = new EUSink(te, cap);
        }
        this.pos = te.getPos();
        this.tileEntityInvalid = te.isInvalid();
        this.world = te.getWorld();
    }

    @Override
    public void update() {
        te.getWorld();
        te.getPos();
        if (!Utils.hasCapability(te.getWorld().getTileEntity(te.getPos()), CapabilityEnergy.ENERGY)) {
            this.onChunkUnload();
            this.invalidate();
        }
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return te.getCapability(CapabilityEnergy.ENERGY, enumFacing).canReceive();
    }

    @Override
    public void onLoad() {
        energySink.onLoad();
        super.onLoad();
    }

    @Override
    public void invalidate() {
        energySink.invalidate();
        super.invalidate();
    }

    @Override
    public void onChunkUnload() {
        energySink.onChunkUnload();
        super.onChunkUnload();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        energySink.readFromNBT(nbt);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        energySink.writeToNBT(nbt);
        super.writeToNBT(nbt);
        return nbt;
    }

}
