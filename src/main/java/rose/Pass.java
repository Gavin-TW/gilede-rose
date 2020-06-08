package rose;

import java.time.LocalDate;

/**
 * @author Gavin
 */
public class Pass extends Production {
    
    private final LocalDate showDay;
    
    public Pass(LocalDate createDay, LocalDate showDay, int quality) throws ProductionException {
        super(createDay, quality);
        this.showDay = showDay;
    }
    
    @Override
    public long getActuallyQuality() {
        long days = LocalDate.now().toEpochDay() - this.getCreateDay().toEpochDay();
        return LocalDate.now().isBefore(showDay) ? this.getQuality() + getPassAddPoint(days) : 0;
    }
    
    private long getPassAddPoint(long days) {
        return days - 5 > 0 ? 10 + (days - 5) * 3 : days * 2;
    }
}
