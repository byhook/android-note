package com.handy.note;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public class TestData {

    public int roomId;

    public String data = String.valueOf(System.currentTimeMillis());

    public TestData(int index) {
        this.roomId = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestData testData = (TestData) o;
        return testData.roomId == this.roomId;
    }

    @Override
    public String toString() {
        return "TestData{" + "roomId=" + roomId + '\'' + '}';
    }
}
