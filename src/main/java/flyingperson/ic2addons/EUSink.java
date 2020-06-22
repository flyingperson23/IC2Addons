package flyingperson.ic2addons;

import ic2.api.energy.prefab.BasicSink;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;

public class EUSink extends BasicSink {

    IEnergyStorage storage;

    public EUSink(TileEntity parent, IEnergyStorage storage) {

        super(parent, Utils.divideByRfToEu(storage.getMaxEnergyStored()), Integer.MAX_VALUE);
        this.storage = storage;
        this.tier = Integer.MAX_VALUE;
    }

    @Override
    public double getDemandedEnergy() {
        return Math.max(0.0D, Utils.divideByRfToEu(storage.getMaxEnergyStored() - storage.getEnergyStored()));
    }

    @Override
    public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
        int actualAmount = (int) amount * Config.RF_PER_EU;
        double received = storage.receiveEnergy(actualAmount, false);
        return amount - Utils.divideByRfToEu(received);
    }

    @Override
    protected String getNbtTagName() {
        return "IC2AddonsEUSink";
    }
}
