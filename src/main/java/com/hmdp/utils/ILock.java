package com.hmdp.utils;

/**
 * @BelongsProject: hm-dianping
 * @BelongsPackage: com.hmdp.utils
 * @Author: zt
 * @CreateTime: 2023-03-25  14:47
 * @Description:
 */
public interface ILock {

    /*
     * @Description: 尝试获取锁
     * @Author: zt
     * @Date: 2023/3/25 14:47
     * @param: [timeoutSec 锁持有的超时时间，过期后自动释放]
     * @return: boolean
     **/
    boolean tryLock(long timeoutSec);

    /*
     * @Description: 释放锁
     * @Author: zt
     * @Date: 2023/3/25 14:48
     * @param: []
     * @return: void
     **/
    void unlock();

}
