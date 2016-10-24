package com.android.lf.lroid.m.bean;

import java.util.ArrayList;

public class FoodRecipeBean {
        private String title;
        private ArrayList<FoodStepBean> method;
        private String ingredients;
        private String sumary;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<FoodStepBean> getMethod() {
            return method;
        }

        public void setMethod(ArrayList<FoodStepBean> method) {
            this.method = method;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getSumary() {
            return sumary;
        }

        public void setSumary(String sumary) {
            this.sumary = sumary;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }



    }