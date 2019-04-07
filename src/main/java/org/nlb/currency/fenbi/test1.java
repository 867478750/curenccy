package org.nlb.currency.fenbi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class test1 {
    private final static ThreadLocal<Long> request = new ThreadLocal();
    public static void add(Long id){
        request.set(id);
    }
    public static Long getId(){
        return request.get();
    }
    public void remove(){
        request.remove();
    }
}
