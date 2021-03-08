import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;

import java.util.Arrays;

@Log4j2
public class LogTst {
    @Test
    public void testLogLevels(){
        //final Logger log = LogManager.getLogger("ClientHandlerLogger");
        ThreadContext.put("user", "nickname");
        log.fatal("fatal"); // 100
        log.error("error"); // 200
        log.warn("warn"); // 300
        log.info("info"); // 400
        log.debug("debug"); // 500
        log.trace("trace"); // 600
    }

    @Test
    public int[] getArrAfterLast4(int[] inArr){
        for (int i = inArr.length-1; i >= 0; i--) {
            if (inArr[i] == 4){
                return Arrays.copyOfRange(inArr,i+1,inArr.length);
            }
        }
        throw new RuntimeException();
    }


}
