package flyingperson.ic2addons;

import flyingperson.ic2addons.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_GENERAL = "general";

    public static int RF_PER_EU = 4;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            IC2Addons.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        RF_PER_EU = cfg.getInt("rf_per_eu", CATEGORY_GENERAL, RF_PER_EU, 1, Integer.MAX_VALUE, "How many RF make 1 EU");
    }
}