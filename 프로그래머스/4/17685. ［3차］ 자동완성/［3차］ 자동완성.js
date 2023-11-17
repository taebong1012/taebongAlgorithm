function solution(words) {
    let answer = 0;
    const trie = new Trie();
    
    for(const word of words) {
        trie.insert(word);
    }
    
    for(const word of words) {
        answer += trie.getCnt(word);
    }
    
    return answer;
}

class Trie {
    constructor() {
        this.root = new Node();
    }
    
    insert(string) {
        let curNode = this.root;
        curNode.cnt++;
        
        for(const char of string) {
            
            // 해당 문자가 없으면 노드 새로 만들어주고 문자 넣어주기
            if(!curNode.children.has(char)) {
                curNode.children.set(char, new Node(curNode.value + char));
            }
            
            curNode = curNode.children.get(char);
            curNode.cnt++;
        }
    }
    
    getCnt(string) {
        let cnt = 0;
        let curNode = this.root;
        
        for(const char of string) {
            cnt++;
            
            curNode = curNode.children.get(char);
            if(curNode.cnt === 1) break;
        }
        
        return cnt;
    }
}

class Node {
    constructor(value) {
        this.value = value;
        this.children = new Map();
        this.cnt = 0;
    }
}