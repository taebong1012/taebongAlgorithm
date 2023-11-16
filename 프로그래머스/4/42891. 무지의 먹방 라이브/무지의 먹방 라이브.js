function solution(food_times, k) {
    let answer = 0;
    let foodLength = food_times.length;
    
    // time의 오름차순으로 정렬
    let foods = food_times.map((time, index) => {
        return [time, index+1];
    }).sort((f1, f2) => {
        return f1[0] - f2[0];
    })
    
    let prev = 0;
    for(let i = 0; i < foodLength; i++) {
        
        let curTime = foods[i][0];  // 이 음식을 먹는데 걸리는 시간
        let remain = foodLength - i;    // 다 먹은 음식을 제외한 음식의 개수
        let usedTime = (curTime - prev) * remain;   // 이 사이클이 도는 동안 사용한 시간
        prev = curTime;
        
        // 한 사이클을 다 돌 수 없다면
        if(k < usedTime) {
            // 이미 다 먹어버린 i번째까지는 제외하고 인덱스 순으로 재정렬
            foods.splice(0, i);
            foods.sort((f1, f2) => {
                return f1[1] - f2[1];
            })
            return foods[k % remain][1];
        }
        
        // 먹느라 사용한 시간 빼주어서 갱신
        k -= usedTime;
        
    }
    
    return -1;
}