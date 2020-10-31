package net.bomee.event;

/**
 * 排序接口
 *
 * @author bomee shiaupo@qq.com
 */
public interface Ordered {

    /**
     * 高优先级
     */
    int HIGH_PRIORITY = Integer.MIN_VALUE;

    /**
     * 普通优先级
     */
    int NORMAL_PRIORITY = 0;

    /**
     * 低优先级
     */
    int LOW_PRIORITY = Integer.MAX_VALUE;

    /**
     * 获取顺序, 数字越低优先级越高
     *
     * @return 顺序
     */
    int getOrder();
}
