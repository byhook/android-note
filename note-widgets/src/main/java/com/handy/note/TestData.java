package com.handy.note;

import java.util.Objects;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public class TestData {

    public int index;

    public String data = String.valueOf(System.currentTimeMillis());

    public TestData(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestData testData = (TestData) o;
        return testData.index == this.index;
    }

}
