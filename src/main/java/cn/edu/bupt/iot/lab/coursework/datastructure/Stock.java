package cn.edu.bupt.iot.lab.coursework.datastructure;

import cn.edu.bupt.iot.lab.hal.RFIDScanner;
import cn.edu.bupt.iot.lab.hal.SerialRFIDScanner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author asaba
 * Date: 2017/12/30
 * Under Creative Commons BY-NC-SA License.
 */
public class Stock {
    /**
     * A {@link Stock} is a list of {@link Tag}s with management functions.
     *
     * @param name The name of this {@link Stock}
     */
    public Stock(String name) {
        logger = Logger.getLogger("Stock." + name);

        tags = new HashSet<Tag>();

        logger.info("Initializing RFID scanner...");
        scanner = new SerialRFIDScanner("/dev/ttys0", 115200);
        scanner.up();
        logger.info("Scanner is up");
    }

    /**
     * Scan and store available {@link Tag}s.
     *
     * @return Current {@link Stock} object
     */
    public Stock scan() {
        Map<String, Integer> tagsData;
        tagsData = scanner.inventory();
        for (String epcId : tagsData.keySet()) {
            logger.fine("Inventory: parsing data \"" + epcId + "\"...");
            tags.add(new Tag(epcId, tagsData.get(epcId)));
        }
        return this;
    }

    /**
     * Judge if a {@link Tag} is in the {@link Stock}.
     *
     * @param id The ID string for targeted {@link Tag}
     * @return True if the {@link Tag} is in this {@link Stock}
     */
    public boolean has(String id) {
        for (Tag tag : tags) {
            if (id.equals(tag.getId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get a {@link Tag} in current {@link Stock} by id.
     *
     * @param id ID string of targeted {@link Tag}
     * @return A {@link Tag} object or null if not in {@link Stock}
     */
    public Tag get(String id) {
        for (Tag tag : tags) {
            if (id.equals(tag.getId())) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Add a {@link Tag} into current {@link Stock}.
     *
     * @param tag A {@link Tag} to be added
     * @return Current {@link Stock} object
     */
    public Stock add(Tag tag) {
        tags.add(tag);
        return this;
    }

    /**
     * Remove a {@link Tag} from current {@link Stock}.
     *
     * @param id The ID string of the {@link Tag} to be removed
     * @return Current {@link Stock} object
     */
    public Stock remove(String id) {
        if (this.has(id)) {
            tags.remove(this.get(id));
        }
        return this;
    }

    /**
     * Get all {@link Tag}s in current {@link Stock}
     *
     * @return A {@link Set} of {@link Tag}s
     */
    public Set<Tag> allTags() {
        return this.tags;
    }

    private Set<Tag> tags;
    private RFIDScanner scanner;
    private Logger logger;
}
