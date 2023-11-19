function solution(A,B){
    let answer = 0;
    
    // 오름차순으로 정렬
    A = A.sort((a, b) => {
        return a - b;
    })
    
    // 내림차순으로 정렬
    B = B.sort((a, b) => {
        return b - a;
    })
    
    A.forEach((a, index) => {
        answer += a * B[index];
    })

    return answer;
}