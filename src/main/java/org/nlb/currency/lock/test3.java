package org.nlb.currency.lock;

import lombok.extern.slf4j.Slf4j;
import org.nlb.currency.anotations.threadSafe;

@threadSafe
@Slf4j
public class test3 {
    private  test3 (){
    }
    public static test3 getTest3(){
        return single.INSTENCE.getSingle();
    }
    private enum single{
        INSTENCE;
        private test3 single;
        single(){
            single = new test3();
        }
        public  test3 getSingle(){
            return  single;
        }
    }
}
