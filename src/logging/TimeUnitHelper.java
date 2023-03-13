package logging;

public class TimeUnitHelper {
    /**
     * Converts time in nanoseconds to the specified unit.
     * @param nanos - time in nanoseconds
     * @param unit - unit of time to convert to
     * @return time in the specified unit
     */
    public static double convert(long nanos, TimeUnit unit){
        int difference = unit.ordinal() - TimeUnit.NANOSECONDS.ordinal();
        return nanos / Math.pow(10, difference*3);
    }

    /**
     * Converts time in the given unit to the specified unit.
     * @param time - time in the specified unit
     * @param from - unit of time to convert from
     * @param to - unit of time to convert to
     * @return time in the specified unit
     */
    public static double convert(double time, TimeUnit from, TimeUnit to)
    {
        int difference = from.ordinal() - to.ordinal();
        return time / Math.pow(10, difference*3);
    }
}
