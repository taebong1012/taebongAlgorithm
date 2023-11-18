function solution(s) {
    let words = s.split(" ");
    
    let words2 = words.map((word) => {
        return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
    })
    
    const answer = words2.join(" ");
    
    return answer;
}