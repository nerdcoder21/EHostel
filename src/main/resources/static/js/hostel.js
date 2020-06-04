function addRooms(){

    var total_rooms = document.getElementById("total_rooms").value;
    var temp = document.getElementById("occupied_rooms").value;
    var result = temp.substring(1, temp.length - 1);
    result = result.split(",");


    for(var room = 1, i = 0; room <= total_rooms; room++){
        var button = document.createElement("button");
        button.innerHTML = room.toString();

        if(result[i] == room){
            button.setAttribute("class", "btn btn-primary");
            i++
        }
        else{
            button.setAttribute("class", "btn btn-success");
        }
        document.getElementById("list-room").appendChild(button);
    }
}