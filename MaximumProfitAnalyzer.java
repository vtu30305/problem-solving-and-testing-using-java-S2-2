import java.util.*;

public class MaximumProfitAnalyzer {

    public static void main(String[] args) {

        // Daily profit/loss values
        int[] profit = {-2, 3, -1, 5, -6, 4, 2, -1};

        int maxProfit = profit[0];
        int currentProfit = profit[0];

        for (int i = 1; i < profit.length; i++) {
            currentProfit = Math.max(profit[i],
                                    currentProfit + profit[i]);

            maxProfit = Math.max(maxProfit, currentProfit);
        }

        System.out.println("Daily Profits: " + Arrays.toString(profit));
        System.out.println("Maximum Profit: " + maxProfit);
    }
}
