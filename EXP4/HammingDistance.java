package EXP4;

public class HammingDistance {
    int MOD = (int) 1e9+7;
        int max = nums[0];
        for(int i: nums) {
            max = Math.max(max, i);
        }

        int ans = 0;
        for(int i=0; (1<<i)<=max; i++) {
            int setCount = 0, unsetCount = 0;
            for(int j: nums) {
                if((j & (1<<i)) != 0) {
                    setCount++;
                } else {
                    unsetCount++;
                }
            }

            ans = (ans + (setCount * unsetCount) % MOD) % MOD;
        }

        return ans;
    }
}
