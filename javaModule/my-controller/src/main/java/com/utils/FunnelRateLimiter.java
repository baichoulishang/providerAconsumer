package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈宜康
 * @date 2019/9/22 20:31
 * @forWhat
 */
public class FunnelRateLimiter {
    // 漏斗map
    private Map<String, Funnel> funnels = new HashMap<>();

    /**
     * 判断某个操作是否允许
     *
     * @param userId      用户id
     * @param actionKey   操作主键.每种行为都有一个唯一主键
     * @param capacity    漏斗预定容量
     * @param leakingRate 流水速度.其实可以映射成某个行为的操作速度
     * @return boolean 是否允许.true:允许;false:不允许
     * @author CYK
     * @Date 20:44 2019/9/22
     **/
    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        // 增加了一次操作,需要1个空间
        return funnel.watering(1);
    }

    static class Funnel {
        // 漏斗容量
        int capacity;
        // 流水速率(假设整个漏斗的流水速率恒定)
        float leakingRate;
        // 剩余空间
        int leftQuota;
        // 上一次检测漏水时间(或者创建漏斗的时间)
        long leakingTs;


        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }


        /**
         * 更新一下漏斗状态
         *
         * @param
         * @return void
         * @author CYK
         * @Date 20:41 2019/9/22
         **/
        void makeSpace() {
            // 现在
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            // 计算从上一次检测到现在为止流出的水的数量,也就是腾出的空间大小
            int deltaQuota = (int) (deltaTs * leakingRate);
            // 间隔时间太长，整数数字过大溢出
            if (deltaQuota < 0) {
                // 重置所有数据
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return; //腾出空间太小，最小单位是1
            }
            // 腾出空间太小.最小单位是1
            if (deltaQuota < 1) {
                return;
            }
            // 触发漏水,并且更新剩余空间
            this.leftQuota += deltaQuota;
            // 更新上一次检测的时间,也就是现在
            this.leakingTs = nowTs;
            // 如果剩余空间大小超出了漏斗范围.但是这种情况不太可能吧
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        // 加水
        boolean watering(int quota) {
            // 先更新一下漏斗状态,因此从上一次检测到现在会漏出水,那么允许操作的次数也会增加
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }
}
