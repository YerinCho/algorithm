package bj;

import java.io.*;
import java.util.*;

public class Q5896 {
    // ㅈㄴ어려움 못풂
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static long M;
    private static List<CowPrice> cows = new ArrayList<>();
    private static PriorityQueue<CowPrice> orderByPrice;
    private static PriorityQueue<CowPrice> orderByCoupon;
    private static PriorityQueue<CowPrice> orderBySale;
    private static Set<CowPrice> set;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        set = new HashSet<>(cows.size());
        orderByPrice = new PriorityQueue<>(CowPrice::sortByPrice);
        orderByCoupon = new PriorityQueue<>(CowPrice::sortByCoupon);
        orderBySale = new PriorityQueue<>(CowPrice::sortBySale);
        orderByCoupon.addAll(cows);
        orderBySale.addAll(cows);

//        int c = buy(K, M, 0, new HashSet<>());


        // 일단 쿠폰 개수만큼 할인적용시켜서 할인가 젤 싼 소들 사기
        long a, b;
        CowPrice cowPrice;
        for (int i = 0; i < N; i++) {
            if (K > 0) {
                cowPrice = orderByCoupon.poll();
                if (M < cowPrice.coupon) break;
                orderByPrice.add(cowPrice);
                K--;
                M -= cowPrice.coupon;
            } else {
                cowPrice = orderByPrice.peek();
//                a = M -
            }
        }

        bw.write(1 + "\n");
        bw.flush();
        bw.close();
    }

    // 중복제거를 안햇다...
    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        M = Long.parseLong(input[2]);
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            cows.add(new CowPrice(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
    }

    static class CowPrice {
        long price;
        long coupon;

        public CowPrice(long price, long coupon) {
            this.price = price;
            this.coupon = coupon;
        }

        public int sortByPrice(CowPrice cowPrice) {
            return Long.compare(price, cowPrice.price);
        }

        public int sortByCoupon(CowPrice cowPrice) {
            return Long.compare(coupon, cowPrice.coupon);
        }

        public int sortBySale(CowPrice cowPrice) {
            return Long.compare(price - coupon, cowPrice.price - cowPrice.coupon);
        }
    }

    private static int buy(int k, long m, int count, Set<CowPrice> cowList) {
        CowPrice cowPrice, cowSale = null;
        int withCoupon = count;
        int withoutCoupon = count;
        if (k > 0) {
            // 쿠폰을 사용했을 경우
            cowSale = orderByCoupon.poll();
            if (cowSale != null && m >= cowSale.coupon) {
                int size = cowList.size();
                cowList.add(cowSale);
                if (size < cowList.size()) {
                    withCoupon = buy(k - 1, m - cowSale.coupon, count + 1, cowList);
                    cowList.remove(cowSale);
                }
//                orderBySale.add(cowPrice);
            }
        }
        //쿠폰을 사용하지 않을 경우
        cowPrice = orderByPrice.poll();
        if (cowPrice != null && m >= cowPrice.price) {
            int size = cowList.size();
            cowList.add(cowPrice);
            if (size < cowList.size()) {
                withoutCoupon = buy(k, m - cowPrice.price, count + 1, cowList);
                cowList.remove(cowPrice);
            }
        }
        if (cowPrice != null) {
            orderByPrice.add(cowPrice);
//            cowList.remove(cowPrice);
        }
        if (cowSale != null) {
            orderByCoupon.add(cowSale);
//            cowList.remove(cowSale);
        }

        for (CowPrice a : cowList) {
            System.out.println(a.toString());
        }
        System.out.println(m + " " + k + " " + cowList.size());
        System.out.println();
        return Math.max(withCoupon, withoutCoupon);
    }
}

//8 4 25
//1 1
//6 1
//6 4
//6 5
//10 2
//10 5
//10 10
//1000 1

//10 4 11
//1 1
//5 1
//6 1
//6 4
//6 5
//10 2
//10 5
//10 10
//1000 1
//2 1

//6 3 9
//6 6
//1 1
//2 1
//3 1
//1000 1
//1000 4
