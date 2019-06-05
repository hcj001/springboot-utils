package com.demo.springbootmybatis.entity;

import java.io.Serializable;

public  class Apple implements Serializable {
    private static final long serialVersionUID = 771866595909233419L;
    private String color;
        private Double weight;
        public Apple(String color, Double weight) {
            this.color = color;
            this.weight = weight;
        }
        public Apple() {}
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public Double getWeight() {
            return weight;
        }
        public void setWeight(Double weight) {
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "Apple [color=" + color + ", weight=" + weight + "]";
        }
    }
