package telran.time;

public class TimePoint implements Comparable<TimePoint> {
    private float amount;
    private TimeUnit timeUnit;

    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }

    @Override
    public int compareTo(TimePoint arg0) {
        return Float.compare(this.convert(arg0.getTimeUnit()).getAmount(), arg0.getAmount());
    }

    public float getAmount() {
        return amount;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TimePoint timePoint = (TimePoint) obj;
        TimePoint converted = timePoint.convert(this.timeUnit);
        return Float.compare(this.amount, converted.amount) == 0;
    }

    public TimePoint convert(TimeUnit timeUnit) {
        // TODO
        // returns new TimePoint object equaled to the "this" object but
        // with a gibven timeUnit
        float newAmount = (this.amount * this.timeUnit.getValueOfSeconds()) / timeUnit.getValueOfSeconds();
        return new TimePoint(newAmount, timeUnit);
    }

    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

}
