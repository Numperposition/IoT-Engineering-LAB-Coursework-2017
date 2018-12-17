package cn.edu.bupt.iot.lab.hal;

import java.util.Map;

/**
 * @author asaba
 * Date: 2018/1/3
 * Under Creative Commons BY-NC-SA License.
 */
public interface RFIDScanner {
    RFIDScanner up();

    boolean down();

    Map<String, Integer> inventory();

    RFIDScanner use(String id);

    String read();

    RFIDScanner write(String partition, String data);
}
