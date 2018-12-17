package cn.edu.bupt.iot.lab.coursework.datastructure;

/**
 * @author asaba
 * Date: 2017/12/30
 * Under Creative Commons BY-NC-SA License.
 */
public class Tag {
    public Tag(String id) {
        this(id, 0);
    }

    public Tag(String id, int rssi) {
        this.id = id;
        this.rssi = 0;
    }

    public String getId() {
        return id;
    }

    public int getRssi() {
        return rssi;
    }

    private String id;
    private int rssi;
}
