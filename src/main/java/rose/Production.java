package rose;

import lombok.Getter;

import java.time.LocalDate;

/**
 * @author Gavin
 */
public class Production {
    @Getter
    private LocalDate createDay;
    @Getter
    private int quality;
    
    public Production(LocalDate now, int quality) throws ProductionException {
        this.createDay = now;
        if (quality <= 50 && quality >= 0) this.quality = quality;
        else throw new ProductionException("the quality is not in [0,50]");
    }
    
    public long getActuallyQuality() {
        
        long days = LocalDate.now().toEpochDay() - createDay.toEpochDay();
        return (quality - days * 1) > 0 ? quality - days * 1 : 0;
    }
}
