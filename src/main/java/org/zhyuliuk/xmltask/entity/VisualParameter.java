package org.zhyuliuk.xmltask.entity;

import java.util.Objects;

public class VisualParameter {
    private String stemColor;
    private String leafColor;
    private int averageSize;

    public VisualParameter(String stemColor, String leafColor, int averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public VisualParameter() {

    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameter that = (VisualParameter) o;
        return averageSize == that.averageSize && Objects.equals(stemColor, that.stemColor) && Objects.equals(leafColor, that.leafColor);
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < stemColor.length(); i++) {
            result = 31 * result + stemColor.charAt(i);
        }
        for (int i = 0; i < leafColor.length(); i++) {
            result = 31 * result + leafColor.charAt(i);
        }
        result = 31 * result + averageSize;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("stemColor='").append(stemColor).append('\'').
                append(", leafColor='").append(leafColor).append('\'').append(", averageSize=").append(averageSize).append('}');
        return stringBuilder.toString();

    }
}
