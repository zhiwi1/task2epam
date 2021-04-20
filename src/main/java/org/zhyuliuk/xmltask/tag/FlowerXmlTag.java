package org.zhyuliuk.xmltask.tag;

public enum FlowerXmlTag {

    FLOWERS("flowers"),
    TIME_FLOWER("time-flower"),
    ID("id"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters"),
    GROWING_TIPS("growing-tips"),
    NAME("name"),
    SOIL("soil"),
    STEM_COLOR("stem-color"),
    LEAF_COLOR("leaf-color"),
    AVERAGE_SIZE("average-size"),
    TEMPERATURE("temperature"),
    LIGHTNING("lightning"),
    WATERING("watering"),
    MULTIPLYING("multiplying"),
    TRANSFER_TIME("transfer-time");
    private String value;

    FlowerXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
