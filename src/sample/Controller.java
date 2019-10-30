package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class Controller {

    final int canvasHeight = 600;
    final int canvasWidth = 900;
    final int boxWidth = 10;
    final int numberOfBoxes = canvasWidth/boxWidth;
    public static int[] arr;
    GraphicsContext canvas;


    @FXML Canvas mainCanvas;
    @FXML Button bubSort;
    @FXML Button gnomSort;
    @FXML Button slctSort;

    @FXML
    void reset(){
        gnomSort.setDisable(false);
        slctSort.setDisable(false);
        bubSort.setDisable(false);
        generateArr();
        draw();
    }


    @FXML
    void draw(){
        canvas = mainCanvas.getGraphicsContext2D();
        canvas.setStroke(Color.BLACK);
        canvas.setLineWidth(3);
        canvas.clearRect(0,0,canvasWidth,canvasHeight);
        for(int i = 0; i < arr.length;i++){
            canvas.strokeLine(i,canvasHeight,i,canvasHeight-arr[i]);
        }
  }
    @FXML
    void generateArr(){
        arr = new int[canvasWidth];
        Random rand = new Random();
        for(int i = 0;i<canvasWidth;i++){
            arr[i] = rand.nextInt(canvasHeight);
        }
        draw();
    }

    void swap(int[] arr,int a,int b) throws InterruptedException {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;;
    }

    @FXML
    void bubbleSort() throws InterruptedException {
        gnomSort.setDisable(true);
        slctSort.setDisable(true);
        Thread thread2 = new Thread(){
            public void run(){
                int i;
                int j;
                int n = arr.length;

                for (i = 0; i < n - 1; i++) {
                    for (j = 0; j < n - i - 1; j++) {
                        if (arr[j] > arr[j + 1]) {
                            try {
                                swap(arr, j + 1, j);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    draw();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        thread2.start();
    }

    void quickSort(int[] arr , int start, int end) throws InterruptedException {
        if (start >= end) {
            return;
        }
        int index = partition(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);

    }

    int partition(int[] arr, int start, int end) throws InterruptedException{
        int pIndex = start;
        Thread th2 = new Thread(){
            public void run(){
                int pIndex = start;
                int pVal = arr[end];
                int i;
                for(i = start; i < end;i++){
                    if(arr[i] < pVal){
                        try {
                            swap(arr,pIndex,i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pIndex++;
                    }
                }
                try {
                    swap(arr,pIndex,end);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                draw();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th2.start();
        return 0;
    }



    @FXML
    void selectionSort() {
        bubSort.setDisable(true);
        gnomSort.setDisable(true);
        Thread th2 = new Thread(){
            public void run(){
                int n = arr.length;
                for (int i = 0; i < n - 1; i++) {
                    int mIndex = i;
                    for (int j = i + 1; j < n; j++) {
                        if (arr[j] < arr[mIndex]) {
                            mIndex = j;
                        }
                    }
                    try {
                        swap(arr,i, mIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    draw();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        th2.start();

    }

    @FXML
    void gnomeSort() throws InterruptedException {
        bubSort.setDisable(true);
        slctSort.setDisable(true);
        Thread th2 = new Thread(){
            @Override
            public void run() {
                int pos = 0;
                while (pos < arr.length){
                    if(pos ==0 || arr[pos] >= arr[pos-1]){
                        pos ++;
                    }
                    else{
                        try {
                            swap(arr,pos,pos-1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pos --;
                    }
                    draw();
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        th2.start();
    }

//    void insertionSort(){
//        int i = 1;
//        while(i < arr.length){
//            int j = 1;
//            while(j > 0 && arr[j-1] > arr[j]){
//                j ++;
//            }
//            i++;
//        }
//    }

}
