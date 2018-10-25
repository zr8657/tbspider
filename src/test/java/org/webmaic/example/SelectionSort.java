package org.webmaic.example;

public class SelectionSort {
    public static void main(String[] arg0) {
        int n[] = ArrayUtil.generatingArray();
        ArrayUtil.sysOutArray(n);
        int length = n.length;
        for (int i=0;i<length;i++){
            int minIndex = i;
            for (int j=i+1;j<length;j++){
                if (n[j]<n[minIndex]){
                    minIndex=j;
                }
            }
            int temp = n[i];
            n[i] = n[minIndex];
            n[minIndex] = temp;
        }
        ArrayUtil.sysOutArray(n);
    }
}
