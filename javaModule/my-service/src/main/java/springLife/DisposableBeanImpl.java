package springLife;

import org.springframework.beans.factory.DisposableBean;

public class DisposableBeanImpl implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("调用接口DisposableBean的destroy方法");
    }

}
