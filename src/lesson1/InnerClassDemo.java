package lesson1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

/**
 * 内置类实例
 *
 * @author Direct-X
 */
public class InnerClassDemo {

    // static块内部类
    static {
        new Runnable() {

            @Override
            public void run() {

            }
        };
    }

    // 实例块内部类
    {
        new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
    }

    // 构造器内部类

    public InnerClassDemo() {
        new Comparable() {

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        //方法（类或实例）内部类
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };
    }

    static class PropertyChangeListenerImpl implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }
    }


}
