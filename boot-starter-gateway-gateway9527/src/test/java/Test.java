import java.time.ZonedDateTime;

/**
 * Created by tianxuanxuan
 * On 2020-09-11 15:21
 */
public class Test {
    @org.junit.Test
    public void getTime(){
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}
