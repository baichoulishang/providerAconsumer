package springLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 陈宜康
 * @date 2019/9/13 19:49
 * @forWhat
 */
public class BeanPostProcessorTest implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("调用了BeanPostProcessor的before----------" + o.getClass().getSimpleName());
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("调用了BeanPostProcessor的after----------" + o.getClass().getSimpleName());
        return o;
    }
}
