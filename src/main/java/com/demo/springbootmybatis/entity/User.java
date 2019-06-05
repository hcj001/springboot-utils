package com.demo.springbootmybatis.entity;

import java.util.List;
import java.util.Map;

public class User {

        private String name;
        private String age;
        private List<Map<String,Object>> testMap;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

    public List<Map<String, Object>> getTestMap() {
        return testMap;
    }

    public void setTestMap(List<Map<String, Object>> testMap) {
        this.testMap = testMap;
    }
}
