class Solution {
    public boolean checkDivisibility(int n) {
        int digit; 
        int temp = n;
        int sum = 0;
        int prod = 1;
        while(n>0){
            digit = n%10;
            sum = sum + digit;
            prod = prod*digit;
            n = n/10;
        }
        int res = sum+prod;
        if(temp%res == 0){
            return true;
        }
        else{
            return false;
        }
    }
}