function solution(money) {
    
    let answer = 0;
    const moneyLength = money.length;
    
    // 1. 집이 3개 일 경우 -> 한 집만 털기
    if(moneyLength === 3) {
        money.sort((m1, m2) => {
            return m2-m1;
        });  // 내림차순으로 정렬
        answer = money[0];   // 가장 큰 숫자 저장
    }
    
    // 2. 집이 4개 이상일 경우
    else {
        
        let dp1 = new Array(moneyLength).fill(0);   // 첫번째 집을 털고 마지막 집을 털지 않을 경우
        let dp2 = new Array(moneyLength).fill(0);   // 첫번째 집을 털지 않을 경우
        
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        for(let i = 2; i < moneyLength - 1; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        
        dp2[0] = 0;
        dp2[1] = money[1];
        for(let i = 2; i < moneyLength; i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        
        console.log(dp1);
        console.log(dp2);

        answer = Math.max(dp1[moneyLength-2], dp2[moneyLength-1]);
        
    }

    return answer;
}