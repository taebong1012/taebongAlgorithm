function solution(k, room_number) {
    
    let answer = [];
    
    const rooms = new Map();    // key: 원하는 방번호, value: 배정될 방번호
    
    for(let i = 0; i < room_number.length; i++) {
        let emptyRoomNum = findEmptyRoom(room_number[i], rooms);
        answer.push(emptyRoomNum);
    }
    
    return answer;
}


function findEmptyRoom(num, rooms) {
    
    // 비어있는 방이라면
    if(!rooms.has(num)) {
        rooms.set(num, num+1);    // 해당 방을 탐색하면 다음 방을 배정해주기 위해서
        return num;
    }
    
    // 이미 배정된 방이라면
    let anotherRoom = findEmptyRoom(rooms.get(num), rooms);
    rooms.set(num, anotherRoom + 1);
    return anotherRoom;
    
}