function addRooms(){

    var total_rooms = document.getElementById("total_rooms").value;
    var room_capacity = document.getElementById("roomCapacity").value;
    var hostelId = document.getElementById("hostelId").value;
    var temp = document.getElementById("occupied_rooms").value;
    var result = temp.substring(1, temp.length - 1);
    result = result.split(",");

    room_capacity = parseInt(room_capacity);

    for(var room = 1, i = 0; room <= total_rooms; ){
        var button = document.createElement("button");
        button.innerHTML = room.toString();

        var redirect = "window.location.href = '/admin/home/" + hostelId + "/hostel/" + room.toString() + "/room'";

        button.setAttribute("onclick", redirect);

        if(i > 0 && parseInt(result[i]) == parseInt(result[i-1])) {
            i++;
            continue;
        }

        if(parseInt(result[i]) == room) {
            if (i + room_capacity - 1 < result.length && parseInt(result[i + room_capacity - 1]) == room) {
                button.setAttribute("class", "btn btn-primary");
            }
            else {
                button.setAttribute("class", "btn btn-warning");
            }
            i++;
        }
        else{
            button.setAttribute("class", "btn btn-success");
        }

        document.getElementById("list-room").appendChild(button);

        room++;
    }
}

function roomDetails(){
    window.location.href = '/home/1/hostel/';
}