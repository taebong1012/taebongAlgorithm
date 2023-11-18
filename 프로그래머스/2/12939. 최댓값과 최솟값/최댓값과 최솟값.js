function solution(s) {
    let answer = '';
    let min = Infinity;
    let max = -Infinity;
    
    const numArr = s.split(" ");
    
    numArr.forEach((num) => {
        const numInt = parseInt(num);
        min = Math.min(min, numInt);
        max = Math.max(max, numInt);
    })
    
    answer = min + " " + max;
    
    return answer;
}