let max;
let ans = 0;

function solution(n) {
    
    max = n;    // 최대로 증가할 수 있는 값
    arr = new Array(2*(n-1));
    
    find(1, 0, 2*(n-1));
    
    return ans;
}

function find(sum, index, endIndex) {
    
    if((sum < 0) || sum > max) {
        return;
    }
    
    if(index === endIndex) {
        if(sum === 1) {
            ans++;
        }
        return;
    }
    
    find(sum+1, index+1, endIndex);
    find(sum-1, index+1, endIndex);
    
}