package rose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

/**
 * @author Gavin
 */
// TODO: 0<=商品的价值<=50
// TODO: 普通商品 每过1天下滑1点
// TODO: 普通商品 过了保质期，每过1天，下滑2点
// TODO: 后台门票是一种特殊的商品
// TODO: 后台门票演出日价值最高
// TODO: 后台门票 演出前10天，每天上涨2点 （共涨5天）
// TODO: 后台门票 演出前5天，每天上涨3点 （共涨5天）
// TODO: 一旦过了演出日，后台门票价值为0
public class GiledeRoseTest {
    
    @DisplayName("0<=商品的价值<=50")
    @ParameterizedTest
    @CsvSource({
            "1",
            "20",
    })
    public void should_production_quality_between_0_and_50(int quality) throws ProductionException {
        Production production = new Production(LocalDate.now(), quality);
        Assertions.assertEquals(quality, production.getQuality());
    }
    
    @DisplayName("商品的价值 not in [0,50]")
    @ParameterizedTest
    @CsvSource({
            "-1",
            "59",
    })
    public void should_throw_exception_if_quality_is_not_in_0_and_50(int quality) throws ProductionException {
        Assertions.assertThrows(ProductionException.class, () -> {
            new Production(LocalDate.now(), quality);
        });
    }
    
    @DisplayName("普通商品 每过1天下滑1点")
    @ParameterizedTest
    @CsvSource({
            "27,2020-06-05,30",
            "17,2020-06-05,20",
            "0,2020-05-05,20",
    })
    public void should_production_decrise_1_Point(int actuallyQuality, LocalDate createDay, int totalQuality) throws ProductionException {
        Production production = new Production(createDay, totalQuality);
        Assertions.assertEquals(actuallyQuality, production.getActuallyQuality());
    }
    
    @DisplayName("后台门票 演出前[10,5]天，每天上涨2点 && 演出前5天，每天上涨3点 （共涨5天）")
    @ParameterizedTest
    @CsvSource({
            "26,2020-06-01,2020-06-09,10",
            "23,2020-06-02,2020-06-09,10",
    })
    public void should_pass_increase_2_Point_before_expired_5(int actuallyQuality,
                                                              LocalDate createDay,
                                                              LocalDate showDay,
                                                              int totalQuality) throws ProductionException {
        Pass pass = new Pass(createDay, showDay, totalQuality);
        Assertions.assertEquals(actuallyQuality, pass.getActuallyQuality());
    }
    
}
