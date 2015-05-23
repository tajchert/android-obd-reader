package pt.lighthouselabs.obd.reader;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRuntimeObdCommand;

/**
 * Created by Michal Tajchert on 2015-05-23.
 */
public class AirFuelRatioCommand extends ObdCommand {

    private int value = 0;

    /**
     * Default ctor.
     */
    public AirFuelRatioCommand() {
        super("01 44");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link pt.lighthouselabs.obd.commands.engine.EngineRuntimeObdCommand} object.
     */
    public AirFuelRatioCommand(EngineRuntimeObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        value = (buffer.get(2) * 256 + buffer.get(3)) / 32768;
    }

    @Override
    public String getFormattedResult() {
        // determine time
        final String hh = String.format("%02d", value / 3600);
        final String mm = String.format("%02d", (value % 3600) / 60);
        final String ss = String.format("%02d", value % 60);
        return String.format("%s:%s:%s", hh, mm, ss);
    }

    @Override
    public String getName() {
        return "Fuel/Air commanded equivalence ratio";
    }

}
